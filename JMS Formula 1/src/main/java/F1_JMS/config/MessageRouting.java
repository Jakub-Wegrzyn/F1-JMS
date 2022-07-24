package F1_JMS.config;


import F1_JMS.model.BolidLimits;
import F1_JMS.model.F1Message;
import F1_JMS.receiver.InformDriver;
import F1_JMS.receiver.InformMechanic;
import org.json.simple.JSONArray;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageHeaders;
import org.springframework.messaging.handler.annotation.Headers;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

@Component
public class MessageRouting {
    JSONArray jsonArray = new JSONArray();

    @JmsListener(destination = JmsConfig.TOPIC_F1_BOLID, containerFactory = "topicConnectionFactory")
    public void receiveMessages(@Payload F1Message convertedMessage, @Headers MessageHeaders messageHeaders, Message message) {
        InformDriver driver = new InformDriver();
        InformMechanic mechanic = new InformMechanic();
        BolidLimits bolidLimits = new BolidLimits();

        if ((convertedMessage.getEngine_temp() > bolidLimits.getDangerous_engine_temp_min() && convertedMessage.getEngine_temp() < bolidLimits.getDangerous_engine_temp_max()) &&
                (convertedMessage.getTire_temp() > bolidLimits.getDangerous_tire_temp_min() && convertedMessage.getTire_temp() < bolidLimits.getDangerous_tire_temp_max()) &&
                (convertedMessage.getTire_pressure() > bolidLimits.getDangerous_tire_pressure_min() && convertedMessage.getTire_pressure() < bolidLimits.getDangerous_tire_pressure_max()) &&
                (convertedMessage.getOil_pressure() > bolidLimits.getDangerous_oil_pressure_min() && convertedMessage.getOil_pressure() < bolidLimits.getDangerous_oil_pressure_max()) &&
                (convertedMessage.getLast_max_g_force() < bolidLimits.getDangerous_last_g_force_max())) {
        } else if ((convertedMessage.getEngine_temp() < bolidLimits.getCritical_engine_temp_min() || convertedMessage.getEngine_temp() > bolidLimits.getCritical_engine_temp_max() ||
                (convertedMessage.getTire_temp() < bolidLimits.getCritical_tire_temp_min() || convertedMessage.getTire_temp() > bolidLimits.getCritical_tire_temp_max()) ||
                (convertedMessage.getTire_pressure() < bolidLimits.getCritical_tire_pressure_min() || convertedMessage.getTire_pressure() > bolidLimits.getCritical_tire_pressure_max()) ||
                (convertedMessage.getOil_pressure() < bolidLimits.getCritical_oil_pressure_min() || convertedMessage.getOil_pressure() > bolidLimits.getCritical_oil_pressure_max()))) {
            mechanic.receiveMechanicMessage(convertedMessage, messageHeaders, message);
            driver.receiveDriverMessage(convertedMessage, messageHeaders, message, true);

        } else {
            driver.receiveDriverMessage(convertedMessage, messageHeaders, message, false);
        }
    }
}

package F1_JMS.receiver;

import F1_JMS.config.WhoToCall;
import F1_JMS.config.JmsConfig;
import F1_JMS.model.F1Message;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageHeaders;
import org.springframework.messaging.handler.annotation.Headers;
import org.springframework.messaging.handler.annotation.Payload;

public class InformDriver {

    @JmsListener(destination = JmsConfig.TOPIC_F1_BOLID, containerFactory = "topicConnectionFactory")
    public void receiveDriverMessage(@Payload F1Message convertedMessage, @Headers MessageHeaders messageHeaders, Message message, boolean logical_value) {
        WhoToCall whoToCall = new WhoToCall();
        if (logical_value == true) {
            System.out.println("Max Verstappen! Big fault in your car. Box Box, Box Box! ");
            whoToCall.Info_to_Pit_stop_guys_and_driver(convertedMessage);
            whoToCall.problem_driver_and_pitstop_message();

        } else {
            whoToCall.Info_to_driver(convertedMessage);
            System.out.println("Max! Watch out mate!");
            whoToCall.problem_driver_message();
        }
    }
}

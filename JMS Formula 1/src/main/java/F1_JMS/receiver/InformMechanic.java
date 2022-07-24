package F1_JMS.receiver;

import F1_JMS.config.WhoToCall;
import F1_JMS.config.JmsConfig;
import F1_JMS.model.F1Message;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageHeaders;
import org.springframework.messaging.handler.annotation.Headers;
import org.springframework.messaging.handler.annotation.Payload;

public class InformMechanic {

    @JmsListener(destination = JmsConfig.TOPIC_F1_BOLID, containerFactory = "topicConnectionFactory")
    public void receiveMechanicMessage(@Payload F1Message convertedMessage, @Headers MessageHeaders messageHeaders, Message message) {

        WhoToCall whoToCall = new WhoToCall();
        System.out.println("Hey Pit Stop Guys! Get ready! We have the following problems: ");
        whoToCall.Info_to_Pit_stop_guys_and_driver(convertedMessage);
        whoToCall.problem_driver_and_pitstop_message();
    }
}

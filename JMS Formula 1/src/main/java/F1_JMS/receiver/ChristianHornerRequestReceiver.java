package F1_JMS.receiver;

import F1_JMS.config.JmsConfig;
import F1_JMS.model.F1Message;
import lombok.RequiredArgsConstructor;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.messaging.MessageHeaders;
import org.springframework.messaging.handler.annotation.Headers;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.Message;

@Component
@RequiredArgsConstructor
public class ChristianHornerRequestReceiver{
    private final JmsTemplate jmsTemplate;
    @JmsListener(destination = JmsConfig.QUEUE_SEND_AND_RECEIVE)
    public void receiveAndRespond(@Payload F1Message convertedMessage,
                                  @Headers MessageHeaders headers,
                                  Message message) throws JMSException {
        System.out.print("\n");
        System.out.println("Message from Max Verstappen: " + convertedMessage.getMessage());
        Destination replyTo = message.getJMSReplyTo();
        F1Message msg = F1Message.builder()
                .message("Yes Max, go Ahead! Keep pushing!")
                .build();
        jmsTemplate.convertAndSend(replyTo, msg);
    }
}

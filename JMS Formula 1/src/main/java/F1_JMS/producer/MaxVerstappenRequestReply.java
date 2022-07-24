package F1_JMS.producer;

import F1_JMS.config.JmsConfig;
import F1_JMS.model.F1Message;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;
import javax.jms.TextMessage;

@Component
@RequiredArgsConstructor
public class MaxVerstappenRequestReply {
    private final JmsTemplate jmsTemplate;
    private final ObjectMapper objectMapper;
    @Async
    @Scheduled(initialDelay = 10000,fixedRate = 50000)
    public void sendAndReceive() throws JMSException, JsonProcessingException {
        F1Message message = F1Message.builder()
                .message("Am I doing well?")
                .build();;
        TextMessage responseMessage = (TextMessage)  jmsTemplate.sendAndReceive(

                JmsConfig.QUEUE_SEND_AND_RECEIVE, new MessageCreator() {
                    @Override
                    public Message createMessage(Session session) throws JMSException {
                        TextMessage plainMessage = session.createTextMessage();
                        try{
                            plainMessage.setText(objectMapper.writeValueAsString(message));
                            plainMessage.setStringProperty("_type",
                                    F1Message.class.getName());
                            return plainMessage;
                        }catch(JsonProcessingException e){
                            throw new JMSException("conversaion to json failed" +
                                    e.getMessage());
                        }
                    }
                });
        String responseText = responseMessage.getText();
        F1Message responseConverted = objectMapper.readValue(responseText,
                F1Message.class);
        System.out.println("Message from Christian Horner: " + responseConverted.getMessage());
    }
}


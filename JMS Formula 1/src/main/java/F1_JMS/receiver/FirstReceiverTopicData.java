package F1_JMS.receiver;


import F1_JMS.config.JmsConfig;
import F1_JMS.model.F1Message;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageHeaders;
import org.springframework.messaging.handler.annotation.Headers;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import java.io.FileWriter;
import java.io.IOException;
import java.time.format.DateTimeFormatter;

@Component
public class FirstReceiverTopicData {
    JSONArray jsonArray = new JSONArray();

    @JmsListener(destination = JmsConfig.TOPIC_F1_BOLID, containerFactory = "topicConnectionFactory")
    public void receiveHelloMessage(@Payload F1Message convertedMessage, @Headers MessageHeaders messageHeaders, Message message) throws IOException {
        JSONObject jsonObject = new JSONObject();

        DateTimeFormatter format = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
        String formatDateTime = convertedMessage.getActual_time().format(format);
        jsonObject.put("actual_time", formatDateTime);
        jsonObject.put("engine_temp", convertedMessage.getEngine_temp());
        jsonObject.put("tire_temp", convertedMessage.getTire_temp());
        jsonObject.put("tire_pressure", convertedMessage.getTire_pressure());
        jsonObject.put("oil_pressure", convertedMessage.getOil_pressure());
        jsonObject.put("last_max_g_force", convertedMessage.getLast_max_g_force());
        jsonArray.add(jsonObject);

        FileWriter file = new FileWriter("json\\f1_data_write.json");
        file.write(jsonArray.toJSONString());
        file.close();

    }
}

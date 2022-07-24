package F1_JMS.producer;

import F1_JMS.JsonFileToArray;
import F1_JMS.config.JmsConfig;
import F1_JMS.model.F1Message;
import lombok.RequiredArgsConstructor;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;


@Component
@RequiredArgsConstructor
public class F1TopicProducer {
    private final JmsTemplate jmsTemplate;
    JsonFileToArray x = new JsonFileToArray();
    private int i = 0;

    @Async
    @Scheduled(fixedRate = 5000)
    public void basicdata() {
        try {
            F1Message message = F1Message.builder()
                    .actual_time(LocalDateTime.now())
                    .engine_temp(x.get_engine_temp(i))
                    .tire_temp(x.get_tire_temp(i))
                    .tire_pressure(x.get_tire_pressure(i))
                    .oil_pressure(x.get_oil_pressure(i))
                    .last_max_g_force(x.get_last_max_g_force(i))
                    .build();
            jmsTemplate.convertAndSend(JmsConfig.TOPIC_F1_BOLID, message);
            System.out.print("\n");
            System.out.println("F1TopicProducer.bolid_data_producer - sent message:" + message);
            i++;

        } catch (IndexOutOfBoundsException e) {
            System.out.print("\n");
            System.out.print("\n");
            System.out.println("No more data. Engine was shut down. I shutting down also this program");
            System.exit(0);
        }

    }
}

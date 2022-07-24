package F1_JMS;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.File;



@SpringBootApplication
public class MainApplication {

    public static void main(String[] args) {
        File f1_data_write = new File("json\\f1_data_write.json");
        f1_data_write.delete();
        SpringApplication.run(MainApplication.class, args);
    }
}

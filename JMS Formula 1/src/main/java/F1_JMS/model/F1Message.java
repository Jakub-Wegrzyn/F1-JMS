package F1_JMS.model;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import lombok.*;
import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class F1Message {
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    private LocalDateTime actual_time;


    private double engine_temp;
    private double tire_temp;
    private double tire_pressure;
    private double oil_pressure;
    private double last_max_g_force;
    private String message;


    public String get_actual_time_name(){
        return "actual_time";
    }

    public String get_engine_temp_name(){
        return "engine_temp";
    }
    public String get_tire_temp_name(){
        return "tire_temp";
    }
    public String get_tire_pressure_name(){
        return "tire_pressure";
    }
    public String get_oil_pressure_name(){
        return "oil_pressure";
    }
    public String get_last_max_g_force_name(){
        return "last_max_g_force";
    }


}

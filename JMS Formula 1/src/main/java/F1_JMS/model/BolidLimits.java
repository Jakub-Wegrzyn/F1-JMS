package F1_JMS.model;

import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class BolidLimits {
    private double dangerous_engine_temp_min = 2800;
    private double dangerous_engine_temp_max = 3000;
    private double dangerous_tire_temp_min = 90;
    private double dangerous_tire_temp_max = 115;
    private double dangerous_tire_pressure_min = 0.8;
    private double dangerous_tire_pressure_max = 1.3;
    private double dangerous_oil_pressure_min = 70;
    private double dangerous_oil_pressure_max = 90;
    private double dangerous_last_g_force_max = 2.2;

    private double critical_engine_temp_min = 2600;
    private double critical_engine_temp_max = 3200;
    private double critical_tire_temp_min = 80;
    private double critical_tire_temp_max = 130;
    private double critical_tire_pressure_min = 0.5;
    private double critical_tire_pressure_max = 1.8;
    private double critical_oil_pressure_min = 60;
    private double critical_oil_pressure_max = 120;
    private double critical_last_g_force_max = 4;


}

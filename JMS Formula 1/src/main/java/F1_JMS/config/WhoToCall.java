package F1_JMS.config;

import F1_JMS.model.BolidLimits;
import F1_JMS.model.F1Message;

import java.util.HashMap;
import java.util.Map;

public class WhoToCall {
    public HashMap<String, Object> map_inform_to_driver_only = new HashMap<String, Object>();
    public HashMap<String, Object> map_inform_to_pitstop_and_driver = new HashMap<String, Object>();

    public void Info_to_driver(F1Message convertedMessage) {
        BolidLimits bolidLimits = new BolidLimits();
        if (convertedMessage.getEngine_temp() > bolidLimits.getCritical_engine_temp_min() && convertedMessage.getEngine_temp() < bolidLimits.getDangerous_engine_temp_min() ||
                (convertedMessage.getEngine_temp() > bolidLimits.getDangerous_engine_temp_max() && convertedMessage.getEngine_temp() < bolidLimits.getCritical_engine_temp_max())) {
            map_inform_to_driver_only.put(convertedMessage.get_engine_temp_name(), convertedMessage.getEngine_temp());
        }
        if ((convertedMessage.getTire_temp() > bolidLimits.getCritical_tire_temp_min() && convertedMessage.getTire_temp() < bolidLimits.getDangerous_tire_temp_min()) ||
                (convertedMessage.getTire_temp() > bolidLimits.getDangerous_tire_temp_max() && convertedMessage.getTire_temp() < bolidLimits.getCritical_tire_temp_max())) {
            map_inform_to_driver_only.put(convertedMessage.get_tire_temp_name(), convertedMessage.getTire_temp());
        }
        if ((convertedMessage.getTire_pressure() > bolidLimits.getCritical_tire_pressure_min() && convertedMessage.getTire_pressure() < bolidLimits.getDangerous_tire_pressure_min()) ||
                (convertedMessage.getTire_pressure() > bolidLimits.getDangerous_tire_pressure_max() && convertedMessage.getTire_pressure() < bolidLimits.getCritical_tire_pressure_max())) {
            map_inform_to_driver_only.put(convertedMessage.get_tire_pressure_name(), convertedMessage.getTire_pressure());
        }
        if ((convertedMessage.getOil_pressure() > bolidLimits.getCritical_oil_pressure_min() && convertedMessage.getOil_pressure() < bolidLimits.getDangerous_oil_pressure_min()) ||
                (convertedMessage.getOil_pressure() > bolidLimits.getDangerous_oil_pressure_max() && convertedMessage.getOil_pressure() < bolidLimits.getCritical_oil_pressure_max())) {
            map_inform_to_driver_only.put(convertedMessage.get_oil_pressure_name(), convertedMessage.getOil_pressure());
        }
        if (convertedMessage.getLast_max_g_force() > bolidLimits.getDangerous_last_g_force_max()) {
            map_inform_to_driver_only.put(convertedMessage.get_last_max_g_force_name(), convertedMessage.getLast_max_g_force());
        }
    }

    public void Info_to_Pit_stop_guys_and_driver(F1Message convertedMessage) {
        BolidLimits bolidLimits = new BolidLimits();
        if (convertedMessage.getEngine_temp() < bolidLimits.getCritical_engine_temp_min() || convertedMessage.getEngine_temp() > bolidLimits.getCritical_engine_temp_max()) {
            map_inform_to_pitstop_and_driver.put(convertedMessage.get_engine_temp_name(), convertedMessage.getEngine_temp());
        }
        if (convertedMessage.getTire_temp() < bolidLimits.getCritical_tire_temp_min() || convertedMessage.getTire_temp() > bolidLimits.getCritical_tire_temp_max()) {
            map_inform_to_pitstop_and_driver.put(convertedMessage.get_tire_temp_name(), convertedMessage.getTire_temp());
        }
        if (convertedMessage.getTire_pressure() < bolidLimits.getCritical_tire_pressure_min() || convertedMessage.getTire_pressure() > bolidLimits.getCritical_tire_pressure_max()) {
            map_inform_to_pitstop_and_driver.put(convertedMessage.get_tire_pressure_name(), convertedMessage.getTire_pressure());
        }
        if (convertedMessage.getOil_pressure() < bolidLimits.getCritical_oil_pressure_min() || convertedMessage.getOil_pressure() > bolidLimits.getCritical_oil_pressure_max()) {
            map_inform_to_pitstop_and_driver.put(convertedMessage.get_oil_pressure_name(), convertedMessage.getOil_pressure());
        }
        if (convertedMessage.getLast_max_g_force() > bolidLimits.getCritical_last_g_force_max()) {
            map_inform_to_pitstop_and_driver.put(convertedMessage.get_last_max_g_force_name(), convertedMessage.getLast_max_g_force());
        }
    }

    public void problem_driver_message() {
        for (Map.Entry<String, Object> entry : map_inform_to_driver_only.entrySet()) {
            System.out.println(entry.getKey() + " " + entry.getValue());
        }
    }

    public void problem_driver_and_pitstop_message() {
        for (Map.Entry<String, Object> entry : map_inform_to_pitstop_and_driver.entrySet()) {
            System.out.println(entry.getKey() + " " + entry.getValue());
        }
    }
}

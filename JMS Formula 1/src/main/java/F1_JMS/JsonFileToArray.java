package F1_JMS;

import com.google.gson.Gson;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;


public class JsonFileToArray {

    public static ArrayList<Object> list = new ArrayList<Object>();


    public JsonFileToArray() {
        main();
    }

    public static void main() {

        JSONParser jsonP = new JSONParser();
        try (
            FileReader reader = new FileReader("json\\f1_data_read.json")) {
            Object obj = jsonP.parse(reader);
            JSONArray boliddata = (JSONArray) obj;
            for (int i = 0; i < boliddata.size(); i++) {
                JSONObject bolid = (JSONObject) boliddata.get(i);
                HashMap<String, Object> bolidmap = new Gson().fromJson(bolid.toString(), HashMap.class);
                list.add(bolidmap);
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    public double get_engine_temp(int i) {
        HashMap<String, Object> x = (HashMap<String, Object>) list.get(i);
        double engine_temp = (double) x.get("engine_temp");
        return engine_temp;
    }

    public double get_oil_pressure(int i) {
        HashMap<String, Object> x = (HashMap<String, Object>) list.get(i);
        double oil_pressure = (double) x.get("oil_pressure");
        return oil_pressure;
    }

    public double get_tire_temp(int i) {
        HashMap<String, Object> x = (HashMap<String, Object>) list.get(i);
        double tire_temp = (double) x.get("tire_temp");
        return tire_temp;
    }

    public double get_tire_pressure(int i) {
        HashMap<String, Object> x = (HashMap<String, Object>) list.get(i);
        double tire_pressure = (double) x.get("tire_pressure");
        return tire_pressure;
    }

    public double get_last_max_g_force(int i) {
        HashMap<String, Object> x = (HashMap<String, Object>) list.get(i);
        double last_max_g_force = (double) x.get("last_max_g_force");
        return last_max_g_force;
    }


}

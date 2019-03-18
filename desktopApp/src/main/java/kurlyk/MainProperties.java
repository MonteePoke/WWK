package kurlyk;

import java.util.HashMap;

public class MainProperties {

    private static MainProperties instance = new MainProperties();
    public static MainProperties getInstance(){
        return instance;
    }

    private static HashMap<String, String> properties;

    private MainProperties(){
        properties = new HashMap<>();
        properties.put("addr", "127.0.0.1:8080");
    }

    public String getProperty(String name){
        return properties.get(name);
    }
}

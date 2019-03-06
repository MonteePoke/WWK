package kurlyk.view;

import java.util.HashMap;

public class Properties {

    private static Properties instance = new Properties();
    public static Properties getInstance(){
        return instance;
    }

    private static HashMap<String, String> properties;

    private Properties(){
        properties = new HashMap<>();
        properties.put("mainTitle", "Адриатический свин");
    }

    public String getProperty(String name){
        return properties.get(name);
    }
}

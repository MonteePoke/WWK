package kurlyk.view.common;

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
        properties.put("delete", "Удалить");
        properties.put("showCharacteristics", "Показать характеристики");
        properties.put("connect", "Соединение");
    }

    public String getProperty(String name){
        return properties.get(name);
    }
}

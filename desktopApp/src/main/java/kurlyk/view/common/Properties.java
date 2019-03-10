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
        //Названия окон
        properties.put("mainTitle", "Адриатический свин");

        //Названия элементов меню
        properties.put("delete", "Удалить");
        properties.put("showCharacteristics", "Показать характеристики");
        properties.put("connect", "Соединение");

        //Названия различных терминов
        properties.put("availabilityFactorProperty", "Коэффициент готовности");
    }

    public String getProperty(String name){
        return properties.get(name);
    }
}

package kurlyk.view.common;

import java.util.HashMap;

public class ViewProperties {

    private static ViewProperties instance = new ViewProperties();
    public static ViewProperties getInstance(){
        return instance;
    }

    private static HashMap<String, String> properties;

    private ViewProperties(){
        properties = new HashMap<>();
        //Названия окон
        properties.put("mainTitle", "ВВК");

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

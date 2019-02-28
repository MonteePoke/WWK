package kurlyk.common;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class PropertyReader {

    private static PropertyReader instance = new PropertyReader();
    public static PropertyReader getInstance(){
        return instance;
    }
    private PropertyReader(){
    }

    public String getProperty(String name){
        Properties properties = new Properties();
        try {
            properties.load(getClass().getResource("/config.properties").openStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return properties.getProperty(name);
    }
}

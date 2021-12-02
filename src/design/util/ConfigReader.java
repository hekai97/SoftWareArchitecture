package design.util;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Properties;

/**
 * @author: hekai
 * @Date: 2021/11/30
 */
public class ConfigReader {
    private static Properties properties;
    private volatile static ConfigReader instance;
    private ConfigReader(){
        try {
            properties=new Properties();
            InputStreamReader inputStreamReader=new InputStreamReader(new FileInputStream("src/design/config.properties"));
            properties.load(inputStreamReader);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public synchronized static ConfigReader getInstance(){
        if(instance ==null){
            synchronized (ConfigReader.class){
                if(instance ==null){
                    instance =new ConfigReader();
                }
            }
        }
        return instance;
    }
    public String getKey(String key) {
        return properties.getProperty(key);
    }
}

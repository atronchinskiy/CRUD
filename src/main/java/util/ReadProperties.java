package util;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ReadProperties {
    private final Properties property;

    {
        property = new Properties();
        try (InputStream fis = new FileInputStream("c:\\ProjectJava\\CrudProject\\src\\main\\resources\\dao.properties ")){
            property.load(fis);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String getData(String key) {
        return property.getProperty(key);
    }
}

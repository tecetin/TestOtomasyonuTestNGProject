package utilities;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ConfigReader {
    static Properties properties = new Properties();

    static {
        try (FileInputStream fis = new FileInputStream("configuration.properties")) {
            properties.load(fis);
        } catch (IOException e) {
            throw new RuntimeException("Yapılandırma dosyası yüklenemedi", e);
        }
    }
    public static String getProperty(String key) {
        return properties.getProperty(key);
    }
}

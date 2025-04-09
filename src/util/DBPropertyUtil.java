package util;

import java.io.InputStream;
import java.util.Properties;

public class DBPropertyUtil {
    public static Properties getConnectionProperties(String propertyFileName) {
        Properties props = new Properties();
        try (InputStream input = DBPropertyUtil.class.getClassLoader().getResourceAsStream(propertyFileName)) {
            if (input == null) {
                throw new RuntimeException("Property file not found: " + propertyFileName);
            }
            props.load(input);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return props;
    }
}
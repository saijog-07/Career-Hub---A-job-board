package util;

import java.io.InputStream;
import java.util.Properties;

public class DBPropertyUtil {

    public static String getConnectionString(String propertyFileName) {
        try (InputStream input = DBPropertyUtil.class.getClassLoader().getResourceAsStream(propertyFileName)) {
            if (input == null) {
                throw new RuntimeException("Property file not found: " + propertyFileName);
            }

            Properties props = new Properties();
            props.load(input);

            String url = props.getProperty("url");
            String username = props.getProperty("username");
            String password = props.getProperty("password");

            // Return full connection string
            return url + "?user=" + username + "&password=" + password;
        } catch (Exception e) {
            throw new RuntimeException("Failed to load connection string from properties: " + e.getMessage());
        }
    }
}

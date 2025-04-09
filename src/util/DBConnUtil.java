package util;

import exception.DatabaseConnectionException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;

public class DBConnUtil {

    private static final String PROPERTIES_FILE = "db.properties";

    public static Connection getConnection() throws DatabaseConnectionException {
        Connection conn = null;
        try {
            Properties props = DBPropertyUtil.getConnectionProperties(PROPERTIES_FILE);
            String url = props.getProperty("url");
            String username = props.getProperty("username");
            String password = props.getProperty("password");
            String driver = props.getProperty("driver");

            Class.forName(driver);
            conn = DriverManager.getConnection(url, username, password);
        } catch (Exception e) {
            throw new DatabaseConnectionException("Failed to connect to database: " + e.getMessage());
        }
        return conn;
    }
}
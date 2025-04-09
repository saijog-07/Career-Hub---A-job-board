package util;

import exception.DatabaseConnectionException;
import java.sql.Connection;
import java.sql.DriverManager;

public class DBConnUtil {

    private static final String PROPERTIES_FILE = "db.properties";

    public static Connection getConnection() throws DatabaseConnectionException {
        try {
            String connectionString = DBPropertyUtil.getConnectionString(PROPERTIES_FILE);

            // DriverManager can auto-load the driver if JDBC 4.0+
            return DriverManager.getConnection(connectionString);

        } catch (Exception e) {
            throw new DatabaseConnectionException("Failed to connect to database: " + e.getMessage());
        }
    }
}

package cw1.bdconnect;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class BdConnection {
    private static final String URL = "jdbc:postgresql://localhost:5432/cw1";
    private static final String USER = "postgres";
    private static final String PASSWORD = "qwerty007";

    static {
        try {
            Class.forName("org.postgresql.Driver");
        } catch(ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }

    private BdConnection() {
    }
}

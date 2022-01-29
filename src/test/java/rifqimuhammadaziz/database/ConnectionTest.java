package rifqimuhammadaziz.database;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionTest {

    // register driver before test connection
    @BeforeAll
    static void beforeAll() {
        try {
            Driver mysqlDriver = new com.mysql.cj.jdbc.Driver();
            DriverManager.registerDriver(mysqlDriver);
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
    }

    // test connect to database
    @Test
    void testConnection() {
        String jdbcUrl = "jdbc:mysql://localhost:3306/java_database";
        String username = "root";
        String password = "root";

        try {
            Connection connection = DriverManager.getConnection(jdbcUrl, username, password);
            System.out.println("Success connect to database.");
        } catch (SQLException exception) {
            Assertions.fail(exception);
        }
    }

    // close connection (if not closed, connection to database always opened)
    @Test
    void testConnectionClose() {
        String jdbcUrl = "jdbc:mysql://localhost:3306/java_database";
        String username = "root";
        String password = "root";

        // auto close connection (try with resource)
        try(Connection connection = DriverManager.getConnection(jdbcUrl, username, password)) {
            System.out.println("Success connect to database.");

        } catch (SQLException exception) {
            Assertions.fail(exception);
        }
    }
}

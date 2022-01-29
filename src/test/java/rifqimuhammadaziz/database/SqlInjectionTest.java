package rifqimuhammadaziz.database;

import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class SqlInjectionTest {

    /**
     * Statement is not used for input from user (can cause SQL Injection)
     * Use PreparedStatement for user input
     */

    @Test
    void testSqlInjection() throws SQLException {
        Connection connection = ConnectionUtil.getDataSource().getConnection();
        Statement statement = connection.createStatement();

        String username = "rifqi'; #"; // sql injection (the next code after # is comments)
        String password = "wrongpassword";

        // text block (""")
        String sql = "SELECT * FROM admin WHERE username = '" + username +
                "' AND PASSWORD = '" + password + "'";
        System.out.println(sql);
        ResultSet resultSet = statement.executeQuery(sql); // send query to get result

        if (resultSet.next()) {
            // login success
            System.out.println("Login success : " + resultSet.getString("username"));
        } else {
            // failed login
            System.out.println("Failed to login!");
        }

        resultSet.close();
        statement.close();
        connection.close();
    }
}

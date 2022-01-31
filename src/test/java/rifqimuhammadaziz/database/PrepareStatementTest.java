package rifqimuhammadaziz.database;

import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class PrepareStatementTest {

    /**
     *  Use Prepare Statement (for user input)
     * To avoid SQL injection
     */

    @Test
    void testPrepareStatement() throws SQLException {
        Connection connection = ConnectionUtil.getDataSource().getConnection();
        String username = "xenosty'; #";
        String password = "xenosty";
        String sql = "SELECT * FROM admin WHERE username = ? AND password = ?";

        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setString(1, username); // sql ? first
        preparedStatement.setString(2, password); // sql ? second

        ResultSet resultSet = preparedStatement.executeQuery();
        if (resultSet.next()) {
            System.out.println("Login Success : " + resultSet.getString("username"));
        } else {
            System.out.println("Failed Login!");
        }

        preparedStatement.close();
        connection.close();
    }
}

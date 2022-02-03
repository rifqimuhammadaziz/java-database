package rifqimuhammadaziz.database;

import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class TransactionTest {

    @Test
    void testCommit() throws SQLException {
        Connection connection = ConnectionUtil.getDataSource().getConnection();
        connection.setAutoCommit(false); // must be committed to insert data

        String sql = "INSERT INTO comments(email, comment) VALUES (?, ?)";

        for (int i = 0; i < 100; i++) {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, "xenosty@test.com");
            preparedStatement.setString(2, "Test Comment Xenosty");
            preparedStatement.executeUpdate();
            preparedStatement.close();
        }

        connection.commit(); // submit or insert to table
        connection.close();
    }

    @Test
    void testRollback() throws SQLException {
        Connection connection = ConnectionUtil.getDataSource().getConnection();
        connection.setAutoCommit(false); // must be committed to insert data

        String sql = "INSERT INTO comments(email, comment) VALUES (?, ?)";

        // insert data
        for (int i = 0; i < 100; i++) {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, "xenosty@test.com");
            preparedStatement.setString(2, "Test Comment Xenosty");
            preparedStatement.executeUpdate();
            preparedStatement.close();
        }

        connection.rollback(); // cancel insert data
        connection.close();
    }
}

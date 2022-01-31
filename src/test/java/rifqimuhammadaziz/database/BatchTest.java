package rifqimuhammadaziz.database;

import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

public class BatchTest {

    /**
     * Batch
     * Save data in memory before send to database
     * Carefully to use batch because this function using memory to save temporary data
     * May run out of memory
     */

    @Test
    void testStatement() throws SQLException {
        Connection connection = ConnectionUtil.getDataSource().getConnection();
        Statement statement = connection.createStatement();

        // static query
        String sql = "INSERT INTO comments(email, comment) VALUES ('rifqi@test.com', 'Test Comment Rifqi')";

        for (int i = 0; i < 1000; i++) {
            // statement.executeUpdate(sql); // add one by one
            statement.addBatch(sql);
        }
        // send massive data to database
        statement.executeBatch();

        statement.close();
        connection.close();
    }

    @Test
    void testPreparedStatement() throws SQLException {
        // use prepared statement to avoid SQL Injection
        Connection connection = ConnectionUtil.getDataSource().getConnection();
        String sql = "INSERT INTO comments(email, comment) VALUES (?, ?)";

        PreparedStatement preparedStatement = connection.prepareStatement(sql);

        for (int i = 0; i < 1000; i++) {
            preparedStatement.clearParameters(); // clear parameter and re set
            preparedStatement.setString(1, "xenosty@test.com");
            preparedStatement.setString(2, "Test Comment Xenosty");
            preparedStatement.addBatch(); // add to batch
        }

        // send massive data to database
        preparedStatement.executeBatch();

        preparedStatement.close();
        connection.close();
    }
}



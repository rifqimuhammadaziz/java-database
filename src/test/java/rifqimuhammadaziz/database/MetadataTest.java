package rifqimuhammadaziz.database;

import org.junit.jupiter.api.Test;

import java.sql.*;

public class MetadataTest {

    @Test
    void testMetadataDatabase() throws SQLException {
        Connection connection = ConnectionUtil.getDataSource().getConnection();
        DatabaseMetaData databaseMetaData = connection.getMetaData();

        System.out.println(databaseMetaData.getDatabaseProductName()); // database name
        System.out.println(databaseMetaData.getDatabaseProductVersion()); // database version

        // get all tables of database
        ResultSet resultSet = databaseMetaData.getTables("java_database", null, null, null);
        while (resultSet.next()) {
            String tableName = resultSet.getString("TABLE_NAME");
            System.out.println("Table : " + tableName);
        }

        connection.close();
    }

    @Test
    void testParameterMetadata() throws SQLException {
        Connection connection = ConnectionUtil.getDataSource().getConnection();
        String sql = "INSERT INTO comments(email, comment) VALUES (?, ?)"; // 2 parameter
        PreparedStatement preparedStatement = connection.prepareStatement(sql);

        ParameterMetaData parameterMetaData = preparedStatement.getParameterMetaData();
        System.out.println(parameterMetaData.getParameterCount());
        // System.out.println(parameterMetaData.getParameterTypeName(1)); // MySQL is not supported
        // System.out.println(parameterMetaData.getParameterTypeName(2)); // MySQL is not supported

        preparedStatement.close();
        connection.close();
    }

    @Test
    void testResultSetMetadata() throws SQLException{
        Connection connection = ConnectionUtil.getDataSource().getConnection();
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("SELECT * FROM customers");

        ResultSetMetaData resultSetMetaData = resultSet.getMetaData();
        System.out.println(resultSetMetaData.getColumnCount());

        // describe table
        for (int i = 1; i <= resultSetMetaData.getColumnCount(); i++) {
            System.out.println("Name : " + resultSetMetaData.getColumnName(i)); // java.sql.Types.VARCHAR
            System.out.println("Type : " + resultSetMetaData.getColumnType(i)); // java.sql.Types.VARCHAR
            System.out.println("Type Name : " + resultSetMetaData.getColumnTypeName(i)); // java.sql.Types.VARCHAR

            if (resultSetMetaData.getColumnType(i) == Types.INTEGER) {
                System.out.println("*This is INTEGER Field");
            } else if (resultSetMetaData.getColumnType(i) == Types.VARCHAR) {
                System.out.println("*This is VARCHAR Field");
            }
        }

        statement.close();
        connection.close();
    }
}

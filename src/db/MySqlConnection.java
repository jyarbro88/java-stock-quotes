package db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MySqlConnection {

    public Connection connectToDatabase() throws SQLException {
        final String USERNAME = "joe";
        final String PASSWORD = "password";
        final String CONN_STRING = "jdbc:mysql://localhost/stock_info?useLegacyDatetimeCode=false&serverTimezone=UTC";

        Connection connection = null;

        try {
            connection = DriverManager.getConnection(CONN_STRING, USERNAME, PASSWORD);
            System.out.println("connected");
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
//            if (connection != null) {
//                connection.close();
//            }
        }
        return connection;
    }
}

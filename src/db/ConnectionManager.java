package db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionManager {

    private static ConnectionManager instance = null;

    private static String USERNAME = "joe";
    private static String PASSWORD = "password";
    private static String MYSQL_CONN_STRING = "jdbc:mysql://localhost/stock_info?useLegacyDatetimeCode=false&serverTimezone=UTC";

    private static DBType dbType = DBType.MYSQL;
    private static Connection connection = null;

    protected ConnectionManager() {
    }

    public static ConnectionManager getInstance() {
        if (instance == null) {
            instance = new ConnectionManager();
        }

        return instance;
    }

    public void setDbType(DBType dbType) {
        this.dbType = dbType;
    }

    private static boolean openConnection() {
        try {
            switch (dbType) {
                case MYSQL:
                    connection = DriverManager.getConnection(MYSQL_CONN_STRING, USERNAME, PASSWORD);
                    return true;

                default:
                    return false;
            }
        } catch (SQLException e) {
            System.err.println(e);
            return false;
        }
    }

    public static Connection getConnection() {
        if (connection == null) {
            if (openConnection()) {
                System.out.println("Connection Opened");
                return connection;
            } else {
                return null;
            }
        }
        return connection;
    }

    public void close() {
        System.out.println("Closing Connection");
        try {
            connection.close();
            connection = null;
        } catch (Exception e) {

        }
    }
}

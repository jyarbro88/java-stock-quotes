package db;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class StockManager {

    public void displayAllRows() throws SQLException {

        String sqlSelectAllStatement = "SELECT symbol, price, volume, date FROM stock_quotes";
        MySqlConnection mySqlConnection = new MySqlConnection();

        try (
                Connection connection = mySqlConnection.connectToDatabase();
                Statement selectStatementToRun = connection.createStatement();
                ResultSet resultSet = selectStatementToRun.executeQuery(sqlSelectAllStatement)
                ) {
            System.out.println("Stock Table");

            while(resultSet.next()) {
                StringBuffer buffer = new StringBuffer();
                buffer.append(resultSet.getString("symbol"));

                System.out.println(buffer.toString());
            }

        }




    }
}

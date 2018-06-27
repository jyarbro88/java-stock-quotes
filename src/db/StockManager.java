package db;

import beans.Stock;

import java.sql.*;

public class StockManager {
    private MySqlConnection mySqlConnection = new MySqlConnection();

//    public void displayAllRows() throws SQLException {
//        String sqlSelectAllStatement = "SELECT symbol, price, volume, date FROM stock_quotes";
//
//        try (
//                Connection connection = mySqlConnection.connectToDatabase();
//                Statement selectStatementToRun = connection.createStatement();
//                ResultSet resultSet = selectStatementToRun.executeQuery(sqlSelectAllStatement)
//                ) {
//            System.out.println("Stock Table");
//
//            while(resultSet.next()) {
//                StringBuffer buffer = new StringBuffer();
//                buffer.append(resultSet.getString("symbol"));
//
//                System.out.println(buffer.toString());
//            }
//
//        }
//    }

    public void insertRecord(Stock stockBean) throws Exception {
        String sqlInsertStatement = "INSERT INTO stock_quotes (Symbol, price, volume, date) VALUES (?, ?, ?, ?);";
        ResultSet keys = null;

        try (
                Connection connection = mySqlConnection.connectToDatabase();
                PreparedStatement insertStatement = connection.prepareStatement(sqlInsertStatement, Statement.RETURN_GENERATED_KEYS);
                ) {
            insertStatement.setString(1, stockBean.getSymbol());
            insertStatement.setDouble(2, stockBean.getPrice());
            insertStatement.setInt(3, stockBean.getVolume());
            insertStatement.setString(4, stockBean.getDate());
            int affectedRow = insertStatement.executeUpdate();

            if (affectedRow == 1) {
                keys = insertStatement.getGeneratedKeys();
                keys.next();

                int newKey = keys.getInt(1);
                stockBean.setId(newKey);
            } else {
                System.err.println("No rows affected");
            }

        } catch (SQLException e) {
            System.err.println(e);
        } finally {
            if (keys != null) {
                keys.close();
            }
        }

    }
}

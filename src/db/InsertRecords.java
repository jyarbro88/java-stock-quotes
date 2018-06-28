package db;

import beans.Stock;
import java.sql.*;

public class InsertRecords {

    private static Connection connection = ConnectionManager.getConnection();

    public void insertRecord(Stock stockBean) {
        ResultSet keys = null;
        String sqlInsertStatement = "INSERT INTO stock_quotes (Symbol, price, volume, date) VALUES (?, ?, ?, ?);";

        try (
                PreparedStatement insertStatement = connection.prepareStatement(sqlInsertStatement, Statement.RETURN_GENERATED_KEYS)
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
        }
    }
}

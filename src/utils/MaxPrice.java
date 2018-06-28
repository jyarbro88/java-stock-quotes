//package utils;
//
//import com.mysql.cj.protocol.Resultset;
//import db.ConnectionManager;
//
//import java.io.IOException;
//import java.sql.*;
//import java.util.Scanner;
//
//public class MaxPrice {
//
//    public void findMaxValue(String inputSymbol) throws SQLException, IOException {
//
//        ResultSet resultSet = null;
//        String queryForMaxPrice = "select * from stock_quotes where price in (select max(price) from stock_quotes where symbol = ? group by symbol);";
//        try (
//                Connection connection = ConnectionManager.getConnection();
//                PreparedStatement preparedStatement = connection.prepareStatement(queryForMaxPrice, ResultSet.TYPE_SCROLL_INSENSITIVE);
//                ) {
//
//            preparedStatement.setString(1, inputSymbol);
//            resultSet = preparedStatement.executeQuery();
//
//            if(resultSet.next()){
//                String foundType = resultSet.getString("price");
//                System.out.println("Max Price: " + foundType);
//
//            }
//        } catch (SQLException e) {
//            System.err.println(e);
//        }
//
//        InputHelper inputHelper = new InputHelper();
//        inputHelper.askUserToContinue();
//
//    }
//}

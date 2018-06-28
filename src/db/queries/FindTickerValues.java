package db.queries;

import db.ConnectionManager;
import utils.menus.ContinueMenu;
import java.io.IOException;
import java.sql.*;

public class FindTickerValues {

    private Connection connection = ConnectionManager.getConnection();

    public void searchForTickerValues(String symbolToSearch, String dateToSearch) throws SQLException, IOException {

        ResultSet maxResultSet;
        ResultSet minResultSet;
        ResultSet closingResultSet;
        ResultSet totalVolumeSet;

        String queryForMaxPrice = "SELECT MAX(price) AS 'price' FROM stock_quotes WHERE symbol = ? AND date LIKE ?;";
        String queryForMinPrice = "SELECT MIN(price) AS 'price' FROM stock_quotes WHERE symbol = ? AND date LIKE ?;";
        String queryForClosingPrice = "SELECT * FROM stock_quotes WHERE id IN (SELECT MAX(id) FROM stock_quotes WHERE symbol = ? AND date LIKE ? GROUP BY symbol);";
        String queryForTotalVolume = "SELECT SUM(volume) AS 'volume' from stock_quotes WHERE symbol = ? AND date LIKE ?;";

        try (
                PreparedStatement preparedMaxPriceStatement = connection.prepareStatement(queryForMaxPrice);
                PreparedStatement preparedMinPriceStatement = connection.prepareStatement(queryForMinPrice);
                PreparedStatement preparedClosingPriceStatement = connection.prepareStatement(queryForClosingPrice);
                PreparedStatement preparedTotalVolumeStatement = connection.prepareStatement(queryForTotalVolume)

        ) {

            preparedMaxPriceStatement.setString(1, symbolToSearch);
            preparedMaxPriceStatement.setString(2, dateToSearch + "%");

            preparedMinPriceStatement.setString(1, symbolToSearch);
            preparedMinPriceStatement.setString(2, dateToSearch + "%");

            preparedClosingPriceStatement.setString(1, symbolToSearch);
            preparedClosingPriceStatement.setString(2, dateToSearch + "%");

            preparedTotalVolumeStatement.setString(1, symbolToSearch);
            preparedTotalVolumeStatement.setString(2, dateToSearch + "%");

            maxResultSet = preparedMaxPriceStatement.executeQuery();
            minResultSet = preparedMinPriceStatement.executeQuery();
            closingResultSet = preparedClosingPriceStatement.executeQuery();
            totalVolumeSet = preparedTotalVolumeStatement.executeQuery();

            if(maxResultSet.next()){
                String maxPrice = maxResultSet.getString("price");
                System.out.println("----------------------");
                System.out.println("Searching " + symbolToSearch + " Quotes For: " + dateToSearch);
                System.out.println("----------------------");
                System.out.println("Max Price: $" + maxPrice);
                System.out.println();
            }

            if (minResultSet.next()) {
                String minPrice = minResultSet.getString("price");
                System.out.println("Min Price: $" + minPrice);
                System.out.println();
            }

            if (closingResultSet.next()) {
                String closingPrice = closingResultSet.getString("price");
                System.out.println("Closing Price: $" + closingPrice);
                System.out.println();
            }

            if (totalVolumeSet.next()) {
                Integer totalVolume = totalVolumeSet.getInt("volume");
                System.out.println("Total Volume Traded: " + totalVolume);
                System.out.println();
            }
        } catch (SQLException e) {
            System.err.println(e);
        }
        ContinueMenu continueMenu = new ContinueMenu();
        continueMenu.askUserToContinue();
    }
}

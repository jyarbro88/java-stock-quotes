package utils;

import db.ConnectionManager;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.*;

public class InputHelper {

    private BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    private Connection connection = ConnectionManager.getConnection();

    private DisplayAll displayAll = new DisplayAll();

    public void getUserInput() throws IOException, SQLException {

        System.out.println("Stock Quote Tracker");
        System.out.println("Press 1 to see list of Ticker Symbols:  ");
        System.out.println("Press 2 to enter Ticker Symbol:  ");
        System.out.println("Press 3 to display all records:  ");
        System.out.println("Input Selection:  ");
        String userInput = reader.readLine();

        switch (userInput) {
            case "1":
                displayListOfTickerSymbols();
                break;
            case "2":
                promptUserForTickerSymbolToSearch();
                break;
            case "3":
                displayAll.displayAllRows();
                break;
//
//            default:
//                return false;
        }
    }

    void askUserToContinue() throws IOException, SQLException {
        System.out.println("Press 1 to continue: ");
        System.out.println("Press 2 to exit: ");
        String userInput = reader.readLine();

        switch (userInput) {
            case "1":
                getUserInput();
                break;
            case "2":
//                exitProgram();
                break;
            default:
                break;
        }
    }

    private void exitProgram() {

        System.out.println("Exiting Application.");
        ConnectionManager.getInstance().close();
    }

    private void promptUserForDate(String symbolToSearch) throws IOException, SQLException {

        System.out.print("Enter Date to Search (YYYY-MM-DD): ");
        String dateToSearch = reader.readLine();

        findMaxValue(symbolToSearch, dateToSearch);

    }

    private void displayListOfTickerSymbols() throws SQLException, IOException {

        String sqlFindTickerSymbols = "SELECT DISTINCT(symbol) FROM stock_quotes ORDER BY symbol ASC;";

        try (

                Statement selectStatementToRun = connection.createStatement();
                ResultSet resultSet = selectStatementToRun.executeQuery(sqlFindTickerSymbols)
        ) {
            while (resultSet.next()) {
                StringBuffer buffer = new StringBuffer();
                buffer.append(resultSet.getString("symbol"));
                System.out.println(buffer.toString());
            }
        }

        promptUserForTickerSymbolToSearch();
    }

    private void promptUserForTickerSymbolToSearch() throws IOException, SQLException {

        System.out.print("Enter Stock Symbol: ");
        String userStockSymbolInput = reader.readLine();
        promptUserForDate(userStockSymbolInput);
//        System.out.flush();
    }

    private void findMaxValue(String symbolToSearch, String dateToSearch) throws SQLException, IOException {

        ResultSet maxResultSet = null;
        ResultSet minResultSet = null;
        ResultSet closingResultSet = null;

        String queryForMaxPrice = "SELECT MAX(price) AS 'price' FROM stock_quotes WHERE symbol = ? AND date LIKE ?;";
        String queryForMinPrice = "SELECT MIN(price) AS 'price' FROM stock_quotes WHERE symbol = ? AND date LIKE ?;";
        String queryForClosingPrice = "SELECT * FROM stock_quotes WHERE id IN (SELECT MAX(id) FROM stock_quotes WHERE symbol = ? AND date LIKE ? GROUP BY symbol);";
        String queryForTotalVolume = "SELECT SUM(volume) from stock_quotes WHERE symbol = ? AND date LIKE ?;";

        try (
                PreparedStatement preparedMaxPriceStatement = connection.prepareStatement(queryForMaxPrice);
                PreparedStatement preparedMinPriceStatement = connection.prepareStatement(queryForMinPrice);
                PreparedStatement preparedClosingPriceStatement = connection.prepareStatement(queryForClosingPrice)

        ) {

            preparedMaxPriceStatement.setString(1, symbolToSearch);
            preparedMaxPriceStatement.setString(2, dateToSearch + "%");

            preparedMinPriceStatement.setString(1, symbolToSearch);
            preparedMinPriceStatement.setString(2, dateToSearch + "%");

            preparedClosingPriceStatement.setString(1, symbolToSearch);
            preparedClosingPriceStatement.setString(2, dateToSearch + "%");

            maxResultSet = preparedMaxPriceStatement.executeQuery();
            minResultSet = preparedMinPriceStatement.executeQuery();
            closingResultSet = preparedClosingPriceStatement.executeQuery();

            if(maxResultSet.next()){
                String maxPrice = maxResultSet.getString("price");
                System.out.println("Searching " + symbolToSearch + " Quotes For: " + dateToSearch);
                System.out.println("Max Price: " + maxPrice);

            }

            if (minResultSet.next()) {
                String minPrice = minResultSet.getString("price");
                System.out.println("Min Price: " + minPrice);
            }

            if (closingResultSet.next()) {
                String closingPrice = closingResultSet.getString("price");
                System.out.println("Closing Price: " + closingPrice);
            }
        } catch (SQLException e) {
            System.err.println(e);
        }

        InputHelper inputHelper = new InputHelper();
        inputHelper.askUserToContinue();

    }

//    private void findLastRecordOfDay() {
//        String sqlForLastRecord = "select * from stock_quotes where id in (select max(id) from stock_quotes where symbol = ? AND date like ?% group by symbol);";
//    }
}

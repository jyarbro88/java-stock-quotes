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
                tickerSymbolToSearch();
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

    public void promptUserForDate(String symbolToSearch) {

        System.out.println("the entered symbol was: " + symbolToSearch);

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

        tickerSymbolToSearch();
    }

    private void tickerSymbolToSearch() throws IOException, SQLException {

//        MaxPrice maxPrice = new MaxPrice();

        System.out.print("Enter Stock Symbol: ");
        String userStockInput = reader.readLine();
        findMaxValue(userStockInput);
        System.out.flush();


    }

    private void findMaxValue(String inputSymbol) throws SQLException, IOException {

        ResultSet resultSet = null;
        String queryForMaxPrice = "select * from stock_quotes where price in (select max(price) from stock_quotes where symbol = ? group by symbol);";
        try (
//                Connection connection = ConnectionManager.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(queryForMaxPrice, ResultSet.TYPE_SCROLL_INSENSITIVE);
        ) {

            preparedStatement.setString(1, inputSymbol);
            resultSet = preparedStatement.executeQuery();

            if(resultSet.next()){
                String foundType = resultSet.getString("price");
                System.out.println("Max Price: " + foundType);

            }
        } catch (SQLException e) {
            System.err.println(e);
        }

        InputHelper inputHelper = new InputHelper();
        inputHelper.askUserToContinue();

    }
}

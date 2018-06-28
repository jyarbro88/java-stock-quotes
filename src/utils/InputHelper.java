package utils;

import db.ConnectionManager;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class InputHelper {

    private BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    private Connection connection = ConnectionManager.getConnection();

    DisplayAll displayAll = new DisplayAll();

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

    private String tickerSymbolToSearch() throws IOException {

        System.out.print("Enter Stock Symbol: ");
        String userStockInput = reader.readLine();
        promptUserForDate(userStockInput);
        System.out.flush();


        return userStockInput;
    }
}

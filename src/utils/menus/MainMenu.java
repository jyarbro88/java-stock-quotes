package utils.menus;

import db.queries.DisplayAllRecords;
import db.queries.DisplayTickerSymbols;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.SQLException;

public class MainMenu {

    public void displayMainMenu() throws IOException, SQLException {

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        DisplayAllRecords displayAllRecords = new DisplayAllRecords();
        DisplayTickerSymbols displayListOfTickerSymbols = new DisplayTickerSymbols();
        SymbolToSearch symbolToSearch = new SymbolToSearch();

        System.out.println("Stock Quote Tracker");
        System.out.println("Press 1 to see list of Ticker Symbols:  ");
        System.out.println("Press 2 to enter Ticker Symbol:  ");
        System.out.println("Press 3 to display all records:  ");
        System.out.println("Input Selection:  ");
        String userInput = reader.readLine();

        switch (userInput) {
            case "1":
                displayListOfTickerSymbols.displayListOfTickerSymbols();
                break;
            case "2":
                symbolToSearch.promptUserForTickerSymbolToSearch();
                break;
            case "3":
                displayAllRecords.displayAllRows();
                break;
        }
    }
}

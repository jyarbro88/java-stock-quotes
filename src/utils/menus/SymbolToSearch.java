package utils.menus;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.SQLException;

public class SymbolToSearch {

    public void promptUserForTickerSymbolToSearch() throws IOException, SQLException {

        DateToSearch dateToSearch = new DateToSearch();

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        System.out.print("Enter Stock Symbol: ");
        String userStockSymbolInput = reader.readLine();
        dateToSearch.promptUserForDate(userStockSymbolInput);

    }
}

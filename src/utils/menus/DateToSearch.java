package utils.menus;

import utils.InputHelper;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.SQLException;

public class DateToSearch {

    void promptUserForDate(String symbolToSearch) throws IOException, SQLException {

        InputHelper inputHelper = new InputHelper();
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        System.out.print("Enter Date to Search (YYYY-MM-DD): ");
        String dateToSearch = reader.readLine();

        inputHelper.findMaxValue(symbolToSearch, dateToSearch);

    }
}

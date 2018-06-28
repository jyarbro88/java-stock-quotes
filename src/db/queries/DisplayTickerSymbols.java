package db.queries;

import db.ConnectionManager;
import utils.menus.SymbolToSearch;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DisplayTickerSymbols {

    public void displayListOfTickerSymbols() throws SQLException, IOException {

        Connection connection = ConnectionManager.getConnection();
        String sqlFindTickerSymbols = "SELECT DISTINCT(symbol) FROM stock_quotes ORDER BY symbol ASC;";
        SymbolToSearch symbolToSearch = new SymbolToSearch();

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

        symbolToSearch.promptUserForTickerSymbolToSearch();
    }
}

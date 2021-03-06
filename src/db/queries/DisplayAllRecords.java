package db.queries;

import db.ConnectionManager;
import utils.menus.ContinueMenu;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DisplayAllRecords {

    public void displayAllRows() throws SQLException, IOException {

        Connection connection = ConnectionManager.getConnection();
        ContinueMenu continueMenu = new ContinueMenu();

        String sqlSelectAllStatement = "SELECT * FROM stock_quotes";
        try (

                Statement selectStatementToRun = connection.createStatement();
                ResultSet resultSet = selectStatementToRun.executeQuery(sqlSelectAllStatement)
        ) {
            System.out.println("----------------------");
            System.out.println();
            System.out.println("- Stock Quote Table -");
            System.out.println();
            System.out.println("----------------------");

            while(resultSet.next()) {
                StringBuffer buffer = new StringBuffer();
                buffer.append(resultSet.getString("id"));
                buffer.append("  :   ");
                buffer.append(resultSet.getString("symbol"));
                buffer.append("  :   ");
                buffer.append(resultSet.getString("price"));
                buffer.append("  :   ");
                buffer.append(resultSet.getString("volume"));
                buffer.append("  :   ");
                buffer.append(resultSet.getString("date"));

                System.out.println(buffer.toString());
            }
        }
        continueMenu.askUserToContinue();
    }
}

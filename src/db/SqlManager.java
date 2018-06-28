package db;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class SqlManager {

    public void displayAllRows() throws SQLException {

        Connection connection = ConnectionManager.getConnection();

        String sqlSelectAllStatement = "SELECT symbol, price, volume, date FROM stock_quotes";
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
                buffer.append(resultSet.getString("symbol"));

                System.out.println(buffer.toString());
            }
        }
    }

    public void findMaxValue(){

        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter Symbol to find MAX Value: ");
        String inputSymbol = scanner.nextLine();

        System.out.println(inputSymbol);

    }
}

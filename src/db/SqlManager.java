package db;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class SqlManager {

    public void findMaxValue(){

        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter Symbol to find MAX Value: ");
        String inputSymbol = scanner.nextLine();

        System.out.println(inputSymbol);

    }
}

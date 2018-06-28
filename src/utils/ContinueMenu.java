package utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.SQLException;

public class ContinueMenu {

    private BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    private InputHelper inputHelper = new InputHelper();
    private ExitProgram exitProgram = new ExitProgram();

    protected void askUserToContinue() throws IOException, SQLException {
        System.out.println("Press 1 to continue: ");
        System.out.println("Press 2 to exit: ");
        String userInput = reader.readLine();

        switch (userInput) {
            case "1":
                inputHelper.getUserInput();
                break;
            case "2":
                exitProgram.exitProgram();
                break;
            default:
                break;
        }
    }

}

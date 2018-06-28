package utils;

import db.ConnectionManager;

public class ExitProgram {

    void exitProgram() {

        System.out.println("Exiting Application.");
        ConnectionManager.getInstance().close();
    }
}

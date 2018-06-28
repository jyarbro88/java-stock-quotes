import utils.ReadJson;
import db.ConnectionManager;
import db.DBType;
import utils.menus.MainMenu;

public class Main {

    public static void main(String[] args) throws Exception {

        ReadJson readJson = new ReadJson();
        MainMenu mainMenu = new MainMenu();

        readJson.readJsonWithObjectMapper();
        ConnectionManager.getInstance().setDbType(DBType.MYSQL);
        mainMenu.displayMainMenu();

    }
}

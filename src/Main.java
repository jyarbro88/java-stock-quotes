import utils.ReadJson;
import db.ConnectionManager;
import db.DBType;
import utils.menus.MainMenu;

public class Main {

    public static void main(String[] args) throws Exception {

        ReadJson readJson = new ReadJson();
        readJson.readJsonWithObjectMapper();

        ConnectionManager.getInstance().setDbType(DBType.MYSQL);
//        FindTickerValues inputHelper = new FindTickerValues();

        MainMenu mainMenu = new MainMenu();

        mainMenu.displayMainMenu();

//        inputHelper.mainMenu();

//        SqlManager sqlManager = new SqlManager();
//        sqlManager.displayAllRows();
//
//        sqlManager.searchForTickerValues();


    }
}

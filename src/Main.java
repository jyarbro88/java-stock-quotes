import beans.ReadJson;
import db.ConnectionManager;
import db.DBType;
import utils.InputHelper;

public class Main {

    public static void main(String[] args) throws Exception {

        ReadJson readJson = new ReadJson();
        readJson.readJsonWithObjectMapper();

        ConnectionManager.getInstance().setDbType(DBType.MYSQL);
        InputHelper inputHelper = new InputHelper();

        inputHelper.getUserInput();

//        SqlManager sqlManager = new SqlManager();
//        sqlManager.displayAllRows();
//
//        sqlManager.findMaxValue();


    }
}

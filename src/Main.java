import beans.ReadJson;
import db.ConnectionManager;
import db.DBType;
import db.SqlManager;
import utils.DisplayAll;
import utils.InputHelper;

public class Main {

    public static void main(String[] args) throws Exception {

        ConnectionManager.getInstance().setDbType(DBType.MYSQL);
        InputHelper inputHelper = new InputHelper();

        inputHelper.getUserInput();


//        ReadJson readJson = new ReadJson();
//        readJson.readJsonWithObjectMapper();

//        SqlManager sqlManager = new SqlManager();
//        sqlManager.displayAllRows();
//
//        sqlManager.findMaxValue();

        ConnectionManager.getInstance().close();


    }
}

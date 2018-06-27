package beans;

import com.fasterxml.jackson.databind.ObjectMapper;
import db.StockManager;
import java.io.*;

public class ReadJson {

    public void readJsonWithObjectMapper() throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();
        StockManager stockManager = new StockManager();

        Stock[] stockList = objectMapper.readValue(new File("/Users/joeyarbrough/Projects/Java-Stock-Quotes/data/week1-stocks.json"), Stock[].class);

        int counter = 0;

        while (counter < stockList.length) {
            counter++;
            stockManager.insertRecord(stockList[counter]);
        }
    }
}

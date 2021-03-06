package utils;

import beans.Stock;
import com.fasterxml.jackson.databind.ObjectMapper;
import db.InsertRecords;
import java.io.*;

public class ReadJson {

    public void readJsonWithObjectMapper() throws Exception {

        ObjectMapper objectMapper = new ObjectMapper();
        InsertRecords insertRecords = new InsertRecords();

        Stock[] stockList = objectMapper.readValue(new File("/Users/joeyarbrough/Projects/Java-Stock-Quotes/data/week1-stocks.json"), Stock[].class);

        int counter = 0;

        while (counter < (stockList.length - 1)) {
            counter++;
            try {
                insertRecords.insertRecord(stockList[counter]);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}

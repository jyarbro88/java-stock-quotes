CREATE TABLE stock_quotes (
  id INT AUTO_INCREMENT PRIMARY KEY,
  symbol VARCHAR(10) NOT NULL,
  price DOUBLE,
  volume INT,
  date VARCHAR(100)
);

INSERT INTO stock_quotes (symbol, price, volume, date) VALUES ("AAPL", 1000.00, 3000)

SELECT STR_TO_DATE(date) from stock_quotes;

drop table stock_quotes;
INSERT INTO stock_quotes (Symbol, price, volume, date) VALUES ("AAPL", 1000.00, 5000, 2018-06-22T08:32:00.000+0000);

SELECT DATE_FORMAT(date, '%m/%d/%Y %H:%i') FROM stock_quotes;
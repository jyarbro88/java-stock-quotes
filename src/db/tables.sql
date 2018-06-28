CREATE TABLE stock_quotes (
  id INT AUTO_INCREMENT PRIMARY KEY,
  symbol VARCHAR(10) NOT NULL,
  price DOUBLE,
  volume INT,
  date VARCHAR(100)
);

drop table stock_quotes;
INSERT INTO stock_quotes (Symbol, price, volume, date) VALUES ("AAPL", 1000.00, 5000, '2018-06-22T08:32:00.000+0000');
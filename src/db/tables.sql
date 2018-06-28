CREATE TABLE stock_quotes (
  id INT AUTO_INCREMENT PRIMARY KEY,
  symbol VARCHAR(10) NOT NULL,
  price DOUBLE,
  volume INT,
  date VARCHAR(100)
);

drop table stock_quotes;
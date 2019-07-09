CREATE TABLE IF NOT EXISTS date (
  id INT (5) PRIMARY KEY AUTO_INCREMENT,
  operationId VARCHAR (10),
  operationName VARCHAR (20),
  operationYear INT (3),
  operationMonth INT (3),
  operationDay INT (3),
  result VARCHAR (8),
  monthEnd BOOLEAN
);
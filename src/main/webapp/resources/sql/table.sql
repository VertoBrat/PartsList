USE test;
DROP TABLE IF EXISTS part;

CREATE TABLE part
(
  id            INT AUTO_INCREMENT
    PRIMARY KEY,
  computer_part VARCHAR(100)    NOT NULL,
  isneeded      CHAR(1)            NULL,
  quantity      INT DEFAULT '0' NOT NULL
)
  ENGINE = InnoDB;


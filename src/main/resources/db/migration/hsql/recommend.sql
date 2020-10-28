DROP TABLE IF EXISTS recommend;
 
CREATE TABLE recommend (
  id INT AUTO_INCREMENT  PRIMARY KEY,
  name VARCHAR(250) NOT NULL
);
 
INSERT INTO recommend (name) VALUES ('Aliko');
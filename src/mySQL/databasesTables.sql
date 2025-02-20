DROP DATABASE IF EXISTS courtsystem;
CREATE DATABASE courtsystem;
USE courtsystem;

CREATE TABLE directory (
    firstName VARCHAR(50) NOT NULL,
    lastName VARCHAR(50) NOT NULL,
	age VARCHAR(5) NOT NULL,
    address VARCHAR(60) NOT NULL,
    phone VARCHAR(50) NOT NULL,
    email VARCHAR(50) NOT NULL,
	username VARCHAR(50) NOT NULL PRIMARY KEY,
    pword VARCHAR(50) NOT NULL
);

CREATE TABLE reservation (
	courtNum int NOT NULL PRIMARY KEY,
    username VARCHAR(50),
    resTime datetime,
    isRes bool NOT NULL,
    FOREIGN KEY (username) REFERENCES directory(username) 
		ON DELETE CASCADE
);

INSERT INTO reservation (courtNum, isRes)
VALUES (1, FALSE);

INSERT INTO reservation (courtNum, isRes)
VALUES (2, FALSE);

INSERT INTO reservation (courtNum, isRes)
VALUES (3, FALSE);

INSERT INTO reservation (courtNum, isRes)
VALUES (4, FALSE);

INSERT INTO reservation (courtNum, isRes)
VALUES (5, FALSE);
DROP DATABASE IF EXISTS passwordmanager_test;
CREATE DATABASE passwordmanager_test;
USE passwordmanager_test;


CREATE TABLE user
(
	user_id INT NOT NULL PRIMARY KEY AUTO_INCREMENT,
	first_name VARCHAR(100) NOT NULL,
	last_name VARCHAR(100) NOT NULL,
	username VARCHAR(80) NOT NULL UNIQUE,
	email VARCHAR(80) NOT NULL UNIQUE,
	password char(128) NOT NULL
);

CREATE TABLE application
(
	app_id INT NOT NULL PRIMARY KEY AUTO_INCREMENT,
	user_id INT NOT NULL,
	app_name VARCHAR(80) NOT NULL,
	app_username VARCHAR(80) NOT NULL,	
	app_password VARCHAR(45) NOT NULL,
	
	CONSTRAINT FOREIGN KEY (user_id) REFERENCES user(user_id) ON DELETE CASCADE ON UPDATE CASCADE	
);

CREATE TABLE log
(
	log_id INT NOT NULL PRIMARY KEY AUTO_INCREMENT,
	user_id INT NOT NULL,
	date DATETIME NOT NULL,
	is_success TINYINT NOT NULL,

	CONSTRAINT FOREIGN KEY (user_id) REFERENCES user(user_id) ON DELETE CASCADE ON UPDATE CASCADE
);



/*INSERT INTO user (first_name, last_name, username, password)
VALUES("ali","veli", "a","954b3a80245b6016e4be7ad10243cea85dc393ce9ca9b5b743febfd2f29b5ff6b4f8886b9e638f333a97d217914ab0890e7ab34c3aea20dcd2ba443d711eb7ca");
*/

-- select * from log where user_id=2;
select * from user;
select * from application;

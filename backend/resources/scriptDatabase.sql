/* inside connection with root user
DROP USER 'ironhacker'@'localhost';
CREATE USER 'ironhacker'@'localhost' IDENTIFIED BY 'Ir0nh4ck3r!';
GRANT ALL PRIVILEGES ON bicisAntidio.* TO 'ironhacker'@'localhost';
GRANT ALL PRIVILEGES ON bicisAntidio_test.* TO 'ironhacker'@'localhost';
*/
DROP SCHEMA IF EXISTS bicisAntidio_test;
CREATE SCHEMA bicisAntidio_test;

DROP SCHEMA IF EXISTS bicisAntidio;
CREATE SCHEMA bicisAntidio;
USE bicisAntidio;

DROP TABLE IF EXISTS category;

CREATE TABLE category(
	type VARCHAR(25) NOT NULL,
	description VARCHAR(255),
    creation_date DATE,
    user_creation VARCHAR(255),
    modification_date DATE,
    user_modification VARCHAR(255),
	PRIMARY KEY(type)
);

DROP TABLE IF EXISTS article;

CREATE TABLE article(
	id INT UNSIGNED NOT NULL AUTO_INCREMENT,
	category_type VARCHAR(25),
	name VARCHAR(50),
    brand VARCHAR(50),
    description VARCHAR(255),
    image_url VARCHAR(255),
	price DECIMAL(7,2),
    creation_date DATE,
    user_creation VARCHAR(255),
    modification_date DATE,
    user_modification VARCHAR(255),
	PRIMARY KEY(id),
    FOREIGN KEY(category_type) REFERENCES category(type)
);

DROP TABLE IF EXISTS stock;

CREATE TABLE stock(
	id INT UNSIGNED NOT NULL AUTO_INCREMENT,
    article_id INT UNSIGNED,
    size VARCHAR(3),
    units SMALLINT UNSIGNED,
    creation_date DATE,
    user_creation VARCHAR(255),
    modification_date DATE,
    user_modification VARCHAR(255),
	PRIMARY KEY(id),
    FOREIGN KEY(article_id) REFERENCES article(id)
);

DROP TABLE IF EXISTS novelty;

CREATE TABLE novelty(
	id INT UNSIGNED NOT NULL AUTO_INCREMENT,
	article_id INT UNSIGNED,
    creation_date DATE,
    user_creation VARCHAR(255),
    modification_date DATE,
    user_modification VARCHAR(255),
	PRIMARY KEY(id),
    FOREIGN KEY(article_id) REFERENCES article(id)
);

DROP TABLE IF EXISTS disccount;

CREATE TABLE discount(
	id INT UNSIGNED NOT NULL AUTO_INCREMENT,
	article_id INT UNSIGNED,
	promotion VARCHAR(255),
    quantity DECIMAL(4,2),
    creation_date DATE,
    user_creation VARCHAR(255),
    modification_date DATE,
    user_modification VARCHAR(255),
	PRIMARY KEY(id),
    FOREIGN KEY(article_id) REFERENCES article(id)
);


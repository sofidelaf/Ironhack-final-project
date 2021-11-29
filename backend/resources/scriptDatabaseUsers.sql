/* inside connection with root user
DROP USER 'ironhacker'@'localhost';
CREATE USER 'ironhacker'@'localhost' IDENTIFIED BY 'Ir0nh4ck3r!';
GRANT ALL PRIVILEGES ON bicisAntidio_admin.* TO 'ironhacker'@'localhost';
GRANT ALL PRIVILEGES ON bicisAntidio_admin_test.* TO 'ironhacker'@'localhost';
*/
DROP SCHEMA IF EXISTS bicisAntidio_admin_test;
CREATE SCHEMA bicisAntidio_admin_test;

DROP SCHEMA IF EXISTS bicisAntidio_admin;
CREATE SCHEMA bicisAntidio_admin;
USE bicisAntidio_admin;

DROP TABLE IF EXISTS user;

CREATE TABLE user(
	id INT UNSIGNED NOT NULL AUTO_INCREMENT,
	username VARCHAR(50) NOT NULL,
	password VARCHAR(100) NOT NULL,
    enabled TINYINT(1) NOT NULL,
    credentialsexpired TINYINT(1) NOT NULL,
    expired TINYINT(1) NOT NULL,
    locked TINYINT(1) NOT NULL,
	PRIMARY KEY(id),
    UNIQUE KEY username (username)
);

INSERT INTO user (username, password, enabled, credentialsexpired, expired, locked) VALUES
("admin","$2a$10$MSzkrmfd5ZTipY0XkuCbAejBC9g74MAg2wrkeu8/m1wQGXDihaX3e", 1, 0, 0, 0);

DROP TABLE IF EXISTS role;

CREATE TABLE role(
	id INT UNSIGNED NOT NULL AUTO_INCREMENT,
	code VARCHAR(50) NOT NULL,
	PRIMARY KEY(id),
    UNIQUE KEY code (code)
);

INSERT INTO role (code) VALUES ("ROLE_ADMIN");

DROP TABLE IF EXISTS user_role;

CREATE TABLE user_role(
	user_id INT UNSIGNED NOT NULL AUTO_INCREMENT,
	role_id INT UNSIGNED NOT NULL,
	PRIMARY KEY(user_id, role_id),
    FOREIGN KEY (user_id) REFERENCES user (id),
    FOREIGN KEY (role_id) REFERENCES role (id)
);

INSERT INTO user_role (user_id, role_id) VALUES (1, 1);

DROP TABLE IF EXISTS contact;

CREATE TABLE contact(
	id BIGINT UNSIGNED NOT NULL AUTO_INCREMENT,
	full_name VARCHAR(255) NOT NULL,
	email VARCHAR(100) NOT NULL,
    subject VARCHAR(100) NOT NULL,
    details VARCHAR(1000) NOT NULL,
    creation_datetime DATETIME,
	PRIMARY KEY(id)
);

CREATE DATABASE managementdb;
USE managementdb;

CREATE TABLE t_user (
    userid int NOT NULL AUTO_INCREMENT,
    username varchar(255) NOT NULL,
    password varchar(100) DEFAULT NULL,
    lastname varchar(255) DEFAULT NULL,
    firstname varchar(255) DEFAULT NULL,
    email varchar(255) NOT NULL,
    phone varchar(12) DEFAULT NULL,
    avatar varchar(255) DEFAULT NULL,
    status int(1) DEFAULT 1,
    PRIMARY KEY (userid),
    CONSTRAINT username_unique UNIQUE (username)
);

INSERT INTO t_user (userid, username, password, lastname, firstname, email, phone, avatar, status)
VALUES (1, 'lars11', '112354', 'Lars', 'Monsen', 'lars@gmail.com', '1111123456', 'https://wpimg.wallstcn.com/f778738c-e4f8-4870-b634-56703b4acafe.gif', 1);
INSERT INTO t_user (userid, username, password, lastname, firstname, email, phone, avatar, status)
VALUES (2, 'helen2', '000000', 'Wang', 'Helen', 'helen@gmail.com', '7785225555', 'https://wpimg.wallstcn.com/f778738c-e4f8-4870-b634-56703b4acafe.gif', 1);
INSERT INTO t_user (userid, username, password, lastname, firstname, email, phone, avatar, status)
VALUES (3, 'liam119', '119873', 'Chen', 'Liam', 'liam@gmail.com', '7785625372', 'https://wpimg.wallstcn.com/f778738c-e4f8-4870-b634-56703b4acafe.gif', 1);
INSERT INTO t_user (userid, username, password, lastname, firstname, email, phone, avatar, status)
VALUES (4, 'smith', '167269', 'Smith', 'Mary', 'mary111@gmail.com', '2067281292', 'https://wpimg.wallstcn.com/f778738c-e4f8-4870-b634-56703b4acafe.gif', 1);
INSERT INTO t_user (userid, username, password, lastname, firstname, email, phone, avatar, status)
VALUES (5, 'amber1', '679303', 'Larry', 'Amber', 'larry.amber@gmail.com', '9820721124', 'https://wpimg.wallstcn.com/f778738c-e4f8-4870-b634-56703b4acafe.gif', 1);

CREATE TABLE t_role (
    roleid int NOT NULL AUTO_INCREMENT,
    rolename varchar(100) NOT NULL,
    roledescription varchar(255) NOT NULL,
    PRIMARY KEY (roleid)
);

INSERT INTO t_role (roleid, rolename, roledescription) VALUES (1, 'admin', 'Administrator');
INSERT INTO t_role (roleid, rolename, roledescription) VALUES (2, 'hr', 'Human Resource');
INSERT INTO t_role (roleid, rolename, roledescription) VALUES (3, 'regular', 'Regular Employee');

CREATE TABLE t_user_role (
    id int NOT NULL AUTO_INCREMENT,
    userid int DEFAULT NULL,
    roleid int DEFAULT NULL,
    PRIMARY KEY (id)
);

INSERT INTO t_user_role (id, userid, roleid) VALUES (1, 1, 3);
INSERT INTO t_user_role (id, userid, roleid) VALUES (2, 2, 1);
INSERT INTO t_user_role (id, userid, roleid) VALUES (3, 3, 2);
INSERT INTO t_user_role (id, userid, roleid) VALUES (4, 4, 3);
INSERT INTO t_user_role (id, userid, roleid) VALUES (5, 5, 3);
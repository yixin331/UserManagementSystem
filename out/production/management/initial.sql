CREATE TABLE Person (
    id int NOT NULL AUTO_INCREMENT,
    username varchar(255) NOT NULL,
    password varchar(100) DEFAULT NULL,
    lastname varchar(255) DEFAULT NULL,
    firstame varchar(255) DEFAULT NULL,
    email varchar(255) DEFAULT NULL,
    phone varchar(12) DEFAULT NULL,
    status int(1) DEFAULT  NULL,
    PRIMARY KEY (id)
)

INSERT INTO Person (id, username, password, lastname, firstame, email, phone, status)
VALUES (1, 'lars11', '112354', 'Lars', 'Monsen', 'lars@gamil.com', '1111123456', 1);
INSERT INTO Person (id, username, password, lastname, firstame, email, phone, status)
VALUES (2, 'helen2', '000000', 'Wang', 'Helen', 'helen@gamil.com', '7785225555', 1);
INSERT INTO Person (id, username, password, lastname, firstame, email, phone, status)
VALUES (3, 'liam119', '119873', 'Chen', 'Liam', 'liam_chen@gamil.com', '7785625372', 1);
INSERT INTO Person (id, username, password, lastname, firstame, email, phone, status)
VALUES (4, 'smith', '167269', 'Smith', 'Mary', 'mary111@gamil.com', '2067281292', 1);
INSERT INTO Person (id, username, password, lastname, firstame, email, phone, status)
VALUES (5, 'amber1', '679303', 'Larry', 'Amber', 'larry.amber@gamil.com', '9820721124', 1);

CREATE TABLE Role (
    roleid int NOT NULL AUTO_INCREMENT,
    rolename varchar(100) NOT NULL,
    PRIMARY KEY (roleid)
)

INSERT INTO Role (roleid, rolename) VALUES (1, 'admin');
INSERT INTO Role (roleid, rolename) VALUES (2, 'hr');
INSERT INTO Role (roleid, rolename) VALUES (3, 'regular');
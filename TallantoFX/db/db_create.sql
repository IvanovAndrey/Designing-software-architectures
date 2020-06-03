/*CREATE TYPE status_user AS ENUM ('ADMIN', 'TEACHER', 'CLIENT');
CREATE TYPE status_nitification AS ENUM('NEW', 'READED', 'APPROVED', 'DENY');
CREATE TYPE status_request AS ENUM('NEW', 'CHECKED', 'CLOSED');
CREATE TYPE status_lesson AS ENUM('OPEN','CLOSED');*/


DROP TABLE IF EXISTS COMPLAINTS;
DROP TABLE IF EXISTS NOTIFICATIONS;
DROP TABLE IF EXISTS REQUESTS;
DROP TABLE IF EXISTS CLIENTS_ON_LESSONS;
DROP TABLE IF EXISTS LESSONS;
DROP TABLE IF EXISTS USERS;


CREATE TABLE USERS (
  id serial NOT NULL PRIMARY KEY,
  name VARCHAR(100) NOT NULL,
  login VARCHAR(100) UNIQUE NOT NULL,
  status VARCHAR(10) NOT NULL,
  password CHAR(40)  
);

CREATE TABLE LESSONS (
  id serial NOT NULL PRIMARY KEY,
  id_teacher INT NOT NULL,
  theme VARCHAR(100) NOT NULL,
  commentary VARCHAR(1000),
  status VARCHAR(10) NOT NULL,
  date_of_lesson date NOT NULL,
  FOREIGN KEY (id_teacher) REFERENCES USERS(id)
);

CREATE TABLE CLIENTS_ON_LESSONS (
  id serial NOT NULL PRIMARY KEY,
  id_client INT NOT NULL,
  id_lesson INT NOT NULL,
  commentary VARCHAR(1000), 
  FOREIGN KEY (id_client) REFERENCES USERS(id),
  FOREIGN KEY (id_lesson) REFERENCES LESSONS(id)
);

CREATE TABLE COMPLAINTS (
  id serial NOT NULL PRIMARY KEY,
  id_incedent INT NOT NULL,
  theme VARCHAR(100) NOT NULL,
  text VARCHAR(1000) NOT NULL,
  FOREIGN KEY (id_incedent) REFERENCES CLIENTS_ON_LESSONS(id)
);

CREATE TABLE NOTIFICATIONS (
  id serial NOT NULL PRIMARY KEY,
  id_from INT NOT NULL,
  id_to INT NOT NULL,
  status VARCHAR(10) NOT NULL,
  theme VARCHAR(100) NOT NULL,
  text VARCHAR(1000) NOT NULL,
  FOREIGN KEY (id_from) REFERENCES USERS(id),
  FOREIGN KEY (id_to) REFERENCES USERS(id)
);


CREATE TABLE REQUESTS (
  id serial NOT NULL PRIMARY KEY,
  id_user INT NOT NULL,
  status VARCHAR(10) NOT NULL,
  dates VARCHAR(1000) NOT NULL,
  date_of_send date NOT NULL,
  FOREIGN KEY (id_user) REFERENCES USERS(id)
);


INSERT INTO USERS(id,name, login, status, password) VALUES (0,'Admin', 'admin', 'ADMIN', 'pass');
INSERT INTO USERS(id,name, login, status, password) VALUES (1,'Teacher', 'teacher', 'TEACHER', 'pass');
INSERT INTO USERS(id,name, login, status, password) VALUES (2,'Client1', 'Client1', 'CLIENT', 'pass');
INSERT INTO USERS(id,name, login, status, password) VALUES (3,'Client1', 'Client2', 'CLIENT','pass');


INSERT INTO REQUESTS (id, id_user, status, dates, date_of_send) VALUES (0,1, 'CLOSED', 'ПН % ВТ % СР','2020-5-31');
INSERT INTO REQUESTS (id, id_user, status, dates, date_of_send) VALUES (1,2, 'CLOSED', 'ПН % ВТ % СР','2020-5-31');
INSERT INTO REQUESTS (id, id_user, status, dates, date_of_send) VALUES (2,3, 'NEW', 'ПН % ВТ','2020-6-10');


INSERT INTO LESSONS (id,id_teacher, theme, commentary, status, date_of_lesson) VALUES (0,1, 'Тема', 'Комментарий', 'OPEN', '2020-6-10');


INSERT INTO CLIENTS_ON_LESSONS (id, id_client,id_lesson) VALUES (0,2,0);

INSERT INTO COMPLAINTS (id,id_incedent,theme,text) VALUES (0,0, 'vse', 'norm');

INSERT INTO NOTIFICATIONS (id, id_from, id_to, status, theme,text ) VALUES (0,0,1,'NEW','ZP','GOOD');


















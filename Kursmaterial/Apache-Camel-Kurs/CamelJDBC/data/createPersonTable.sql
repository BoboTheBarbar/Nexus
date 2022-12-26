DROP TABLE Person;

CREATE TABLE Person
(id integer NOT NULL GENERATED ALWAYS AS IDENTITY,
name varchar(30),
password varchar(30),
PRIMARY KEY (id));
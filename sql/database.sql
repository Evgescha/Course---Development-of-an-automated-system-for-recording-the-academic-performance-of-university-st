DROP DATABASE IF EXISTS PerformanceStudents;
CREATE DATABASE PerformanceStudents;
USE PerformanceStudents;

CREATE TABLE groupM (
  id int(11) NOT NULL AUTO_INCREMENT,
  name varchar(128) NOT NULL,  
  PRIMARY KEY (id)
);

CREATE TABLE teacherM (
  id int(11) NOT NULL AUTO_INCREMENT,
  name varchar(128) NOT NULL,  
  PRIMARY KEY (id)
);

CREATE TABLE studentM (
  id int(11) NOT NULL AUTO_INCREMENT,
  name varchar(128) NOT NULL,  
  groupM int NOT NULL,
  PRIMARY KEY (id),
  FOREIGN KEY(groupM) REFERENCES groupM(id)
);

CREATE TABLE subjectM (
  id int(11) NOT NULL AUTO_INCREMENT,
  name varchar(128) NOT NULL,  
  teacherM int NOT NULL,
  PRIMARY KEY (id),
  FOREIGN KEY(teacherM) REFERENCES teacherM(id)
);


CREATE TABLE scoreM (
  id int(11) NOT NULL AUTO_INCREMENT,
  studentM int NOT NULL,
  subjectM int NOT NULL,
  score int NOT NULL,
  semestr int NOT NULL,
  PRIMARY KEY (id),
  FOREIGN KEY(studentM) REFERENCES studentM(id),
  FOREIGN KEY(subjectM) REFERENCES subjectM(id)
);



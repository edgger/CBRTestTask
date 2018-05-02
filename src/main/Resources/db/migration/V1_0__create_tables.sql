CREATE TABLE positions (
  id   INT GENERATED BY DEFAULT AS IDENTITY (START WITH 1000) NOT NULL PRIMARY KEY,
  name VARCHAR(255)  NOT NULL UNIQUE
);

CREATE TABLE employees (
  id          INT GENERATED BY DEFAULT AS IDENTITY (START WITH 1000) NOT NULL PRIMARY KEY,
  first_name  VARCHAR(255) NOT NULL,
  middle_name VARCHAR(255) NOT NULL,
  last_name   VARCHAR(255) NOT NULL,
  position_id INT          NOT NULL,
  FOREIGN KEY (position_id) REFERENCES positions (id)
);

CREATE TABLE absences (
  id           INT GENERATED BY DEFAULT AS IDENTITY (START WITH 1000) NOT NULL PRIMARY KEY,
  date         DATE         NOT NULL,
  time_minutes SMALLINT     NOT NULL,
  reason       VARCHAR(255) NOT NULL,
  employee_id  INT          NOT NULL,
  FOREIGN KEY (employee_id) REFERENCES employees (id) ON DELETE CASCADE
);
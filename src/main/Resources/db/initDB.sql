DROP TABLE absences
IF EXISTS;
DROP TABLE employees
IF EXISTS;
DROP TABLE positions
IF EXISTS;

CREATE TABLE positions (
  id   INTEGER GENERATED BY DEFAULT AS IDENTITY (START WITH 1000) PRIMARY KEY,
  name VARCHAR(255) NOT NULL UNIQUE
);

CREATE TABLE employees (
  id          INTEGER GENERATED BY DEFAULT AS IDENTITY (START WITH 1000) PRIMARY KEY,
  first_name  VARCHAR(255) NOT NULL,
  middle_name VARCHAR(255) NOT NULL,
  last_name   VARCHAR(255) NOT NULL,
  position_id INTEGER     NOT NULL,
  FOREIGN KEY (position_id) REFERENCES positions (id)
);
CREATE UNIQUE INDEX staff_unique_fml_idx
  ON employees (first_name, middle_name, last_name);

CREATE TABLE absences (
  id           INTEGER GENERATED BY DEFAULT AS IDENTITY (START WITH 1000) PRIMARY KEY,
  date         DATE         NOT NULL,
  time_minutes SMALLINT     NOT NULL,
  reason       VARCHAR(255) NOT NULL,
  employee_id  INTEGER      NOT NULL,
  FOREIGN KEY (employee_id) REFERENCES employees (id)
    ON DELETE CASCADE
);

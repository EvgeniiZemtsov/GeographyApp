CREATE TABLE Cities
(
  city_id    SERIAL,
  name       VARCHAR(30) NOT NULL,
  country_id INT         NOT NULL,
  area       INT         NULL    ,
  population INT         NULL    ,
  PRIMARY KEY (city_id)
);

CREATE TABLE Continents
(
  continent_id SERIAL,
  name         VARCHAR(20) NOT NULL,
  area         INT         NOT NULL,
  PRIMARY KEY (continent_id)
);

CREATE TABLE Country_general
(
  country_id       SERIAL,
  name             VARCHAR(50) NOT NULL,
  population       INT         NOT NULL,
  area             INT         NOT NULL,
  currency_id      INT         NULL    ,
  head_of_state_id INT         NULL    ,
  language_id      INT         NULL    ,
  continent_id     INT         NOT NULL,
  PRIMARY KEY (country_id)
);

CREATE TABLE Currency
(
  currency_id   SERIAL,
  name          VARCHAR(45) NOT NULL,
  currency_code VARCHAR(3)  NOT NULL,
  PRIMARY KEY (currency_id)
);

CREATE TABLE Current_heads_of_states
(
  head_of_state_id SERIAL,
  first_name       VARCHAR(50) NOT NULL,
  last_name        VARCHAR(50) NOT NULL,
  date_of_birth    DATE        NULL    ,
  title            VARCHAR(20) NULL    ,
  PRIMARY KEY (head_of_state_id)
);

CREATE TABLE Languages
(
  language_id               SERIAL,
  name                      VARCHAR(50) NOT NULL,
  number_of_native_speakers INT         NULL    ,
  PRIMARY KEY (language_id)
);

INSERT INTO Languages (name, number_of_native_speakers)
VALUES ('French', 267000000);

INSERT INTO Continents (name, area)
VALUES ('Europe', 22134900);

INSERT INTO Currency (name, currency_code)
VALUES ('Euro', 'EUR');

insert into Current_heads_of_states (head_of_state_id, first_name, last_name, date_of_birth, title) values (1, 'Emmanuel', 'Macron', '1977-04-02', 'President');

insert into Cities (city_id, name, country_id, area, population) values (1, 'Paris', 1, 22564, 9030481);

insert into Country_general (country_id, name, population, area, currency_id, head_of_state_id, language_id, continent_id) values (1, 'France', 42271314, 331691, 1, 1, 1, 1);
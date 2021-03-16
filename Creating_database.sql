CREATE DATABASE Geography;
       
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

CREATE TABLE Country_capital
(
  country_id INT NOT NULL,
  city_id    INT NOT NULL
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

CREATE TABLE Country_mountain
(
  country_id  INT NOT NULL,
  mountain_id INT NOT NULL
);

CREATE TABLE Country_ocean
(
  country_id INT NOT NULL,
  ocean_id   INT NOT NULL
);

CREATE TABLE Country_sea
(
  country_id INT NOT NULL,
  sea_id     INT NOT NULL
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

CREATE TABLE Mountains
(
  mountain_id SERIAL,
  name        VARCHAR(30) NOT NULL,
  height      INT         NOT NULL,
  PRIMARY KEY (mountain_id)
);

CREATE TABLE Oceans
(
  ocean_id  SERIAL,
  name      VARCHAR(15) NOT NULL,
  area      INT         NOT NULL,
  max_depth INT         NOT NULL,
  PRIMARY KEY (ocean_id)
);

CREATE TABLE Seas
(
  sea_id    SERIAL,
  name      VARCHAR(20) NOT NULL,
  area      INT         NOT NULL,
  max_depth INT         NULL    ,
  PRIMARY KEY (sea_id)
);

CREATE TABLE Sights
(
  sight_id SERIAL,
  name     VARCHAR(150) NOT NULL,
  city_id  INT          NULL    ,
  PRIMARY KEY (sight_id)
);

ALTER TABLE Country_general
  ADD CONSTRAINT FK_Languages_TO_Country_general
    FOREIGN KEY (language_id)
    REFERENCES Languages (language_id);

ALTER TABLE Country_general
  ADD CONSTRAINT FK_Currency_TO_Country_general
    FOREIGN KEY (currency_id)
    REFERENCES Currency (currency_id);

ALTER TABLE Sights
  ADD CONSTRAINT FK_Cities_TO_Sights
    FOREIGN KEY (city_id)
    REFERENCES Cities (city_id);

ALTER TABLE Country_general
  ADD CONSTRAINT FK_Current_heads_of_states_TO_Country_general
    FOREIGN KEY (head_of_state_id)
    REFERENCES Current_heads_of_states (head_of_state_id);

ALTER TABLE Cities
  ADD CONSTRAINT FK_Country_general_TO_Cities
    FOREIGN KEY (country_id)
    REFERENCES Country_general (country_id);

ALTER TABLE Country_general
  ADD CONSTRAINT FK_Continents_TO_Country_general
    FOREIGN KEY (continent_id)
    REFERENCES Continents (continent_id);

ALTER TABLE Country_sea
  ADD CONSTRAINT FK_Country_general_TO_Country_sea
    FOREIGN KEY (country_id)
    REFERENCES Country_general (country_id);

ALTER TABLE Country_sea
  ADD CONSTRAINT FK_Seas_TO_Country_sea
    FOREIGN KEY (sea_id)
    REFERENCES Seas (sea_id);

ALTER TABLE Country_ocean
  ADD CONSTRAINT FK_Country_general_TO_Country_ocean
    FOREIGN KEY (country_id)
    REFERENCES Country_general (country_id);

ALTER TABLE Country_ocean
  ADD CONSTRAINT FK_Oceans_TO_Country_ocean
    FOREIGN KEY (ocean_id)
    REFERENCES Oceans (ocean_id);

ALTER TABLE Country_mountain
  ADD CONSTRAINT FK_Country_general_TO_Country_mountain
    FOREIGN KEY (country_id)
    REFERENCES Country_general (country_id);

ALTER TABLE Country_mountain
  ADD CONSTRAINT FK_Mountains_TO_Country_mountain
    FOREIGN KEY (mountain_id)
    REFERENCES Mountains (mountain_id);

ALTER TABLE Country_capital
  ADD CONSTRAINT FK_Country_general_TO_Country_capital
    FOREIGN KEY (country_id)
    REFERENCES Country_general (country_id);

ALTER TABLE Country_capital
  ADD CONSTRAINT FK_Cities_TO_Country_capital
    FOREIGN KEY (city_id)
    REFERENCES Cities (city_id);

      
use master
go

DROP DATABASE IF EXISTS ERaceway
GO

CREATE DATABASE ERaceway
GO

USE ERaceway
GO
CREATE TABLE Driver (
	DriverID smallint IDENTITY(101,1) NOT NULL PRIMARY KEY,
	FirstName nvarchar(30) NOT NULL,
	LastName nvarchar(30) NOT NULL,
	BirthDate Date NOT NULL,
	Age AS DateDiff(YY, BirthDate, GetDate()),
	Wins smallint NULL,
	Crashes smallint NULL
)
GO

CREATE TABLE Car (
	CarID smallint IDENTITY(1001,1) NOT NULL PRIMARY KEY, 
	CarMake nvarchar(30) NOT NULL, 
	CarModel nvarchar(30) NOT NULL, 
	CarYear smallint NOT NULL, 
	CarColor nvarchar(30) NULL, 
	CarName nvarchar(30) NULL, 
	DriverID smallint NOT NULL FOREIGN KEY REFERENCES Driver(DriverID)
)
GO

CREATE TABLE Race (
	RaceID int IDENTITY(202101, 1) NOT NULL PRIMARY KEY,
	RaceName nvarchar(30) NOT NULL,
	RaceLocation nvarchar(30) NOT NULL
)
GO

CREATE TABLE RaceWinner (
	RaceWinnerID smallint IDENTITY(1,1) NOT NULL PRIMARY KEY,
	RaceDate date NOT NULL,
	RaceID int NOT NULL,
	DriverID smallint NOT NULL
)
GO

CREATE TABLE Sponsor (
	SponsorID smallint IDENTITY(1,1) NOT NULL PRIMARY KEY,
	SponsorName nvarchar(60) NOT NULL
)
GO

CREATE TABLE SponsorDriver (
	SponsorID smallint NOT NULL,
	DriverID smallint NOT NULL,
	PRIMARY KEY (
		SponsorID,
		DriverID
	)
)
GO

-- Add Foreign Keys

ALTER TABLE RaceWinner
	ADD CONSTRAINT FK_RaceWinner_Driver -- name consists of FK_ThisTable-LinkingTable
	FOREIGN KEY (DriverID)
	REFERENCES Driver(DriverID)
GO

ALTER TABLE RaceWinner
	ADD CONSTRAINT FK_RaceWinner_Race -- name consists of FK_ThisTable-LinkingTable
	FOREIGN KEY (RaceID)
	REFERENCES Race(RaceID)
GO

ALTER TABLE SponsorDriver
	ADD CONSTRAINT FK_SponsorDriver_Driver
	FOREIGN KEY (DriverID)
	REFERENCES Driver(DriverID)
GO

ALTER TABLE SponsorDriver
	ADD CONSTRAINT FK_SponsorDriver_Sponsor
	FOREIGN KEY (SponsorID)
	REFERENCES Sponsor(SponsorID)
GO

-- Add Data to our database
--Driver Table
insert Driver (FirstName, LastName, BirthDate, Crashes, Wins)
	values ('Aryton', 'Senna', '1960-03-21', 2, 1)
	, ('Lewis', 'Hamilton', '1985-07-01', NULL, 2)
	, ('Valentino', 'Rossi', '1979-02-16', NULL, NULL)
	, ('Danica', 'Patrick', '1982-03-25', 1, 1)
	, ('Travis', 'Pastrana', '1983-10-08', 2,1)
	, ('Mario', 'Andretti', '1940-02-28', 2, NULL)
	, ('Collete', 'Davis', '1993-12-27', NULL, 1)
	, ('Ken', 'Block', '1967-11-21', 1, 1)
	, ('Michele', 'Mouton', '1951-06-23', NULL, 2)
	, ('Adam', 'LZ', '1995-05-05', 1, 1)


--Car Table
SET IDENTITY_INSERT Car ON
INSERT INTO Car (CarID, CarMake, CarModel, CarYear, CarColor, CarName, DriverID)
	VALUES (1001, 'Lotus', 'Evija', 2020, 'Blue', 'Pistola', 101)
INSERT INTO Car (CarID, CarMake, CarModel, CarYear, CarColor, CarName, DriverID)
	VALUES (1002, 'Audi', 'E-Tron GTR', 2022, 'Black', 'Sparks', 102)
INSERT INTO Car (CarID, CarMake, CarModel, CarYear, CarColor, CarName, DriverID)
	VALUES (1003, 'Aston Martin', 'Valhalla', 2022, 'Green', 'High Side', 103)
INSERT INTO Car (CarID, CarMake, CarModel, CarYear, CarColor, CarName, DriverID)
	VALUES (1004, 'Pininfarina', 'Battista', 2020, 'Black', 'Empower', 104)
INSERT INTO Car (CarID, CarMake, CarModel, CarYear, CarColor, CarName, DriverID)
	VALUES (1005, 'Drako', 'GTE', 2019, 'Black', 'Nitro', 105)
INSERT INTO Car (CarID, CarMake, CarModel, CarYear, CarColor, CarName, DriverID)
	VALUES (1006, 'Rimac', 'C_Two', 2019, 'White', 'Shiver', 106)
INSERT INTO Car (CarID, CarMake, CarModel, CarYear, CarColor, CarName, DriverID)
	VALUES (1007, 'Dendrobium', 'D-1', 2020, 'White', 'Neon Flash', 107)
INSERT INTO Car (CarID, CarMake, CarModel, CarYear, CarColor, CarName, DriverID)
	VALUES (1008, 'Aspark', 'Owl', 2019, 'Yellow', 'Hoonigan', 108)
INSERT INTO Car (CarID, CarMake, CarModel, CarYear, CarColor, CarName, DriverID)
	VALUES (1009, 'Tesla', 'Roadster', 2018, 'Red', 'Rev-Limiter', 109)
INSERT INTO Car (CarID, CarMake, CarModel, CarYear, CarColor, CarName, DriverID)
	VALUES (1010, 'Nio', 'EP9', 2020, 'Silver', 'Two-step', 110)
SET IDENTITY_INSERT Car OFF
GO

--Race Table
INSERT Race (RaceName, RaceLocation)
	VALUES ('Cactus Cup', 'Arizona'),
	('Salt Cup', 'Utah'),
	('Pineapple Cup', 'Hawaii'),
	('Grapes Cup', 'California'),
	('BBQ Cup', 'Texas'),
	('Big Apple Cup', 'New York'),
	('Redwoods Cup', 'California'),
	('Wildflower Cup', 'Oregon'),
	('Arch Cup', 'Missouri'),
	('Potato Cup', 'Idaho')
		
GO

-- RaceWinner Table
INSERT RaceWinner(RaceDate, RaceID, DriverID)
	VALUES (CAST('2021-01-01' AS Date), 202101, 104),
		('2021-01-20', 202102, 108),
		('2021-02-13', 202103, 105)
GO

--Sponsor Table
INSERT Sponsor (SponsorName)
	VALUES ('Coke'),
	('Doritos'),
	('Dell'),
	('LG Energy'),
	('GoDaddy'),
	('Red Bull'),
	('Energizer'),
	('Duracell'),
	('House of PRIX'),
	('Gymkana'),
	('Tesla'),
	('EverReady'),
	('LZMFG')
GO

-- SponsorDriver
SET IDENTITY_INSERT Sponsor ON
INSERT SponsorDriver (SponsorID, DriverID)
	VALUES (1, 101)
	, (1,106)
	, (2, 101)
	, (2, 108)
	, (3, 102)
	, (4, 102)
	, (5, 104)
	, (6, 105)
	, (6, 109)
	, (7, 106)
	, (8, 107)
	, (9, 107)
	, (10, 108)
	, (11, 109)
	, (12, 110)
	, (13, 110)
SET IDENTITY_INSERT Sponsor OFF
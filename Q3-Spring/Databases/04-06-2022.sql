/*
-- DDL - Data Definition Language
	- Create
	- Alter
	- Drop
-- DML - Data Manipulation Language
	- C - CREATE - INSERT
	- R - READ - SELECT
	- U - UPDATE - UPDATE
	- D - DELETE - DROP
-- DCL - Data Control Language
	- Security
	- Permission
	- Grant
	- Deny
	- Revoke
*/




-- Allow all car types not just electric
-- SuperCarRaceSeries
use master
go

-- ALTER DATABASE Eraceway
--	MODIFY NAME = SuperCarRaceSeries
--GO

use SuperCarRaceSeries
GO

ALTER TABLE Car
	ALTER COLUMN DriverID smallint NULL
GO

INSERT INTO Car (CarYear, CarColor, CarMake, CarModel)
VALUES (2021, 'Blue', 'BMW', 'M1')
	, (2021, 'White', 'Aston Martin', 'Valhalla')
--	, (2020, 'Black', 'Aston Martin', )


-- Car Numbers
ALTER TABLE Car
	ADD CarNumber smallint NULL
GO

-- Update car numbers
UPDATE Car
	Set CarNumber = 101
	WHERE CarID = 1001
GO

UPDATE Car
	Set CarNumber = 188
	WHERE CarID = 1003
GO

select * from Car
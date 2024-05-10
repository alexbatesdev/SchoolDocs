----------------------------------------------
------------- DATABASE SETUP -----------------
----------------------------------------------

/*
You need to download the data files and create your Average Price Database before 
answering the questions found in the Group and Individual files for this assignment.

Download and review all the files from http://download.bls.gov/pub/time.series/ap/

Part 1: The SQL script below creates the AveragePrice database and adds the necessary tables. 
Part 2: You will need to edit the end of the script to insert all the AP data files 
EXCEPT ap.txt and ap.contacts. Also ensure each table has a primary key.
*/


--------------------------------------------
-- Part 1: Create the Database and Tables --
--------------------------------------------

Use Master
GO

DROP DATABASE IF EXISTS AveragePrice
GO

CREATE DATABASE AveragePrice
GO

USE AveragePrice
GO

SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO

CREATE TABLE dbo.series(
	series_id varchar(17) NULL,
	area_code varchar(4) NULL,
	item_code varchar(9) NULL,
	series_title varchar(200) NULL,
	footnote_codes varchar(10) NULL,
	begin_year smallint NULL,
	begin_period varchar(3) NULL,
	end_year smallint NULL,
	end_period varchar(3) NULL
) 

CREATE TABLE dbo.period(
	period varchar(3) NULL,
	period_abbr varchar(5) NULL,
	period_name varchar(14) NULL
) ON [PRIMARY]

CREATE TABLE dbo.item(
	item_code varchar(9) NULL,
	item_name varchar(89) NULL
) ON [PRIMARY]

CREATE TABLE dbo.household_fuels(
	series_id varchar(17) NULL,
	year smallint NULL,
	period varchar(3) NULL,
	value varchar(50) NULL,
	footnote_codes varchar(10) NULL
) ON [PRIMARY]

CREATE TABLE dbo.gasoline(
	series_id varchar(17) NULL,
	year smallint NULL,
	period varchar(3) NULL,
	value varchar(50) NULL,
	footnote_codes varchar(10) NULL
) ON [PRIMARY]

CREATE TABLE dbo.footnote(
	footnote_code varchar(10) NULL,
	footnote_text varchar(50) NULL
) ON [PRIMARY]

CREATE TABLE dbo.food(
	series_id varchar(50) NULL,
	year varchar(50) NULL,
	period varchar(50) NULL,
	value varchar(50) NULL,
	footnote_codes varchar(50) NULL
) ON [PRIMARY]

CREATE TABLE [current](
	series_id varchar(17) NULL,
	year smallint NULL,
	period varchar(3) NULL,
	value varchar(50) NULL,
	footnote_codes varchar(10) NULL
) ON [PRIMARY]

CREATE TABLE dbo.area(
	area_code varchar(4) NULL,
	area_name varchar(300) NULL
) ON [PRIMARY]

CREATE TABLE dbo.seasonal(
	seasonal_code varchar(2) NULL,
	seasonal_text varchar(25) NULL,
) ON [PRIMARY]
GO


-------------------------------------------------------
-- Part 2: Load Data into the Average Price Database --
-------------------------------------------------------

-- Follow the examples below to insert the data from the remaining data files
-- that you downloaded from: http://download.bls.gov/pub/time.series/ap/.  
-- Import all tables EXCEPT ap.contacts and ap.txt.


BULK INSERT Area FROM 'C:\Users\mbates\OneDrive\School Docs\Q3-Spring\Databases\Download\ap.area' WITH (FIRSTROW = 2)
BULK INSERT [Current] FROM 'C:\Users\mbates\OneDrive\School Docs\Q3-Spring\Databases\Download\ap.data.0.Current' WITH (FIRSTROW = 2)
BULK INSERT HouseHold_Fuels FROM 'C:\Users\mbates\OneDrive\School Docs\Q3-Spring\Databases\Download\ap.data.1.HouseholdFuels' WITH (FIRSTROW = 2)
BULK INSERT Gasoline FROM 'C:\Users\mbates\OneDrive\School Docs\Q3-Spring\Databases\Download\ap.data.2.Gasoline' WITH (FIRSTROW = 2)
BULK INSERT Food FROM 'C:\Users\mbates\OneDrive\School Docs\Q3-Spring\Databases\Download\ap.data.3.Food' WITH (FIRSTROW = 2)
BULK INSERT Footnote FROM 'C:\Users\mbates\OneDrive\School Docs\Q3-Spring\Databases\Download\ap.footnote' WITH (FIRSTROW = 2)
BULK INSERT Item FROM 'C:\Users\mbates\OneDrive\School Docs\Q3-Spring\Databases\Download\ap.item' WITH (FIRSTROW = 2)
BULK INSERT [Period] FROM 'C:\Users\mbates\OneDrive\School Docs\Q3-Spring\Databases\Download\ap.period' WITH (FIRSTROW = 2)
BULK INSERT Seasonal FROM 'C:\Users\mbates\OneDrive\School Docs\Q3-Spring\Databases\Download\ap.seasonal' WITH (FIRSTROW = 2)
BULK INSERT Series FROM 'C:\Users\mbates\OneDrive\School Docs\Q3-Spring\Databases\Download\ap.series' WITH (FIRSTROW = 2)

-- Examples:
-- BULK INSERT period FROM 'c:\ap\ap.period' WITH (FIRSTROW = 2)
-- BULK INSERT Current FROM 'c:\ap\ap.data.0.Current' WITH (FIRSTROW = 2)

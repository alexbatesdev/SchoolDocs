-- Switches our "focus" off of CompanyDB so we can delete and then recreate it
USE MASTER
GO
-- Deletes CompanyDB if it exists
DROP DATABASE IF EXISTS CompanyDB
GO
-- Creates CompanyDB
CREATE DATABASE CompanyDB
GO
-- Switches our "focus" back to CompanyDB
USE CompanyDB
GO


-- Make tables
CREATE TABLE Department
	( DepartmentID smallint IDENTITY(01,1) NOT NULL PRIMARY KEY
	, Title nvarchar(30) NOT NULL
	, DepartmentHead smallint NULL -- Department Manager
	)

CREATE TABLE Employee
	( EmployeeID smallint IDENTITY(001,1) NOT NULL PRIMARY KEY
	, FirstName nvarchar(30) NOT NULL
	, LastName nvarchar(30) NOT NULL
	, DOB Date NOT NULL
	, Position nvarchar(120) NULL
	, Wage float NULL
	, DepartmentID smallint NULL foreign key references Department(DepartmentID)
	, ReportsTo smallint NOT NULL foreign key references Employee(EmployeeID) -- CEO does not report to anyone, is it possible to link this ReportsTo back to the CEO's EmployeeID?
	)

CREATE TABLE Project
	( ProjectID int IDENTITY(0001,1) NOT NULL PRIMARY KEY
	, Title nvarchar(30) NOT NULL
	, DepartmentID smallint NOT NULL foreign key references Department(DepartmentID)
	, ProjectLead smallint NOT NULL foreign key references Employee(EmployeeID)
	)

CREATE TABLE Task
	( TaskID int IDENTITY(00001,1) NOT NULL PRIMARY KEY
	, Title nvarchar(30) NOT NULL
	, ProjectID int NOT NULL foreign key references Project(ProjectID)
	, AssignedTo smallint NOT NULL foreign key references Employee(EmployeeID)
	)

CREATE TABLE ProjectTask
	( ProjectID int NOT NULL foreign key references Project(ProjectID)
	, TaskID int NOT NULL foreign key references Task(TaskID)
	, PRIMARY KEY (
		ProjectID,
		TaskID
		)
	)

CREATE TABLE EmployeeProjectTime 
	( logID int IDENTITY(109001,1) NOT NULL PRIMARY KEY
	, EmployeeID smallint NOT NULL foreign key references Employee(EmployeeID)
	, startTime as (DATEDIFF(SECOND,'1970-01-01', GETUTCDATE()))
	, WorkTime float NOT NULL
	)

-- Adding the foreign key for the Department Manager
ALTER TABLE Department
	ADD CONSTRAINT FK_Department_Employee
	FOREIGN KEY (DepartmentHead)
	REFERENCES Employee(EmployeeID)
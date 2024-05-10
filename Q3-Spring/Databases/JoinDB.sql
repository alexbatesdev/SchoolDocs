USE master
GO

DROP DATABASE IF EXISTS JoinDB
GO

CREATE DATABASE JoinDB
GO

USE JoinDB
GO

CREATE TABLE Employee 
(
        EmployeeId INT IDENTITY(101, 1) NOT NULL PRIMARY KEY,
        FirstName VARCHAR(20) NOT NULL,
        LastName VARCHAR(30) NOT NULL,
        State CHAR(2) NOT NULL
)
GO

CREATE TABLE Task
(
        TaskId INT IDENTITY(1,1) NOT NULL PRIMARY KEY,
        EmployeeId INT NULL FOREIGN KEY REFERENCES Employee(EmployeeId),
        TaskName VARCHAR(100) NOT NULL,
        EstimatedHours INT NOT NULL DEFAULT 0,
        WorkedHours INT NOT NULL DEFAULT 0
)
GO

SET IDENTITY_INSERT dbo.Employee ON
INSERT INTO Employee(EmployeeId, FirstName, LastName, State) VALUES (101,'Victor', 'Shade', 'VA')
INSERT INTO Employee(EmployeeId, FirstName, LastName, State) VALUES (102,'Stephen', 'Strange', 'NY')
INSERT INTO Employee(EmployeeId, FirstName, LastName, State) VALUES (103,'Bruce', 'Wayne', 'NJ')
INSERT INTO Employee(EmployeeId, FirstName, LastName, State) VALUES (104,'Tony', 'Stark', 'CA')
INSERT INTO Employee(EmployeeId, FirstName, LastName, State) VALUES (105,'Sue', 'Storm', 'NY')
INSERT INTO Employee(EmployeeId, FirstName, LastName, State) VALUES (106,'Steve', 'Rogers', 'DC')
INSERT INTO Employee(EmployeeId, FirstName, LastName, State) VALUES (107,'Jean', 'Grey', 'NY')
INSERT INTO Employee(EmployeeId, FirstName, LastName, State) VALUES (108,'Wanda', 'Maximoff', 'NJ')
INSERT INTO Employee(EmployeeId, FirstName, LastName, State) VALUES (109,'Clark', 'Kent', 'NY')
INSERT INTO Employee(EmployeeId, FirstName, LastName, State) VALUES (110,'Ben', 'Grimm', 'NY')
INSERT INTO Employee(EmployeeId, FirstName, LastName, State) VALUES (111,'Reed', 'Richards', 'NY')
INSERT INTO Employee(EmployeeId, FirstName, LastName, State) VALUES (112,'Johnny', 'Storm', 'NY')
SET IDENTITY_INSERT dbo.Employee OFF

SET IDENTITY_INSERT dbo.Task ON
INSERT INTO TASK (TaskId, EmployeeId, TaskName, EstimatedHours, WorkedHours) VALUES (1, 102, 'Create Website Artwork',32, 24)
INSERT INTO TASK (TaskId, EmployeeId, TaskName, EstimatedHours, WorkedHours) VALUES (2, 106, 'Create Database',40, 42)
INSERT INTO TASK (TaskId, EmployeeId, TaskName, EstimatedHours, WorkedHours) VALUES (3, 103, 'Program API',38, 12)
INSERT INTO TASK (TaskId, EmployeeId, TaskName, EstimatedHours, WorkedHours) VALUES (4, 105, 'Develop Front End',36, 16)
INSERT INTO TASK (TaskId, EmployeeId, TaskName, EstimatedHours, WorkedHours) VALUES (5, NULL, 'User Testing',32, 0)
INSERT INTO TASK (TaskId, EmployeeId, TaskName, EstimatedHours, WorkedHours) VALUES (6, NULL, 'Front-End Testing',36, 0)
INSERT INTO TASK (TaskId, EmployeeId, TaskName, EstimatedHours, WorkedHours) VALUES (7, NULL, 'Create Reports',48, 0)
SET IDENTITY_INSERT dbo.Task OFF

/*****************************************************

			CRUD Assignment

			INSTRUCTIONS

1.  Do all your work in the Northwind and DBT130 databases.

2.  Complete all queries and answers in this file, with each query 
	directly below its question.  Your queries should not be commented out.  Do not delete
	the question text or renumber the questions.  All questions require SQL
	responses unless marked with "English".

3.  Do not paste the query results into this file, unless specifically requested
	in the question.

4.  Submit this file with your answers in Canvas by the deadline as a .sql file.

6.  For clarity: "List" means Select * (unless otherwise specified).

7.  All responses are to be SQL queries unless the question specifies ENGLISH. 
	Plain English answers should be commented out, like these instructions
	or the questions themselves.

8.  There is no need for Group by or Having clauses in any response.

For full credit, there must be no syntax errors in this file.



*****************************************************/
/* 1.1
Create a database called DBT130.
*/
USE MASTER
GO

DROP DATABASE IF EXISTS DBT130
GO

CREATE DATABASE DBT130
GO
/* 2.1
Switch to the new DBT130 database.
*/
USE DBT130
GO
/* 3.1
Add a table called People to the database with the following
columns: first name, last name, date of birth, phone number,
and all the fields necessary for a full US address.  Use 
data types for each field that match and will be able to contain
data for people living in the US.
*/
CREATE TABLE People
	( FirstName nvarchar(30) NOT NULL
	, LastName nvarchar(30) NOT NULL
	, DOB DATE NOT NULL
	, Phone nvarchar(12) NULL
	, Street nvarchar(240) NULL
	, City nvarchar(60) NULL
	, State nvarchar(30) NULL
	, Zipcode int NULL)
GO
/* 3.2
Add two people to the People table, populating all columns
for each person.
*/
insert into People (FirstName, LastName, DOB, Phone, Street, City, State, Zipcode)
VALUES
	( 'Greg'
	, 'Murtins'
	, '09-05-1990'
	, '801-554-2034'
	, '556 E 422 S'
	, 'Payson'
	, 'Utah'
	, 84651)

insert into People (FirstName, LastName, DOB, Phone, Street, City, State, Zipcode)
VALUES
	( 'Phill'
	, 'Sallad'
	, '09-05-1990'
	, '385-225-1525'
	, '715 N Valley Drive'
	, 'Spanish Fork'
	, 'Utah'
	, 846660)
/* 3.3
Return all the people in the People table.
*/
select * from People


/* 3.4
Assume you made a mistake on the zip code for one of the 
people.  Fix the mistake with an update.
*/
update People
	set Zipcode = 84660
	where Zipcode = 846660

/* 3.5
Make sure your update worked by returning the row
for just that person.
*/
select * from People where Zipcode = 84660

/* 3.6
Delete the row you didn't update.
*/
delete from People where FirstName != 'Phill'

/* 3.7
Make sure the row you updated is still there with 
a select.
*/
select * from People

/* 3.8
Delete all the remaining rows (there should be just one) in the 
People table.
*/
delete from People

/* 4.1 ENGLISH
What happens when you run a select on the People table, which
is now empty?  What is returned?  ("Nothing" is not a correct answer.  
Something _is_ returned)
*/
--The columns are returned
/* 4.2
Remove the People table from the DBT130 database.
*/
DROP TABLE People
GO
/* 4.3 ENGLISH
Now try selecting from a table that doesn't exist.  What
is returned?  (Again, "nothing" is not correct)
*/
-- An error is returned
/* 5.1
Change contexts to the Northwind database.
*/
USE Northwind
GO
/* 6.1
Return all the Employees who live in London.
*/
SELECT * FROM Employees WHERE City = 'London'

/* 6.2
Add an Employee to the employees table. (Read the next question
before starting this one... they are related)
*/
INSERT INTO Employees (LastName, FirstName, Title, TitleOfCourtesy, BirthDate, HireDate, Address, City, Region, PostalCode, Country, HomePhone, Extension, Notes, ReportsTo)
	values ('Smith', 'Rob', 'Sales Representative', 'Mr.', 09-16-1990, GETDATE(), '715 N Valley Drive', 'Salt Lake', 'Utah', '84102', 'US', '801-456-8008', '+1', 'Big Stinky', '1')
/* 6.3 ENGLISH
List here any headaches, problems, or specific hoops you had
to jump through to be able to add a new employee.
*/
-- I didn't have any problems? 
-- It just worked
/* 7.1 ENGLISH
What are at least two ways you can discover the table definition,
meaning the data types, key fields, and whether fields allow
nulls or not.
*/
-- EXEC SP_help 'dbo.Employees' 
-- and
-- EXEC SP_columns Employees

/* 8.1 ENGLISH
What are the advantages and disadvantages of requiring a field
in a table to be "Not Null"?
*/
-- It makes things much more complete and consistent, but it makes things difficult if you don't have that info or know how to input it
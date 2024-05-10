USE Northwind
go

-- string concatonation
select FirstName + ' ' + LastName
from Employees

-- Alias
select FirstName + ' ' + LastName AS [Full Name]
from Employees

-- find customer 9
select FirstName + ' ' + LastName AS [Full Name]
from Employees
where EmployeeID != 9


-- find all except customer 7
select FirstName + ' ' + LastName AS [Full Name]
from Employees
where EmployeeID != 7

-- add to table
insert into Employees(LastName, FirstName, Title)
	values ('Smith', 'Rob', 'Sales Rep')
		, ('Samson', 'Robbie', 'Sales Rep')
		, ('Jamison', 'Bob', 'Sales Rep')
		, ('Brunner', 'Roberto', 'Sales Rep')
GO

select * from Employees

--filter using string compare
update Employees
	set Title = 'Sales Representative'
	where Title = 'Sales Rep'

-- filter using range of employee id
update Employees
	set Title = 'Sales Representative'
	where EmployeeID > 9
		and EmployeeID < 14

-- filter using IN
update Employees
	set Title = 'Sales Representative'
	where EmployeeID IN (10,12,13)


-- change hiredate to todays date
update Employees
	set HireDate = getdate()
	where HireDate IS NULL

update Employees
	set FirstName = 'Alex'
where EmployeeID in (10, 11, 12, 13)

select * from Employees


-- delete
delete from Employees
where FirstName = 'Alex'

-- list only specific columns
select CustomerID, companyname, city, phone
from Customers
where City = 'Madrid'

-- how many from madrid
select count(*) AS 'Madrid Customers'
from Customers
where City = 'Madrid'

-- list employee full name and city
select FirstName + ' ' + LastName AS [Full Name], City
from Employees

--Order details
select * from OrderDetails

--calculate the line total for an order
select ProductID
	, UnitPrice
	, Quantity
	, UnitPrice * Quantity AS [Total Price]
from OrderDetails

select ProductID
	, UnitPrice
	, Quantity
	, Discount
	, UnitPrice * Quantity AS [Total Price]
	, (UnitPrice * Quantity) * (1-Discount) AS [Discounted Total]
from OrderDetails


-- customer
select * from Customers

-- list customers from Mexico, Spain, Brazil, Germany
select ContactName, ContactTitle, Country
from Customers
where Country IN ('Mexico', 'Spain', 'Brazil', 'Germany')

-- sort by country
select ContactName, ContactTitle, Country
from Customers
where Country IN ('Mexico', 'Spain', 'Brazil', 'Germany')
order by Country desc
-- descending

-- sort by country descending then title
select ContactName, ContactTitle, Country
from Customers
where Country IN ('Mexico', 'Spain', 'Brazil', 'Germany')
order by Country desc, ContactTitle

-- list number of customers per country
select * from customers

select Country, count(*) AS [count]
from Customers
group by Country

select * 
from customers
where country = 'Germany'

-- list the customers from British Columbia
select *
from customers
where region <> 'BC'
	or region IS NULL

-- Explore some Aggregates in orderdetails

select * from OrderDetails

select orderid
	, SUM(quantity) AS 'SUM_QTY'
	, AVG(quantity) AS 'AVG_QTY'
	, MIN(quantity) AS 'MIN_QTY'
	, MAX(quantity) AS 'MAX_QTY'
from OrderDetails
group by OrderID


--list orders that have an AVG greater than 9
select orderid
	, SUM(quantity) AS 'SUM_QTY'
	, AVG(quantity) AS 'AVG_QTY'
	, MIN(quantity) AS 'MIN_QTY'
	, MAX(quantity) AS 'MAX_QTY'
from OrderDetails
where AVG(quantity) > 9
group by OrderID
-- does not work due to WHERE filtering row by row :(


select orderid
	, SUM(quantity) AS 'SUM_QTY'
	, AVG(quantity) AS 'AVG_QTY'
	, MIN(quantity) AS 'MIN_QTY'
	, MAX(quantity) AS 'MAX_QTY'
from OrderDetails
group by OrderID
having AVG(quantity) > 9
-- HAVING filters aggregates





-- Joins Joins Joins Joins Joins Joins Joins Joins Joins Joins Joins Joins Joins Joins Joins Joins Joins Joins Joins Joins Joins Joins Joins Joins Join
-- intro to Joins
select * from Customers	
select * from Orders

select c.CustomerID, c.CompanyName, c.Country, o.CustomerID, o.OrderDate, o.ShipCountry
from Customers as c
	left join orders as o
	on c.CustomerID = o.CustomerID
group by c.CustomerID, c.CompanyName, c.Country, o.CustomerID, o.OrderDate, o.ShipCountry
use Northwind
go

--union
-- two queries
select ContactName, Phone
from Customers

select concat(TitleOfCourtesy, ' ', FirstName, ' ', LastName) as [Employee Name]
	, HomePhone
from Employees

--linked queries with union
select ContactName, Phone
from Customers
UNION
select concat(TitleOfCourtesy, ' ', FirstName, ' ', LastName) as [Employee Name]
	, HomePhone
from Employees
order by Phone
-- 105 rows
-- sorts list
-- removes duplicates

select country
from Customers
--91 rows
select distinct Country
from Customers
--21 rows

select distinct Country
from Employees
--2 rows

-- UNION
select distinct Country
from Customers
union
select distinct Country
from Employees


-- UNION with duplicates
select distinct Country
from Customers
union ALL
select distinct Country
from Employees


-- INTERSECT
-- include only values that exist in both
select Country 
from Customers
INTERSECT
select Country
from Employees


-- EXCEPT
-- Include only the stuff that is not in both
select Country
from Customers
EXCEPT
select Country
from Employees

-- Aggreates
select * from OrderDetails
-- 2,155 rows

--quantity by order id
select OrderID, count(*)
from OrderDetails
group by OrderID

-- comparing
select * from Customers

select count(*) as [all rows]
	, count(region) as [regions]
from customers
-- does not include nulls

-- unique regions
select count(*) as [all rows]
	, count(region) as [region count]
	, count(distinct Region) as [unique regions]
from customers

-- numeric
select count(quantity) as [QTY]
	, SUM(quantity) as [SUM]
from OrderDetails

-- show all by quantity
select * from OrderDetails
order by Quantity desc

-- show top 5 orders by quantity
select top (5) * from OrderDetails
order by Quantity desc

-- include any ties
select top (5) with ties * 
from OrderDetails
order by Quantity desc


-- show's the quantity of good per orderID
select OrderID, SUM(Quantity)
from OrderDetails
group by OrderID
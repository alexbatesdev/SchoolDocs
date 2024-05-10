v/*
***************************************************
		4C - Aggregates 
		  Instructions
****************************************************

Work individaully, place your answers in this file, save, and submit.

1.  Use the Northwind database

2.  Complete all queries and answers in this file, with each query directly below its question.
The queries should not be commented out.

3.  Do not paste the results of your queries into this file.

4.  Submit this file as .sql with your answers in Canvas by the deadline.  

6.  "List" means Select * (unless otherwise specified), 
	"How many" means "return a number that shows..."

7.  All questions should be answered using a SQL query except those specified with ENGLISH.

8.  No subqueries.

9.  ****** Do not use any of the existing views in the database. ******

** For full credit, there must be no syntax errors in this file
** All queries should not be commented out.  
** Plain English answers should be commented out just like 
	these instructions or the questions themselves.

*****************************************************/
use Northwind
go
-- 1.1 How many orders exist? (Select * From Orders is not the answer!)

select count(*) as [Total Orders] from Orders

-- 1.2 How many orders were placed in 2012?

select count(*) as [Total Orders in 2012] from Orders
where year(OrderDate) = 2012


-- 1.3 How many orders were placed each year?

select year(OrderDate) as [Year], count(*) as [Orders] from Orders
group by year(OrderDate)
order by year(OrderDate)

-- 1.4 SQL and ENGLISH: Is the company's business increasing?
-- Assume we're measuring business growth by the number of 
-- orders placed, and today is 5/6/2014.
-- Show all your SQL work and explain the results in ENGLISH.

select year(OrderDate) as [Year], count(*) as [Orders] from Orders
group by year(OrderDate)
order by year(OrderDate)

--It's the same query? 
--The companies business is decreasing from the previous year, 
--but compared to their first year they are still doing better 
--so 2013 might've just been a spike in popularity and sales are returning back to normal

-- 2.2 List the country names and counts for each country
-- that had least 10 orders placed between 1/1/2014 and 3/1/2014.
-- Use shipcountry in Orders.
-- select * from Orders

select * --ShipCountry, count(*) as [Orders]
from (
select ShipCountry, count(*) as [Orders]
from Orders
where DATEDIFF(DAYOFYEAR, OrderDate, '2014-01-01') < 0
	and DATEDIFF(DAYOFYEAR, OrderDate, '2014-03-01') > 0
group by ShipCountry
) as o
where [Orders] >= 10
order by [Orders] desc
-- 3.1 Show the category name, the number of products, and the average, minimum, and maximum price
-- of products in each category.  (Use Products and Categories tables, not OrderDetails)

select max(CategoryName) as [Category]
	, count(*) as [Products]
	, avg(p.UnitPrice) as [Average Price]
	, min(p.UnitPrice) as [Min Price]
	, max(p.UnitPrice) as [Max Price]
from Categories as c
left join Products as p
	on c.CategoryID = p.CategoryID
group by p.CategoryID

-- 4.1 The OrderDetails table includes the price and quantity for products
-- ordered with each order.  List the order id and the total order
-- dollar amount for each order.  Ignore the discount field.


select OrderID, (Quantity * UnitPrice) as [Order Total Price]
from OrderDetails



-- 4.2 Which categories (by category name) bring in the most revenue for the company? 
-- Sort from highest to lowest total revenue.  

select max(c.CategoryName) as [Category], sum(od.UnitPrice) as [Total Revenue]
from OrderDetails as od
left join Products as p
	on od.ProductID = p.ProductID
left join Categories as c
	on p.CategoryID = c.CategoryID
group by c.CategoryID
order by [Total Revenue] desc

-- 4.3 List each customer id and the total dollar amount ordered for each customer.  
-- Ignore the discount field.
select [CustomerID], sum([Order Sum]) as [Customer Total Spent]
from (
select max(c.CustomerID) as [CustomerID], od.OrderID, sum(od.Quantity * od.UnitPrice) as [Order Sum]
from Customers as c
left join Orders as o
	on c.CustomerID = o.CustomerID
left join OrderDetails as od
	on o.OrderID = od.OrderID
group by od.OrderID
) as os
group by [CustomerID]

-- 5.1 SQL and ENGLISH: Customers call the call center to place their orders.
-- The call center is currently staffed to handle an equal call volume for each day 
-- of the week.  A new business manager has suggested that we reduce the number
-- of agents in the call center by half on Saturdays and Sundays as an employee benefit.
-- Is this plan a good idea?
-- If not, suggest an alternative.
-- Show all your SQL, and **** explain your response in ENGLISH. *****
--select OrderDate, DATEPART(WEEKDAY, OrderDate) as [DayO'Week] from Orders order by OrderDate

select DATEPART(WEEKDAY, OrderDate) as [Day of Week], count(*) as [Orders] from Orders -- 1 on [Day of Week] is Sunday
group by DATEPART(WEEKDAY, OrderDate)
order by [Day of Week]

--The call center should definitely reduce the number of agents on Fridays and Saturdays, they don't get calls those days
--They have never gotten a single order on any Friday or Saturday so they should have those days off

-- 6.1 The company is having a sales contest.  List all of the employee last names and the 
-- number of orders they've placed. Sort the list by order count with the current 
-- sales leaders at the top of the list.

select e.EmployeeID, max(LastName) as [Last Name], count(o.OrderID) as [Orders]
from Employees as e
left join Orders as o
	on o.EmployeeID = e.EmployeeID
group by e.EmployeeID
order by [Orders] desc


-- 6.2  SQL and ENGLISH: Andrew Fuller is upset at the ranking for the sales contest.  
-- He claims that while he has a lower order count, the total value for his orders 
-- is greater than Laura Callahan. Is his claim true?  
-- You must use SQL queries and English to prove your case, and show
-- all your work.  Your response can have multiple queries.  For simplicity, ignore 
-- the discount field in OrderDetails in your order calculation.

select q.[EmployeeID], max(e.LastName) as [Last Name], count(OrderID) as [Orders], sum([Order Value]) as [Revenue]
from (
select max(e.EmployeeID) as [EmployeeID], o.OrderID, sum(od.Quantity * od.UnitPrice) as [Order Value]
from Employees as e
left join Orders as o
	on o.EmployeeID = e.EmployeeID
left join OrderDetails as od
	on o.OrderID = od.OrderID
group by o.OrderID
) as q
left join Employees as e
	on e.EmployeeID = q.[EmployeeID]
group by q.[EmployeeID]
order by [Revenue] desc
-- This query (If the math parts are correct) should show all the revenue brought in from all the orders each Employee has handled
--Andrew was right, looking at total sales instead of total money earned is leaving out inormation

-- 7.1 English (SQL as needed).  Here's a query that demonstrates the difference between counting different columns.
-- Add to the query by completing the following tasks:
--		1. Give aliases that accurately explain the data in each column.
--				For readability, longer column names with spaces are fine.
--		2. In comments for each field, explain what a null in the joined field result represents (if null is possible)
--		____and___ what count() on that field represents.
--		I've completed steps 1 and 2 for the first two columns for you.

--Feel free to write and include extra queries to make sure your answers are correct.
select * from Customers as c
left join Orders as r
	on r.CustomerID = c.CustomerID
Where c.customerId in (1, 6, 10, 15, 22)

Select c.customerId as 'Customer Id', --Primary key of Customers.  Nulls are not possible in this field in the source table.  Count represents the number of orders for this customer, _unless_ they have no orders, then the value 1 is returned representing the customer's row, not the number of orders.
	c.companyname as 'Company Name', --Non-key field, and nulls are not allowed in the source table.  Count represents the number of orders for this customer, _unless_ they have no orders, then the value 1 is returned representing the customer's row, not the number of orders.
	count(*) as [Rows], --This is not a field in the source table, this will never be null and this count represents the amount of entries that were grouped together which also happens to be the amount of order the customer has placed
	count(c.customerId) as [Orders], --In the source table this is a key field and cannot be null, the count of this field represents how many orders the customer has placed. 1 means they have no orders yet
	count(c.region) as [Orders with associated Region], --non-key field, nulls are allowed, this field is the count of orders going somewhere with a region. A null in this field means the country being shipped to does not have regions/states/provinces
	count(r.OrderId) as [Placed Orders], --This is a key field in the source table, this cannot bu null. As a count this is just another way to count how many orders they have, but it if they have zero orders it actually shows a 0 instead of a 1. This is the best way to count the orders
	count(r.shippeddate) as [Shipped Orders] --this is not a key field and can be null, null means we have yet to ship the order but it has been placed.
From Customers c
	Left Join Orders r
	On c.customerId = r.customerId
Where c.customerId in (1, 6, 10, 15, 22)
Group by c.customerId, c.companyname
Order by c.customerId


-- 7.2 English: Summarize what you learned in 7.1, including which field types 
-- you should count when you're joining tables and aggreating results.

/*
I learned that count() generally gives us unhelpful or redundant information unless used right
The field types that should be counted are fieldtypes that don't already represent counts and fieldtypes where there can be nulls
*/
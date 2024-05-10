use Northwind
go


-- Subqueries
-- Query within a query

-- number of orders from customers in the USA
select count(*)
from Customers as c
inner join Orders as o
	on c.CustomerID = o.CustomerID
where c.Country = 'USA'
--122 rows

-- break apart into subquery
-- list customerids from USA
select c.CustomerID
from Customers as c
where c.Country = 'USA'
-- 13 rows

select COUNT(*)
from Orders as o
where CustomerID IN 
	(
		select c.CustomerID
		from Customers as c
		where c.Country = 'USA'
	)
-- 122 rows
-- inner query is not dependant on the outer query
-- Non-Correlated Subquery
-- IN function can be very resource intensive

select COUNT(*)
from Orders as o
where EXISTS
	(
		select c.CustomerID -- Does not match
		from Customers as c
		where c.Country = 'USA'
			and c.CustomerID = o.CustomerID -- moved the filter logic
	)
-- 122 rows


-- Correlated subquery
-- inner query is dependent on information from outer query

-- EXISTS performs better than IN
-- Inner Joins typically faster than Subqueries
-- Not about speed
-- Readability, complexity



-- EXISTS

select customerid
from Customers
where Country = 'USA'
-- 13 rows

-- IN clause - loop through every row
-- EXISTS clause will ask TRUE / FALSE
-- does the calues exist in the list
-- It uses index and cache
-- Exists = "Is there ANY returned rows or NO rows?"

select 'BOB'
from Customers as c
where country = 'USA'
	and c.CustomerID = 43
-- EXISTS = TRUE

select 1
from Customers as c
where country = 'USA'
	and c.CustomerID = 143
-- EXISTS = FALSE

select top(10) *
from orders as o
where exists
	 (
		select 1
		from Customers as c
		where country = 'USA'
			and c.CustomerID = o.CustomerID
	)


-- Subs with Group By
-- create a sorted list of USA customers by the numberof orders they have
-- JOIN First

select c.CompanyName, count(o.OrderID) as [Order Count]
from Customers as c
inner join Orders as o
	on c.CustomerID = o.CustomerID
where Country = 'USA'
group by c.CompanyName
order by [Order Count] desc


-- Subquery version
select c.companyname,
	(
		select count(*)
		from Orders as o
		where o.CustomerID = c.Customerid
	) as [Order Count]
from Customers as c
where c.Country = 'USA'
order by [Order Count] desc
-- inner dependent on outer
-- Correlated subquery


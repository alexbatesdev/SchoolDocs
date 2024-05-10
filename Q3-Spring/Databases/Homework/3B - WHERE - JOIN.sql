/*****************************************************
			
			WHERE - JOIN Assignment

			INSTRUCTIONS

1.  Work individually.  Do all your work in the Northwind database.

2.  Complete all queries and answers in this file, with each query 
	directly below its question.  Your queries should not be commented out.  Do not delete
	the question text or renumber the questions.

3.  Do not paste the query results into this file, unless specifically requested
	in the question.

4.  Submit this file with your answers in Canvas by the deadline as a .sql file.

5.	Use the "On" style join syntax for all questions that require querying multiple tables, unless the 
	question specifically warrants a cross join/cartesian product, then use the
	comma style or cross join syntax.

6.  "List" means Select (* unless otherwise specified).

7.  All responses are to be SQL queries unless the question specifies ENGLISH.

8.  There is no need for Group by or Having clauses in any response.

9.  Do not use any views in any answer (this means no: sales.custorders, 
	sales.emporders, sales.ordertotalsbyyear, sales.ordervalues)

Plain English answers should be commented out, like these instructions
or the questions themselves.

*****************************************************/
use Northwind
go
select * from Orders
/*
1.1 Using AND/OR logic (no "in" syntax), list all the orders that 
were shipped to Utah, Idaho, and Wyoming.  
Include the appropriate columns and display the results
in an appropriate order.
*/
select * from Orders
	where ShipRegion = 'UT'
	or ShipRegion = 'ID'
	or ShipRegion = 'WY'


/*
1.2 Using AND/OR logic (no "in" syntax), list all the orders placed in 2012 
 that were shipped to Utah, Idaho, and Wyoming.  
Include the appropriate columns and display the results
in an appropriate order.
*/
select OrderID, OrderDate, ShipAddress, ShipCity, ShipRegion, ShipPostalCode, ShipCountry from Orders
	where (ShipRegion = 'UT'
	or ShipRegion = 'ID'
	or ShipRegion = 'WY')
	and year(ShippedDate) = 2012

/*
1.3 Using the "in" syntax, list all the orders placed in 2012 
 that were shipped to Utah, Idaho, and Wyoming. 
*/

select * from Orders
	where ShipRegion in('UT','ID','WY')
	and year(ShippedDate) in(2012)

/* 1.4
ENGLISH: Walk me through how to figure out how many rows the 1.5 query 
should return without writing or running 1.5.
Hint, look at the entire orders table and your response to 1.1.
*/
-- We would use the count() function on the query from 1.2 or 1.3
-- Then we take the total amount of entries which we can find by using the count() function on a query that selects every item and subtract the two
-- TotalCount - UTIDWYCount = CountWithoutUTIDWY
/*
1.5 List all the orders except those shipped to Utah, Idaho, and Wyoming.

(Think: Does your row count match what you anticipated in 1.4?  If not, why?
Make sure your query and its output are correct)
*/
select * from Orders
	where (ShipRegion != 'UT'
	and ShipRegion != 'ID'
	and ShipRegion != 'WY')
	or ShipRegion is null
/*
2.1 ENGLISH: If every employee sold one order to every 
customer, how many orders would result?  
Show your math and explain.
*/
select count(*) from Employees --27
select count(*) from Customers --91
-- If every employee sold one order to every customer we would have 27 * 91 orders which is 2,457 orders
/*
2.2 Write the query that returns all the possible combinations 
of employees and customers.
*/
select * from Employees 
cross join Customers

/*
3.1 List all the orders placed by the employee with the last name of King.  
No hardcoding of King's employeeId in your query.  
Specifying the last name 'King' is ok.  (No subqueries!)
*/
select * from Orders as O inner join Employees as E
	on E.EmployeeID = O.EmployeeID
	where E.LastName = 'King'

/*
4.1 Using orders, customers, and employees tables, select the Order Id, 
the Order Date, the customer company name and the employee 
last name for each order
*/
select o.OrderID, O.OrderDate, C.CompanyName, E.LastName
from orders as o
left join customers as c
	on o.CustomerID = c.CustomerID
left join Employees as e
	on o.EmployeeID = e.EmployeeID

/*
5.1 List the customers who have not placed orders.
*/
select C.CompanyName, O.OrderID
from Orders as O right outer join Customers as C
	on O.CustomerID = C.CustomerID
where O.OrderID is null
order by c.CompanyName


/*
6.1 List each supplier who has at least one discontinued product.  
Do not repeat suppliers in the results.
*/
select distinct s.SupplierID, s.CompanyName
from Suppliers as s
left join Products as p
	on s.SupplierID = p.SupplierID
where p.Discontinued = 1
order by s.SupplierID

/*
7.1 List the employee name, employee title, manager name, 
and manager title for each employee.  Use  clear column 
aliases for your results, and do not exclude any employees in your list.
*/

select e.FirstName + ' ' + e.LastName as [Name], e.Title,  m.FirstName + ' ' + m.LastName as [Manager], m.
from Employees as e
left join Employees as m
	on e.ReportsTo = m.EmployeeID


/*
8.1 List the product name and price history for Product Id 42 
in chronological order. "Price history" represents how the 
price of a product has changed over time.
*/
select distinct p.ProductName, od.UnitPrice
from OrderDetails as od
left join Products as p
	on od.ProductID = p.ProductID
left join Orders as o
	on od.OrderID = o.OrderID
where p.ProductID = 42

/*
Extra Credit (5 points) Look at the region column on the customers table.  
It's populated on some rows but not on others.  Does the country 
determine whether region is populated?  Write a single query that 
determines whether there are any countries who's region is specified
on some rows but not on others.
*/
/*
***************************************************
		4D - Subqueries 
		   Instructions
****************************************************

1.  Work individually.

2.  Do all your work in the Northwind database

3.  Complete all queries and answers in this file, with each query 
	directly below its question.  Your queries should not be commented out.  Do not delete
	the question text or renumber the questions.

4.  Do not paste the results of your queries into this file.

5.  Submit this file with your answers in Canvas by the deadline as a .sql file.

6.	Use subqueries for all questions as necessary.  
	NOTE: A "Derived Table" refers to a subquery used in the FROM clause.

7.  For clarity: "List" means SELECT * (unless otherwise specified), 
	"How many" means "return a number that shows..."

8.  All responses are to be SQL queries unless the question specifies ENGLISH.

For full credit, there must be no syntax errors in this file, 
meaning you are able to run all the queries at once (F5 with no text selected).  
Plain English answers should be commented out, like these instructions
or the questions themselves.

*****************************************************/


USE Northwind
GO

--1.1 Use a subquery on the where clause to return all the orders
--for customers who live in Poland (there happens to be just one customer).

select *
from Orders
where CustomerID = (
select CustomerID from Customers
where Country = 'Poland'
)

--1.2 Paste your query from 1.1 here and change it to return the orders
--for those customers who live in Sweden.

select *
from Orders
where CustomerID in (
select CustomerID from Customers
where Country = 'Sweden'
)

--1.3 ENGLISH: You're assigned to write a query like 1.1 to be used in a
--production environment for _any_ country (country will be supplied as a variable).
--Explain _why_ you should use "=" or "in" in your query.  
/*
You should use the "in" function because "=" only works if your subquery returns a single value
"=" is for 1-to-1 comparisons, "in" is for 1-to-many comparisons
*/
--1.4 Use a subquery on the where clause to return all the orders
--for any customer whose contact's last name is Ottlieb.  

select * from Orders
where customerID = (
select CustomerID from Customers
where ContactName like '%Ottlieb%'
)

--2.1 Return all rows and columns from products, with the average order quantity for each product.
--Put your subquery in the Select.  Do not use a derived table or a join.
select *,
	(
		select avg(Quantity) as [Orders]
		from OrderDetails as o
		where o.ProductID = p.ProductID
		group by ProductID
	) as [Average Quantity]
from Products as p


--2.2 ENGLISH: Must the previous query have a group by clause?  Explain your response.

--Apparently not, but I used it 

--2.3 Paste your query from 2.1 here, and modify just the subquery to exclude the average order quantity for 
--productId 1.

select *,
	(
		select avg(Quantity) as [Orders]
		from OrderDetails as o
		where o.ProductID = p.ProductID
		and ProductID != 1
		group by ProductID
	) as [Average Quantity]
from Products as p


--2.4 ENGLISH: What is the content of the "average quantity ordered" column for
--productId 1 from your 2.4 query?

 --All the content is still there, except the average count

--2.5 ENGLISH: Does your query in the scenario in 2.4 act more like an inner or outer join?

--outer

--3.1 Return all the columns in the customers table for each customer,
--and the total order amount (ignoring discount) for each customer.
--You must not have any of the columns from the customers table in your group by.

select c.*, q.[TotalAmountOrdered] from Customers as c
left join (
select sum(od.quantity * od.unitprice) as [TotalAmountOrdered], CustomerID	
from OrderDetails as od
left join Orders as o
	on o.OrderID = od.OrderID
group by o.CustomerID
) as q
	on q.CustomerID = c.CustomerID
--3.2 Rewrite the previous query without using any subqueries or derived tables.
--select * from Customers
select c.CustomerID, c.CompanyName, c.ContactName, c.[Address], c.City, c.Region, c.PostalCode, c.Country, c.Phone, c.Fax
	, sum(od.Quantity * od.UnitPrice) as [TotalAmountOrdered]
from Customers as c
left join Orders as o
	on o.CustomerID = c.CustomerID
left join OrderDetails as od
	on od.OrderID = o.OrderID
group by c.CustomerID, c.CompanyName, c.ContactName, c.[Address], c.City, c.Region, c.PostalCode, c.Country, c.Phone, c.Fax
order by c.CustomerID
--3.3 ENGLISH: Which query do you prefer: 3.1 or 3.2?  Why?

--3.1 lol, much less manual entering

--3.4 We'd like to see the most popular products.  Return all columns from products for only 
--those products whose average order quantity is greater than the average order quantity 
--for all products.  

select *
from  Products as p
left join (
	select ProductID, avg(quantity) as [Average Quantity]
	from OrderDetails
	group by ProductID
) as AvgProd
	on p.ProductID =  AvgProd.ProductID
where AvgProd.[Average Quantity] > (
	select avg(Quantity) as [Average Total Quantity]
	from OrderDetails
)

--4.1 The unshipped orders are due by the required date.  Assuming they _still_ have not been shipped, how many days late are 
--these orders?  Include the orderId, required date, and days late from today in your select.

select o.OrderID
	, o.RequiredDate
	, DATEDIFF(Day, RequiredDate, GETDATE()) as [Days Late]
from Orders as o
where ShippedDate is null

--4.2 Using subqueries to limit the number of returned records, create a phone list for the customers whose orders have 
--not shipped so you can call each one an apologize for the delay in shipping their order.  
--The phone list should have at least the company name, the contact name, and the phone number
--for each customer who has late orders.

select c.ContactName, c.CompanyName, c.Phone
from Customers as c
where c.CustomerID in ( 
	select CustomerID
from Orders as o
where ShippedDate is null
)

--4.3 Using only join syntax (no subqueries), answer 4.2 again.  

select distinct c.CompanyName
	, c.ContactName
	, c.Phone
from Customers as c
left join Orders as o
	 on c.CustomerID = o.CustomerID
where ShippedDate is null
order by CompanyName

--4.4 Is the row count different between 4.2 and 4.3?  If so, paste your response from 4.3 below and ensure there are no 
--duplicates in the results.
--There are duplicates because some people have multiple late orders
select distinct c.CompanyName
	, c.ContactName
	, c.Phone
from Customers as c
left join Orders as o
	 on c.CustomerID = o.CustomerID
where ShippedDate is null
order by CompanyName

--4.5 English:
--Using a subquery on the where clause can return similar results to an inner join, as we've seen with
--4.2 and 4.3.  Describe one benefit of using a subquery in the where clause, and another benefit to 
--using a subquery (derived table) in the from.

--Using a subquery in the where clause is like an inner join of filtering out data that matches
--Using a subquery in the from clause is how we add more data to the query

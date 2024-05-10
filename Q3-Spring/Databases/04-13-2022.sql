use Northwind
go

select *
from OrderDetails

select * from Orders

-- list Company name and orderID in one query
select O.OrderID, C.CompanyName
from Orders as O inner join Customers as C
	on O.CustomerID = C.CustomerID
order by c.CompanyName


-- list all customers with or without orders
select C.CompanyName, O.OrderID
from Orders as O right outer join Customers as C
	on O.CustomerID = C.CustomerID
order by c.CompanyName


-- list all customers without orders
select C.CompanyName, O.OrderID
from Orders as O right outer join Customers as C
	on O.CustomerID = C.CustomerID
where O.OrderID is null
order by c.CompanyName

-- list company name, orderID, and order quantity
select c.CompanyName, o.OrderID, od.quantity
from Customers as c
left outer join Orders as o
	on o.CustomerID = c.CustomerID
left outer join OrderDetails as od
	on od.OrderID = o.OrderID
order by c.CompanyName
-- Left / Left = 2157 Rows


select c.CompanyName, o.OrderID, od.quantity
from Customers as c
inner join Orders as o
	on o.CustomerID = c.CustomerID
inner join OrderDetails as od
	on od.OrderID = o.OrderID
order by c.CompanyName
-- inner / inner = 2155 (We lost the 2 customers without orders)


select c.CompanyName, o.OrderID, od.quantity
from Customers as c
left outer join Orders as o
	on o.CustomerID = c.CustomerID
inner join OrderDetails as od
	on od.OrderID = o.OrderID
order by c.CompanyName
-- left / inner = 2155 (We lost the same 2 customers without orders)

-- VIEW

-- list all USA customers with orders
select o.*
from customers as c
inner join orders as o
	on c.CustomerID = o.CustomerID
where c.Country = 'USA'
go

create view vUSA_Orders
as
(
	select o.*
	from customers as c
	inner join orders as o
		on c.CustomerID = o.CustomerID
	where c.Country = 'USA'
)
go

select * from vUSA_Orders


select c.CompanyName, usa.OrderID
from customers as c
inner join vUSA_Orders as usa
	on c.CustomerID = usa.CustomerID

-- list all USA customers and any orders and order details they have placed
select c.CompanyName, usa.OrderID
from customers as c
left outer join vUSA_Orders as usa
	on c.CustomerID = usa.CustomerID
inner join OrderDetails as od
	on usa.OrderID = od.OrderID
--352 rows

select c.CompanyName, usa.OrderID
from customers as c
left outer join vUSA_Orders as usa
	on c.CustomerID = usa.CustomerID
left outer join OrderDetails as od
	on usa.OrderID = od.OrderID
-- 430 rows - includes non-usa


select c.CompanyName, usa.OrderID
from customers as c
left outer join vUSA_Orders as usa
	on c.CustomerID = usa.CustomerID
left outer join OrderDetails as od
	on usa.OrderID = od.OrderID
where od.OrderID is not null
-- where usa.shipcountry = 'USA'

select c.CompanyName, usa.OrderID, od.ProductID, od.Quantity
from vUSA_Orders as usa
inner join OrderDetails as od
	on usa.OrderID = od.OrderID
right join Customers as c
	on usa.CustomerID = c.CustomerID
where usa.ShipCountry = 'USA'
order by c.CompanyName DESC, usa.OrderID ASC


-- provide company and total orders placed for each company

select c.CompanyName, count(usa.OrderID) as [Orders Placed]
from Customers as c
left join vUSA_Orders as usa
	on c.CustomerID = usa.CustomerID
inner join OrderDetails as od
	on usa.OrderID = od.OrderID
group by c.CompanyName


-- Stored Procedures
use Northwind
go
-- all order details
select  o.OrderID
	, month(o.orderdate) as [Order Month]
	, p.ProductName
	, p.UnitPrice
	, p.UnitsInStock
	, s.CompanyName
from orders as o
inner join OrderDetails as od
	on o.OrderID = od.OrderID
inner join Products as p
	on p.ProductID = od.ProductID
inner join Suppliers as s
	on s.SupplierID = p.SupplierID
order by [Order Month]

-- save this query as a stored procedure
go
create procedure sp_FetchAllOrderDetails
as
begin
	select  o.OrderID
		, month(o.orderdate) as [Order Month]
		, p.ProductName
		, p.UnitPrice
		, p.UnitsInStock
		, s.CompanyName
	from orders as o
	inner join OrderDetails as od
		on o.OrderID = od.OrderID
	inner join Products as p
		on p.ProductID = od.ProductID
	inner join Suppliers as s
		on s.SupplierID = p.SupplierID
	order by [Order Month]
end
go

exec sp_FetchAllOrderDetails


-- Parameters
go
create procedure sp_CustomerProductDetails
(
	@p_CustomerID int
)
as
begin
	select cat.CategoryName
		, cat.Description
		, p.ProductName
		, p.UnitPrice
		, p.UnitsInStock
	from Customers as c
	inner join Orders as o
		on o.CustomerID = c.CustomerID
	inner join OrderDetails as od
		on o.OrderID = od.OrderID
	inner join Products as p
		on p.ProductID = od.ProductID
	inner join Categories as cat
		on cat.CategoryID = p.CategoryID
	where c.CustomerID = @p_CustomerID
end
go

exec sp_CustomerProductDetails 80

-- two parameters
-- select the top employee by sales
go
create procedure sp_EmployeeoftheMonth
(
	@p_year int,
	@p_month nvarchar(10)
)
as
begin
	select FirstName, LastName
	from Employees
	where EmployeeID in
	(
		select EmployeeID
		from (
		select top 1 EmployeeID
			, count(orderid) as [Total Orders]
		from Orders
		where YEAR(orderdate) = @p_year
			and datename(month, orderdate) = @p_month
		group by EmployeeID
		order by [Total Orders] desc
		) as EmployeeoftheMonth
	)
end
go

exec sp_EmployeeoftheMonth 2013, 'September'
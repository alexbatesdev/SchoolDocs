-- VAriables and functions
Declare @name varchar(20)
set @name = 'John'

select @name as [Name]
go

declare @name varchar(20) = 'Mary'
select @name
go
-- Dynamic Example
use Northwind
go
declare @name varchar(20)

select @name = CompanyName
from Customers 
where CustomerID = 50 --without this line @name returns the last item

select @name as [Name]


declare @count int

select @count = count(*)
from Products
where UnitPrice > 50

--select @count 

if @count > 0
	begin
		print @count
	end
else
	begin
		print 'No Products Match'
	end


declare @empID int
set @empID = 1

while @empID <= 10
begin
	 set @empID = @empID + 1
	 print @empID
end

go
-- Functions
create function dbo.calclinetotal -- MUST use the Schema with custom functions
(
	@price decimal(18,2),
	@quantity int,
	@discount decimal(18,2)
)
returns decimal(18,2)
AS
begin
	return @price * @quantity * (1- @discount)
end
go

-- use the function

select c.CompanyName, dbo.calclinetotal(UnitPrice, Quantity, Discount) AS [LineTotal]
from customers as c
inner join orders as o 
	on o.customerid = c.CustomerID
inner join OrderDetails as od
	on o.OrderID = od.OrderID
where dbo.calclinetotal(UnitPrice, Quantity, Discount) > 2000


declare @empName varchar(20) = 'Peacock'
declare @empID as int

select @empID = EmployeeID
from Employees
where LastName = @empName

select 'Employee' as TableType
	, *
from Employees
where EmployeeID = @empID

select 'Sales' as TableType
	, c.companyName
	, o.orderID
	, o.orderdate
	, SUM(dbo.calclinetotal(UnitPrice, Quantity, Discount)) as OrderTotal
from customers as c
inner join orders as o 
	on o.customerid = c.CustomerID
inner join OrderDetails as od
	on o.OrderID = od.OrderID
where o.EmployeeID = @empID
group by c.companyName
	, o.orderID
	, o.orderdate





-- built-in functions
exec sp_who

exec sp_who2

select @@spid as SPID

select @@VERSION as Version

select @@SERVERNAME as [Server Name]
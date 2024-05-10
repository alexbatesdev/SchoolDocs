use Northwind
go

-- Joins / Subqueries / View / Temp Tables / CTE

select c.CategoryID, c.CategoryName
	, p.ProductName, p.UnitPrice
from Products as p
inner join Categories as c
	on p.CategoryID = c.CategoryID

-- rewrite as sub-query
select *
from (
		select c.CategoryID, c.CategoryName
			, p.ProductName, p.UnitPrice
		from Products as p
		inner join Categories as c
			on p.CategoryID = c.CategoryID
	) as ProductCategories

-- My own nonsense, triple query, double nested
select *
from (
	select *
	from (
			select c.CategoryID, c.CategoryName
				, p.ProductName, p.UnitPrice
			from Products as p
			inner join Categories as c
				on p.CategoryID = c.CategoryID
		) as ProductCategories
	) as ProductProductCategories

--View
go
create view vProductCategories
as
(
	select c.CategoryID, c.CategoryName
		, p.ProductName, p.UnitPrice
	from Products as p
	inner join Categories as c
		on p.CategoryID = c.CategoryID
)
go

select * 
from vProductCategories

-- Temp Table
select c.CategoryID, c.CategoryName
	, p.ProductName, p.UnitPrice
INTO #TempProductCategories
from Products as p
inner join Categories as c
	on p.CategoryID = c.CategoryID

select * 
from #TempProductCategories

drop table #TempProductCategories



use AveragePrice
go

select * from gasoline
select * from food
select * from household_fuels

-- create ALL Product temp table
--  add gasoline
select series_id
	, year
	, period
	, value
into #all_products
from gasoline

select * from #all_products

-- add Food
insert into #all_products
select series_id
	, year
	, period
	, value
from Food

-- add household fuels
insert into #all_products
select series_id
	, year
	, period
	, value
from household_fuels


create table #allproducts
(
)


-- CTEs
-- Common Table Expressions

use Northwind
go

with ProdCat as
(
	select c.CategoryID, c.CategoryName
		, p.ProductName, p.UnitPrice
	from Products as p
	inner join Categories as c
		on p.CategoryID = c.CategoryID
)
select * from ProdCat-- Does work
select * from ProdCat -- Doesn't work
-- CTEs only get one use

-- multiple CTEs
go
with AvgPrice as
(
	select avg(unitprice) as average
	from Products
), AverageProducts as
(
	select p.categoryID, p.productID, p.productName, p.unitprice
	from Products as p
	inner join AvgPrice as a
		on p.UnitPrice > a.average
)
select c.categoryname
	, ap.productname
	, ap.unitprice
from Categories as c
inner join AverageProducts as ap
	on c.CategoryID = ap.CategoryID
order by c.CategoryName
go

-- Join/subquery
-- list beverages
select p.ProductName, p.UnitPrice
from Products as p
inner join Categories as c
	on p.CategoryID = c.CategoryID
where CategoryName = 'Beverages'

-- rewrite as subquery
select p.ProductName, p.UnitPrice
from Products as p
where categoryid in
( --subquery for list of categoryIDs for beverages
	select categoryID
	from Categories
	where CategoryName = 'Beverages' 
)


-- list all product names and their price
-- for any product that has a price which is
-- greater than the average of al prices

select avg(unitprice) as [avg]
	, min(unitprice) as [min]
	, max(unitprice) as [max]
from Products

select ProductName, UnitPrice
from Products
where UnitPrice > (
	select AVG(UnitPrice)
	from Products
)

-- is this possible with a JOIN?
-- Yes, anytihng is possible with joins if you try hard enough
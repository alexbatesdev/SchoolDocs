/******************** Average Price Database ******************************************

You will use thie Average Price Database for two assignments.  
The first assignment you will complete as a group and the second is an individual assignment.

Unless specified otherwise, use as many separate queries as you need in your responses.

All questions require SQL responses except those marked "ENGLISH".

The data for this lab is available from the US Bureau of Labor Statistics, and is freely available
here: http://download.bls.gov/pub/time.series/ap/.  Contained in that directory is a documentation 
file named ap.txt which describes the nature of the data.

***********************************************************************/


---------------------- SETUP -----------------------------

/*
You need to complete the Setup of your Average Price Database before answering the questions below.

Download and review all the files from http://download.bls.gov/pub/time.series/ap/
The "4A - AP_DB - Create.sql" script creates the AveragePrice database and adds the necessary tables.  
Edit the end of the script to insert all the AP data files except ap.txt and ap.contacts,
and ensure each table has a primary key.
*/


-------------------------------------------------
----------------Group Portion--------------------
-------------------------------------------------

--------------- QUESTIONS -----------------------

/* 1.1 
ENGLISH: 
What data is contained in the database? 
Hint: Read the documentation in ap.txt, and "average prices" or "a series of prices" is not specific
enough.  What will you tell a non-technical person this database contains?
*/
--This database stores the average monthly cost of household expenses such as electricity, gas, food, etc.

/* 2.1
Look at the Series table.  Area_code references rows in Area, item_code references
rows in items, and likewise with the two period columns.  

Write single query that returns all the rows and columns from 
Series plus the matching rows, if any, for the above columns from their source tables.

Include all the rows from series, regardless of a matching row in any other table.

*/
--select * from series
--select * from area
--select * from item

select * 
from series as s
left join area as a
	on s.area_code = a.area_code
left join item as i
	on i.item_code = s.item_code
go
/* 2.2
Your query above will probably be useful in the future.  Create a view
called SeriesDescription based on that query.  Be explicit with your 
column names (do not use *), and do not duplicate columns in the view.

For help in creating and using views, see the Ben-Gan book, pg 169-171.

A view stores a _query_, not the query result, and lets you write

Select *
From <whatever your view is called> 
Where...

to simplify and shorten future queries.
*/
go
create view vSeriesDescription
	as
		(
			select s.series_id, s.series_title, s.begin_year, s.begin_period, s.end_year, s.end_period
				, a.area_code, a.area_name
				, i.item_code, i.item_name
			from series as s
			left join area as a
				on s.area_code = a.area_code
			left join item as i
				on i.item_code = s.item_code
		)
go
/* 3.1
Use your new view to return the descriptions for series APU0000702212.
*/
select *
from vSeriesDescription
where series_id = 'APU0000702212'
go
/* 3.2
Join your SeriesDescription view to Food and filter to seriesid APU0000702212,
Order the results chronologically.
*/
--select * from Food
select * from vSeriesDescription as v
left join food as f 
	on f.series_id = v.series_id
where v.series_id = 'APU0000702212'
order by [year], [period]
go
/*
3.3 ENGLISH
What does the value column represent in the result of 3.2 for series_id APU0000702212?
Be specific in your response ("price" is not good enough).
*/
-- I have no clue, I thought it was the price but apparently not?
-- is it just the average price per pound of bread?


/* 3.4
Using the food table, return the prices for series APU0000702212 for each April, sorted
in reverse chronological order.
*/
select v.item_code, v.item_name, f.[year], f.[period], f.[value] -- *
from vSeriesDescription as v
left join food as f 
	on f.series_id = v.series_id
where v.series_id = 'APU0000702212'
	and f.[period] = 'M04' -- Jan M01, Feb M02, Mar M03, Apr M04
order by [year] desc, [period] desc
go



/* 4.1
Did you notice Food, Gasoline, and HouseholdFuels all have the same structure?
select * from Food
where footnote_codes is not null
union
select * from Gasoline
where footnote_codes is not null
union
select * from Household_Fuels
where footnote_codes is not null


Having these records split between different tables is like putting appointments
for different days of the week in separate tables. Let's fix it.

Create an AllProducts table to meet the following requirements.  The new table must:
	a. Not lose any information found in the old tables
	b. Improve any mis-typed data (think: should month and year really be split into two columns?)
	c. Include the source table for each record, so that returning to the 
		old format will be possible with a where clause like this:
			Select *
			From AllProducts
			Where SourceTable = 'Gasoline'

			Hint: Remember that you can hard code a string in the select, like Select 'test', * From ATable

	d. Ensure the AllProducts table has a suitable primary key and foreign keys to any
		related code tables (but not to the Food, Gasoline, and Household fuel tables)
*/
go
alter table series alter column series_id varchar(17) not null
go
alter table series
add primary key (series_id)

drop table if exists AllProducts

create table AllProducts (
	series_id varchar(17) NULL foreign key references series(series_id),
	[period] date NULL,
	[value] float NULL,
	SourceTable nvarchar(30) NULL -- I dropped the footnote_codes becuase they were ALL unll, so no data was lost :)
	)

select series_id from series
go
/* 4.2
Populate the AllProducts table with all the data from 
the gasoline, food, and householdfuels tables.  Use appropriate techniques to 
convert the data in those source tables to the format required by the 
AllProducts table.  

Use a _single_ query for your insert.

See the Union operator, Ben-Gan page 192.  In a union, multiple selects act as one larger query.

select * from AllProducts
select top(5) series_id, year, period, value from gasoline
select top(5) series_id, CAST(CAST(([year]+'-'+SUBSTRING([period], 2, 3)+'-01') as nvarchar(10)) as date) as date, value from gasoline
select top(5) series_id, [year]+'-'+SUBSTRING([period], 2, 3)+'-01' from gasoline
select top(5) series_id, [year]+'-'+SUBSTRING([period], 2, 3)+'-01' from food
select series_id, CAST([year]+'-'+SUBSTRING([period], 2, 3)+'-01' as date) from food
select series_id, year, period from food
select * from AllProducts where day(period) != 1
select * from AllProducts where SourceTable = 'Gasoline' order by series_id, period
select * from gasoline
*/
insert into AllProducts
select series_id, CAST([year]+'-'+SUBSTRING([period], 2, 3)+'-01'as date), [value], 'Food' from Food
where [value] != '           -'
union
select series_id, CAST(CAST([year] as varchar(4))+'-'+SUBSTRING([period], 2, 3)+'-01' as date), [value], 'Gasoline' from gasoline
where [value] != '           -'
union
select series_id, CAST(CAST([year] as varchar(4))+'-'+SUBSTRING([period], 2, 3)+'-01' as date), [value], 'household_fuel' from household_fuels
where [value] != '           -'

/* 4.3
Remove the Food, Gasoline, and HouseholdFuels tables from the database.
(Remove the tables, not just their rows)
*/

drop table food
drop table gasoline
drop table household_fuels
go

/* 4.4

(No answer required. UI tools are fine here) <-- Can't get UI tools to work... :(

Ensure that all tables in AP have primary keys, and that foreign keys exist to constrain
and show relationships.  You can ignore the footnotes table as it has 0 rows.

You'll submit a database diagram image with your lab submission.
*/
--altering area
select * from area
--disallowing nulls
alter table area
alter column area_code varchar(4) not null
go
--adding primary key
alter table area
add primary key (area_code)
go

--altering period
select * from period
--disallowing nulls
alter table [period]
alter column [period] varchar(3) not null
go
--adding keys
alter table [period]
add primary key ([period])
go

--altering current
select * from [current]
--disallowing nulls
alter table [current]
alter column series_id varchar(17) not null
alter table [current]
alter column [year] smallint not null
alter table [current]
alter column [period] varchar(3) not null
go
-- adding keys
alter table [current]
add primary key (series_id, [year], [period]) --I thought it might be a bad practice to have the primary key also be a foreign key so I made the primary key be multiple columns
alter table [current]
add foreign key (series_id) references series(series_id)
alter table [current]
add foreign key ([period]) references [period]([period])
go
--altering item
select * from item
--disallowing nulls
alter table item
alter column item_code varchar(9) not null
go
--adding primary key
alter table item
add primary key (item_code)
go

--altering seasonal
select * from seasonal
--disallowing nulls
alter table seasonal
alter column seasonal_code varchar(2) not null
go
--add primary key
alter table seasonal
add primary key (seasonal_code)
go

--altering series
select * from series
--adding foreign keys
alter table series
add foreign key (area_code) references area(area_code)
alter table series
add foreign key (item_code) references item(item_code)
alter table series
add foreign key (begin_period) references [period]([period])
alter table series
add foreign key (end_period) references [period]([period])
go


--altering AllProducts because I'm dumb
--disallowing nulls
alter table AllProducts
alter column series_id varchar(17) not null
alter table AllProducts
alter column period date not null
go
--adding primary key
alter table AllProducts
add primary key (series_id, [period])
go
--adding foreign key
alter table AllProducts
add foreign key (series_id) references series(series_id)
go
--
/* 4.5 ENGLISH
So... that was a lot of work.  Why bother?  Why create one new table and remove the three old ones?  Explain
when starting from scratch on a new project how you will decide what data belongs in the same 
table and what should be stored in different tables.
*/

--Merging our data into one table makes it much easier to query
--In a previous assignment I had 4 tables with the same column setup and decided to merge them like we did here (luckily I made that decision before inserting data so it wasn't hard to edit)
--I'll make tables however they intuitively pop in my head, then combine tables that are are very similar in parameters/columns

/* 5.1
Return the records in period that are not used in the AllProducts table.

(Aren't you glad we have an all products table?  Think of how much more of a pain
this query would have been if the data was still split across 3 tables)

*/
select * from AllProducts
where month([period]) in (
select substring([period], 3, 1)
from [period] )
go
/* 5.2
Return the records in AllProducts that have a period that doesn't exist
in the period table
*/
select * from AllProducts
where month([period]) not in (
select substring([period], 3, 1)
from [period] )
go
/* 5.3 ENGLISH
Explain how a foreign key acts on the Primary and Foreign Key tables.  Which table
can have values not found in the other table?  Which table(s) is/are modified to receive
the constraint?
*/
--the foreign key helps connect 2 tables
--only one table actually has a foreign key
-- a foreign key is like the keyhole for the primary key of another table
--Table A             Table B
--PK         .------->PK
--DATA		 |   	  DATA
--FK---------'


/* 6.1
Return the top 100 products that had the largest month-to-month change in price as 
a percent (p2 - p1)/p1.

select * from AllProducts
select * from series
select period from AllProducts
select month(period)+1 from AllProducts
select cast((month(period)+1) as varchar) from AllProducts
*/
with PriceDiff as
(
select a.series_id
	, [period]
	, [value] as [p1]
	, LAG([value], 1) over ( partition by a.series_id order by [period] ) as [p2]
	, SourceTable
	, s.item_code
	from AllProducts as a
	left join series as s
		on a.series_id = s.series_id
where [value] != 0
)
select top(100) series_id, [period], diff, p1, p2, SourceTable as [Category], item_name
from (
	select series_id, [period], ABS((p2 - p1)/p1) as diff, SourceTable, p1, p2, item_code
	from PriceDiff
) as p
left join item as i
	on i.item_code = p.item_code
order by diff desc
/* 6.2 ENGLISH
How did you handle division by 0?  Are the zeros good data, or bad?  Justify your decision.
*/
-- I skipped those tables in the price difference calculation
/* 6.3 ENGLISH
What food items tend to have the largest price changes?  (SQL is OK here, but I want you to explain
the result in English).
*/
-- Peppers and Tomatoes are at the top of the list being the only foods in the top 25 entrie
-- Oranges show up at around row 30, then it goes back to peppers and tomatoes


/* 6.4 ENGLISH
Records from HouseholdFuels show up a lot in the top 15 records with the largest price changes.
Look at the item name for each.  Is there any other explanation other than there were a lot 
of months with large price changes that make HouseholdFuels appear so many times in the top 15?
Justify your response.
*/
--This is piped gas, the stuff that heats homes, the demand of gas heat soaras in cold months and goes down in warm months
--The price difference calculation shows price change, not price increase, so using significantly less gas also puts you high on the list
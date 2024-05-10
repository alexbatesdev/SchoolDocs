------------------------------------------------------------------------
---------------- Individual Portion ------------------------------------
------------------------------------------------------------------------


/* Submit the following questions individually in the Lab 1 - Individual portion.  Do not 
work in your groups on these questions.  All answers here must be your own work.  */

/* 
1a: SQL Queries
1b: ENGLISH

Find something else interesting in this database by describing your intent in English and 
extracting the data with SQL queries. 

Dig and explore the data. 
A single query with a simple WHERE clause is not sufficient.
Get curious, use as many queries as you'd like. 
Explain what you looked for and what you found in English.
*/

use AveragePrice
go

create view vPriceDiffs
as 
with PriceDiff as
(
select s.*
	, a.[period]
	, a.SourceTable
	, a.[value] as [p1]
	, LAG(a.[value], 1) 
		over ( 
		partition by a.series_id 
		order by a.[period] ) 
		as [p2]
	from AllProducts as a
	left join series as s
		on a.series_id = s.series_id
where a.[value] != 0
)
select series_id, [period], diff, p1, p2, SourceTable as [Category], item_name
from (
	select *, ABS((p2 - p1)/p1) as diff
	from PriceDiff
) as p
left join item as i
	on i.item_code = p.item_code
go

select * from vPriceDiffs

--This shows the items with the MOST change on average
--All the items with lost of change on average are produce items
--Cherries are number one followed by corn on the cob and grapes
select item_name, avg(diff) as [Average Change] from vPriceDiffs
where /*Category = 'Food'
	and*/ diff is not null
group by item_name
order by 2 desc

--This shows the items with the LEAST change on average
--These are all products with a high shelf life, generally beverages
--Vodka is number one followed by milk and cofee
select item_name, avg(diff) as [Average Change] from vPriceDiffs
where /*Category = 'Food'
	and*/ diff is not null
group by item_name
order by 2 asc

/* 2: ENGLISH

What features of this database were easy to use?  What was hard to use?
Be specific. Include examples of both easy and hard.
*/

/*
Dealing with inconsistencies was the most difficult part
when I was merging period and year into a date datatype I concatenated 
things together to make a string that could be formatted to a date
One of the tables stored the year as an int instead of a string and it 
meant that instead of concatenating the numbers to make a string it did 
math on them, made a new number and then did it's best to turn that into a date (it didn't do very well)
As for things that were easy to use? Not much, I don't 100% understand what the series are supposed to be
My view that I made for this assignment was very easy to use to get the one thing I want, but it only became that way after the hardship of making it
*/
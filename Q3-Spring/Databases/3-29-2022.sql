use master
go

drop database if exists store2
go

create database store2
go

use store2
go

create table Customers
(
	CID int not null,
	FirstName nvarchar(30) not null,
	LastName nvarchar(30) null,
	PreferredName nvarchar(120) null
)
/*
create table Products
(
	columns
)
*/

insert Customers values (1, 'Simon', 'Silverman', 'Timon Silvomar')
insert Customers values (1, 'Bob', null, null)

select *
from Customers
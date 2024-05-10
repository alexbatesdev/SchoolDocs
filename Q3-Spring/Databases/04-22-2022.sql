use master
go

drop database if exists TriggersDB
go

create database TriggersDB
go

use TriggersDB


create table Characters
( FName varchar (50) null
, LName varchar (50) null
)
go

create table InsertedRecords
( [Date] smalldatetime
, FName varchar (50) null
, LName varchar (50) null
)
go

create table DeletedRecords
( [Date] smalldatetime
, FName varchar (50) null
, LName varchar (50) null
)
go


--Create Trigger
create trigger CatchInsertDelete
on Characters
for insert, delete
as
	insert into InsertedRecords (Date, FName, LName)
		select GETDATE(), FName, LName
		from inserted
	insert into DeletedRecords (Date, FName, LName)
		select GETDATE(), FName, LName
		from deleted
go

-- Test Insert
insert into Characters values ('Jeph', 'Jefferson')
insert into Characters values ('Peenfo', 'Gibbs')
insert into Characters values ('Hans', 'Heffley')
insert into Characters values ('Spoom', 'Boomson')
insert into Characters values ('Cyarlos', 'Manny')
insert into Characters values ('Spencor', 'Snivvle')

select * from Characters
select * from DeletedRecords
select * from InsertedRecords

-- Test Delete
delete from Characters
where LName like 'Heffley'
	or LName like 'Manny'


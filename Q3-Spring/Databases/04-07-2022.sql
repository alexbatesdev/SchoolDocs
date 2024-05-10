use SuperCarRaceSeries
go

select * from Car

delete Car

--remove 1004
delete from Car
where CarID = 1004

delete from RaceWinner
where RaceWinnerID = 1

select * from RaceWinner

delete from Driver
where DriverID = 101

select * from Driver
select * from Car
select * from RaceWinner
select * from SponsorDriver


-- need to alter table to allow cascading deletes
alter table Car
	drop constraint FK__Car__DriverID__267ABA7A
go

alter table Car
	add constraint FK_Car_Driver
	foreign key(DriverID)
	references Driver(DriverID)
	on delete cascade
go

-- also on racewinner
alter table RaceWinner
	drop constraint FK_RaceWinner_Driver
go

alter table RaceWinner
	add constraint FK_RaceWinner_Driver
	foreign key(DriverID)
	references Driver(DriverID)
	on delete cascade
go

-- also on SponsorDriver
alter table SponsorDriver
	drop constraint FK_SponsorDriver_Driver
go

alter table SponsorDriver
	add constraint FK_SponsorDriver_Driver
	foreign key(DriverID)
	references Driver(DriverID)
	on delete cascade
go
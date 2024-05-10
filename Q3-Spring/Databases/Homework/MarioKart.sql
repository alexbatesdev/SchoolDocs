use master
go

drop database if exists MarioKart
go

CREATE DATABASE MarioKart
go

use MarioKart
go

--Create Tables
--Players
create table Racers
	( RacerID smallint identity(101,1) not null primary key
	, FirstName nvarchar(30) not null
	, LastName nvarchar(30) not null
	, Username nvarchar(30) not null
	)
--Parts and Characters
create table Parts -- In the future parts would be seperated into type, they are very awkward to work with in this state
	( PartID smallint identity(201,1) not null primary key
	, [Name] nvarchar(30) not null
	, [Type] nvarchar(30) not null --Wheels, Body, Glider, Character
	, Speed float not null
	, Acceleration float not null
	, [Weight] float not null
	, Handling float not null
	, Traction float not null
	, MiniTurbo float not null
	)
--The info on the track raced on
create table Tracks
	( TrackID int identity(301,1) not null primary key
	, [Name] nvarchar(30) not null
	, Cup nvarchar(30) not null
	, isRetro bit not null
	)
--The kart and character combination used
create table RacerSetup
	( SetupID smallint identity(401,1) not null primary key
	, RacerID smallint not null foreign key references Racers(RacerID)--Racers
	, CharID smallint not null foreign key references Parts(PartID)--Characters
	, Wheels smallint not null foreign key references Parts(PartID)--Parts
	, Body smallint not null foreign key references Parts(PartID)--Parts
	, Glider smallint not null foreign key references Parts(PartID)--Parts
	)
--The recpords for the races
create table Races
	( RaceID int identity(501, 1) not null primary key
	, TrackID int not null foreign key references Tracks(TrackID)--Tracks
	, [First] smallint not null foreign key references Racers(RacerID)--Racers
	, [Second] smallint not null foreign key references Racers(RacerID)--Racers
	, [Third] smallint not null foreign key references Racers(RacerID)--Racers
	--, [Fourth] smallint not null foreign key references Racers(RacerID)--Racers
	--, [Fifth] smallint not null foreign key references Racers(RacerID)--Racers
	)

			--At this point I just realized how similar to ERaceway this is... Oh well hopefully it's differen't enough, I wasn't conciously modeling this after that database but they are similar

-- Insert Player Data
insert into Racers (FirstName, LastName, Username) 
values('William', 'Penrod', 'WetWilly96')
	, ('Beth', 'Streudal', 'XxAbyssalxX')
	, ('Frank', 'Goozman', 'OceansDeepest001')
	, ('Simon', 'Silverman', 'JewsGoose') --Pronounced Juice-Goose
	, ('Tobie', 'Allen', 'ShadowWalker1999')
	, ('Brendan', 'Polley', 'LeanMeanGreenMachine')
	, ('Arsene', 'Petit', 'WibblySaucer')
	, ('Grayson', 'Sudweeks', 'Gravy_Train')
	, ('Emile', 'Randolph', 'BUwUp02')
	, ('Eric', 'Kohler', 'Quadratica')
	, ('Raymond', 'Maple', 'Jesus') --Once a student said "Jesus..." :O in such shock and aw of another students project and Maple responded "Yes?"
	, ('Michael', 'Pritchard', 'Michael++')
	, ('Chris', 'Canterra', 'Chris') --13 Players
-- Insert Character and Part data
insert into Parts ([Name], [Type], Speed, Acceleration, [Weight], Handling, Traction, MiniTurbo)
values('Dry Bones', 'Character', 2.5, 4.25, 2.25, 4.5, 4, 3.75) --Characters first
	, ('Baby Luigi', 'Character', 2.5, 4.25, 2.25, 4.5, 4, 3.75)
	, ('Baby Mario', 'Character', 2.5, 4.25, 2.25, 4.5, 4, 3.75)
	, ('Koopa Troopa', 'Character', 2.75, 4, 2.5, 4.5, 4.25, 3.75)
	, ('Bowser Junior', 'Character', 2.75, 4, 2.5, 4.5, 4.25, 3.75)
	, ('Lakitu', 'Character', 2.75, 4, 2.5, 4.5, 4.25, 3.75)
	, ('Toad', 'Character', 3, 4, 2.75, 4.25, 4, 3.5)
	, ('Shy Guy', 'Character', 3, 4, 2.75, 4.25, 4, 3.5)
	, ('Larry', 'Character', 3, 4, 2.75, 4.25, 4, 3.5)
	, ('Peach', 'Character', 3.5, 3.75, 3, 3.75, 3.75, 3.5)
	, ('Daisy', 'Character', 3.5, 3.75, 3, 3.75, 3.75, 3.5)
	, ('Yoshi', 'Character', 3.5, 3.75, 3, 3.75, 3.75, 3.5)
	, ('Luigi', 'Character', 3.75, 3.5, 3.5, 3.75, 3.25, 3.25)
	, ('Mario', 'Character', 3.75, 3.5, 3.5, 3.75, 3.25, 3.25)
	, ('Iggy', 'Character', 3.75, 3.5, 3.5, 3.75, 3.25, 3.25)
	, ('Donkey Kong', 'Character', 4.5, 3.25, 4, 3, 3, 3) 
	, ('Roy', 'Character', 4.5, 3.25, 4, 3, 3, 3) 
	, ('Waluigi', 'Character', 4.5, 3.25, 4, 3, 3, 3) 
	, ('Wario', 'Character', 4.75, 3, 4.25, 2.75, 3.25, 2.75)
	, ('Dry Bowser', 'Character', 4.75, 3, 4.25, 2.75, 3.25, 2.75)
	, ('Bowser', 'Character', 4.75, 3, 4.5, 2.5, 3, 2.75)
	, ('Standard', 'Kart', 0, 0, 0, 0, 0, 0) --Karts ------------------------------------
	, ('Pipe Frame', 'Kart', -0.5, 0.5, -0.25, 0.5, 0.25, 0.5)
	, ('Cat Cruiser', 'Kart', -0.25, 0.25, 0, 0.25, 0, 0.25)
	, ('BiddyBuggy', 'Kart', -0.75, 0.75, -0.5, 0.5, -0.25, 0.75)
	, ('LandShip', 'Kart', -0.5, 0.5, 0.5, 0.25, 0.75, 0.5)
	, ('Blue Falcon', 'Kart', 0.25, 0.25, 0.5, -0.25, 0, 0.25)
	, ('Flame Rider', 'Bike', -0.25, 0.25, -0.25, 0.25, 0.5, 0.25)
	, ('Mr. Scooty', 'Bike', -0.75, 0.75, -0.5, 0.5, 0.25, 0.75) 
	, ('Standard', 'Wheels', 0, 0, 0, 0, 0, 0) --Wheels ---------------------------------
	, ('Monster', 'Wheels', 0, -0.5, 0.5, -0.75, 0.5, 0.25)
	, ('Roller', 'Wheels', -0.5, 0.5, -0.5, 0.25, -0.25, 0.75)
	, ('Slick', 'Wheels', 0.5, -0.75, 0.25, -0.25, -1.25, -0.75)
	, ('Off Road', 'Wheels', 0.25, -0.25, 0.25, -0.5, 0.25, -0.5)
	, ('Super', 'Glider', 0, 0, 0, 0, 0, 0) --Glider ------------------------------------
	, ('Waddle Wing', 'Glider', 0, 0, 0, 0, 0, 0)
	, ('Cloud', 'Glider', -0.25, 0.25, -0.25, 0, 0, 0.25)
	, ('Paper', 'Glider', -0.25, 0.25, -0.25, 0, 0, 0.25)
-- insert data into tracks
insert into Tracks([Name], Cup, isRetro)
values('Mario Kart Stadium', 'Mushroom Cup', 0)
	, ('Sunshine Airport', 'Mushroom Cup', 0)
	, ('WII Coconut Mall', 'Mushroom Cup', 1)
	, ('N64 Rainbow Road', 'Mushroom Cup', 1)
	, ('Thwomp Ruins', 'POW Cup', 0)
	, ('Dragon Driftway', 'POW Cup', 0)
	, ('Hyrule Circuit', 'POW Cup', 0)
	, ('GBA Bowsers Castle', 'POW Cup', 1)
	, ('Cloudtop Cruise', 'Propeller Cup', 0)
	, ('Shy Guy Falls', 'Propeller Cup', 0)
	, ('DS Tick Tock Clock', 'Propeller Cup', 1)
	, ('Big Blue', 'Propeller Cup', 0)
--insert player setup
insert into RacerSetup (RacerID, CharID, Wheels, Body, Glider)
	--(Racer, Chrctr, Whls, Body, Glider)
values('101', '215', '232','222','236')
	, ('104', '219', '231','226','237')
	, ('105', '201', '233', '224', '237')
	, ('106', '213', '230', '223', '237')
	, ('107', '208', '231', '227', '235')
	, ('108', '216', '230', '226', '236')
	, ('109', '207', '234', '229', '237')
	, ('110', '206', '232', '225', '237')
	, ('111', '221', '230', '226', '237')
	, ('112', '214', '230', '223', '236')
	, ('113', '209', '233', '222', '235')
--insert Races
-- select * from Tracks
insert into Races (TrackID, [First], [Second], [Third])
values(301, 111, 108, 109)
	, (302, 108, 111, 110)
	, (303, 109, 107, 111)
	, (304, 111, 109, 108)
	, (309, 101, 105, 104)
	, (310, 113, 104, 101)
	, (311, 106, 112, 105)
	, (312, 105, 109, 108)
go
--Time to make views
create view vPlayersWithCloudGlider 
as
	(
		select r.RacerID, r.FirstName + ' ' + r.LastName as [Name], r.Username, c.Name as [Character], w.Name as [Wheels], b.Name as [Kart], g.Name as [Glider]
		from Racers as r
		left join RacerSetup as s
			on s.RacerID = r.RacerID
		left join  Parts as c
			on c.PartID = s.CharID
		left join  Parts as w
			on w.PartID = s.Wheels
		left join  Parts as b
			on b.PartID = s.Body
		left join  Parts as g
			on g.PartID = s.Glider
		where g.PartID = 237
	)
go

create view vRetro_Cup
as
	(
		select TrackID, [Name], Cup
		from Tracks
		where isRetro = 1
	)
go

create view vWinnersWithCloudGlider
as
	(
		select r.[Name], r.Username, r.[Character], t.[Name] as [Track] --t.Name as Winner
		from vPlayersWithCloudGlider as r
		inner join races as ra
			on r.RacerID = ra.[First]
		left join Tracks as t
			on t.TrackID = ra.TrackID
	)
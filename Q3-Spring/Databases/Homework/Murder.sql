-- My Team: ME, also this kid Josh asked to be in the same group but then left immediately so I left his group because I did this all on my own

SELECT name 
  FROM sqlite_master
 where type = 'table'
 
 SELECT sql 
  FROM sqlite_master
 where name = 'crime_scene_report' --I went through every table fill out the notes below
 
 /* Notes for my thinking but I have no reason to not leave them here
 Tables
 crime_scene_report
  - date int
  - type text
  - description text
  - city text
 drivers_license
  - id int PK
  - age int
  - height int
  - eye_color text
  - hair_color text
  - gender text
  - plate_number text
  - car_make text
  - car_model text
 person
  - id int PK
  - name text
  - license_id int FK -> drivers_license(id)
  - address_number int
  - address_street_name text
  - ssn int
 facebook_event_chekin
  - person_id int FK -> person(id)
  - event_id int
  - date int
 interview
  - person_id int FK -> person(id)
  - transcript text
 get_fit_now_member
  - id text PK
  - person_id int FK -> person(id)
  - name text
  - membership_start_date int
  - membership_statis text
 get_fit_now_check_in
  - membership_id FK -> get_fit_now_member(id)
  - check_in_date int
  - check_in_time int
  - check_out_time int
 income
  - ssn int PK
  - annual_income int
 solution
 - user int
 - value text
 */
 --There's a murder in SQL city! Build the Lego chopper and find clues to hunt down your suspect!
 
select * from person -- gave me too much
--returned so many rows
 
select * from crime_scene_report --so much again, let's filter it
--returned so many rows
 
select * from crime_scene_report -- I found something sus on the 3rd row
where type = 'murder' --2 witnesses, lets find their deets
	and city = 'SQL City'
--returned 3 rows

/*
	Security footage shows that there were 2 witnesses. 
	The first witness lives at the last house on "Northwestern Dr". 
	The second witness, named Annabel, lives somewhere on "Franklin Ave".
*/

select * from person --I decided to look into Annabel first, let's just call it a detective's hunch 🕵️‍‍
where name like 'Annabel%'

/*
id: 16371 | Name: Annabel Miller | license_id: 490173 | address: 103 Franklin Ave | ssn: 318771143
*/

select * from person -- Now let's take a look at Northwestern Dr for out second witness
where address_street_name = 'Northwestern Dr'
order by address_number

/*
The Witness lives at the end of the road
id: 14887 | Name: Morty Schapiro | license_id: 118009 | address: 4919 Northwestern Dr | ssn: 111564949
*/

select * from interview -- Let's see what Annabel had to say
where person_id = 16371

/*
I saw the murder happen, and I recognized the killer from my gym 
when I was working out last week on January the 9th.
*/

select * from interview --checking what our second victim saw
where person_id = 14887

/*
This is what Morty had to say: 
I heard a gunshot and then saw a man run out. 
He had a "Get Fit Now Gym" bag. 
The membership number on the bag started with "48Z". 
Only gold members have those bags. 
The man got into a car with a plate that included "H42W".
*/

select *  -- This query things things down to all our possible subjects according to Annabel's interview
from Person as p
inner join get_fit_now_member as gfm
	on p.id = gfm.person_id
left join get_fit_now_check_in gfc
	on gfm.id = gfc.membership_id
where check_in_date = 20180109



select *  --This query containing all the clues we've gotten up to this point returns just one man, Jeremy Bowers
from Person as p
inner join get_fit_now_member as gfm
	on p.id = gfm.person_id
left join get_fit_now_check_in gfc
	on gfm.id = gfc.membership_id
left join drivers_license as d
	on d.id = p.license_id
where check_in_date = 20180109
	and gfm.id like '%48z%'
	and d.plate_number like '%H42W%'
	
--The murderer is Jeremy Bowers!

-- But there's a mastermind behind this all!!!
-- Here's what Jeremy has to say about the mastermind who hired him!
/*
I was hired by a woman with a lot of money. 
I don't know her name but I know she's around 5'5" (65") or 5'7" (67"). 
She has red hair and she drives a Tesla Model S. 
I know that she attended the SQL Symphony Concert 3 times in December 2017.
*/

select *  --All the clues of our mastermind put into one query
from Person as p
left join drivers_license as d
	on d.id = p.license_id
where d.height > 65
	and d.height < 67
	and d.car_make = 'Tesla'
	and d.car_model = 'Model S'
-- We've found 2 suspects behind this 
/*
id: 90700 | Name: Regina George | LicenseID: 291182 | Address: 332 Maple Ave | ssn: 337169072
	LicenseID: 291182 | Age:65 | Height: 66 | EyeColor: blue | hair: color red | gender: female | plate: 08CM64 Car: Tesla Model S

id: 99716 | Name: Miranda Priestly | LicenseID: 202298 | Address: 1883 Golden Ave | ssn: 987756388
	LicenseID: 202298 | Age:68 | Height: 66 | EyeColor: green | hair: red | gender: female | plate: 500123 | Car: Tesla Model S

*/

select * --This query is to look at the annual income of both our suspects
from Person as p
left join income as i
	on p.ssn = i.ssn
where p.ssn = 337169072
	or p.ssn = 987756388
	
--Regina George has no income, or is hiding it
--Miranda Priestly has an annual_income of 310,000

--This info doesn't prove anything

select * --I tried finding interviews for our suspects but neither have been talked to
from Person as p
left join interview as i
	on p.id = i.person_id
where p.ssn = 337169072
	or p.ssn = 987756388
	
	
select * -- This query was to check if they were in any events that could clue us in to anything
from Person as p
left join facebook_event_checkin as fb
	on fb.person_id = p.id
where p.ssn = 337169072
	or p.ssn = 987756388
	
-- I found that Regina George has not gone to any events
-- Miranda Priestly went to SQL Symphony Concert a few times, this shows that she has lots of money

-- I'm going to say that the mastermind is  Miranda Priestly


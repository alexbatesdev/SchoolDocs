use JoinDB
go

select * from Employee
select * from Task

-- list employees with tasks
select *
from Employee
inner join task
	on Employee.EmployeeId = Task.EmployeeId

-- list employees with or without tasks assigned
select *
from Employee
left outer join task
	on Employee.EmployeeId = Task.EmployeeId

-- list all tasks with or without employees
select *
from Employee
right outer join task
	on Employee.EmployeeId = Task.EmployeeId

-- list all records
select *
from Employee
full outer join task
	on Employee.EmployeeId = Task.EmployeeId

 -- list all employees without tasks
select e.EmployeeId, e.FirstName + ' ' + e.LastName as [Full Name], t.TaskName
from Employee as e
left join Task as t
	on e.EmployeeId = t.EmployeeId
where t.TaskName is null


-- STREAKS :)

-- list all tasks without employees
select e.EmployeeId, e.FirstName + ' ' + e.LastName as [Full Name], t.TaskName
from Employee as e
right join Task as t
	on e.EmployeeId = t.EmployeeId
where t.EmployeeId is null

-- List employees without tasks
-- AND last name starts with 'R'
select e.EmployeeId, e.FirstName, e.LastName, t.TaskName
from Employee as e
left join Task as t
	on e.EmployeeId = t.EmployeeId
where t.TaskId is null
	and e.LastName like 'R%'

-- List employees with tasks
-- AND first name starts with 'S'
select e.EmployeeId, e.FirstName, e.LastName, t.TaskName
from Employee as e
left join Task as t
	on e.EmployeeId = t.EmployeeId
where t.TaskId is not null
	and e.FirstName like 'S%'

-- sort the list of employees by how many hours they are scheduled

select e.EmployeeId, e.FirstName + ' ' + e.LastName as [Full Name], t.TaskName, t.EstimatedHours, t.WorkedHours, t.EstimatedHours - t.WorkedHours as [Scheduled Hours]
from Employee as e left join task as t
	on e.EmployeeId = t.EmployeeId
	where t.TaskId is not null
	and t.EstimatedHours > t.WorkedHours
	order by [Scheduled Hours]

-- list all possible combinations of employees and tasks
-- cartesian product
select e.EmployeeId, e.FirstName + ' ' + e.LastName AS [Full Name], t.TaskName
from Employee as e
cross join task as t
order by e.EmployeeId
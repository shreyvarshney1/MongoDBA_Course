# MongoDBA_Course
CREATE DATABASE ORG123;
SHOW DATABASES;
USE ORG123;

CREATE TABLE Worker (
	WORKER_ID INT NOT NULL PRIMARY KEY AUTO_INCREMENT,
	FIRST_NAME CHAR(25),
	LAST_NAME CHAR(25),
	SALARY INT(15),
	JOINING_DATE DATETIME,
	DEPARTMENT CHAR(25)
);

INSERT INTO Worker 
	(WORKER_ID, FIRST_NAME, LAST_NAME, SALARY, JOINING_DATE, DEPARTMENT) VALUES
		(001, 'Monika', 'Arora', 100000, '14-02-20 09.00.00', 'HR'),
		(002, 'Niharika', 'Verma', 80000, '14-06-11 09.00.00', 'Admin'),
		(003, 'Vishal', 'Singhal', 300000, '14-02-20 09.00.00', 'HR'),
		(004, 'Amitabh', 'Singh', 500000, '14-02-20 09.00.00', 'Admin'),
		(005, 'Vivek', 'Bhati', 500000, '14-06-11 09.00.00', 'Admin'),
		(006, 'Vipul', 'Diwan', 200000, '14-06-11 09.00.00', 'Account'),
		(007, 'Satish', 'Kumar', 75000, '14-01-20 09.00.00', 'Account'),
		(008, 'Geetika', 'Chauhan', 90000, '14-04-11 09.00.00', 'Admin');
SELECT * FROM Worker WHERE SALARY > 100000 AND DEPARTMENT = 'HR';
Select * from Worker where Salary > 200000;
select * from worker where salary between 100000 and 200000;
select * from worker where worker_id in (2,4,6,8);
SELECT * 
FROM Worker 
WHERE salary BETWEEN 200000 AND 400000 
AND WORKER_ID IN (1, 2, 3, 4, 5);
SELECT *  
FROM Worker  
WHERE DEPARTMENT = 'HR'  
ORDER BY SALARY ASC ;
SELECT MIN(SALARY) FROM Worker WHERE DEPARTMENT = 'HR';
SELECT FIRST_NAME, LAST_NAME  
FROM Worker  
WHERE DEPARTMENT = 'HR'  
AND SALARY = (SELECT MIN(SALARY) FROM Worker WHERE DEPARTMENT = 'HR');
SELECT DEPARTMENT, FIRST_NAME, LAST_NAME, SALARY
FROM Worker w
WHERE SALARY = (
    SELECT MAX(SALARY) 
    FROM Worker 
    WHERE DEPARTMENT = w.DEPARTMENT
);
SELECT FIRST_NAME, LAST_NAME, SALARY, DEPARTMENT  
FROM Worker  
WHERE (DEPARTMENT = 'Admin' OR DEPARTMENT = 'Account')  
AND SALARY = (
    SELECT MIN(SALARY) 
    FROM Worker 
    WHERE DEPARTMENT IN ('Admin', 'Account') 
    AND SALARY = (SELECT MIN(SALARY) FROM Worker WHERE DEPARTMENT IN ('Admin', 'Account'))
);
select * from worker;
select distinct(department) from worker;
#alias - help u to give some temp name for a coloumn
select worker_id as emp_code from worker;
SELECT worker_id FROM worker
UNION #Union print once and union all print twice
SELECT first_name FROM worker;
SELECT DEPARTMENT, SALARY 
FROM Worker 
WHERE DEPARTMENT = 'HR' AND SALARY > 100000
UNION
SELECT DEPARTMENT, SALARY 
FROM Worker 
WHERE DEPARTMENT = 'Admin' AND SALARY > 100000
ORDER BY SALARY;
SELECT DEPARTMENT,WORKER_ID 
FROM Worker 
WHERE DEPARTMENT = 'HR'
UNION
SELECT DEPARTMENT, WORKER_ID 
FROM Worker 
WHERE DEPARTMENT = 'Account'
ORDER BY WORKER_ID;
SELECT worker_id, first_name,department,
CASE
    WHEN salary > 300000 THEN 'Rich people'
    WHEN salary <=300000 && salary >=100000 THEN 'Middle stage'
    ELSE 'Poor people'
END 
AS People_stage_wise
FROM worker;
SELECT DEPARTMENT, COUNT(WORKER_ID) AS Total_Employees
FROM Worker
GROUP BY DEPARTMENT
ORDER BY DEPARTMENT;
SELECT DISTINCT DEPARTMENT,
       (SELECT COUNT(*) 
        FROM Worker AS W2 
        WHERE W2.DEPARTMENT = W1.DEPARTMENT) AS Total_Employees
FROM Worker AS W1
ORDER BY DEPARTMENT;
SELECT 'HR' AS DEPARTMENT, COUNT(WORKER_ID) AS Total_Employees
FROM Worker 
WHERE DEPARTMENT = 'HR'
UNION
SELECT 'Admin' AS DEPARTMENT, COUNT(WORKER_ID) AS Total_Employees
FROM Worker 
WHERE DEPARTMENT = 'Admin'
UNION
SELECT 'Account' AS DEPARTMENT, COUNT(WORKER_ID) AS Total_Employees
FROM Worker 
WHERE DEPARTMENT = 'Account'
ORDER BY DEPARTMENT;

Select * from worker;
(
  SELECT department, COUNT(department) AS total_employees
  FROM worker
  GROUP BY department
  ORDER BY total_employees ASC
  LIMIT 1
)

UNION ALL

(
  SELECT department, COUNT(department) AS total_employees
  FROM worker
  GROUP BY department
  ORDER BY total_employees DESC
  LIMIT 1
);
Create Table Persons(
	ID int not null,
    LastName varchar(255) NOT NULL,
	FirstName varchar(255) NOT NULL,
	Age int
);

Alter table persons
add unique(ID);

Create Table Persons1(
	ID int primary key,
    LastName varchar(255) NOT NULL unique,
	FirstName varchar(255) NOT NULL unique,
	Age int
    #primary key(ID)
);
desc Persons1;
create table category(
c_id int primary key,
c_name varchar(25) not null unique,
c_decrp varchar(250) not null
);

insert into category values (102, 'furnitures', 'it stores all set of wooden items');
select * from category;
desc category;

use org123;
CREATE TABLE Products (
    P_ID int primary key,
    p_Name varchar(250) NOT NULL,
    c_id int ,
    CONSTRAINT c_id FOREIGN KEY (c_id)
    REFERENCES category(c_id)
);
show tables from org123;
desc products;
drop table products;
insert into products values (904, 'Wooden table', null);
select * from products;
select * from category;
delete from category where c_id=101;
drop table category;

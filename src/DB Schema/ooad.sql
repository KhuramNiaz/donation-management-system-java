create table project
(
id int primary key,
name varchar(MAX),
description varchar(MAX),
budgetRequired int,
currentBalance int,

)
go
create table donor
(
id int primary key,
name varchar(MAX),
cnic varchar(MAX),
address varchar(MAX),

)

go
create table donation
(
id int primary key,
amount int,
donorid int,
date varchar(MAX),
projectid int,

)
go

create table pledge
(
id int primary key,
monthlyAmount int,
durationMonths int,
donorid int,
projectid int,

)
go

create table beneficiery
(
id int primary key,
name varchar(MAX),
cnic varchar(MAX),
address varchar(MAX),
projectid int,

)

go

create table funding
(
id int primary key,
amount int,
date varchar(MAX),
beneficieryId int, 
projectid int,

)


go
create table volunteer
(
id int primary key,
name varchar(MAX),
status int,
projectid int,

)
go
create table assesor
(
projectid int,
volunteerid int,
)

go

create table projectTeam
(
projectid int,
volunteerid int,
)
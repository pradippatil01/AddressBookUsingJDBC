use addressBook_services;

create table persondata(
personId int auto_increment,
firstName varchar(20) not null,
lastName varchar(20) not null,
city varchar(20) not null,
state varchar(20) not null,
zipCode int not null,
phoneNumber numeric ,
dateAdded DATE NOT NULL,
primary key (personId)
);

insert into persondata (firstname,lastname,city,state,zipcode,phoneNumber,dateAdded) values('Pradip','Patil','Pune','Maharashtra',411027,9665353284,'2018-01-03');
insert into persondata (firstname,lastname,city,state,zipcode,phoneNumber,dateAdded) values('Avinash','Patil','Pune','Maharashtra',411027,9834157022,'2018-01-05');
insert into persondata  (firstname,lastname,city,state,zipcode,phoneNumber,dateAdded) values('Ketan','Sharma','shirpur','Maharashtra',425427,9923456734,'2018-02-01');
insert into persondata (firstname,lastname,city,state,zipcode,phoneNumber,dateAdded) values('Mehul','Patil','sendwa','Madhypradesh',394312,8866675434,'2019-01-06');


select * from persondata;

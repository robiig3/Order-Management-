drop database if exists orderManagement;
create database if not exists orderManagement;
use orderManagement;
drop table if exists client;
drop table if exists product;
drop table if exists orders;
SET GLOBAL time_zone = '+3:00';
create table if not exists client(
	id_client int unique primary key auto_increment,
    name varchar(45) default null,
    address varchar(45) default null
);

create table if not exists product(
	id_product int unique primary key auto_increment,
	name varchar(45) default null,
    stock int default null,
    price float default null
);

create table if not exists orders(
	
    id_order int unique primary key auto_increment,
    id_client int,
    id_product int,
    quantity int default null,
    total_price float default null,
    
    foreign key(id_client) references client(id_client) on delete cascade,
    foreign key(id_product) references product(id_product) on delete cascade
);

insert into client(name, address) values
("Muresan Roberta", "Cluj, Cluj-Napoca, Viilor 29"),
("Baciu Iulia", "Sibiu, Medias, Parlea 23"),
("Muresan Klara", "Mures, Tarnaveni, Mediasului 14");

insert into product(name, stock, price) values
("milk", 10, 3),
("honey", 35, 30),
("yogurt", 50, 2),
("mila oreo chocolate", 70, 5);

insert into orders(id_client, id_product, quantity, total_price) values
(1, 4, 5, 25),
(2, 3, 10, 20),
(3, 1, 4, 12);

-- drop trigger total_price_trigger;
select * from client;
select * from product;
select * from orders;

-- delete from product where id_product = 3;
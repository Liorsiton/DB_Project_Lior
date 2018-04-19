
create database chainShops;
use chainShops;
create table cities (
id int not null auto_increment,
name varchar(20) not null,
PRIMARY KEY (ID)
);

create table streets (
id int not null auto_increment,
name varchar(20) not null,
PRIMARY KEY (ID)
);

create table chains (
id int not null auto_increment,
name varchar(20) not null,
PRIMARY KEY (ID)
);

create table mall_groups (
id int not null auto_increment,
name varchar(20) not null,
PRIMARY KEY (ID)
);

create table malls (
id int not null auto_increment,
name varchar(20) not null,
city int ,
street int,
stree_number int not null,
mall_group int,
PRIMARY KEY (ID),
FOREIGN KEY (city) REFERENCES cities(id),
FOREIGN KEY (street) REFERENCES streets(id),
FOREIGN KEY (mall_group) REFERENCES mall_groups(id)
);

create table shops (
id int not null auto_increment,
name varchar(20) not null,
city int ,
street int,
stree_number int null,
chain int,
mall int,
shop_number_in_mall int,
PRIMARY KEY (ID),
FOREIGN KEY (city) REFERENCES cities(id),
FOREIGN KEY (street) REFERENCES streets(id),
FOREIGN KEY (mall) REFERENCES malls(id),
FOREIGN KEY (chain) REFERENCES chains(id)
);

create table employees (
id int not null auto_increment,
name varchar(20) not null,
shop int,
chain int,
PRIMARY KEY (ID),
FOREIGN KEY (shop) REFERENCES shops(id),
FOREIGN KEY (chain) REFERENCES chains(id)
);
insert into cities(name) value('Tel-Aviv');
insert into cities(name) value('Jerusalem');
insert into cities(name) value('Haifa');

insert into streets(name) value('Ben-Gurion'),('Herzel'),('Galil');
insert into chains(name) value('Fox'),('Castro');
insert into mall_groups(name) values('Azrieli'),('Ofer');
insert into malls(name,city,street,stree_number,mall_group)  values('Azrieli_TA',1,1,5,1),('Malcha',2,2,50,2);
insert into shops(name,city,street,stree_number,chain,mall,shop_number_in_mall) values('Fox_Azrieli_TA',null,null,null,1,1,20);
insert into shops(name,chain,mall,shop_number_in_mall) values('Fox_Malcha',1,2,30);

insert into employees(name,shop,chain)value('Eli Mizrachi',1,1);

use s2emp;
drop table if exists customer;
create table customer (
  id integer(5) unsigned not null auto_increment,
  firstName varchar(25) not null,
  lastName varchar(25) not null,
  age integer(3) not null,
  homeState varchar(25) not null,
  purchase varchar(25) not null,
  price decimal(7,2) not null,
  primary key(id)
);

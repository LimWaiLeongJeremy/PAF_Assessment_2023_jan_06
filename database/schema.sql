drop schema if exists eshop;

select "Creating eshop database" as "";

create database eshop;

use eshop;

select "Creating customers table" as "";

create table customers (
	name 			varchar(128) 			not null,
	address 		varchar(512) 			not null,
	email 			varchar(512) 			not null,

	primary key(name)
);

insert into customers values
("fred", "201 Cobblestone Lane", "fredflintstone@bedrock.com") , 
("sherlock", "221B Baker Street, London", "sherlock@consultingdetective.org") ,
("spongebob", "124 Conch Street, Bikini Bottom"," spongebob@yahoo.com") ,
("jessica", "698 Candlewood Land, Cabot Cove", "fletcher@gmail.com") ,
("dursley", "4 Privet Drive, Little Whinging, Surrey", "dursley@gmail.com");


	-- last_update TIMESTAMP DEFAULT CURRENT_TIMESTAMP 
	-- 	ON UPDATE CURRENT_TIMESTAMP,
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



	-- last_update TIMESTAMP DEFAULT CURRENT_TIMESTAMP 
	-- 	ON UPDATE CURRENT_TIMESTAMP,
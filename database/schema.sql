drop schema if exists eshop;

select "Creating eshop database" as "";

create database eshop;

use eshop;

select "Creating customers table" as "";

drop table if exists customers;

create table customers (
	name 			char(32) 			not null,
	address 		char(128) 			not null,
	email 			char(128) 			not null,

	primary key(name)
);

insert into customers values
("fred", "201 Cobblestone Lane", "fredflintstone@bedrock.com") , 
("sherlock", "221B Baker Street, London", "sherlock@consultingdetective.org") ,
("spongebob", "124 Conch Street, Bikini Bottom"," spongebob@yahoo.com") ,
("jessica", "698 Candlewood Land, Cabot Cove", "fletcher@gmail.com") ,
("dursley", "4 Privet Drive, Little Whinging, Surrey", "dursley@gmail.com");


select "Creating orders table" as "";

drop table if exists orders;

create table orders (
	order_id 			char(8) 						        not null,
    item                char(128)                                        ,
    quantity            int                                             ,
    name 			    char(32) 						        not null,

	primary key(order_id),
    constraint fk_name
        foreign key(name)
        references customers(name)
);

select "Creating order_status table" as "";

drop table if exists order_status;

create table order_status (
    delivery_id 		char(8)                  		not null,
    status   		    enum('pending', 'dispatched')  	default 'pending',
	status_update 		TIMESTAMP DEFAULT CURRENT_TIMESTAMP 
		                ON UPDATE CURRENT_TIMESTAMP,
    order_id 			char(8) 						not null,

	primary key(delivery_id),
    constraint fk_order_id
        foreign key(order_id)
        references orders(order_id)
);




	-- last_update TIMESTAMP DEFAULT CURRENT_TIMESTAMP 
	-- 	ON UPDATE CURRENT_TIMESTAMP,
create table Bank_acc
	(bank_id				varchar(6),
	 bank_account_number   			numeric (10),
	 primary key (bank_id)
	);

create table City_and_states
	(postal_code_id				varchar(6), 
	 city					varchar(20), 
	 states		        		varchar(20),
	 postal_code           			varchar(6),
	 primary key (Postal_code_id)
	);

create table Contact_info
	(contact_id				varchar(6), 
	 first_name				varchar(20), 
	 last_name				varchar(20),
	 email					varchar(35),
	 phone_number				numeric(10,0),
	 primary key (contact_id)
	);

create table Members
	(member_id				varchar(6), 
	 shipping_house_number			varchar(20), 
	 shipping_Street_name			varchar(35), 
	 shipping_postal_code_id		varchar(6),
	 billing_house_number			varchar(20), 
	 billing_Street_name			varchar(35),
	 billing_postal_code_id			varchar(6),
	 contact_id				varchar(6),
	 password                               varchar(15),
	 primary key (member_id),
	 foreign key (contact_id) references Contact_info,
	 foreign key (shipping_postal_code_id) references City_and_states(postal_code_id),
	 foreign key (billing_postal_code_id) references City_and_states(postal_code_id)
	);

create table Publisher
	(publisher_id				varchar(6), 
	 house_number				varchar(6),
	 street_name				varchar(35), 
	 postal_code_id				varchar(6),
	 bank_id				varchar(6),
	 contact_id				varchar(6),
	 primary key (publisher_id),
	 foreign key (postal_code_id) references City_and_states,
	 foreign key (bank_id) references Bank_acc,
	 foreign key (contact_id) references Contact_info 
	);

create table Book
	(isbn					varchar(13), 
         title					varchar(35),
	 author_first_name			varchar(20), 
	 author_last_name			varchar(20), 
	 genre					varchar(20),
	 page_number				numeric(6,0),
	 price 					numeric(6,2),
	 royalty_percentage 			numeric(2,0),
	 quantity_sold				numeric(6,0),
	 quantity_available			numeric(6,0),
	 publisher_id				varchar(6),
	 primary key (isbn),
	 foreign key (publisher_id) references Publisher
	);

create table Orders
	(order_id				varchar(6), 
	 status                 		varchar(10),
	 date_placed            		DATE NOT NULL DEFAULT CURRENT_DATE,
	 shipping_house_number			varchar(20), 
	 shipping_Street_name			varchar(20), 
	 billing_house_number			varchar(20), 
	 billing_Street_name			varchar(20),
	 member_id				varchar(6),
	 shipping_postal_code_id		varchar(6),
	 billing_postal_code_id			varchar(6),
	 primary key (order_id),
	 foreign key (member_id) references Members,
	 foreign key (shipping_postal_code_id) references City_and_states(postal_code_id),
	 foreign key (billing_postal_code_id) references City_and_states(postal_code_id)
	);

create table placeOrder
	(place_id				varchar(6),
	 order_id				varchar(6),
	 isbn					varchar(13),
	 primary key(place_id),
	 foreign key(order_id) references Orders,
	 foreign key(isbn) references Book 
	);




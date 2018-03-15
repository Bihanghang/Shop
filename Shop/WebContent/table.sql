create table users(
	user_name nvarchar(20) null,
	user_paswprd nvarchar(20) null,
	user_sex nvarchar(4) null,
	user_addr nvarchar(50) null,
	user_phone nvarchar(11) primary key not null,
	user_birth nvarchar(20) null,
	user_jifen int null,
	user_touxiang nvarchar(100)                      
);

create table product(
	item nvarchar(20) null,
	Product_Name nvarchar(20) primary key not null,
	Unit_Price nvarchar(50) null,
	Delivery_Details  TINYTEXT null                
);

create table cart(
    -> user_phone varchar(11) not null,
    -> itemid  int(10) null
    -> );


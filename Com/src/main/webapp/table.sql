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
     user_phone varchar(11) not null,
     itemid  int(10) null,
     itemnum int(4) null
     );
    
create table message(
	user nvarchar(20) null,
	to_mess  TINYTEXT null,
	to_date datetime null,
	to_user nvarchar(20) null,
	isSelf tinyint null
);
 insert into user (user_name,user_password,user_sex,user_addr,user_phone,
 user_birth,user_jifen,user_touxiang) values("葫芦娃","456",'男','广东','159','97-10-23',100,'lk');
 
 insert into product(pro_name,pro_brand,pro_size,pro_color,pro_discount,pro_classify,pro_price,pro_photo,pro_describe) 
 					values('进化手表',' Watch','Medium','Black',5,'四星',400.0,'p-7','explore now');
 
 

create table customers(
customer_id integer primary key,
first_name varchar2(10) not null,
last_name varchar2(10) not null,
dob date,
phone varchar2(12)
);

create table product_types(
  product_type_id Integer primary key,
  product_type_name varchar2(20)
);

create table products(
 product_id integer primary key,
 product_type_id integer
 constraint products_fk_product_types
 references product_types(product_type_id),
 name varchar2(30) not null,
 description varchar2(50),
 price number(5,2)
);

create table purchases(
  product_id integer constraint purchases_fk_products
  references products(product_id),
  customer_id integer constraint purchase_fk_customer
  references customers(customer_id),
  quantity integer not null,
  constraint purchaes_pk primary key (product_id ,customer_id )  
);

create table employees(
employee_id integer primary key,
manager_id integer,
first_name varchar2(10) not null,
last_name varchar2(10) not null,
title varchar2(20),
salay number(6)
);

create table salary_grades(
salary_grades_id integer primary key,
low_salay number(6,0),
high_salay number(6,0)
);
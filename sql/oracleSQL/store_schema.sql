CREATE TABLE customers (
  customer_id INTEGER PRIMARY KEY,
  first_name varchar2(10) NOT NULL,
  last_name varchar2(10) NOT NULL,
  dob         DATE,
  phone varchar2(12)
);

CREATE TABLE product_types (
  product_type_id INTEGER PRIMARY KEY,
  product_type_name varchar2(20)
);

CREATE TABLE products (
  product_id      INTEGER PRIMARY KEY,
  product_type_id INTEGER
  CONSTRAINT products_fk_product_types
  REFERENCES product_types(product_type_id),
  name varchar2(30) NOT NULL,
  description varchar2(50),
  price NUMBER (5, 2)
);

CREATE TABLE purchases (
  product_id  INTEGER CONSTRAINT purchases_fk_products
  REFERENCES products(product_id),
  customer_id INTEGER CONSTRAINT purchase_fk_customer
  REFERENCES customers(customer_id),
  quantity    INTEGER NOT NULL,
  CONSTRAINT purchaes_pk PRIMARY KEY (product_id, customer_id)
);

CREATE TABLE employees (
  employee_id INTEGER PRIMARY KEY,
  manager_id  INTEGER,
  first_name varchar2(10) NOT NULL,
  last_name varchar2(10) NOT NULL,
  title varchar2(20),
  salay NUMBER (6)
);

CREATE TABLE salary_grades (
  salary_grades_id INTEGER PRIMARY KEY,
  low_salay NUMBER (6, 0),
  high_salay NUMBER (6, 0)
);

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
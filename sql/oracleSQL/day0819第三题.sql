--1.编写一个查询，显示与Susan Jones同一个上司的其他员工的姓名，但是Susan Jones不包含在显示的结果内
select c2.first_name||last_name name from 
employees c1, employees c2 
where c1.manager_id = c2.manager_id and c1.first_name = 'Susan' 
and c1.last_name = 'Jones' and c2.first_name != 'Jones';

--2编写一个查询，显示工资大于平均工资的员工的编号及姓名，输出结果按工资降序排列
select emp.employee_id,emp.first_name||emp.last_name name
from employees emp
where salary>(select avg(salary) from employees)
order by emp.salary desc;

--3编写一个查询，显示编号为2的产品的订购数量
select count(*),sum(quantity) from purchases pur
where pur.product_id =2;

--4编写一个查询，显示购买过的顾客以及所购买的产品信息，按下面指定的列名显示结果。
--cstm_id,first_name,last_name,prd_id,prd_name,quantity
select .. from purchases pur,customers cst,products prd 
where pur.product_id = prd.product_id and 
pur.customer_id = cst.customer_id;
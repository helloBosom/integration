--1.��дһ����ѯ����ʾ��Susan Jonesͬһ����˾������Ա��������������Susan Jones����������ʾ�Ľ����
select c2.first_name||last_name name from 
employees c1, employees c2 
where c1.manager_id = c2.manager_id and c1.first_name = 'Susan' 
and c1.last_name = 'Jones' and c2.first_name != 'Jones';

--2��дһ����ѯ����ʾ���ʴ���ƽ�����ʵ�Ա���ı�ż������������������ʽ�������
select emp.employee_id,emp.first_name||emp.last_name name
from employees emp
where salary>(select avg(salary) from employees)
order by emp.salary desc;

--3��дһ����ѯ����ʾ���Ϊ2�Ĳ�Ʒ�Ķ�������
select count(*),sum(quantity) from purchases pur
where pur.product_id =2;

--4��дһ����ѯ����ʾ������Ĺ˿��Լ�������Ĳ�Ʒ��Ϣ��������ָ����������ʾ�����
--cstm_id,first_name,last_name,prd_id,prd_name,quantity
select .. from purchases pur,customers cst,products prd 
where pur.product_id = prd.product_id and 
pur.customer_id = cst.customer_id;
create or replace procedure DisplayComm(emp_num in number,comm out number)
as
emp_sal emp.sal%type;
mycomm emp.comm%type;
begin
select sal,comm into emp_sal,mycomm from emp where empno = emp_num;
if emp_sal > 1500 then 
comm := mycomm+1000;
end if;
exception
when no_data_found then dbms_output.put_line('���޴��ˣ�')��
end DisplayComm;

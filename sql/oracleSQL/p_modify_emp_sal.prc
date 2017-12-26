create or replace procedure p_modify_emp_sal is
v_emp_old_sal employees.salary%type;
v_emp_new_sal employees.salary%type;

begin
  select salary into v_emp_old_sal from employees where employee_id = 1002;
  update employees set salary = 6000.00 where employee_id = 1002;
  dbms_output.put_line('old salary:'||v_emp_old_sal);
   dbms_output.put_line('new salary:'||v_emp_new_sal);
  exception 
  when no_data_found then 
  dbms_output.put_line('no data');
  when others then 
  dbms_output.put_line('error');
end p_modify_emp_sal;


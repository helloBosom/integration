create or replace procedure p_count_emp(v_count out number)
is
begin
  select count(*) into v_count from emp where sal>10;
  exception
  when others then 
  dbms_output.put_line('error');
end p_count_emp;


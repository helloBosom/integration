create or replace procedure p_insert_dept(v_deptno in number,v_deptname varchar2,v_loc varchar2) 
is
begin
    insert into dept(deptno,dname,loc)
    values(v_deptno,v_deptname,v_loc);
    commit;
    exception
    when others then
    dbms_output.put_line('error!');
end p_insert_dept;


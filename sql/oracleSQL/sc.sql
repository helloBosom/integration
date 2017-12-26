DECLARE
v_account NUMBER;
BEGIN
p_count_emp(v_account);
dbms_output.put_line(v_account);
END;

BEGIN
p_insert_dept(60, 'sales', 'xian');
END;
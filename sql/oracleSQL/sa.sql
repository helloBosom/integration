CREATE OR REPLACE PROCEDURE DisplaySal
IS
newsal emp.sal%type;
oldsal emp.sal%type;
BEGIN
SELECT sal
INTO oldsal
FROM emp
WHERE ename = 'ALLEN';
UPDATE emp
SET sal = sal + sal * 0.5
WHERE ename = 'ALLEN';
SELECT sal
INTO newsal
FROM emp
WHERE ename = 'ALLEN';
dbms_output.put_line('ALLENԭ���Ĺ����ǣ�'||oldsal||'���º�Ĺ����ǣ�'||newsal);
exception
WHEN no_data_found THEN
dbms_output.put_line('���޴���');
END DisplaySal;


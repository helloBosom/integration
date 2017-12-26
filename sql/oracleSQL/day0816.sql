SELECT
  empno,
  jobId
FROM emp
WHERE empno = 7369;

DECLARE
v_empno emp.empno % TYPE;
v_job   emp.job%TYPE;
BEGIN
SELECT
  empno,
  jobId
INTO v_empno, v_job
FROM emp
WHERE empno = 7369;
dbms_output.put_line('v_empno:' || v_empno);
dbms_output.put_line('v_job:' || v_job);
END;

SELECT *
FROM emp;

DECLARE
v_test1 VARCHAR2(30) NOT NULL := 'test1';
v_test2 v_test1%TYPE := 'test3';
BEGIN
v_test2 := v_test1 || 'and test2';
dbms_output.put_line(v_test2);
END;

DECLARE
v_sal emp.sal % TYPE;
BEGIN
SELECT sal
INTO v_sal
FROM emp
WHERE empno = 7839;
IF v_sal > 5000
THEN
dbms_output.put_line('o');
ELSIF v_sal > 2000
THEN
dbms_output.put_line('n');
ELSE
dbms_output.put_line('add');
END IF;
END;

DECLARE
v_count     NUMBER(3) := 0;
v_sumResult NUMBER (5) := 0;
BEGIN
LOOP
v_count := v_count + 1;
v_sumResult := v_sumResult + v_count;
IF (v_count >= 100)
THEN
EXIT;
END IF;
END LOOP;
dbms_output.put_line('sum:' || v_sumResult);
END;

DECLARE
v_count     NUMBER(4) := 0;
v_sumResult NUMBER (6) := 0;
BEGIN
LOOP
v_count := v_count + 1;
v_sumResult := v_sumResult + v_count;
EXIT WHEN (v_count >= 100);
END LOOP;
dbms_output.put_line(v_sumResult);
END;

DECLARE
v_count     NUMBER(4) := 0;
v_sumResult NUMBER (6) := 0;
BEGIN
WHILE (v_count < 100) LOOP
v_count := v_count + 1;
v_sumResult := v_sumResult + v_count;
END LOOP;
dbms_output.put_line(v_sumResult);
END;


DECLARE
v_count     NUMBER(4) := 0;
v_sumResult NUMBER (6) := 0;
BEGIN
FOR v_count IN 1..100 LOOP
v_sumResult := v_sumResult + v_count;
END LOOP;
dbms_output.put_line(v_sumResult);
END;

DECLARE
v_count     NUMBER(6) := 1;
v_sumResult NUMBER (6) := 1;
v_temp NUMBER (6) := 1;
BEGIN
WHILE (v_count < 5) LOOP
v_sumResult := v_sumResult * v_count;
v_count := v_count + 1;
END LOOP;
v_temp := v_sumResult;
WHILE (v_count > 0) LOOP
v_sumResult := v_temp / v_count + v_sumResult;
v_count := (v_count - 1) * v_count;
END LOOP;
dbms_output.put_line(v_sumResult);
END;

DECLARE
TYPE T_PRODUCT_RECORD_TYPE IS RECORD (v_product_id products.product_id%TYPE,
v_name products.name%TYPE, v_price products.price%TYPE);
v_product_record T_PRODUCT_RECORD_TYPE;
BEGIN
SELECT
  product_id,
  name,
  price
INTO v_product_record
FROM products
WHERE product_id = '10002';
dbms_output.put_line(v_product_record.v_product_id);
dbms_output.put_line(v_product_record.v_name);
dbms_output.put_line(v_product_record.v_price);
END;
SELECT *
FROM products;

DECLARE
TYPE T_STUDENT_RECORD_TYPE IS RECORD (
v_stuid VARCHAR2(3), v_stuname VARCHAR2(10));
v_stu1_record T_STUDENT_RECORD_TYPE;
v_stu2_record T_STUDENT_RECORD_TYPE;
BEGIN
v_stu1_record.v_stuid := 1;
v_stu1_record.v_stuname := 'www';

v_stu2_record := v_stu1_record;
v_stu1_record.v_stuid := 2;
v_stu1_record.v_stuname := 'aaa';
dbms_output.put_line('stuid1:' || v_stu1_record.v_stuid);
dbms_output.put_line('stuname1:' || v_stu1_record.v_stuname);
dbms_output.put_line('stuid2:' || v_stu2_record.v_stuid);
dbms_output.put_line('stuid2:' || v_stu2_record.v_stuname);
END;

DECLARE
CURSOR v_cur_product IS
SELECT *
FROM products
WHERE product_type_id = 1;
v_product_info products%ROWTYPE;
BEGIN
OPEN v_cur_product;
FETCH v_cur_product INTO v_product_info;
WHILE v_cur_product% FOUND LOOP
dbms_output.put_line(v_product_info.product_id);
FETCH v_cur_product INTO v_product_info;
END LOOP;
dbms_output.put_line(v_cur_product%ROWCOUNT);
CLOSE v_cur_product;
END;

DECLARE TYPE T_PRODUCT_RECORD_TYPE IS RECORD (v_product_id products.product_id%TYPE,
v_name products.name%TYPE, v_price products.price%TYPE);
v_product_record T_PRODUCT_RECORD_TYPE;
CURSOR t_cur_product RETURN T_PRODUCT_RECORD_TYPE IS SELECT
                                                       product_id,
                                                       name,
                                                       price
                                                     FROM products;
BEGIN
OPEN t_cur_product;
LOOP
FETCH t_cur_product INTO v_product_record;
EXIT WHEN t_cur_product%NOTFOUND;
dbms_output.put_line(v_product_record.v_product_id || '  ' ||
v_product_record.v_name || ' '
|| v_product_record.v_price);
END LOOP;
IF (t_cur_product%ISOPEN)
THEN
CLOSE t_cur_product;
END IF;
END;

DECLARE
CURSOR v_cur_product IS
SELECT
  product_id,
  name,
  price
FROM products;
v_product_record v_cur_product%ROWTYPE;
BEGIN
FOR v_product_record IN v_cur_product LOOP
dbms_output.put_line(v_product_record.product_id || '  ' ||
v_product_record.name || '  ' || v_product_record.price);
END LOOP;
END;


DECLARE
v_product_record products % ROWTYPE;
BEGIN
FOR v_product_record IN ( SELECT *
FROM products) LOOP
dbms_output.put_line(v_product_record.product_id || ' ' ||
v_product_record.name || '  ' || v_product_record.price);
END LOOP;
END;

DECLARE
v_count     NUMBER(6) := 100000;
v_productId product_change.product_id%TYPE;
BEGIN
SELECT max(product_id)
INTO v_productId
FROM product_change;
IF v_productId IS NULL
THEN
v_productId := 1;
ELSE
v_productId := v_productId + 1;
END IF;
WHILE (v_count > 0) LOOP
INSERT INTO product_change (product_id, product_type_id, product_name, price)
VALUES (v_productId, 1, 'product' || v_productId, dbms_random.value * 1000);
v_count := v_count - SQL %ROWCOUNT;
v_productId := v_productId + 1;
END LOOP;
COMMIT;
END;

DECLARE
CURSOR cur_product IS SELECT
                        product_id,
                        product_type_id,
                        name,
                        price
                      FROM products;
v_product_info cur_product%ROWTYPE;
v_count NUMBER (3) := 0;
BEGIN
FOR v_product_info IN cur_product LOOP
SELECT count(*)
INTO v_count
FROM product_change pc
WHERE pc.product_id = v_product_info.product_id;
IF (v_count = 0)
THEN
INSERT INTO product_change VALUES (
v_product_info.product_id, v_product_info.product_type_id,
v_product_info.name, v_product_info.price);
ELSE
UPDATE product_change
SET product_type_id = v_product_info.product_type_id,
  product_name      = v_product_info.name, price = v_product_info.price
WHERE product_id = v_product_info.product_id;
END IF;
END LOOP;
COMMIT;
END;

SELECT *
FROM product_change;
SELECT
  e.empno,
  e.ename,
  e.sal,
  e.deptno,
  d.dname
FROM emp e
LEFT JOIN dept d ON e.deptno = d.deptno;

SELECT
  e.empno,
  e.ename,
  e.job,
  e.mgr,
  deptno,
  d.dname,
  d.loc
FROM emp e
INNER JOIN dept d USING (deptno);

SELECT *
FROM emp
WHERE LOWER(ename) = 'king';

SELECT *
FROM emp
WHERE Upper(ename) = 'KING';

SELECT INSTR('my name is lv.', 'lv')
FROM DUAL;

SELECT instr('you are my wife', 'wife')
FROM dual;
SELECT initcap('you are my wife')
FROM dual;

SELECT
  ASCII('A'),
  CHR(65)
FROM DUAL
SELECT
  ascii('s'),
  chr(98)
FROM dual;

SELECT length('hello world')
FROM dual;

SELECT
  c.customer_id,
  LPAD(c.first_name, 20, '*')
FROM customers c

SELECT
  ename,
  lpad(ename, 10, '$$')
FROM emp;

SELECT 'hello world'
FROM dual;

SELECT LTRIM('    ZHANG SAN.    ')
FROM DUAL;

SELECT ltrim('    hello world ')
FROM dual;

SELECT TO_CHAR(23456.78954, 'S099999.99').FROM DUAL;

SELECT to_char(123456.789654, '909999d999999')
FROM dual;

SELECT TO_NUMBER('23456.789')
FROM DUAL;

SELECT to_number('123456.789')
FROM dual;

SELECT
  CAST(234.456 AS VARCHAR2(10)),
  CAST('1998-09-03' AS DATE),
  CAST('123' AS NUMBER(5)),
  CAST(DATE '1998-09-05' AS VARCHAR2(20))
FROM DUAL;

SELECT
  cast(123.456 AS VARCHAR2(10)),
  cast('05-98年 -98' AS DATE),
  cast(DATE '1998-09-05' AS VARCHAR2(20))
FROM dual;

SELECT
  deptno,
  count(*)
FROM emp
GROUP BY deptno
HAVING count(*) > 3;

SELECT to_char(sysdate, 'CC')
FROM dual;

INSERT INTO customers VALUES (7, 'test', 'test', DATE '1989-9-2', '0299878998');

INSERT INTO customers (customer_id, first_name, last_name, dob) VALUES (9, 'test', 'test', '12-6月-98');

ALTER SESSION SET nls_date_language = 'american'
SELECT
  c.*,
  to_char(c.dob, 'CC YY/mon/DD Dy HH24:MI:SS') dob1
FROM customers c;

SELECT DATE '1989-9-2'
FROM dual;

SELECT
	p.product_id,
	p. NAME,
	t.c,
	t.s
FROM
	products p
INNER JOIN (
	SELECT
		pur.product_id,
		COUNT(*) c,
		SUM(pur.quantity) s
	FROM
		purchases pur
	GROUP BY
		pur.product_id
) t ON p.product_id = t.product_id;

SELECT
	t.product_type_id,
	t.product_type_name,
	t2.avg_price,
	t2.c
FROM
	product_types t,
	(
		SELECT
			p.product_type_id,
			AVG(price) avg_price,
			COUNT(*) c
		FROM
			products p
		GROUP BY
			p.product_type_id
	) t2
WHERE
	t.product_type_id = t2.product_type_id;
                
SELECT
	c2.first_name || last_name NAME
FROM
	employees c1,
	employees c2
WHERE
	c1.manager_id = c2.manager_id
AND c1.first_name = 'Susan'
AND c1.last_name = 'Jones'
AND c2.first_name != 'Jones';

SELECT
	emp.employee_id,
	emp.first_name || emp.last_name NAME
FROM
	employees emp
WHERE
	salary > (
		SELECT
			avg(salary)
		FROM
			employees
	)
ORDER BY
	emp.salary DESC;

SELECT
	count(*),
	sum(quantity)
FROM
	purchases pur
WHERE
	pur.product_id = 2;

SELECT
	..
FROM
	purchases pur,
	customers cst,
	products prd
WHERE
	pur.product_id = prd.product_id
AND pur.customer_id = cst.customer_id;
/*
 *存储过程 
 */
CREATE OR REPLACE PROCEDURE PrpWage IS
num1 NUMBER;
sum1 NUMBER;
BEGIN
SELECT count(*)
INTO num1
FROM ProWage
WHERE
  Wage < 2000;
SELECT count(*)
INTO sum1
FROM ProWage;


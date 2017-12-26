CREATE OR REPLACE PROCEDURE swap(num1 IN OUT NUMBER, num2 IN OUT NUMBER )
IS
temp NUMBER;
BEGIN
temp := num1;
num1 := num2;
num2 :=temp;
END swap;


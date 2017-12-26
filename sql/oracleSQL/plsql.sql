declare
begin
 dbms_output.put_line('Hello Cissst!');
end;


declare
 -- 声明变量
 v_username varchar2(20) not null := 'cissst';
 v_number1 int := 10;
 v_number2 real := 100;
 -- 声明常量
 c_pi constant number(8,2) default 3.14;
begin
  dbms_output.put_line('v_username:' || v_username);
  dbms_output.put_line('v_number1:' || v_number1);
  dbms_output.put_line('v_number2:' || v_number2);
  dbms_output.put_line('PI:' || c_pi);
end;

-- PL/SQL计算圆的面积
declare
 v_radius number(8,2) ;
 v_area number(10,2);
 c_pi constant number(8,2) default 3.14;
begin
   v_radius := 10;
   v_area := c_pi * v_radius * v_radius;
   dbms_output.put_line('area:' || v_area);  
end;

-- 上机练习：计算梯形的面积.
declare
v_upper number(8,2);
v_bottom number(8,2);
v_high number(8,2);
v_area number(10,2);
begin
v_upper := 12;
v_bottom := 23;
v_high := 10;
v_area := (v_upper+v_bottom)*v_high/2;
dbms_output.put_line('area:'||v_area);
end;



declare
v_username varchar2(25) := 'Tom';
begin
  v_username := v_username || 'and Kitty';
  dbms_output.put_line('Hello:' || v_username);
end;


-- 赋值操作 :=
--          select ...into

select empno,job from emp where empno=7369;
declare
  v_empno number(4);
  v_job varchar2(10);
begin
   select empno,job into v_empno, v_job
    from emp where empno=7369;
   dbms_output.put_line('v_empno:' || v_empno);
   dbms_output.put_line('v_job:' || v_job);
end;

select empno,job from emp where empno=7369;
declare
  v_empno emp.empno%type;
  v_job emp.job%type;
begin
   select empno,job into v_empno, v_job
    from emp where empno=7369;
   dbms_output.put_line('v_empno:' || v_empno);
   dbms_output.put_line('v_job:' || v_job);
end;

declare
  v_test1 varchar2(30) not null := 'test1';
  v_test2 v_test1%type := 'test3';
begin
  v_test2 := v_test1 || ' and test2';
  dbms_output.put_line(v_test2);
end;


-- 选择结构
1，IF-THEN语句
	IF 条件  THEN  
		语句;
	END  IF;.
2，IF-THEN-ELSE语句
	IF 条件   THEN  
		语句1;
          ELSE 
		语句2；
	END  IF;.
3.IF 条件1  
		THEN  语句1;
	ELSIF 条件2  
		THEN   语句2;
	ELSIF 条件3
		THEN   语句3;
	...
	END  IF;
--select sal from emp where empno=7934;
declare
  v_sal emp.sal%type;
begin
   select sal into v_sal from emp where empno=7839;
   if v_sal > 5000 then
      dbms_output.put_line('工资偏高!');
   elsif v_sal > 2000 then
      dbms_output.put_line('工资正常!');
   else
      dbms_output.put_line('工资太低，老板要加薪！');
   end if;
end;

-- 循环结构
4，基本循环
	LOOP.	执行语句；.	EXIT WHEN 条件；.END LOOP;　


  
 -- 计算1 + .. + 100的累加和
 
declare
   v_count number(3) :=0;
   v_sumResult number(5) :=0;
 begin
  loop
     v_count := v_count + 1;
     v_sumResult := v_sumResult + v_count;
     if (v_count >= 100) then
        exit;
     end if;
  end loop;
  dbms_output.put_line('sum:' || v_sumResult);
 end;
 
 declare
   v_count number(3) :=0;
   v_sumResult number(5) :=0;
 begin
  loop
     v_count := v_count + 1;
     v_sumResult := v_sumResult + v_count;     
     exit when (v_count >= 100);   
  end loop;
  dbms_output.put_line('sum:' || v_sumResult);
 end;
 
--WHILE循环
	WHILE 条件 LOOP
             执行语句；.
  END LOOP;
  
  declare
   v_count number(3) :=0;
   v_sumResult number(5) :=0;
  begin
     while (v_count < 100) loop
         v_count := v_count + 1;
         v_sumResult := v_sumResult + v_count;
     end loop;
     dbms_output.put_line('sum-->' || v_sumResult);
  end;
  
  -- 6，FOR循环
	FOR 计数器 IN 低界..高界 LOOP.        执行语句；
	END LOOP;
  
   declare
   v_count number(3) :=0;
   v_sumResult number(5) :=0;
   begin
   for v_count in 1..100 loop
      v_sumResult := v_sumResult + v_count;
   end loop;
   dbms_output.put_line('sum:' || v_sumResult);
   end;
  -- 用循环结构实现5! 
  -- 上机练习:用循环结构实现 1! + 2! +...5!
   
  --编写PL/SQL块，查询并输出编号为1002的产品的编号、名称和价格 
  select product_id,name,price from products where product_id='1002';
  declare
    v_product_id products.product_id%type;
    v_name products.name%type;
    v_price products.price%type;
  begin
    select product_id,name,price into v_product_id,v_name,v_price
    from products where product_id='1002';
   --error
   -- select product_id,name,price into v_product_id,v_name,v_price
   -- from products;
    dbms_output.put_line(v_product_id || ' ' || v_name || ' ' || v_price);
  end; 
  
 --TYPE typeName IS RECORD (memberDefineList)
--注：typeName 指定类型名；
--memberDefineList 表示以逗号隔开的该记录中的成员定义列表。
--Oracle建议类型名带t_前缀，带_record_type后缀；记录类型变量带_record后缀。
declare
   -- 定义记录类型
   type t_product_record_type is record(v_product_id products.product_id%type,
   v_name products.name%type,v_price products.price%type);
   -- 定义记录类型变量
   v_product_record  t_product_record_type;
   
begin
   select product_id,name,price into v_product_record
    from products where product_id='1002';
    dbms_output.put_line(v_product_record.v_product_id );
    dbms_output.put_line(v_product_record.v_name);
    dbms_output.put_line(v_product_record.v_price);
end;

declare
       type t_student_record_type 
       is record(v_stuid varchar2(3),
                 v_stuname varchar2(10));
       
       v_stu1_record t_student_record_type;
       v_stu2_record t_student_record_type;
begin
      v_stu1_record.v_stuid := 1;
      v_stu1_record.v_stuname :='ww';
      
      v_stu2_record := v_stu1_record;
      v_stu1_record.v_stuid := 2;
      v_stu1_record.v_stuname :='aa';
      
      dbms_output.put_line('stuid1:' || v_stu1_record.v_stuid);
      dbms_output.put_line('stuid1:' || v_stu1_record.v_stuname);
      dbms_output.put_line('stuid2:' || v_stu2_record.v_stuid);
      dbms_output.put_line('stuid2:' || v_stu2_record.v_stuname);
end;

--%ROWTYPE用来获取表中的行数据类型。即，记录类型。
declare
  v_product_record products%rowtype;
begin
  select * into v_product_record from products  where product_id='1002';
  dbms_output.put_line('productId:' || v_product_record.product_id);
  dbms_output.put_line(v_product_record.product_type_id || ' ' ||
                      v_product_record.name || ' '  ||
                      v_product_record.description || ' ' ||
                      v_product_record.price);
end;

-- error:值过多
declare
  v_product_record products%rowtype;
begin
  select product_id,product_type_id,name into v_product_record 
  from products  where product_id='1002';
  dbms_output.put_line('productId:' || v_product_record.product_id);
  dbms_output.put_line(v_product_record.product_type_id || ' ' ||
                      v_product_record.name );
end;

-- 游标操作
(1)在声明部分定义游标：指定了一个游标的名称以及它对应的查询语句；
	(2)在执行部分打开游标：当打开游标时，Oracle数据库会执行游标对应的查询语句，并把查询结果放入结果集中；
	(3)从游标中一次获取一行记录，进行处理；
	(4)返回第三步，直至所有记录被处理完毕；
	(5)关闭游标。
CURSOR cursor_name  [ RETURN return_type ] IS select_statement;
OPEN cursor_name;
FETCH cursor_name INTO variableList;
CLOSE cursor_name;

--编写PL/SQL语句块，使用游标，输出products表中的产品类别为1的行的所有信息
--select product_id,product_type_id 
--from products where product_type_id=1;
declare
  --声明游标
  cursor v_cur_product is 
   select * from products where product_type_id=1;
  v_product_info products%rowtype; 
begin
   -- 打开游标
   open v_cur_product;
   --提取每行记录信息
   fetch v_cur_product into v_product_info;
   while v_cur_product%found loop
      dbms_output.put_line(v_product_info.product_id);
      fetch v_cur_product into v_product_info;
   end loop;
   --统计记录行数
   dbms_output.put_line(v_cur_product%rowcount);
   --关闭游标
   close v_cur_product;
end;

declare
  --声明游标
  cursor v_cur_product is 
   select product_id,name,price from products where product_type_id=1;
  v_product_info v_cur_product%rowtype; 
begin
   -- 打开游标
   open v_cur_product;
   --提取每行记录信息
   fetch v_cur_product into v_product_info;
   while v_cur_product%found loop
      dbms_output.put_line(v_product_info.product_id);
      fetch v_cur_product into v_product_info;
   end loop;
   --统计记录行数
   dbms_output.put_line(v_cur_product%rowcount);
   --关闭游标
   close v_cur_product;
end;


--使用定义了返回类型的游标，输出products表中的产品编号、产品名、价格。
declare
   --自定义记录类型
   type t_product_record_type is record(v_product_id products.product_id%type,
   v_name products.name%type,v_price products.price%type);
   -- 定义记录类型变量
   v_product_record  t_product_record_type;
   -- 声明游标
   -- CURSOR cursor_name  [ RETURN return_type ] IS select_statement;
   cursor t_cur_product return t_product_record_type
   is select product_id,name,price from products;
begin
   --打开游标
   open t_cur_product;
   -- 循环提取游标指向的记录信息
   loop
       fetch t_cur_product into v_product_record;
       exit when t_cur_product%notfound;
       dbms_output.put_line(v_product_record.v_product_id || ' ' ||
                          v_product_record.v_name || ' ' ||
                          v_product_record.v_price);
   end loop;
   --关闭游标
   if (t_cur_product%isopen) then
      close t_cur_product;
   end if;
end;

-- FOR v_product_record IN v_product_cursor LOOP
	--处理数据
--END LOOP;
declare
   cursor v_cur_product is 
   select product_id,name,price from products;
   
   v_product_record v_cur_product%rowtype;
begin
   for v_product_record in v_cur_product loop
     dbms_output.put_line(v_product_record.product_id || ' ' ||
                   v_product_record.name || ' ' || 
                   v_product_record.price);
   end loop;
end;

-- 思考:查询商品编号，名称
declare
    v_product_record products%rowtype;
begin
   for v_product_record in (select * from products) loop
        dbms_output.put_line(v_product_record.product_id || ' ' ||
                   v_product_record.name || ' ' || 
                   v_product_record.price);
   end loop;
end;


-- 使用VALUES子句插入单条记录(产品)
declare
  v_product_id products.product_id%type;
begin
  select max(product_id) into v_product_id from products;
  if v_product_id is null then
     v_product_id := 1;
  else
      v_product_id := v_product_id + 1;
  end if;
  -- insert into
  insert into products values (v_product_id,1,'product'||v_product_id,
  'desc'||v_product_id ,dbms_random.value*100);
  dbms_output.put_line('rows:' || sql%rowcount);
  commit;
end;

--【示例】使用循环控制插入多条记录
-- 向product_change表中添加1000条记录
declare
  v_count number(4) :=1000;
  v_productId product_change.product_id%type;
begin 
  select max(product_id) into v_productId from product_change;
  if v_productId is null then
     v_productId := 1;
  else
     v_productId := v_productId + 1;
  end if;
  
  while (v_count > 0) loop
      insert into product_change(product_id,product_type_id,product_name,price)
      values (v_productId ,1,'product'||v_productId,dbms_random.value * 1000);
      -- 改变计数器的值
      v_count := v_count - sql%rowcount;
      v_productId := v_productId +1;
  end loop;  
  commit;
end;

-- 合并两个表中的记录
-- 将products表中的记录信息合并到product_change表中.
declare
 -- 声明游标
 cursor cur_product is 
     select product_id,product_type_id,name,price from products;
 v_product_info  cur_product%rowtype;
 v_count number(3) := 0;   
begin
  for v_product_info in cur_product loop
     --update/insert
     select count(*) into v_count from product_change pc
     where pc.product_id = v_product_info.product_id;
     if(v_count =0) then
       --insert
       insert into product_change values 
       (v_product_info.product_id,v_product_info.product_type_id,
       v_product_info.name,v_product_info.price);
     else
       --update
       update product_change set product_type_id=v_product_info.product_type_id,
       product_name=v_product_info.name,price=v_product_info.price
       where product_id = v_product_info.product_id;
     end if;
  end loop;
  commit;
end;








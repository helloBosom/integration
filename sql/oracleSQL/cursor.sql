declare
begin
 dbms_output.put_line('Hello world!');
end;

declare
 v_username varchar2(20) not null := 'hello';
 v_number1 int := 10;
 v_number2 real := 100;
 c_pi constant number(8,2) default 3.14;
begin
  dbms_output.put_line('v_username:' || v_username);
  dbms_output.put_line('v_number1:' || v_number1);
  dbms_output.put_line('v_number2:' || v_number2);
  dbms_output.put_line('PI:' || c_pi);
end;

declare
 v_radius number(8,2) ;
 v_area number(10,2);
 c_pi constant number(8,2) default 3.14;
begin
   v_radius := 10;
   v_area := c_pi * v_radius * v_radius;
   dbms_output.put_line('area:' || v_area);  
end;

declare
    v_upper  number(8,2);
    v_bottom number(8,2);
    v_high   number(8,2);
    v_area   number(10,2);
begin
    v_upper  := 12;
    v_bottom := 23;
    v_high   := 10;
    v_area   := (v_upper+v_bottom)*v_high/2;
    dbms_output.put_line('area:'
    ||v_area);
end;

declare
v_username varchar2(25) := 'Tom';
begin
  v_username := v_username || 'and Kitty';
  dbms_output.put_line('Hello:' || v_username);
end;

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
	END  IF;
--select sal from emp where empno=7934;
declare
  v_sal emp.sal%type;
begin
   select sal into v_sal from emp where empno=7839;
   if v_sal > 5000 then
      dbms_output.put_line('����ƫ��!');
   elsif v_sal > 2000 then
      dbms_output.put_line('��������!');
   else
      dbms_output.put_line('����̫�ͣ��ϰ�Ҫ��н��');
   end if;
end;

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

   declare
   v_count number(3) :=0;
   v_sumResult number(5) :=0;
   begin
   for v_count in 1..100 loop
      v_sumResult := v_sumResult + v_count;
   end loop;
   dbms_output.put_line('sum:' || v_sumResult);
   end;

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
declare
   type t_product_record_type is record(v_product_id products.product_id%type,
   v_name products.name%type,v_price products.price%type);
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

declare
  v_product_record products%rowtype;
begin
  select product_id,product_type_id,name into v_product_record 
  from products  where product_id='1002';
  dbms_output.put_line('productId:' || v_product_record.product_id);
  dbms_output.put_line(v_product_record.product_type_id || ' ' ||
                      v_product_record.name );
end;

CURSOR cursor_name  [ RETURN return_type ] IS select_statement;
OPEN cursor_name;
FETCH cursor_name INTO variableList;
CLOSE cursor_name;

declare
  cursor v_cur_product is
   select * from products where product_type_id=1;
  v_product_info products%rowtype; 
begin
   open v_cur_product;
   fetch v_cur_product into v_product_info;
   while v_cur_product%found loop
      dbms_output.put_line(v_product_info.product_id);
      fetch v_cur_product into v_product_info;
   end loop;
   dbms_output.put_line(v_cur_product%rowcount);
   close v_cur_product;
end;

declare
  cursor v_cur_product is
   select product_id,name,price from products where product_type_id=1;
  v_product_info v_cur_product%rowtype; 
begin
   open v_cur_product;
   fetch v_cur_product into v_product_info;
   while v_cur_product%found loop
      dbms_output.put_line(v_product_info.product_id);
      fetch v_cur_product into v_product_info;
   end loop;
   dbms_output.put_line(v_cur_product%rowcount);
   close v_cur_product;
end;

declare
   type t_product_record_type is record(v_product_id products.product_id%type,
   v_name products.name%type,v_price products.price%type);
   v_product_record  t_product_record_type;
   -- CURSOR cursor_name  [ RETURN return_type ] IS select_statement;
   cursor t_cur_product return t_product_record_type
   is select product_id,name,price from products;
begin
   open t_cur_product;
   loop
       fetch t_cur_product into v_product_record;
       exit when t_cur_product%notfound;
       dbms_output.put_line(v_product_record.v_product_id || ' ' ||
                          v_product_record.v_name || ' ' ||
                          v_product_record.v_price);
   end loop;
   if (t_cur_product%isopen) then
      close t_cur_product;
   end if;
end;

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

declare
    v_product_record products%rowtype;
begin
   for v_product_record in (select * from products) loop
        dbms_output.put_line(v_product_record.product_id || ' ' ||
                   v_product_record.name || ' ' || 
                   v_product_record.price);
   end loop;
end;


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
      v_count := v_count - sql%rowcount;
      v_productId := v_productId +1;
  end loop;  
  commit;
end;

declare
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
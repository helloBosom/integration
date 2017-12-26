begin
DisplaySal();
end;

declare
mycomm number;
begin 
DisplayComm(7499,mycomm);
dbms_output.put_line(""||mycomm);
end;

declare
firstvalue number := 10;
secondvalue number := 20;
begin 
swapvalue(firstvalue,secondvalue);
dbms_output.put_line(firstvalue||'  '||secondvalue);
end;
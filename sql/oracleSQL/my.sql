CREATE TABLE BOOK (
  BOOK_ID NUMBER (10),
  SORT VARCHAR2(10),
  BOOK_NAME VARCHAR2(50),
  WRITER VARCHAR2(10),
  OUTPUT VARCHAR2(50),
  PRICE NUMBER (3)
);
CREATE TABLE READER (
  READER_ID NUMBER (3),
  COMPANY VARCHAR2(10),
  NAME VARCHAR2(10),
  SEX VARCHAR2(2),
  GRADE VARCHAR2(10),
  ADDR VARCHAR2(50)
);
CREATE TABLE BORROW (
  READER_ID NUMBER (3),
  BOOK_ID NUMBER (10),
  BORROW_DATE DATE
);
INSERT INTO book VALUES (445501, 'TP3/12', '���ݿ⵼��', '��ǿ', '��ѧ������', 17.90);
INSERT INTO book VALUES (445502, 'TP3/12', '���ݿ⵼��', '��ǿ', '��ѧ������', 17.90);
INSERT INTO book VALUES (445503, 'TP3/12', '���ݿ⵼��', '��ǿ', '��ѧ������', 17.90);
INSERT INTO book VALUES (332211, 'TP5/10', '���������', '��ΰ', '�ߵȽ���������', 18.00);
INSERT INTO book VALUES (112266, 'TP3/12', 'FoxBASE', '����', '���ӹ�ҵ������', 23.60);
INSERT INTO book VALUES (665544, 'TS7/21', '�ߵ���ѧ', '����', '�ߵȽ���������', 20.00);
INSERT INTO book VALUES (114455, 'TR9/12', '���Դ���', '��ҵ', '������ѧ������', 20.80);
INSERT INTO book VALUES (113388, 'TR7/90', '��ѧӢ��', '����', '�廪��ѧ������', 12.50);
INSERT INTO book VALUES (446601, 'TP4/13', '���ݿ����', '������', '�����ʵ������', 22.50);
INSERT INTO book VALUES (446602, 'TP4/13', '���ݿ����', '������', '�����ʵ������', 22.50);
INSERT INTO book VALUES (446603, 'TP4/13', '���ݿ����', '������', '�����ʵ������', 22.50);
INSERT INTO book VALUES (449901, 'TP4/14', 'FoxPro��ȫ', '�ܺ�', '��ѧ������', 32.70);
INSERT INTO book VALUES (449902, 'TP4/14', 'FoxPro��ȫ', '�ܺ�', '��ѧ������', 32.70);
INSERT INTO book VALUES (118801, 'TP4/15', '���������', '������', '�ߵȽ���������', 21.80);
INSERT INTO book VALUES (118802, 'TP4/15', '���������', '������', '�ߵȽ���������', 21.80);

INSERT INTO reader VALUES (111, '��Ϣϵ', '��ά��', 'Ů', '����', '1��¥424');
INSERT INTO reader VALUES (112, '�ƻ�ϵ', '��  ��', '��', '������', '2��¥316');
INSERT INTO reader VALUES (113, '����ϵ', '��  ��', '��', '��ʦ', '3��¥105');
INSERT INTO reader VALUES (114, '��Ϣϵ', '�ܻ���', '��', '��ʦ', '1��¥316');
INSERT INTO reader VALUES (115, '��Ϣϵ', '������', '��', '����ʦ', '1��¥224');
INSERT INTO reader VALUES (116, '��Ϣϵ', '��  ��', '��', '������', '1��¥318');
INSERT INTO reader VALUES (117, '�����ϵ', '��С��', '��', '����', '1��¥214');
INSERT INTO reader VALUES (118, '�����ϵ', '������', '��', '����', '1��¥216');
INSERT INTO reader VALUES (119, '�����ϵ', '������', '��', '����', '1��¥318');
INSERT INTO reader VALUES (120, '����ó��', '��  ѩ', '��', '������', '4��¥506');
INSERT INTO reader VALUES (121, '����ó��', '��  ˬ', 'Ů', '��ʦ', '4��¥510');
INSERT INTO reader VALUES (122, '����ó��', '��  ��', 'Ů', '��ʦ', '4��¥512');
INSERT INTO reader VALUES (123, '�ƻ�ϵ', '��Сϼ', 'Ů', '����', '2��¥202');
INSERT INTO reader VALUES (124, '�ƻ�ϵ', '��  ��', '��', '��ʦ', '2��¥210');
INSERT INTO reader VALUES (125, '�ƻ�ϵ', '��Ӣ��', '��', '������', '2��¥212');

INSERT INTO borrow VALUES (112, 445501, '19-3��-2006');
INSERT INTO borrow VALUES (125, 332211, '12-2��-2006');
INSERT INTO borrow VALUES (111, 445503, '21-8��-2006');
INSERT INTO borrow VALUES (112, 112266, '14-3��-2006');
INSERT INTO borrow VALUES (114, 665544, '21-10��-2006');
INSERT INTO borrow VALUES (120, 114455, '02-11��-2006');
INSERT INTO borrow VALUES (120, 118801, '18-10��-2006');
INSERT INTO borrow VALUES (119, 446603, '12-11��-2006');
INSERT INTO borrow VALUES (112, 449901, '23-10��-2006');
INSERT INTO borrow VALUES (115, 449902, '21-8��-2006');
INSERT INTO borrow VALUES (118, 118801, '10-9��-2006');

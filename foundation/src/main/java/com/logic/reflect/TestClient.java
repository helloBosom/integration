package com.logic.reflect;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

import org.junit.Test;

/**
 * 反射机制:
 * Class 类的实例表示正在运行的 Java 应用程序中的类和接口。
 * 枚举是一种类，注释是一种接口。每个数组属于被映射为 Class 对象的一个类，
 * 所有具有相同元素类型和维数的数组都共享该 Class 对象。
 * 基本的 Java 类型（boolean、byte、char、short、int、long、float 和 double）
 * 和关键字 void 也表示为 Class 对象。 Class 没有公共构造方法。
 * Class 对象是在加载类时由 Java 虚拟机以及通过调用类加载器中的 defineClass 方法自动构造的。
 */
public class TestClient {
    @Test
    public void testMethod1() {
        Person person = new Person();
        Class<? extends Person> c1 = person.getClass();
        System.out.println(c1.getName());
    }

    @Test
    public void testMethod2() {
        Class<?> c = null;
        try {
            c = Class.forName("com.logic.reflect.Person");
            System.out.println(c.getName());
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        System.out.println(c);
    }

    @Test
    public void testMethod3() {
        Class<Person> c = Person.class;
        System.out.println(c.getSimpleName());
    }

    @Test
    public void testMethod4() {
        Class<Integer> c1 = int.class;
        System.out.println(c1.getName());
        Class<Integer> c2 = Integer.class;
        System.out.println(c2.getName());
        Class<?> c3 = Integer.TYPE;
        System.out.println(c3.getName());
    }

    @Test
    public void testMethod5() throws ClassNotFoundException, InstantiationException, IllegalAccessException {
        System.out.println("-------------------------------------------------");
        /**
         * Class forName(String className) 返回与带有给定字符串名的类或接口相关联的 Class 对象
         */
        Class<?> c = Class.forName("com.logic.reflect.Person");
        /**
         * newInstance() 创建此 Class 对象所表示的类的一个新实例。
         */
        Person person = (Person) c.newInstance();
        person.setPersonId("1001");
        person.setPersonName("hello");
    }

    @Test
    public void testMethod6() throws ClassNotFoundException {
        Class<?> c = Class.forName("com.logic.reflect.Person");
        /**
         * Field 提供有关类或接口的单个字段的信息,以及对它的动态访问权限.
         * 反射的字段可能是一个类（静态）字段或实例字段.
         * getDeclaredFields() 返回 Field 对象的一个数组，这些对象反映此 Class
         * 对象所表示的类或接口所声明的所有字段，包括公共、保护、默认（包）访问和私有字段，
         * 但不包括继承的字段。
         */
        Field[] fields = c.getDeclaredFields();
        System.out.println(fields.length);
        System.out.println("--------------------------------------------------------------");
        for (Field f : fields) {
            System.out.println(f.getName());
        }
        System.out.println("--------------------------------------------------------------");
        /**
         * getDeclaredMethods() 返回 Method 对象的一个数组，这些对象反映此 Class
         * 对象表示的类或接口声明的所有方法，包括公共、保护、默认（包）访问和私有方法，
         * 但不包括继承的方法。
         */
        Method[] method = c.getDeclaredMethods();
        System.out.println(method.length);
        System.out.println("--------------------------------------------------------------");
        for (Method m : method) {
            System.out.println(m.getName());
        }
    }

    @Test
    public void testMethod8() {
        String[] buffer = new String[5];
        Integer[][] array = new Integer[3][3];
        Class<? extends String[]> c1 = buffer.getClass();
        Class<? extends Integer[][]> c2 = array.getClass();
        System.out.println(c1.getName());
        System.out.println(c2.getName());
    }
}

package com.logic.service;

/**
 * interface test
 */
public class TestClient {
    public static void main(String[] args) {
        A a = new C();
        String s = a.getClass().getName();
        a.method1();
        a.method2();
        B b = new C();
        b.method2();
        b.method3();
        String s1 = b.getClass().getName();
        System.out.println(s);
        System.out.println(s1);
        C c = new C();
        c.method1();
        c.method2();
        c.method3();
    }
}

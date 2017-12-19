package com.logic.arithmetic;

public class Test1 {
    public static void main(String[] args) {
        CircleRadius circleRadius = new CircleRadius();
        circleRadius.setRadius(2);
        double i = circleRadius.calculateArea();
        System.out.println(i);
    }
}

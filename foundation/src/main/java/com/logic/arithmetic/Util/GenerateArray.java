package com.logic.arithmetic.Util;

import java.util.Random;

/**
 * auto generate array
 * @author logic
 */
public class GenerateArray {
    public static double[] generateDoubleArray(int length) {
        Random random = new Random();
        double[] array = new double[random.nextInt(length) + 1];
        System.out.println("the random length of array less than 10 is: " + array.length);
        for (int i = 0; i < array.length; i++) {
            array[i] = Math.random() * 10;
        }
        System.out.println("array content:");
        for (int j = 0; j < array.length; j++) {
            System.out.println(array[j]);
        }
        return array;
    }

    public static int[] generateIntArray(int length,int max) {
        Random random = new Random();
        int[] array = new int[random.nextInt(length) + 1];
        System.out.println("the random length of array less than 10 is: " + array.length);
        for (int i = 0; i < array.length; i++) {
            array[i] = random.nextInt(max);
        }
        System.out.println("array content:");
        for (int j = 0; j < array.length; j++) {
            System.out.println(array[j]);
        }
        return array;
    }
}

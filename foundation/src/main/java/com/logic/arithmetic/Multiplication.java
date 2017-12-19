package com.logic.arithmetic;

import java.util.Random;

/**
 * @author logic
 * m*n multiplication
 */
public class Multiplication {

    public static void main(String[] args) {
        Random random = new Random();
        int limit = random.nextInt(100);
        System.out.println("the random number of limited less than 100 is : " + limit);
        int m = random.nextInt(limit);
        int n = random.nextInt(limit);
        if (m > n) {
            m = m ^ n;
            n = m ^ n;
            m = m ^ n;
        }
        System.out.println("random start number of random limit : " + m);
        System.out.println("random end number of random limit :" + n);
        System.out.println("multiplication result of" + m + "*" + n);
        Multiplication(m, n);
    }

    static void Multiplication(int m, int n) {
        for (int i = m; i < n + 1; i++) {
            for (int j = m; j <= i; j++) {
                System.out.print(j + "*" + i + "=" + i * j + "  ");
            }
            System.out.println();
        }
    }

}
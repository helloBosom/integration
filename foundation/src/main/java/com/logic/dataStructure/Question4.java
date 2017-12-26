package com.logic.dataStructure;

import java.util.Scanner;

/**
 * judge palindrome
 */
public class Question4 {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String s = scanner.next();
        boolean b = isPalindrome(s);
        if (b) {
            System.out.println("is palindrome");

        } else {
            System.out.println("is not palindrome");
        }
    }

    public static boolean isPalindrome(String strIn) {
        boolean flag = false;
        for (int i = 0; i < (strIn.length() + 1) / 2; i++) {
            if (strIn.charAt(i) == strIn.charAt(strIn.length() - i - 1)) {
                flag = true;
            }
        }
        return flag;
    }
}

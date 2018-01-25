package com.logic.dataStructure;

import java.util.Scanner;

public class Alphabet {
    static final String Regex = "[a-z]+";

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        String str = scan.nextLine();
        StringToAscii(str);
    }

    public static void StringToAscii(String str) {
        if (!str.matches(Regex)) {
            return;
        }
        char[] temp = str.toCharArray();
        for (char c : temp) {
            if (c + 5 > 'z') {
                c = (char) (c - 21);
            } else {
                c = (char) (c + 5);
            }
            System.out.print(c);
        }
    }
}

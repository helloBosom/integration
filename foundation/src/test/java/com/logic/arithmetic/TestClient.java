package com.logic.arithmetic;

import java.util.Scanner;

/*
 * 判断用户名,密码,email合法性
 */
public class TestClient {
    static User user = new User();

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        System.out.println("请输入用户名");
        String username = scan.next();
        user.setUsername(username);
        System.out.println("请输入密码");
        String password = scan.next();
        user.setPassword(password);
        System.out.println("请输入email");
        String email = scan.next();
        user.setEmail(email);
        judge();
    }

    public static void judge() {
        try {
            for (int i = 0; i < user.getUsername().length(); i++) {
                char c = user.getUsername().charAt(i);
                if ((user.getUsername().equals(null)
                        && !((c > '0' && c < '9') && (c == '_') && ((c > 'a' && c < 'z') || (c > 'A' && c < 'Z'))))) {
                    throw new RegisterException(" 用户名不合法");
                }
            }
            int pwd = user.getPassword().length();
            if (!(pwd > 6 && pwd < 20)) {
                throw new RegisterException("密码需在6-20位之间");
            }
            for (int i = 0; i < user.getEmail().length(); i++) {
                int pointStartPosition = user.getEmail().indexOf(".");
                int flagStartPosition = user.getEmail().indexOf("@");
                if (pointStartPosition == -1 || flagStartPosition == -1 || pointStartPosition < flagStartPosition) {
                    throw new RegisterException("email不合法");
                }
            }
            System.out.println("success");
        } catch (RegisterException e) {
            e.printStackTrace();
        }
    }
}

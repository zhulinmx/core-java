package com.basic.ch03;

import java.io.FileInputStream;
import java.io.IOException;

/**
 * 调用方法异常
 * 注意throw和throws的使用
 */
public class Ch0302MethodException {
    public static void main(String[] args) {
        try {
            //m(0);
            f2();
        } catch (IOException e) {
            //打印异常栈调用信息
            System.out.println("这是io异常");
            e.printStackTrace();
        } catch (Exception e) {
            System.out.println("这是其他异常");
            e.printStackTrace();
        }
    }

    public static void m(int i) throws ArithmeticException {
        if (i == 0)
            throw new ArithmeticException("参数为0异常...");
    }

    public static void f() throws IOException {
        FileInputStream in = null;
        try {
            in = new FileInputStream("myfile.txt");
            int b;
            b = in.read();
            while (b != -1) {
                System.out.print((char) b);
                b = in.read();
            }
        } catch (IOException e) {
            throw e;
        } finally {
            if (null != in) {
                try {
                    in.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static void f2() throws IOException {
        f();
    }
}



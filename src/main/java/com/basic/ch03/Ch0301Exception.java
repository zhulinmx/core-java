package com.basic.ch03;

import java.io.*;

/**
 * 异常处理
 * try catch finally语法
 */
public class Ch0301Exception {
    public static void main(String[] args) {

        FileInputStream in = null;
        try {
            in = new FileInputStream("myfile.txt");
            int b;
            b = in.read();
            while (b != -1) {
                System.out.print((char) b);
                b = in.read();
            }
        } catch (FileNotFoundException e) {
            //捕获到FileNotFoundException，打印异常信息
            //如果在实际应用中可能会以log.error的方式记录日志
            System.out.println(e.getMessage());
        } catch (IOException e) {
            e.printStackTrace();
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

}
package com.basic.io;

import java.io.*;

public class TestFileOutputStream {
    public static void main(String[] args) {
        int b = 0;
        FileInputStream in = null;
        FileOutputStream out = null;
        try {
            in = new FileInputStream("d:/share/java/HelloWorld.java");
            out = new FileOutputStream("d:/share/java/io/HW.java");
            while ((b = in.read()) != -1) {
                out.write(b);
            }
            in.close();
            out.close();
        } catch (FileNotFoundException e2) {
            System.out.println("找不到指定文件");
            System.exit(-1);
        } catch (IOException e1) {
            System.out.println("文件复制错误");
            System.exit(-1);
        }
        System.out.println("文件已复制");
    }
}

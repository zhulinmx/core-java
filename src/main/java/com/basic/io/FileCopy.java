package com.basic.io;

import java.io.*;

public class FileCopy {
    public static void main(String[] args) {
        int b = 0;
        FileReader in = null;
        FileWriter out = null;
        try {
            in = new FileReader("src/main/java/com/basic/chapter0700/HelloWorld.java");
            out = new FileWriter("src/main/java/com/basic/chapter0700/HW.txt");
            while ((b = in.read()) != -1) {
                out.write(b);
            }
            out.close();
            in.close();
        } catch (FileNotFoundException e2) {
            e2.printStackTrace();
            System.exit(-1);
        } catch (IOException e1) {
            e1.printStackTrace();
            System.exit(-1);
        }
        System.out.println("success");
    }

}

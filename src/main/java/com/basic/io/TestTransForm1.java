package com.basic.io;

import java.io.*;

public class TestTransForm1 {
    public static void main(String[] args) {
        try {
            OutputStreamWriter osw = new OutputStreamWriter(new FileOutputStream("src/main/java/com/basic/chapter0700/char.txt"));
            osw.write("mircosoftibmsunapplehp");
            System.out.println(osw.getEncoding());
            osw.close();
            osw = new OutputStreamWriter(new FileOutputStream("src/main/java/com/basic/chapter0700/char.txt", true), "ISO8859_1"); // latin-1
            osw.write(System.lineSeparator());
            osw.write("mircosoftibmsunapplehp");
            System.out.println(osw.getEncoding());
            osw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
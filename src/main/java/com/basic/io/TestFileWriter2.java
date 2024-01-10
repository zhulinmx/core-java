package com.basic.io;

import java.io.*;

public class TestFileWriter2 {
    public static void main(String[] args) throws Exception {
        FileReader fr = new FileReader("src/main/java/com/basic/chapter0700/TestFileWriter2.java");
        FileWriter fw = new FileWriter("src/main/java/com/basic/chapter0700/TestFileWriter2.bak");
        int b;
        while ((b = fr.read()) != -1) {
            fw.write(b);
        }
        fr.close();
        fw.close();
    }
}
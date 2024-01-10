package com.basic.io;

import java.io.BufferedWriter;
import java.io.FileWriter;

public class Test {
    public static void main(String[] args) throws Exception {
        FileWriter fw = new FileWriter("src/main/java/com/basic/chapter0700/111.txt");
        BufferedWriter bw = new BufferedWriter(fw);
        for (int i = 1; i <= 25; i++) {
            bw.write(i + " ");
            if (i % 5 == 0) bw.newLine();
        }

        bw.flush();
        bw.close();
    }
}

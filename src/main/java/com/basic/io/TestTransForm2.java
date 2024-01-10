package com.basic.io;

import java.io.*;

public class TestTransForm2 {
    public static void main(String args[]) {
        InputStreamReader isr = new InputStreamReader(System.in);
        BufferedReader br = new BufferedReader(isr);
        String s = null;
        try {
            while ((s = br.readLine()) != null) {
                if (s.equalsIgnoreCase("exit")) break;
                System.out.println(s.toUpperCase());
                s = br.readLine();
            }
            br.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
} // 阻塞
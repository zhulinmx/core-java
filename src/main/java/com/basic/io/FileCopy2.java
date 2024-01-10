package com.basic.io;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class FileCopy2 {
    public static void main(String[] args) {
        try {
            //java 11 new features
            Path p = Path.of("src/main/java/com/basic/chapter0700/HelloWorld.java");
            String content = Files.readString(p);
            System.out.println(content);
            Files.copy(p, Path.of("src/main/java/com/basic/chapter0700/HW1.txt"));
            System.out.println("end");

        } catch (IOException io) {

        }


    }
}

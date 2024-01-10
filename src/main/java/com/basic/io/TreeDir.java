package com.basic.io;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.stream.Stream;

public class TreeDir {
    public static void main(String[] args) throws Exception {
        listF(new File("src/main/java/com/basic/"), 0);

        Stream<Path> lst = Files.list(Path.of("src/main/java/com/basic/"));
        lst.forEach(System.out::println);
    }

    public static void listF(File f, int level) {
        String preStr = "";
        for (int i = 0; i < level; i++) preStr += "    ";
        System.out.println(preStr + f.getName());
        if (f.isDirectory()) {
            File[] files = f.listFiles();
            for (File cf : files) listF(cf, level + 1);
        }
    }
}
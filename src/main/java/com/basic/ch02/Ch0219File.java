package com.basic.ch02;

import java.io.File;
import java.io.IOException;

/**
 * File类：操作文件
 * 创建、删除、添加、查找等
 */
public class Ch0219File {
    public static void main(String[] args) {
        // File仅仅是内存中的一个对象
        String separator = File.separator;
        String filename = "myfile.txt";
        String directory = "mydir1" + separator + "mydir2";
        //String directory = "mydir1/mydir2";

        // 不推荐这种写法
        //String directory = "mydir1\\mydir2";
        File f = new File(directory, filename);
        if (f.exists()) {
            System.out.println("文件名：" + f.getAbsolutePath());
            System.out.println("文件大小：" + f.length());
        } else {
            f.getParentFile().mkdirs();
            try {
                f.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        File d = new File("src/main/java/com/basic/");
        System.out.println(d.getName());
        tree(d, 1);
    }


    /**
     * 遍历某目录下的所有文件
     * @param f
     * @param level
     */
    private static void tree(File f, int level) {
        File[] childs = f.listFiles();
        for (int i = 0; i < childs.length; i++) {
            if (childs[i].isDirectory()) {
                tree(childs[i], level + 1);
            }
            System.out.println(childs[i].getName());
        }
    }
}

package com.basic.ch02;

import java.util.Arrays;

/**
 * 数组[空间]介绍
 * 一维数组声明方式
 * type var[] 或者 type[] var
 * Java语言声明数组时不能指定其长度。
 * int[5] a; // 非法，但是c或c++语言是可以的，分配在占内存空间上
 * 元素为引用类型的数据中的每个元素都需要实例化（对象数组）
 *
 * 声明、赋值、遍历
 *
 */
public class Ch0212Array {
    public static void main(String[] args) {
        // 第一种：静态初始化
        int[] a = {1, 3, 5, 7, 9};
        for (int temp : a) {
            System.out.println(temp);
        }

        // 第二种：动态初始化
        int[] arr;
        arr = new int[5];

        // 给数组元素赋值
        for (int i = 0; i < 5; i++) {
            arr[i] = i;
        }

        // 变量数组元素
        for (int j : arr) {
            System.out.println(j);
        }

        // 二位数组静态初始化
        int[][] arrA = {{1, 3}, {1, 2, 4, 5}, {2, 4, 6}};
        // int[3][2] arrB = {{1, 2}, {2, 3}, {4, 5}};

        // 二维数组赋值
        int[][] test = new int[3][];
        int[][] array = new int[3][3];
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                array[i][j] = 0;
            }
        }

        // 二位数组遍历
        System.out.println("二位数组的长度=" + array.length);
        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array[i].length; j++) {
                System.out.println(array[i][j]);
            }
        }

        System.out.println("stream foreach: ");
        //java 8+ stream遍历
        Arrays.stream(array).forEach(x-> {
            Arrays.stream(x).forEach(System.out::println);
        });

        //对象数组
        Point[] pointArray = new Point[3];
        for (int i = 0; i < pointArray.length; i++) {
            pointArray[i] = new Point(i, i);
        }

        Arrays.stream(pointArray).forEach(x-> System.out.println(x));
    }
}
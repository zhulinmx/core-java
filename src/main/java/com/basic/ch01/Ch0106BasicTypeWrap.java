package com.basic.ch01;

/**
 * 基础类型封装类
 *
 */
public class Ch0106BasicTypeWrap {
    public static void main(String[] args) {
        boolean b = false;
        Boolean b2 = Boolean.TRUE;

        int a = 10;
        Integer a2 = 10;

        byte by = 1;
        Byte by2 = 2;

        short sh = 1;
        Short sh2 = 2;

        long l = 12L;
        Long l2 = 23L;

        char c = 'c';
        Character c2 = 'c';

        float f1 = 3.14f;
        Float f2 = 3.14f;

        double d = 3.14;
        Double d2 = 3.14;
        System.out.println(d2.compareTo(d));

        // 具体值参考Java API

    }
}

package com.basic.ch02;

/**
 * 变量：
 *
 *  - 成员变量
 *     可以是Java语言中的任何一种数据类型，基本类型和引用类型
 *     在定义成员变量时可以直接对其初始化，如果不对其初始化，
 *     Java将使用默认的值进行初始化
 *     byte 0
 *     short 0
 *     int 0
 *     long 0L
 *     char '\u0000'
 *     float 0.0F
 *     double 0.0D
 *     boolean false
 *     引用类型 null
 *
 *  - 局部变量：
 *     使用时需要初始化
 *     声明 -> 赋值 -> 使用
 *
 */
public class Ch0201Variable {
    private int a;
    private boolean b;

    public void print() {
        System.out.print(a);
        System.out.print(b);
    }

    public static void main(String[] args) {
        Ch0201Variable obj = new Ch0201Variable();
        obj.print(); // 0false
    }
}

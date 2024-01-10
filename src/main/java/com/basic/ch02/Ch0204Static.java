package com.basic.ch02;

/**
 * static 关键字
 * static 修饰的成员变量为静态成员变量，它为该类的共有变量，在第一次使用时被初始化，对该类的所有对象来说，static 成员变量只有一份。
 * 用 static 声明的方法为静态方法，在调用该方法时，不会将对象的引用传给它，所有在静态方法中不可以访问非静态成员。
 * 静态方法不再是针对某个对象调用，所以不能访问非静态成员
 * 可以通过类名或对象引用调用 static 方法
 *
 */
public class Ch0204Static {

    // 存在于内存中数据区，Data Segment，可用于计数
    static int i;

    /**
     * 静态初始化块
     */
    static {
        System.out.println(123);
    }

    public static void print() {
        System.out.println("hello " + i);
        //this.add(3);  // 错误 java: 无法从静态上下文中引用非静态 变量 this
    }

    public void add(int j) {
        System.out.println(i+j);
    }

    public static void main(String[] args) {
        Ch0204Static.i = 10;
        print();
    }
}

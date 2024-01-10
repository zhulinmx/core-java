package com.basic.ch02;

/**
 * 接口测试
 * 接口只能包含常量和方法声明。
 * 接口中的方法只能为 public（缺省为 public）
 *
 */
public class Ch0207Interface implements A, B {
    public static void main(String[] args) {
        Ch0207Interface t = new Ch0207Interface();
        t.print();

        A a = new Ch0207Interface();
        t.print(a);

    }

    @Override
    public void print() {
        System.out.println("hello world!");
    }

    @Override
    public void print(A a) {
        System.out.println(a);
        System.out.println(A.aa);
    }
}

interface A {
    public static final int aa = 123;
    void print();
}

interface B {
    void print(A a);
    void print();
}

interface C {
    void test();
}

interface D extends A, B, C {
    void test();
    void sleep();
    void sing();
}

class F implements D {

    @Override
    public void print() {

    }

    @Override
    public void print(A a) {

    }

    @Override
    public void test() {

    }

    @Override
    public void sleep() {

    }

    @Override
    public void sing() {

    }
}
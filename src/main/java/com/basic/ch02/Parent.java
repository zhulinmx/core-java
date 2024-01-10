package com.basic.ch02;

public class Parent {

    private static int a;
    private static final int b = 1;

    public Parent() {
        System.out.println("this is constructor of parent");
    }

    static {
        System.out.println("this is static content of parent");
        System.out.println("a = "+ a);
    }

    {
        System.out.println("this is parent");
    }

}

package com.java8;

public class Demo09Default implements Demo09DefaultInterface {
    @Override
    public int test1() {
        return 0;
    }

    public static void main(String[] args) {
        new Demo09Default().test1();
        new Demo09Default().test(123);
        new Demo09Default().test();
    }
}

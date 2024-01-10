package com.java8;

/**
 * 接口中的default方法
 * 很像抽象方法中的非abstract方法
 */
public interface Demo09DefaultInterface {
    public default int test(int i) {
        System.out.println(i);
        return 1;
    }


    public default int test() {
        return 1;
    }

    public int test1();
}

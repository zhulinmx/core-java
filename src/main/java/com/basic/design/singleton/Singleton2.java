package com.basic.design.singleton;
/**
 * 单例
 * 饿汉式，基于classLoad机制
 */
public class Singleton2 {

    private static Singleton2 instance = new Singleton2();

    private Singleton2 () {}

    public static Singleton2 getInstance() {
        return instance;
    }
}

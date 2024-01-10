package com.basic.design.singleton;

public class Singleton1 {

    private volatile static Singleton1 uniqueInstance; // 需要加volatile保持可见性

    private Singleton1() {

    }

    public static Singleton1 getUniqueInstance() {
        if (uniqueInstance == null) {  //第一次检查
            synchronized (Singleton1.class) {
                if (uniqueInstance == null) {  // 第二次检查
                    uniqueInstance = new Singleton1();
                }
            }
        }
        return uniqueInstance;
    }

    public static void main(String[] args) {
        System.out.println(Singleton.getInstance());
    }
}

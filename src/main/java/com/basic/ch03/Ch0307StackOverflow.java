package com.basic.ch03;

/**
 * 线程栈溢出：一般是由于递归太深或方法调用层级过多导致的。
 * 发生栈溢出的错误信息为：
 *
 * 解决方法：
 * 1. 优化程序设计，减少方法调用层次，检查是否递归没有出口；
 * 2. 调整-Xss参数增加线程栈大小；
 *
 */
public class Ch0307StackOverflow {

    /**
     * 这是危险的递归
     */
    public static void print() {
        print();
    }

    /**
     * java.lang.OutOfMemoryError: unable to create native thread这个错误要么是Stack空间不足或者过小导致无法创建额外的线程，要么就是创建的线程过多。
     *
     * 下面这个方法就是创建过多线程导致OutOfMemoryError
     * 可怕的递归
     */
    public static void createThreads() {
        while (true) {
            new Thread(()->{
                System.out.println(111);
                createThreads();
            }).start();
        }
    }

    public static void main(String[] args) {
        //print();
        createThreads();
    }
}

package com.concurrency.art.basic;

/**
 *
 * 1. 死锁产生的条件
 * 2. 如何避免死锁
 *
 */
public class DeadLock1 {
    private static String A = "A";
    private static String B = "com.basic.chapter0200.B";

    public static void main(String[] args) {

        new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("t1 start");
                synchronized (A) {
                    try {
                        Thread.sleep(2000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    synchronized (B) {
                        System.out.println("1");
                    }
                }
            }
        }).start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("t2 start");
                synchronized (B) {
                    try {
                        Thread.sleep(9000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    synchronized (A) {
                        System.out.println("2");
                    }
                }
            }
        }).start();
    }
}

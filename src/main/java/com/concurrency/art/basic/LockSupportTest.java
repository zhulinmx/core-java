package com.concurrency.art.basic;

import java.util.concurrent.locks.LockSupport;

/**
 * 唤醒“指定”的某个线程
 *
 * 使用Java6引入了LockSupport这个类
 * park(): 可以让当前线程进入wait状态
 */
public class LockSupportTest {
    public static void main(String[] args) throws Exception {
        Thread t1 = new Thread(()->{
            System.out.println("start t1");
            LockSupport.park();
            System.out.println("continue t1");
        });

        Thread t2 = new Thread(()->{
            System.out.println("start t2");
            LockSupport.parkNanos(3000000000L);
            System.out.println("continue t2");
        });

        t1.start();
        t2.start();

        Thread.sleep(1000);
        LockSupport.unpark(t1); // unpark可以解除指定线程的wait态，不需要拥有某个对象的特定锁
    }
}

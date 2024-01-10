package com.concurrency.art.ch00;

import java.util.concurrent.CountDownLatch;

/**
 *
 * 对于 CountDownLatch 来说，重点是“一个线程（多个线程）等待”，而其他的 N 个线程在完成“某件事情”之后，可以终止，也可以等待。
 * 而对于 CyclicBarrier，重点是多个线程，在任意一个线程没有完成，所有的线程都必须等待。
 *
 * CountDownLatch 是计数器，线程完成一个记录一个，只不过计数不是递增而是递减，而
 * CyclicBarrier 更像是一个阀门，需要所有线程都到达，阀门才能打开，然后继续执行。
 *
 */
public class CountDownLatchTest {
    // 10 个线程
    static CountDownLatch countDownLatch = new CountDownLatch(10);

    public static void main(String[] args) throws InterruptedException {
        for (int i = 0; i < 10; i++) {
            int threadNo = i;
            new Thread(new Runnable() {
                @Override
                public void run() {
                    System.out.println("thread: " + threadNo);
                    // 执行完打印语句之后，count 数减一
                    countDownLatch.countDown();
                }
            }).start();
        }

        // 当count为0时，就可以执行以后的打印语句
        // Causes the current thread to wait until the latch has counted down to zero
        countDownLatch.await();

        System.out.println("all threads finished. count: "+countDownLatch.getCount());
    }
}

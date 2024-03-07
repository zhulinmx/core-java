package com.concurrency.art.ch02;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/**
 * CyclicBarrier 可用于多线程计算数据，最后合并计算结果的场景。
 *
 * 对于 CountDownLatch 来说，重点是“一个线程（多个线程）等待”，而其他的 N 个线程在完成“某件事情”之后，可以终止，也可以等待。
 * 而对于 CyclicBarrier，重点是多个线程，在任意一个线程没有完成，所有的线程都必须等待。
 *
 * CountDownLatch 是计数器，线程完成一个记录一个，只不过计数不是递增而是递减，而
 *
 * CyclicBarrier 更像是一个阀门，需要所有线程都到达，阀门才能打开，然后继续执行。
 *
 */
public class CyclicBarrierTest {
    static CyclicBarrier cyclicBarrier = new CyclicBarrier(2);

    public static void main(String[] args) throws BrokenBarrierException, InterruptedException {
        new Thread(() -> {
                try {
                    cyclicBarrier.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (BrokenBarrierException e) {
                    e.printStackTrace();
                }
                System.out.println(1);
            }
        ).start();

        cyclicBarrier.await();
        System.out.println(2);
    }
}

package com.concurrency.art.basic;

import java.util.concurrent.TimeUnit;

/**
 * 和interrupt相关的三个方法：
 *
 * interrupt()，在一个线程中调用另一个线程的interrupt()方法，即会向那个线程发出信号——线程中断状态已被设置。至于那个线程何去何从，由具体的代码实现决定。
 * isInterrupted()，用来判断当前线程的中断状态(true or false)。
 * interrupted()是个Thread的static方法，返回当前线程的中断状态，并设置为false，即清除打断状态。
 *
 *
 */
public class Interrupted {
    public static void main(String[] args) throws InterruptedException {
        Thread sleepRunner = new Thread(new SleepRunner(), "SleepRunner");
        sleepRunner.setDaemon(true);

        Thread busyRunner = new Thread(new BusyRunner(), "BusyRunner");
        busyRunner.setDaemon(true);

        sleepRunner.start();
        busyRunner.start();

        TimeUnit.SECONDS.sleep(5);
        sleepRunner.interrupt();
        busyRunner.interrupt();

        System.out.println("SleepThread interrupted is " + sleepRunner.isInterrupted());
        System.out.println("BusyThread interrupted is " + busyRunner.isInterrupted());

        SleepUtils.second(2);

    }

    static class SleepRunner implements Runnable {
        @Override
        public void run() {
            while (true) {
                SleepUtils.second(10);
            }
        }
    }

    static class BusyRunner implements Runnable {
        @Override
        public void run() {
            while (true) {

            }
        }
    }
}

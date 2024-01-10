package com.concurrency.art.basic;

/**
 *
 * 通过Thread.setDaemon(false)设置为用户线程；
 * 通过Thread.setDaemon(true)设置为守护线程；
 ***** 如果不设置属性，默认为用户线程。*******
 *
 * 用户线程和守护线程的区别：
 * 1. 主线程结束后，用户线程还会继续运行，JVM 存活；主线程结束后，守护线程和JVM的状态由下面第2条确定。
 * 2. 如果没有用户线程，只有守护线程的话，那么JVM 就会结束。
 *
 * 守护线程：
 * 定义：守护线程 -- 也称“服务线程”，在没有用户线程可服务时会自动离开；
 * 优先级：守护线程的优先级比较低，用于为系统中的其它对象和线程提供服务；
 * 设置：通过setDaemon(true)来设置线程为“守护线程”；
 * 实例: 垃圾回收线程就是一个经典的守护线程。当我们的程序中不再有任何运行的线程，程序就不会再产生垃圾，垃圾回收器也就无事可做，所以当垃圾回收线程是JVM上仅剩的线程时，垃圾回收线程会自动离开。它始终在低级别的状态中运行，用于实时监控和管理系统中的可回收资源。
 * 生命周期：守护进程（Daemon）是运行在后台的一种特殊进程。它独立于控制终端并且周期性地执行某种任务或等待处理某些发生的事件。
 *
 * 也就是说守护线程不依赖于终端，但是依赖于系统，与系统“同生共死”。
 *
 *
 */
public class Daemon {
    public static void main(String[] args) throws Exception {
        Thread thread = new Thread(new DaemonRunner(), "DaemonRunner");
        thread.setDaemon(Boolean.TRUE);
        thread.start();
        System.out.println(123);
    }

    static class DaemonRunner implements Runnable {
        @Override
        public void run() {
            SleepUtils.second(10);
        }
    }
}

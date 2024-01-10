package com.concurrency.art.ch03;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

/**
 *  线程池使用ThreadLocal
 *  可以统计每个线程执行了多少次task
 */
public class ThreadLocal3 {

    // withInitial用于创建一个新的 ThreadLocal 对象，并为其设置一个初始值
    private static ThreadLocal<AtomicInteger> threadLocal = ThreadLocal.withInitial(() -> new AtomicInteger(0));

    public static void main(String[] args) {
        // 提交任务到线程池
        Runnable task = () -> {
            // 获取当前线程的 ThreadLocal 变量值
            AtomicInteger count = threadLocal.get();
            count.incrementAndGet();
            System.out.println(Thread.currentThread() + " 当前线程的计数值：" + count);
        };

        ExecutorService executorService = Executors.newFixedThreadPool(2);
        for (int i = 0; i < 10; i++) {
            executorService.submit(task);
        }

        // 关闭线程池
        executorService.shutdown();
        System.out.println(threadLocal.get());  // 0
        threadLocal.set(new AtomicInteger(2));
        System.out.println(threadLocal.get());  // 2
        threadLocal.remove();
        System.out.println(threadLocal.get());  // 0
    }
}

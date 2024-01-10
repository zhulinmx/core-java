package com.concurrency.art.ch06;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 保证任务执行前后顺序 线程池中只有一个线程
 *
 */
public class T09_SingleThreadPool {

    public static void main(String[] args) throws InterruptedException {
        ExecutorService service = Executors.newSingleThreadExecutor();
        for (int i = 0; i < 5; i++) {
            final int j = i;
            service.execute(() -> {
                System.out.println(j + " " + Thread.currentThread().getName());
            });
        }
        System.out.println(service.isTerminated());
        service.shutdown();
        System.out.println(service.isShutdown());
        System.out.println(service.isTerminated());
    }
}

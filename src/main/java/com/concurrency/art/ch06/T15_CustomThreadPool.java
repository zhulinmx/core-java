package com.concurrency.art.ch06;

import java.util.concurrent.*;

/**
 * 自定义线程池
 *
 * */
public class T15_CustomThreadPool {

    public static void main(String[] args) {
        // Returns the number of processors available to the Java virtual machine.
        int processorCount = Runtime.getRuntime().availableProcessors();
        ThreadPoolExecutor threadPoolExecutor =
                new ThreadPoolExecutor(processorCount,
                        processorCount * 2,
                        1,
                        TimeUnit.SECONDS,
                        new ArrayBlockingQueue(10),
                        new T13_CustomThreadFactory("common")); //自定义ThreadFactory

        System.out.println(threadPoolExecutor);
        int sum = 0;

        try {
            for (int i = 0; i < 10; i++) {
                final int tmpInt = i;
                Future<Integer> result = threadPoolExecutor.submit(() -> sum(tmpInt, tmpInt + 10));
                sum += result.get();
            }
        } catch (RejectedExecutionException e) {
            System.out.println("task completed count=" + threadPoolExecutor.getCompletedTaskCount());
            threadPoolExecutor.shutdownNow();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        System.out.println("Sum="+sum);
        System.out.println("Core Pool Size=" + threadPoolExecutor.getCorePoolSize());
        System.out.println("Maximum Pool Size=" + threadPoolExecutor.getMaximumPoolSize());
        System.out.println("Keepalive Time=" + threadPoolExecutor.getKeepAliveTime(TimeUnit.SECONDS));
        threadPoolExecutor.shutdown();

    }

    static int sum(int start, int end) {
        int sum = 0;
        if (start >= end) {
            return sum;
        }
        for (int i = start; i <= end; i++) {
            sum += i;
        }
        return sum;
    }
}


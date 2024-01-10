package com.concurrency.art.ch06;

import java.util.Arrays;
import java.util.Random;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;

/**
 * ForkJoin
 *
 * ForkJoinPool是Java中的一个用于并行执行任务的线程池，它是由Fork/Join框架提供的。
 * Fork/Join框架是一种用于解决大规模并行计算问题的框架，它将一个大任务分解为若干个小任务，然后通过递归的方式将这些小任务分配给不同的线程执行，最后将各个线程的结果合并得到最终结果。
 *
 * ForkJoinPool的主要特点有以下几点：
 * 自动管理线程池：ForkJoinPool会自动创建和管理线程池，无需手动创建和管理线程。
 * 任务分解：ForkJoinPool可以将大任务分解为若干个小任务，每个小任务都可以独立执行，不需要等待其他任务完成。
 * 结果合并：ForkJoinPool可以自动将各个线程的结果合并，无需手动进行结果合并。
 * 可扩展性：ForkJoinPool可以根据需要动态调整线程池的大小，以适应不同规模的任务。
 *
 * 非常经典的算法: https://www.ibm.com/developerworks/cn/java/j-lo-forkjoin/index.html
 * 使用场景：大规模数据计算
 */
public class T12_ForkJoinPool {

    static int[] nums = new int[1000000];
    static final int MAX_NUM = 50000;
    static Random r = new Random();

    static {
        for (int i = 0; i < nums.length; i++) {
            nums[i] = r.nextInt(100);
        }
        System.out.println(Arrays.stream(nums).sum());
    }

    /**
     * RecursiveAction表示一个可以递归执行的任务，通常用于实现分治算法
     */
    static class AddTask extends RecursiveTask<Long> {

        int start, end;

        public AddTask(int start, int end) {
            this.start = start;
            this.end = end;
        }

        @Override
        protected Long compute() {
            if (end - start <= MAX_NUM) {
                long sum = 0L;
                for (int i = start; i < end; i++) {
                    sum += nums[i];
                }
                return sum;
            }

            int middle = start + (end - start) / 2;

            AddTask subTask1 = new AddTask(start, middle);
            AddTask subTask2 = new AddTask(middle, end);
            subTask1.fork();
            subTask2.fork();

            return subTask1.join() + subTask2.join();
        }
    }

    public static void main(String[] args) {
        ForkJoinPool fjp = new ForkJoinPool();
        AddTask task = new AddTask(0, nums.length);
        long result = fjp.invoke(task);
        System.out.println("Result: " + result);
    }
}
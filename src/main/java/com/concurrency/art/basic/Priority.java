package com.concurrency.art.basic;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * 线程优先级Priority:
 * priority在1～10之间: MIN_PRIORITY = 1; MAX_PRIORITY = 10; default是NORM_PRIORITY=5;
 * 高优先级的线程会抢占低优先级的线程
 *
 */
public class Priority {
    private static volatile boolean notStart = true;
    private static volatile boolean notEnd = true;
    private static final int THREAD_NUMS = 10;


    public static void main(String[] args) throws InterruptedException {

        List<Job> jobs = new ArrayList<>();
        for (int i = 0; i < THREAD_NUMS; i++) {
            int priority = i < 5 ? Thread.MIN_PRIORITY : Thread.MAX_PRIORITY;
            Job job = new Job(priority, "thread:" + i);
            jobs.add(job);
            Thread thread = new Thread(job, "thread-" + i);
            thread.setPriority(job.priority);
            thread.start();
        }
        notStart = false;
        TimeUnit.SECONDS.sleep(5);
        notEnd = false;

        for (Job job : jobs) {
            System.out.println(job.name+" Job Priority:" + job.priority + ", Count : " + job.jobCount);
        }
    }

    static class Job implements Runnable {
        private int priority;
        private long jobCount;
        private String name;

        public Job(int priority) {
            this.priority = priority;
        }

        public Job(int priority, long jobCount) {
            this(priority);
            this.jobCount = jobCount;
        }

        public Job(int priority, String name) {
            this(priority);
            this.name = name;
        }

        @Override
        public void run() {
            while (notStart) {
                Thread.yield();
            }

            while (notEnd) {
                Thread.yield();
                jobCount++;
            }
        }
    }
}

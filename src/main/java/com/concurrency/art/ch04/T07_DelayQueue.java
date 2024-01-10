package com.concurrency.art.ch04;

import java.util.Random;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.DelayQueue;
import java.util.concurrent.Delayed;
import java.util.concurrent.TimeUnit;

/**
 * 用于执行定时任务
 */
public class T07_DelayQueue {
	// 排好顺序的，等待时间最长的被先拿出去
	static BlockingQueue<MyTask> tasks = new DelayQueue<>(); // 无界队列， 线程池里装的都是一个一个的任务

	static Random r = new Random();
	
	static class MyTask implements Delayed {
		
		long runningTime;
		
		public MyTask(long runningTime) {
			this.runningTime = runningTime;
		}

		@Override
		public int compareTo(Delayed o) {
			if (this.getDelay(TimeUnit.MILLISECONDS) < o.getDelay(TimeUnit.MILLISECONDS)) {
				return -1;
			} else if (this.getDelay(TimeUnit.MILLISECONDS) > o.getDelay(TimeUnit.MILLISECONDS)) {
				return 1;
			}
			return 0;
		}

		@Override
		public long getDelay(TimeUnit unit) {
			return unit.convert(runningTime - System.currentTimeMillis(), TimeUnit.MILLISECONDS);
		}

		@Override
		public String toString() {
			return "MyTask [runningTime=" + runningTime + "]";
		}
	}

	public static void main(String[] args) throws InterruptedException {
		long now = System.currentTimeMillis();
		MyTask t1 = new MyTask(now + 1000);
		MyTask t2 = new MyTask(now + 2000);
		MyTask t3 = new MyTask(now + 1500);
		MyTask t4 = new MyTask(now + 2500);
		MyTask t5 = new MyTask(now + 500);
		
		tasks.put(t1);
		tasks.put(t2);
		tasks.put(t3);
		tasks.put(t4);
		tasks.put(t5);
		
		System.out.println(tasks);
		
		for (int i = 0; i < 5; i ++) {
			System.out.println(tasks.take());
		}
	}
}
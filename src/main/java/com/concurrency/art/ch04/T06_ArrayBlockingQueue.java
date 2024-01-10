package com.concurrency.art.ch04;

import java.util.Random;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;

/**
 *  并发队列ArrayBlockingQueue
 *  非常适合生产者消费者模式
 */
public class T06_ArrayBlockingQueue {
	static BlockingQueue<String> strs = new ArrayBlockingQueue<>(10); // 有界队列， 线程池里装的都是一个一个的任务

	static Random r = new Random();

	public static void main(String[] args) throws InterruptedException {
		for (int i = 0; i < 10; i++) {
			strs.put("a" + i);
		}
		// strs.put("aaa"); // 满了就等待
		strs.add("aaa");
		// strs.offer("aaa");
		// strs.offer("aaa", 1, TimeUnit.SECONDS); // 按时间段阻塞
		
		System.out.println(strs);
	}
}
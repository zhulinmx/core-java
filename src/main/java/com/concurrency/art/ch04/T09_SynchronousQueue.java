package com.concurrency.art.ch04;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.SynchronousQueue;

/**
 * SynchronousQueue
 * 没有容量的队列
 */
public class T09_SynchronousQueue {

	public static void main(String[] args) throws InterruptedException {
		// 特殊的TransferQueue, 没有容量的Queue, 容量为0。
		BlockingQueue<String> strs = new SynchronousQueue<>();
		
		new Thread(() -> {
			try {
				System.out.println(strs.take());
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}).start();
		
		// strs.add("aaa");
		strs.put("aaa");  // 阻塞等待消费者消费
		System.out.println(strs.size());
	}
}
package com.concurrency.art.ch04;

import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

/**
 * 重点内容，重点掌握
 * ConcurrentLinkedQueue无界队列，容量无限，操作不需要等待，非阻塞
 *
 */
public class T04_ConcurrentLinkedQueue {
	
	public static void main(String[] args) {
		Queue<String> strs = new ConcurrentLinkedQueue<>();
		
		for (int i=0; i<10; i++) {
			strs.offer("a" + i);
		}
		
		System.out.println(strs);
		
		System.out.println(strs.poll());
		System.out.println(strs.size());
		
		System.out.println(strs.peek());
		System.out.println(strs.size());
		
		// 双端队列 Deque
		// 并发容器中常用

	}
}
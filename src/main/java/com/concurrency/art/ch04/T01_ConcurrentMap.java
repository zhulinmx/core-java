package com.concurrency.art.ch04;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentSkipListMap;
import java.util.concurrent.CountDownLatch;

/**
 * 阅读ConcurrentSkipListMap
 * http://blog.csdn.net/sunxianghuang/article/details/52221913
 *
 * 总结
 * 1. 对于Map/Set的选择与使用
 * 	    1) 不加锁（单线程时候）
 * 	    HashMap
 * 	    TreeMap
 * 	    LinkedHashMap
 * 
 * 	    2) 加锁（多线程场景）
 * 	    并发不高
 * 	    Hashtable
 * 	    Collections.synchronizedXXX
 *
 * 	    并发高的时候
 * 	    ConcurrentHashMap
 *
 * 	    并发性高且排序
 * 	    ConcurrentSkipListMap
 * 	    提供了一种线程安全的并发访问的排序映射表。内部是SkipList（跳表）结构实现，结合CAS，在理论上能够O(log(n))时间内完成查找、插入、删除操作。效率是AVL的水平。
 *      其存储结构：
 *      ConcurrentSkipListMap存储结构跳跃表（SkipList）：
 *      1、最底层的数据节点按照关键字升序排列。
 *      2、包含多级索引，每个级别的索引节点按照其关联数据节点的关键字升序排列。
 *      3、高级别索引是其低级别索引的子集。
 *      4、如果关键字key在级别level=i的索引中出现，则级别level<=i的所有索引中都包含key。

 * 
 * 2. 队列
 * 	    ArrayList
 * 	    LinkedList
 * 	    Collections.synchronizedXXX
 *
 * 	    高并发场景
 * 	    ArrayList  --> CopyOnWriteArrayList
 * 	    LinkedList --> ConcurrentLinkedQueue // 非阻塞
 * 	    BlockingQueue         // 阻塞
 * 	    	LinkedBlockingQueue
 * 	    	ArrayBlockingQueue
 * 	    	TransferQueue
 * 	    	SynchronousQueue
 * 	    DelayQueue：定时任务
 * 
 *
 */
public class T01_ConcurrentMap {
	
	public static void main(String[] args) {
		// 不同容器在多线程并发下的效率问题
//		Map<String, String> map = new ConcurrentHashMap<>();
//		Map<String, String> map = new ConcurrentSkipListMap<>();  // 高并发， 排序
		
//		Map<String, String> map = new Hashtable<>();
		Map<String, String> map = new HashMap<String, String>(); // Collections.synchronizedXXX
//		TreeMap
		Random r = new Random();
		Thread[] threads = new Thread[100];
		CountDownLatch latch = new CountDownLatch(threads.length);
		
		long start = System.currentTimeMillis();
		for (int i=0; i<threads.length; i++) {
			threads[i] = new Thread(() -> {
				for (int j=0; j<10000; j++) map.put("a" + r.nextInt(100000), "a" + r.nextInt(100000));
				latch.countDown();
			});
		}
		
		Arrays.asList(threads).forEach(t->t.start());
		try {
			latch.await();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		long end = System.currentTimeMillis();
		System.out.println(end - start);
	}
}
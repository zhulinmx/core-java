package com.concurrency.art.ch00;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 解决同样的问题更高效的方法，使用AtomicXXX类
 * AtomXXX类本身方法都是原子性的， 但不能保证多方法连续调用的原子性。
 */
public class T12 {
	/*volatile int count = 0;*/
	
	AtomicInteger count = new AtomicInteger(0); // 内部value由volatile修饰
	
	/*synchronized*/ void m() {
		for (int i = 0; i < 10000; i++) {
			// if (count.get() < 1000)
			// 这个位置没有原子性
			count.incrementAndGet(); // 替换 count ++;
		}
	}
	
	public static void main(String[] args) {
		T12 t = new T12();
		List<Thread> threads = new ArrayList<Thread>();
		
		for (int i = 0; i < 10; i ++) {
			threads.add(new Thread(t::m, "thread-" + i));
		}
		
		threads.forEach((o) -> o.start());
		
		threads.forEach((o) -> {
			try {
				o.join();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		});
		
		System.out.println(t.count);
	}
}
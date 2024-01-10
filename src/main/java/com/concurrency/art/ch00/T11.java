package com.concurrency.art.ch00;

import java.util.ArrayList;
import java.util.List;

/**
 * 对比上一个程序，可以用synchronized解决， synchronized既保证原子性，又保证可见性；
 * 而volatile只保证可见性、有序性（禁止指令重排）
 */
public class T11 {
	/*volatile*/ int count = 0;
	synchronized void m() {
		for (int i = 0; i < 10000; i++) {
			count ++;
		}
	}
	
	public static void main(String[] args) {
		T11 t = new T11();
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
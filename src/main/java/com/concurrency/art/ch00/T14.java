package com.concurrency.art.ch00;

import java.util.concurrent.TimeUnit;

/**
 * 锁定某对象o， 如果o的属性发生改变，不影响锁的使用
 * 但如果o变成另外一个对象，则锁定的对象发生改变
 * 应该避免将锁定对象的引用变成另外的对象
 * 锁在堆内存new出来的对象
 *
 */
public class T14 {
	
	Object o = new Object();
	
	void m() {
		synchronized(o) {
			while (true) {
				try {
					TimeUnit.SECONDS.sleep(1);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				System.out.println(Thread.currentThread().getName());
			}
		}
	}
	
	public static void main(String[] args) {
		T14 t = new T14();
		
		// start first thread
		new Thread(t::m, "t1").start();
		try {
			TimeUnit.SECONDS.sleep(3);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println(123);
		// create second thread
		Thread t2 = new Thread(t::m, "t2"); // 锁对象发生改变，所以t2线程得以执行
		t.o = new Object(); // 如果注释掉这句话，线程2将永远得不到执行
		t2.start();
	}
}
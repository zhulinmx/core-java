package com.concurrency.art.ch00;

/**
 * synchronized 对方法加锁, 同步方法和非同步方法是否可以同时调用
 * 在执行m1的过程之中，能否执行m2?
 * ---同步方法执行时， 非同步方法可以执行
 *
 * @author ZL
 */
public class T5 {

	public synchronized void m1() {
		System.out.println(Thread.currentThread().getName() + "m1 start...");
		try {
			Thread.sleep(10000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println(Thread.currentThread().getName() + "m1 end...");
	}

	public void m2() {
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println(Thread.currentThread().getName() + "m2 ...");
	}
	
	public static void main(String[] args) {
		T5 t = new T5();
		
		new Thread(() -> t.m1(), "t1").start();
		new Thread(() -> t.m2(), "t2").start();
		
//		new Thread(t::m1, "t1").start();
//		new Thread(t::m2, "t2").start();

		/**
		new Thread(new Runnable() {
			@Override
			public void run() {
				t.m1();
			}
		}).start();

		new Thread(new Runnable() {
			@Override
			public void run() {
				t.m2();
			}
		}).start();
		 */
	}
}

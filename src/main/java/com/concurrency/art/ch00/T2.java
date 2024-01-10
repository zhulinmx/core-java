package com.concurrency.art.ch00;

/**
 * synchronized 对类加锁 T2.class
 */
public class T2 implements Runnable {

	private static int count = 10;

	@Override
	public void run() {
		// lock current object.
		synchronized(T2.class) {
			count --;
			System.out.println(Thread.currentThread().getName() + "" + count);
		}
	}

	public static void main(String[] args) {
		for (int i=0; i<10; i++) {
			new Thread(new T2(), "thread" + i + "-").start();
		}
	}

}

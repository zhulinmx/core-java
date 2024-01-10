package com.concurrency.art.ch00;

/**
 * synchronized 对对象加锁
 */
public class T4 implements Runnable {

	private static int count = 10;

	@Override
	public synchronized void run() {
		count --;
		System.out.println(Thread.currentThread().getName() + " count= " + count);
	}
	
	public static void main(String[] args) {
		T4 t = new T4();
		for (int i = 0; i < 10; i++) {
			new Thread(t, "Thread" + i).start();
		}
	}
}

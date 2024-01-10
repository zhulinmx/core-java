package com.concurrency.art.ch00;

import java.util.concurrent.TimeUnit;

/**
 * 一个同步方法可以调用另一个同步方法。 一个线程已经获得某个对象的锁，再次申请的时候仍然会得到该对象的锁
 * 也就是说synchronized获得的锁是可重入的
 * synchronized 锁升级： https://blog.csdn.net/tongdanping/article/details/79647337
 */
public class T6 implements Runnable {

	@Override
	public synchronized void run() {
		System.out.println("m1 start...");
		try {
			TimeUnit.SECONDS.sleep(1);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		m2();//m2也是同步方法
		System.out.println("m1 end...");
	}
	
	private synchronized void m2() {
		try {
			TimeUnit.SECONDS.sleep(2);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("m2");
	}

	public static void main(String[] args) {
		T6 t = new T6();
		new Thread(t::run).start();
	}
}

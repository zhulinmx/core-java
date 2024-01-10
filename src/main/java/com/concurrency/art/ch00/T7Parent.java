package com.concurrency.art.ch00;

import java.util.concurrent.TimeUnit;

/**
 * 这里是继承中可能发生的情形，子类调用父类的同步方法，锁定的是同一对象
 */
public class T7Parent {
	synchronized void m1() {
		System.out.println("m1 start...");
		try {
			TimeUnit.SECONDS.sleep(4);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("m1 end...");
	}
	
	public static void main(String[] args) {
		new T7Parent().m1();
		new T7Child().m1();
	}
}

class T7Child extends T7Parent {
	@Override
	synchronized void m1() {
		System.out.println("child m1 start...");
		super.m1();
		System.out.println("child m1 end...");
	}
}

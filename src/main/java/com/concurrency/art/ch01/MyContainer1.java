package com.concurrency.art.ch01;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * 经典老题
 * 实现一个程序，提供两个方法， add, size
 * 写两个线程，线程一添加10个元素到容器中，线程2实现监控线程的个数，
 * 当个数到5个时，线程2给出提示并结束
 * 
 * 分析下面的程序，能完成这个功能吗？
 *
 */
public class MyContainer1 {
	
	List list = new ArrayList();
	
	public void add(Object o) {
		list.add(o);
	}
	
	public int size() {
		return list.size();
	}
	
	public static void main(String[] args) {
		MyContainer1 c = new MyContainer1();
		
		new Thread(() -> {
			for(int i=0; i<10; i++) {
				c.add(new Object());
				System.out.println("add" + i);
				
				try {
					TimeUnit.SECONDS.sleep(1);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}, "t1").start();

		System.out.println("Thread2...");

		new Thread(() ->{ 
			while(true) {
				if (c.size() == 5) {
					break;
				}
			}
			System.out.println("Thread2 finished...");
		}, "t2").start();
	}
}
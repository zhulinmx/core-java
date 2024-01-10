package com.concurrency.art.basic;


/**
 * 加锁顺序不一致产生死锁
 *  加锁一致也会产生死锁，比如哲学家问题，哲学家同时拿去左手的筷子，就会进入互相等待的情况
 *  Synchronized产生的死锁无法打断
 *
 */
public class DeadLock2 implements Runnable {
	public int flag = 1;
	static Object o1 = new Object(), o2 = new Object();

	@Override
	public void run() {
		System.out.println("flag=" + flag);
		if(flag == 1) {
			synchronized(o1) {
				try {
					Thread.sleep(500);
				} catch (Exception e) {
					e.printStackTrace();
				}
				synchronized(o2) {
					System.out.println("1");	
				}
			}
		}
		if(flag == 0) {
			synchronized(o2) {
				try {
					Thread.sleep(500);
				} catch (Exception e) {
					e.printStackTrace();
				}
				synchronized(o1) {
					System.out.println("0");
				}
			}
		}
	}	
	
	public static void main(String[] args) {
		DeadLock2 td1 = new DeadLock2();
		DeadLock2 td2 = new DeadLock2();
		td1.flag = 1;
		td2.flag = 0;
		Thread t1 = new Thread(td1,"td1");
		Thread t2 = new Thread(td2,"td2");
		t1.start();
		t2.start();
	}
}
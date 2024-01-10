package com.concurrency.art.ch00;

/**
 * synchronized对对象加锁的不同写法
 *
 */
public class T3 {

	private static int count = 10;
	
	public synchronized static void m() { // 等同于synchronized(T3.class)
		count --;
		System.out.println(Thread.currentThread().getName() + ": " + count);
	}
	
	public static void mm() {
		synchronized (T3.class) { //考虑下，这里写synchronized(this)是否可以？不可以，因为静态方法，变量是不需要new出对象就可以访问的
			count --;
			System.out.println(Thread.currentThread().getName() + ": " + count);
		}
	}

	public static void main(String[] args) {
		for (int i=0; i<10; i++) {
			new Thread(()-> {
				T3.mm();
			}).start();
		}

		for (int i=0; i<10; i++) {
			new Thread(()-> {
				T3.m();
			}).start();
		}
	}
}

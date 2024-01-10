package com.concurrency.art.ch00;

/**
 * synchronized关键字
 * 对当前对象加锁
 */
public class T1 {

	private int count = 10;

	public void m() {
		// lock current object.
		synchronized(this) { // 任何线程要执行下面的代码， 必须先拿到this的锁, 锁定this对象, 锁对象， 而不是代码块
			count --;
			System.out.println(Thread.currentThread().getName() + ": " + count);
		}
	}

	public synchronized void mm() { // 等同于 synchronized(this)， 锁定当前对象
		count --;
		System.out.println(Thread.currentThread().getName() + "" + count);
	}

	public static void main(String[] args) {
		T1 t = new T1();
		for (int i=0; i<10; i++) {
			new Thread(()-> {
				t.m();
			}).start();
		}
	}
}

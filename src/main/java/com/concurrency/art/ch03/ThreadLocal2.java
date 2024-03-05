package com.concurrency.art.ch03;

import java.util.concurrent.TimeUnit;

/**
 * 
 * 运行下面程序，理解ThreadLocal
 * 在使用的时候，状态改变，自己维护这个状态，不用通知其他线程，这时使用ThreadLocal
 * 可能会导致内存泄漏
 *
 *
 * 使用场景：自己进行改变，自己维护这个状态，不用其他线程
 * https://blog.csdn.net/zsfsoftware/article/details/50933151
 *
 * 必须回收自定义的 ThreadLocal 变量，尤其在线程池场景下，线程经常会被复用，如果不清理自定义的 ThreadLocal 变量，可能会影响后续业务逻辑和造成内存泄露等问题。
 * 尽量在代理中使用try-finally 块进行回收。
 *
 * objectThreadLocal.set(userInfo);
 * try {
 *   // ...
 * } finally {
 * objectThreadLocal.remove();
 * }
 *
 */
public class ThreadLocal2 {
	// 每个线程各放一份，修改只改自己的一份， 不会是别人的一份，以空间换时间，效率更高

	static ThreadLocal<Person> t1 = new ThreadLocal<>();
	static ThreadLocal<String> t2 = new ThreadLocal<>();
	static ThreadLocal<Integer> t3 = new ThreadLocal<>();

	public static void main(String[] args) {

		new Thread(() -> {
			try {
				TimeUnit.SECONDS.sleep(1);
				t2.set("111");
				System.out.println("1:" + t1.get());
			} catch (InterruptedException e) {
				e.printStackTrace();
			} finally {
				t2.remove();
				t1.remove();
			}

		}).start();
		
		new Thread(() -> {
			try {
				TimeUnit.SECONDS.sleep(1);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			t1.set(new Person("a"));
			t2.set("222");
			t3.get();
			System.out.println("2:" + t1.get());
		}).start();

		try {
			TimeUnit.SECONDS.sleep(3);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		Thread newThread = new Thread(() -> {
			try {
				TimeUnit.SECONDS.sleep(1);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			t1.set(new Person("b"));
			t3.set(333);
			System.out.println("3:" + t1.get());
		});

		// 启动新线程
		newThread.start();

		// 等待新线程执行完毕
		try {
			newThread.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		System.out.println("main:" + t1.get());
	}
	
	static class Person {
		String name = "zhangsan";

		private void setName(String n) {
			name = n;
		}

		public Person() {
		}

		public Person(String name) {
			this.name = name;
		}

		@Override
		public String toString() {
			return "Person{" +
					"name='" + name + '\'' +
					'}';
		}
	}
}
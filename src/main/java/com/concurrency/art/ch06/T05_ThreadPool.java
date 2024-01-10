package com.concurrency.art.ch06;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * 固定线程个数的线程池
 *
 */
public class T05_ThreadPool {
	public static void main(String[] args) throws InterruptedException {
		/**
		 *
		 *  可以看到这里是new的ThreadPoolExecutor，参数不同会有不同类型，可自定义ThreadPool
		 *
		 *      public static ExecutorService newFixedThreadPool(int nThreads) {
		 *         return new ThreadPoolExecutor(nThreads, nThreads,
		 *                                       0L, TimeUnit.MILLISECONDS,
		 *                                       new LinkedBlockingQueue<Runnable>());
		 *     }
		 *
		 */
		ExecutorService service = Executors.newFixedThreadPool(9);

		for (int i = 1; i <= 50; i ++) {
			service.execute(()->{
				try {
					TimeUnit.MILLISECONDS.sleep(20);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				System.out.println(Thread.currentThread().getName());
			});
		}
		
		System.out.println(service);

		// 关闭线程池
		service.shutdown();
		System.out.println(service.isTerminated());
		System.out.println(service.isShutdown());

		System.out.println(service);
		
		TimeUnit.SECONDS.sleep(5);
		System.out.println(service.isTerminated());
		System.out.println(service.isShutdown());

		System.out.println(service);
	}
}
package com.concurrency.art.ch06;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.FutureTask;
import java.util.concurrent.TimeUnit;

/**
 * FutureTask
 *
 */
public class T04_Future {
	public static void main(String[] args) throws InterruptedException, ExecutionException {

		/** java 8 lambada **/

		FutureTask<Integer> task = new FutureTask<>(() -> {
			TimeUnit.MILLISECONDS.sleep(500);
			return 1000;
		}); // new Callable() {Integer call();}
		
		new Thread(task).start();
		System.out.println(task.get());  // 阻塞
		
		// ***************************
		ExecutorService service = Executors.newFixedThreadPool(5);
		Future<Integer> f = service.submit(() -> {
			TimeUnit.MILLISECONDS.sleep(500);
			return 123;
		});
		
		System.out.println(f.get());
		System.out.println(f.isDone());

		// 结束线程
		service.shutdown();
	}
}
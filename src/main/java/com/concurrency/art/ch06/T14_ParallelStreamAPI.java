package com.concurrency.art.ch06;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * 往多线程上考虑考虑
 * java8 增加 parallelStream
 */
public class T14_ParallelStreamAPI {
	public static void main(String[] args) throws InterruptedException, IOException {
		List<Integer> nums = new ArrayList<Integer>();
		Random r = new Random();
		for (int i = 0; i < 10000; i ++)
			nums.add(1000000 + r.nextInt(1000000));
		
		long start = System.currentTimeMillis();
		nums.forEach(v->isPrime(v));
		long end = System.currentTimeMillis();
		System.out.println(end - start);
		
		start = System.currentTimeMillis();
		// 默认使用多线程
		nums.parallelStream().forEach(T14_ParallelStreamAPI::isPrime);
		end = System.currentTimeMillis();
		System.out.println(end - start);
	}

	static boolean isPrime(int num) {
		for (int i = 2; i <= num; i++) {
			if (num % i == 0) {
				return false;
			}
		}
		return true;
	}
}
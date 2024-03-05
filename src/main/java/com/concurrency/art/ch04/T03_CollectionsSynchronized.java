package com.concurrency.art.ch04;

import java.util.*;

/**
 * 加锁的容器使其安全
 * Collections.synchronizedXXXXX()
 *
 */
public class T03_CollectionsSynchronized {
	
	public static void main(String[] args) {
		List<String> strings = new ArrayList<String>();
		Map map = new HashMap();

		List<String> strsSync = Collections.synchronizedList(strings);
		Map mapSync = Collections.synchronizedMap(map);
	}
}
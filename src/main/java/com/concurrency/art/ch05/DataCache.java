package com.concurrency.art.ch05;

import java.util.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class DataCache {

    private final Map<Integer, Integer> cache = new LinkedHashMap<>();
    private final ReadWriteLock rwLock;
    private final Lock readLock;
    private final Lock writeLock;

    public DataCache() {
        this.rwLock = new ReentrantReadWriteLock();
        this.readLock = rwLock.readLock();
        this.writeLock = rwLock.writeLock();
    }

    public List<Integer> allKeys() {
        readLock.lock(); // 获取读锁
        try {
            // 执行读取操作
            return new ArrayList<>(cache.keySet());
        } finally {
            readLock.unlock(); // 释放读锁
        }
    }

    public Integer readData(Integer key) {
        readLock.lock(); // 获取读锁
        try {
            // 执行读取操作
            return cache.get(key);
        } finally {
            readLock.unlock(); // 释放读锁
        }
    }

    public void writeData(Integer key, Integer value) {
        writeLock.lock(); // 获取写锁
        try {
            // 执行写入操作
            cache.put(key, value);
        } finally {
            writeLock.unlock(); // 释放写锁
        }
    }

    public static void main(String[] args) {
        DataCache dc = new DataCache();
        ExecutorService executorService = Executors.newFixedThreadPool(5);

        for (int i = 0; i < 1000; i++) {
            int finalI = i;
            executorService.execute(() -> {
                if (finalI < 100) {
                    dc.writeData(finalI, finalI);
                } else {
                    System.out.println(dc.readData((int) (Math.random() * 100)));
                }
            });
        }

        executorService.shutdown();
        System.out.println(dc.allKeys());
    }

}

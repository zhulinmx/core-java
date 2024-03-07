package com.concurrency.art.ch05;

import java.util.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * 使用ReadWriteLock来设计一个缓存
 *
 *  1. ReentrantReadWriteLock允许选择公平性或非公平性。
 *      默认情况下，它是非公平的，但如果需要，可以在构造时启用公平性。 // xxx = new ReentrantReadWriteLock(true);
 *
 *  2. ReentrantReadWriteLock和其他锁，比如ReentrantLock的区别吧。
 *     ReentrantLock是一种排他锁，也就是说，不管是读操作还是写操作，同一时间只能有一个线程访问。
 *     而ReadWriteLock则更灵活，允许多个线程同时进行读操作。
 *
 *  2. ReadWriteLock读写锁适合场景：
 *    1) 读多写少的场景
 *    当一个应用主要涉及到读取操作，而写操作相对较少时，使用ReadWriteLock非常合适。因为它允许多个线程同时读取数据，从而大大提高了并发性能。
 *    2) 数据一致性要求高的场景
 *
 *  3. ReadWriteLock的锁降级和锁升级
 *     锁降级是指在持有写锁的同时获取读锁，然后释放写锁的过程。这个过程中，数据不会被其他写操作修改，保证了数据的一致性。
 *     锁升级，即从读锁升级到写锁，则在ReadWriteLock中是不被允许的。这是因为允许锁升级会引起死锁。
 *
 */
public class DataCache<K,V> {

    private final Map<K,V> cache = new LinkedHashMap<>();
    private final ReadWriteLock rwLock;
    private final Lock readLock;
    private final Lock writeLock;

    public DataCache() {
        this.rwLock = new ReentrantReadWriteLock();
        this.readLock = rwLock.readLock();
        this.writeLock = rwLock.writeLock();
    }

    public List<K> allKeys() {
        readLock.lock(); // 获取读锁
        try {
            // 执行读取操作
            return new ArrayList<>(cache.keySet());
        } finally {
            readLock.unlock(); // 释放读锁
        }
    }

    public V readData(K key) {
        readLock.lock(); // 获取读锁
        try {
            // 执行读取操作
            return cache.get(key);
        } finally {
            readLock.unlock(); // 释放读锁
        }
    }

    public void writeData(K key, V value) {
        writeLock.lock(); // 获取写锁
        try {
            // 执行写入操作
            cache.put(key, value);
        } finally {
            writeLock.unlock(); // 释放写锁
        }
    }

    public static void main(String[] args) {
        DataCache<Integer, Integer> dc = new DataCache<>();
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

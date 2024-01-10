package com.concurrency.art.ch04;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.TimeUnit;

/**
 * 生产者消费者
 */
public class T10 {

    public static void main(String[] args) {
        int capacity = 100;
        ArrayBlockingQueue queue = new ArrayBlockingQueue<>(capacity);

        Thread consumer = new Thread(() -> {
            while (true) {
                try {
                    queue.take();
                    TimeUnit.MILLISECONDS.sleep(2);
                }catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        Thread producer = new Thread(() -> {
            while (true) {
                try {
                    queue.put(1);
                    System.out.println("current size is " + queue.size());
                    TimeUnit.MILLISECONDS.sleep(1);
                }catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        consumer.start();
        producer.start();
    }
}

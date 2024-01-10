package com.concurrency.art.ch00;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.TimeUnit;

public class CyclicBarrierTest03 {
    static CyclicBarrier cyclicBarrier = new CyclicBarrier(2);

    public static void main(String[] args) throws InterruptedException {
        Thread t = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    cyclicBarrier.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (BrokenBarrierException e) {
                    e.printStackTrace();
                }
            }
        });
        t.start();
        t.interrupt();
        System.out.println( "status: " + t.isInterrupted() + cyclicBarrier.getNumberWaiting());
        TimeUnit.SECONDS.sleep(1);
        try {
            cyclicBarrier.await();
        } catch (Exception e) {
            System.out.println(cyclicBarrier.isBroken());
        }
    }
}

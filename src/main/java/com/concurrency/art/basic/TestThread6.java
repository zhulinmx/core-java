package com.concurrency.art.basic;

public class TestThread6 {
    public static void main(String args[]) {
        //main线程和t线程交替执行
        Thread t = new Runner6();
        t.start();

        for (int i = 0; i < 50; i++) {
            System.out.println("MainThread: " + i);
        }
    }
}

class Runner6 extends Thread {
    public void run() {
        System.out.println(Thread.currentThread().isAlive());
        for (int i = 0; i < 50; i++) {
            System.out.println("SubThread: " + i);
        }
    }
}
package com.concurrency.art.basic;


public class TestJoin {

    private static int sum = 0;

    public static void add() {
        for (int i = 0; i < 99; i++) {
            sum +=i;
        }
    }

    public static void main(String[] args) {
        Thread t1 = new Thread(()-> {
            add();
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        t1.start();

        try {
            t1.join(); //waits for this thread to die (等t1执行完成，主线程才继续执行)
        } catch (InterruptedException e) {

        }
        System.out.println("i am main thread "+sum);
    }
}


package com.concurrency.art.ch05;

import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 哲学家进餐问题
 * @author ZL
 */
class Philosopher extends Thread {
    private final Lock leftFork;
    private final Lock rightFork;
    private boolean isEating = false;
    private final Condition leftCondition;
    private final Condition rightCondition;

    public Philosopher(Lock leftFork, Lock rightFork, Condition leftCondition, Condition rightCondition) {
        this.leftFork = leftFork;
        this.rightFork = rightFork;
        this.leftCondition = leftCondition;
        this.rightCondition = rightCondition;
    }

    @Override
    public void run() {
        while (true) {
            if (!isEating) {
                // 尝试获取左右两边的筷子
                if (leftFork.tryLock() && rightFork.tryLock()) {
                    try {
                        // 如果成功获取到筷子，开始吃饭
                        isEating = true;
                        System.out.println(Thread.currentThread().getName() + " 开始吃饭");
                        // 模拟吃饭过程
                        int seconds = ThreadLocalRandom.current().nextInt(2000, 4000);
                        Thread.sleep(seconds);
                        System.out.println(Thread.currentThread().getName() + " 结束吃饭");
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    } finally {
                        // 吃完饭后，释放左右两边的筷子并进入等待状态
                        leftCondition.signalAll(); // 唤醒等待的哲学家们
                        rightCondition.signalAll(); // 唤醒等待的哲学家们
                        leftFork.unlock();
                        rightFork.unlock();
                        isEating = false;
                        //思考
                        try {
                            Thread.sleep(ThreadLocalRandom.current().nextInt(1000, 3000));
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                } else {
                    // 如果无法获取到筷子，等待其他哲学家释放筷子的信号
                    try {
                        if(leftFork.tryLock()) {
                            leftCondition.await();
                        }
                        if(rightFork.tryLock()) {
                            rightCondition.await();
                        }
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            } else {
                System.out.println(Thread.currentThread().getName() + " eating");
            }
        }
    }

    public static void main(String[] args) {
        Lock lock1 = new ReentrantLock();
        Lock lock2 = new ReentrantLock();
        Lock lock3 = new ReentrantLock();
        Lock lock4 = new ReentrantLock();
        Lock lock5 = new ReentrantLock();

        Condition c1 = lock1.newCondition();
        Condition c2 = lock2.newCondition();
        Condition c3 = lock3.newCondition();
        Condition c4 = lock4.newCondition();
        Condition c5 = lock5.newCondition();

        Philosopher p1 = new Philosopher(lock1, lock2, c1, c2);
        Philosopher p2 = new Philosopher(lock2, lock3, c2, c3);
        Philosopher p3 = new Philosopher(lock3, lock4, c3, c4);
        Philosopher p4 = new Philosopher(lock4, lock5, c4, c5);
        Philosopher p5 = new Philosopher(lock5, lock1, c5, c1);

        p1.start();
        p2.start();
        p3.start();
        p4.start();
        p5.start();

    }

}

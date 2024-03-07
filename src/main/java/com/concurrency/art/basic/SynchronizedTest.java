package com.concurrency.art.basic;

/**
 *
 *  synchronized（重量级、可重入、非公平锁），底层实现基于the object's monitor（对象监视器）
 *
 *  1. 控制台中执行如下指令查看分析synchronized字节码：
 *  javac SynchronizedTest.java
 *  javap -c -s -v SynchronizedTest.class
 *  可以发现字节码中生成了这两个指令：monitorenter 和 monitorexit：
 *  //
 *    6: monitorenter
 *    7: getstatic     #4                  // Field java/lang/System.out:Ljava/io/PrintStream;
 *   10: ldc           #5                  // String Inside synchronized block
 *   12: invokevirtual #6                  // Method java/io/PrintStream.println:(Ljava/lang/String;)V
 *   15: aload_1
 *   16: monitorexit
 *   //
 *  意味着当一个线程进入synchronized代码块即执行monitorenter指令，进行加锁，获取对象的Monitor，
 *  object的对象头有个Mark Word（标记字），在重量级锁状态下，会指向对象监视器Monitor的指针，只有一个线程可以和Monitor建立关联；
 *  当线程执行完monitorexit，该线程会释放锁，其他线程会尝试和Monitor建立关联。
 *
 *  2. 被称为重量级的原因？
 *  Java线程是映射到操作系统的原生线程之上的，如果要阻塞或者唤醒一个线程，都需要操作系统来完成，会使CPU在用户态和核心态之间频繁切换，
 *  因此状态转换需要耗费很多的处理时间，甚至比用户执行代码的时间还长，所以代价高、效率低。
 *
 *
 */
public class SynchronizedTest {
    private Object lock = new Object();

    public void method() {
        synchronized (lock) {
            System.out.println("Inside synchronized block");
        }
    }
}

# 学习方式
个人觉得并发属于精华也是最难理解的部分，所以很重要啦！
并发你不理解的话，很难把握其运行，所以很多人建议说如果不明白尽量不要瞎写多线程，很容易出问题，这句话倒是没错，但咱们就是爱挑战呐！
其实并发的核心就是确保数据安全，就是数据应该有着正确的结果，假设是10个人去竞争100个面包，花了100个面包的钱结果还剩5个面包，那么就不对了，这就是竞争产生的面包"不安全"。
可以按照com.concurrency.art.basic -> com.concurrency.art.ch00 -> com.concurrency.art.ch01...的顺序层层递进学习，边看边查
首要是熟悉API（concurrency.art.basic是基础），自己跑跑每个例子
其次，理解每个例子实现的原理，CAS，AQS这些都是什么东西；
再者，考虑是否有更好解决方式；
最后，阶段性回看，也许你随着深入了解之后，会强化理解甚至推翻你最开始的想法；

# Java.util.concurrent包
JDK1.5之后引入，实现的一个粗粒度的并发框架。
- java.util.concurrent :
  ConcurrentLinkedQueue、ConcurrentHashMap、CountDownLatch
- java.util.concurrent.locks :
  并发锁
- java.util.concurrent.atomic :
  各种原子类


# 共享内存的正确性或者说线程安全的三个基本特性：
- 原子性（Atomicity）
- 可见性（Visibility）
- 有序性（Ordering）


# 思考题
- volatile和synchronized区别
  可从以下几个地方描述
  - 用法
  - 线程安全性
  
- 写一个程序，证明AtomXXX类比synchronized更高效。

- AtomXXX类可以保证可见性吗？写个程序来证明。

- 写一个程序证明AtomXXX类的多个方法并不构成原子性。

- 写一个程序模拟死锁。

- 写一个程序，在main线程中启动100个线程，100个线程完成后，主线程打印完成。使用join和CountDownLatch都可以完成。

- Synchronize和ReentrantLock区别。
  - ReentrantLock 粒度更细
  - ReentrantLock比synchronize灵活
    - tryLock
    - tryLock可指定时间
    - lockInterruptly()
    - 可以指定锁为公平锁
  - 释放方式不同，Synchronize在加锁代码完成或者异常时会自动释放锁，而ReentrantLock需要在finally中手动释放。

- Lock原理
   底层原理是AQS
   
- AQS实现原理（AbstractQueuedSynchronizer）

   - AQS中 维护了一个volatile int state（代表共享资源）和一个FIFO线程等待队列（多线程争用资源被阻塞时会进入此队列）。
   这里volatile能够保证多线程下的可见性，当state=1则代表当前对象锁已经被占有，其他线程来加锁时则会失败，加锁失败的线程会被放入一个FIFO的等待队列中，并且会被UNSAFE.park()操作挂起，等待其他获取锁的线程释放锁才能够被唤醒。
   另外state的操作都是通过 CAS 来保证其并发修改的安全性。
    
   - 参照 https://juejin.cn/post/6844904146127044622#heading-2

- 非公平锁和公平锁的区别（ReentrantLock默认创建非公平锁）：
   公平锁在加锁的时候，会先判断AQS等待队列中是存在节点，如果存在节点则会直接入队等待
   非公平锁性能高于公平锁性能。非公平锁可以减少CPU唤醒线程的开销，整体的吞吐效率会高点，CPU也不必取唤醒所有线程，会减少唤起线程的数量
   非公平锁性能虽然优于公平锁，但是会存在导致线程饥饿的情况。在最坏的情况下，可能存在某个线程一直获取不到锁。
   不过相比性能而言，饥饿问题可以暂时忽略，这可能就是ReentrantLock默认创建非公平锁的原因之一了。
   
- Condition和wait/notify的比较
  - Condition可以精准的对多个不同条件进行控制，wait/notify只能和synchronized关键字一起使用，并且只能唤醒一个或者全部的等待队列；
  - Condition需要使用Lock进行控制，使用的时候要注意lock()后及时的unlock()，Condition有类似于await的机制，因此不会产生加锁方式而产生的死锁出现，
    同时底层实现的是park/unpark的机制，因此也不会产生先唤醒再挂起的死锁，一句话就是不会产生死锁，但是wait/notify会产生先唤醒再挂起的死锁。


- 线程间通信：
    共享内存
    线程间发消息
    
- Executor：执行任务


- ExecutorService
  Callable(exist return value) = Runnable(non-return value)

- Executors: 
  工具类，类似工具类：Arrays, Collections


- ThreadPool

  - 线程池大小：
  考虑因素除了硬件，还有线程任务是CPU密集型还是IO密集型，以及是否其他任务在同时运行。
  如果你的程序是计算密集型的并且没有IO操作，那么建议线程数设置为cpu核数+1，减少上下文切换。
  如果你的程序是IO密集型的（包括网络连接等待），那么可以按照 Nthreads = NCPU * UCPU * (1 + W/C) 计算线程数.
  其中：
  NCPU是处理器的核的数目，可以通过Runtime.getRuntime().availableProcessors()得到
  UCPU是期望的CPU利用率（该值应该介于0和1之间）
  W/C是等待时间与计算时间的比率
  比如4核的处理器NCPU是4，你的程序计算一个方法需要5秒钟，整个程序运行也就需要5秒钟，那么W/C比率应该是100，
  NCPU利用率希望是100%那么也就是1，总体程序最佳的线程数应该是4*1*(1+100）=404个线程数，但实际操作中，
  设置404个线程明显不能带来性能的优势，这么多线程数只会增加上下文来回切换带来更严重的性能问题。
  
  - 线程池的好处：
  优势: 实现对线程的复用，避免了反复创建及销毁线程的开销；使用线程池统一管理线程可以减少并发线程的数目，而线程数过多往往会在线程上下文切换上以及线程同步上浪费过多时间。





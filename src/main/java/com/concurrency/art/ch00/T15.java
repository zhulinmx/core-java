package com.concurrency.art.ch00;

import java.util.concurrent.atomic.LongAdder;

/**
 *
 * LongAdder使用上类似AtomicXXX
 * 适用于高并发场景下的计数器、累加器等需求，能够提高程序的性能和吞吐量
 *
 * @author ZL
 *
 */
public class T15 {
    public static void main(String[] args) {
        LongAdder la = new LongAdder();
        la.increment();
        System.out.println(la);
        la.add(123);
        System.out.println(la);
    }
}

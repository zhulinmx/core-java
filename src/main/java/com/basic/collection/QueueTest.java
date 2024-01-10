package com.basic.collection;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * ArrayDeque既可以当栈使用，也可以当双端队列，默认长度是16
 *
 */
public class QueueTest {

    public static void main(String[] args) {
        Deque deque = new ArrayDeque<Integer>();
        deque.addFirst(12);
        deque.addLast(13);

    }
}

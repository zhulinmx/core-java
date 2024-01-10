package com.basic.collection;

import java.util.Arrays;

/***
 *
 * 实现一个循环队列
 * 判空
 * 判满
 *
 */
public class CircleQueue<E> {

    private E[] elements;
    private int front;
    private int rear;

    public CircleQueue(E[] inputArray) {
        //虽然长度为k+1，但是只能存放k个
        this.elements = inputArray;
        this.front = 0;
        this.rear = 0;
    }

    public boolean isEmpty() {
        return this.rear == this.front ? true: false;
    }

    public boolean isFull() {
        return ((this.rear+1) % this.elements.length) == this.front ? true: false;
    }

    public void push(E e) {
        if(!isFull()) {
            this.elements[rear] = e;
            rear = (rear+1) % this.elements.length;
        }
    }

    public E pop() {
        if(!isEmpty()) {
            E e = this.elements[front];
            this.elements[front] = null;
            front = (front+1) % this.elements.length;
            return e;
        }
        return null;
    }

    public void print() {
        Arrays.stream(this.elements).forEach(e -> System.out.print(" " + e));
    }

    public static void main(String[] args) {
        // 创建不同类型数组： Integer, Double 和 Character
        Integer[] intArray = new Integer[6];
        Double[] doubleArray = new Double[5];
        Character[] charArray = new Character[3];
        String[] stringArray = { null, null, null, null, null};
        CircleQueue cq = new CircleQueue(intArray);
        cq.push(2);
        cq.push(100);
        cq.push(101);
        cq.push(99);
        cq.push(4);
        cq.print();
        cq.pop();
        cq.pop();
        cq.pop();
        cq.pop();
        cq.push(5);
        cq.push(6);
        cq.push(7);
        cq.pop();
        cq.print();
    }
}

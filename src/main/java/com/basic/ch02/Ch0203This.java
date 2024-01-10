package com.basic.ch02;

/**
 * this 关键字
 * 在类的方法定义中使用的this关键字代表使用该方法的对象的引用
 * 当必须指定当前使用方法的对象是谁的时候用this
 * 使用this处理类中构造方法中，成员变量和参数重名的问题
 * 当前对象的引用
 *
 */
public class Ch0203This {

    int i = 0;

    /**
     * 原则：当你确定不了一个参数到底是哪个变量的时候，找离他最近的声明
     *
     */
    public Ch0203This(int i) {
        this.i = i;
    }

    /**
     * 链式编程
     * 返回值在栈空间分配
     *
     * @return
     */
    public Ch0203This increment() {
        i ++;
        return this;
    }

    public void print() {
        System.out.println("i = " + i);
    }

    public static void main(String[] args) {
        Ch0203This c = new Ch0203This(1);
        c.increment().increment().print();
    }
}

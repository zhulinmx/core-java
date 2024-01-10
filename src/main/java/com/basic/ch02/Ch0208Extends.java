package com.basic.ch02;

/**
 *  继承：Child类，Parent类
 *  class Child extends Parent
 *
 * 继承中的构造方法
 *   规则：
 *   1. 子类在构造的时候，要首先调用父类的构造方法
 *   2. super 关键字
 *   3. 如果调用了super，必须写在子类构造方法的第一行
 *
 */
public class Ch0208Extends {

    public static void main(String[] args) {
        Child c = new Child();
        System.out.println(Child.getA1());
        System.out.println(c);
    }
}

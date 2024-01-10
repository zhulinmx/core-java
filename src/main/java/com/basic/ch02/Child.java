package com.basic.ch02;

/**
 * 先后执行顺序：静态代码块 → 普通代码块 → 构造方法
 *
 * 当比较子父类执行顺序时：
 * 父类静态代码块 → 子类静态代码块 → 父类普通代码块 → 父类构造方法 → 子类普通代码块 → 子类构造方法（注意交错顺序）
 *
 * 1、静态与非静态
 *
 * 静态的是随着类的加载而执行，普通的则是实例化的时候执行：
 * 静态早于普通
 *
 * 2、变量与代码块的执行顺序
 *
 * 谁先声明谁先执行：
 * 静态变量与静态代码块谁先声明谁先执行；
 * 普通变量与普通代码块谁先声明谁先执行；
 *
 */

public class Child extends Parent {

    private static int a1;
    private static final int b1 = 333;

    public Child() {
        super();
        System.out.println("this is constructor of child");
    }

    static {
        System.out.println("this is static content of child");
    }

    {
        System.out.println("this is child");
    }

    public static int getA1() {
        return a1;
    }

    public static void main(String[] args) {

        Child c = new Child();
        System.out.println(Child.getA1());
        System.out.println(c);
    }
}

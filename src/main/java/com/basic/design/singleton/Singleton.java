package com.basic.design.singleton;

/**
 * 单例模式
 * 静态内部类
 */
public class Singleton {
    private int x = 1;
    private static int y = 2;

//    public Singleton() {
//        //防止反射破坏代理模式
//        if (getInstance() != null) {
//            throw new RuntimeException();
//        }
//    }

    public void test() {
        System.out.println(new InnerClass().a);
        System.out.println(InnerClass.b);
        System.out.println(InnerClass.class);
        System.out.println(getInstance());
//        getInstance().test();
    }

    /**
     * 静态内部类与非静态内部类的区别：
     *     静态内部类不会持有外围类的引用，而非静态内部类会隐式持有外围类的一个引用。
     *     （1）静态内部类不需要有指向外部类的引用；但非静态内部类需要持有对外部类的引用。
           （2）非静态内部类能够访问外部类的静态和非静态成员；静态类不能访问外部类的非静态成员，他只能访问外部类的静态成员。
           （3）一个非静态内部类不能脱离外部类实体被创建，一个非静态内部类可以访问外部类的数据和方法，因为他就在外部类里面。
     */

    static class InnerClass {
        private int a = 3;
        private static int b = 4;
        private static final Singleton INSTANCE = new Singleton();

        public void test() {
            System.out.println(b+1);
            System.out.println(y); //只能访问y
        }
    }

    class NonStaticInnerClass {
        private int d;
        public void setD(int dd) {
            d = dd;
            System.out.println(x + y); //可以访问x和y
        }
    }

    public static Singleton getInstance() {
        return InnerClass.INSTANCE;
    }

    public static void main(String[] args) {
        Singleton s = new Singleton();
        s.test();
        InnerClass ns = new InnerClass();
        ns.test();

        Singleton.getInstance().x = 23;
        InnerClass.INSTANCE.x = 34;

        System.out.println(Singleton.getInstance().x);
    }

}

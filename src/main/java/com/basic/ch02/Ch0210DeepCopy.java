package com.basic.ch02;


/**
 *
 * object 中定义了哪些方法？
 * ----- clone(), equals(), hashCode(), toString(), notify(), notifyAll(), wait(), finalize(), getClass()
 *
 *  wait():
     在指定对象上调用 wait 方法会让当前 线程进入等待状态（前提是当前线程持有该对象的 monitor），
     此时当前 线程会释放相应对象的 monitor，这样一来其它线程便有机会获取这个对 象的 monitor 了。
     当其它线程获取了这个对象的 monitor 并进行了所需操 作时，便可以调用 notify 方法唤醒之前进入等待状态的线程。
 *
 * Java中提供了以下四种创建对象的方式:
 * - new创建新对象
 * - 通过反射机制
 * - 采用clone机制
 * - 通过序列化机制
 *
 * 浅拷贝:被复制对象的所有变量都含有与原来的对象相同的值,而所有的对其他对象的引用仍然指向原来的对象.换言之,浅拷贝仅仅复制所考虑的对象,而不复制它所引用的对象.
 * 深拷贝:被复制对象的所有变量都含有与原来的对象相同的值.而那些引用其他对象的变量将指向被复制过的新对象.而不再是原有的那些被引用的对象.换言之.深拷贝把要复制的对象所引用的对象都复制了一遍.
 *
 */
public class Ch0210DeepCopy {

    public static void main(String[] args) throws Exception{
        Object obj1 = new Object();
        Object obj2 = new Object();

        System.out.println(obj1 == obj2);
        System.out.println(obj1.equals(obj2));

        Address ad1 = new Address(021, "shanghai");

        Person p1 = new Person("aa", "female", ad1);
        Person p2 = p1; //浅复制
        Person p3 = (Person) p1.deepClone(); //深复制
        Person p4 = (Person) p1.clone();  //深复制

        System.out.println(p1);
        System.out.println(p2);
        System.out.println(p3);
        System.out.println(p4);

    }
}


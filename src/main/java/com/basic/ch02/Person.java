package com.basic.ch02;


import java.io.*;


/**
 *
 * Java 原型模式是一种创建型设计模式，它允许你通过克隆现有对象来创建新对象，而无需通过实例化类并手动设置其属性来创建对象。
 * 以下是 Java 原型模式的优劣点：
 *
 * 优点：
 *
 * 减少对象创建的成本：使用原型模式可以通过复制现有对象来创建新对象，而无需创建新对象的成本。因为它避免了重新初始化和设置对象的属性。
 * 简化对象的创建过程：原型模式可以避免复杂对象的创建过程，尤其是当创建对象需要进行一系列复杂的步骤时。这意味着你可以在实际需要时轻松地创建和配置对象，而不必在程序的许多地方执行大量的初始化和配置代码。
 * 提高程序性能：在大多数情况下，复制对象比创建新对象更快。原型模式可以在运行时使用它已经存在的对象，这有助于提高程序的性能。
 *
 * 劣点：
 *
 * 复制对象可能会带来副作用：复制一个对象可能会导致副作用，例如如果原型对象的属性是可变的，那么它们的值可能会在对象被克隆时改变。
 * 复制对象可能会导致深度克隆问题：如果一个对象具有多层嵌套结构，复制它可能会变得很困难，因为你需要确保复制所有嵌套的对象及其属性。这可能会导致深度克隆问题。
 * 必须保证克隆的对象是可用的：在某些情况下，原型对象可能无法克隆，这可能是因为它没有实现 Cloneable 接口，或者它的构造函数是私有的。在这种情况下，原型模式无法使用
 *
 */

public class Person implements Cloneable, Serializable {

    private String name;
    private Address address;
    private transient String sex; //transient = no need to serialize

    public Person(String name, String sex, Address address) {
        this.name = name;
        this.sex = sex;
        this.address = address;
    }

    /**
     * ①让每个引用类型属性内部都重写clone()方法，比较繁琐
     * @return
     * @throws CloneNotSupportedException
     */
    protected Object clone() throws CloneNotSupportedException {
        Person p = (Person) super.clone();
        p.address = (Address) address.clone();
        return p;
    }

    /**
     * ②利用序列化实现深复制
     * 序列化是将对象写到流中便于传输，而反序列化则是把对象从流中读取出来。这里写到流中的对象则是原始对象的一个拷贝，因为原始对象还存在JVM中，所以我们可以利用对象的序列化产生克隆对象，然后通过反序列化获取这个对象。
     * **注意：每个需要序列化的类都要事先Serializable接口，如果有某个属性不需要序列化，可以将其声明为transient，即将其排除在克隆属性之外。
     *
     * Serializable接口：在 Java 对象序列化期间，对象的状态被转换为字节流，以便在网络上传输或持久保存到磁盘。
     *
     */
    public Object deepClone() throws Exception {
        //序列化
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        ObjectOutputStream oos = new ObjectOutputStream(bos);

        oos.writeObject(this);

        //反序列化
        ByteArrayInputStream bis = new ByteArrayInputStream(bos.toByteArray());
        ObjectInputStream ois = new ObjectInputStream(bis);

        return ois.readObject();
    }

    @Override
    public String toString() {
        return  hashCode() + "name=" + name + " sex=" + sex + this.address.toString() + "";
    }
}
package com.basic.ch02;

import java.util.Arrays;
import java.util.Comparator;

/**
 * Comparator接口：比较对象大小
 */
public class Ch0221CompareOverride {

    public static void main(String[] args) {
        Address[] ads = {
                new Address(11, "shanghai"),
                new Address(33, "beijing"),
                new Address(22, "anhui"),
        };

        System.out.println(Arrays.toString(ads));

        //匿名内部类实现的Comparator接口
        //两个比较对象o1和o2：返回结果负数表示o1<o2, 0表示o1=o2，正数表示o1>o2
        //可以看看Comparator的内部表述，写得很详细，看看别人怎么写注释，不管对自己理解还是未来自己写注释都很有帮助
        Arrays.sort(ads, new Comparator<Address>() {
            @Override
            public int compare(Address o1, Address o2) {
                return o1.getCode() - o2.getCode();
            }
        });

        System.out.println(Arrays.toString(ads));

        Coffee[] coffees = {
                new Coffee(9.9, 350),
                new Coffee(19.9, 500),
                new Coffee(9.9, 450)
        };

        Arrays.sort(coffees);
        System.out.println(Arrays.toString(coffees));
    }
}

/**
 * Coffee实现Comparable接口，重写compareTo方法
 */
class Coffee implements Comparable<Coffee> {
    private double price;
    private int capacity;

    public Coffee(double price, int capacity) {
        this.price = price;
        this.capacity = capacity;
    }

    @Override
    public int compareTo(Coffee o) {
        return this.capacity - o.capacity;
    }

    @Override
    public String toString() {
        return "Coffee{" +
                "price=" + price +
                ", capacity=" + capacity +
                '}';
    }
}

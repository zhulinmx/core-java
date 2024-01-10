package com.basic.collection;

import java.util.*;

/**
 * ArrayList和LinkedList的区别：
 *
 * ArrayList底层的实现是Array, 数组扩容实现，自动扩容
 * LinkList是一个双链表,在添加和删除元素时具有比ArrayList更好的性能.但在get与set方面弱于ArrayList.
 *
 */
public class ListTest {
    public static void main(String[] args) {
        // 读快写慢 ArrayList 基于数组实现
        // 写快读慢 LinkedList 基于链表实现
        // 介于两者中间 Hash
        List<String> strs = new ArrayList<>();
        strs.add("aaaa");
        strs.add("gggg");
        System.out.println(strs.toArray());  //toArray方法返回一个数组

        int c[] = {23,43,56,78};
        System.out.println(Arrays.asList(c)); //asList方法返回一个列表

        // 不要在 foreach 循环里进行元素的 remove/add 操作。remove 元素请使用 Iterator方式，如果并发操作，需要对 Iterator 对象加锁。
        List<String> list2 = new ArrayList<>();
        list2.add("1");
        list2.add("2");
        Iterator<String> iterator = list2.iterator();
        while (iterator.hasNext()) {
            String item = iterator.next();
            if (item.length() > 0) {
                iterator.remove();
            }
        }
    }
}

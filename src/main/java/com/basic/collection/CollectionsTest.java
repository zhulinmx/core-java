package com.basic.collection;

import java.util.*;

/**
 * Collection是集合类的上级接口，子接口有 Set、List、LinkedList、ArrayList、Vector、Stack、Set；
 * Collections是集合类的一个工具类， 它包含有各种有关集合操作的静态多态方法，用于实现对各种集合的搜索、排序、线程安全化等操作。
 *
 */
public class CollectionsTest {
    public static void main(String[] args) {
        List<String> strs = new LinkedList<>();

        for (int i = 0; i < 10; i++) {
            strs.add("a" + i);
        }
        System.out.println(strs);

        Collections.shuffle(strs);
        System.out.println(strs);

        Collections.reverse(strs);
        System.out.println(strs);

        Collections.sort(strs);
        System.out.println(strs);

        System.out.println(Collections.binarySearch(strs, "a5"));
        System.out.println(Collections.binarySearch(strs, "a10"));

    }
}

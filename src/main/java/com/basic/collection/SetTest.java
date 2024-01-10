package com.basic.collection;

import java.util.HashSet;
import java.util.Set;
import java.util.TreeSet;

/**
 * Set：不可重复
 * HashSet：无序。
 * TreeSet：有序。实现了SortedSet接口，内部使用红黑树（Red-Black Tree）数据结构来存储元素；
 *
 * @author ZL
 *
 */
public class SetTest {
    public static void main(String[] args) {
        Set<String> set = new HashSet<>();
        set.add("c");
        set.add("a");
        set.add("b");
        set.add("b");
        set.add("zzz");
        System.out.println("HashSet: " + set);

        // 创建一个自然排序的TreeSet
        Set<Integer> treeSet = new TreeSet<>();
        treeSet.add(5);
        treeSet.add(3);
        treeSet.add(1);
        treeSet.add(4);
        treeSet.add(2);
        System.out.println("自然排序的TreeSet: " + treeSet);

    }
}

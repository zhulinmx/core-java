package com.java11;

import java.util.ArrayList;
import java.util.List;

/**
 *  copyOf方法:
 *  会先判断来源集合是不是 AbstractImmutableList 类型的，如果是，就直接返回，如果不是，则调用 of 创建一个新的集合。
 *  因为用的 new 创建的集合，不属于不可变 AbstractImmutableList 类的子类，所以 copyOf 方法又创建了一个新的实例，所以为false.
 *  注意：使用 of 和 copyOf 创建的集合为不可变集合，不能进行添加、删除、替换、排序等操作，不然会报 java.lang.UnsupportedOperationException 异常。
 *
 *  以下演示了 List 的 of 和 copyOf 方法，Set 和 Map 接口都有copyOf。
 *
 * @author ZL
 */
public class TestCollection {
    public static void main(String[] args) {
        var list = List.of("Java", "Python", "C");
        var copy = List.copyOf(list);
        System.out.println(list == copy);  // true


        var list1 = new ArrayList<String>();
        var copy1 = List.copyOf(list);
        System.out.println(list1 == copy1);  // false
    }
}

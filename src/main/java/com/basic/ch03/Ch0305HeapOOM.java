package com.basic.ch03;

import java.util.ArrayList;
import java.util.List;

/**
 * 堆内存溢出
 * OutOfMemoryError: java heap space
 *
 * 一般是因为堆太小，导致异常的原因，没有足够的内存。
 * 解决方法：
 * 查看系统是否使用大内存的代码或死循环 
 *
 */
public class Ch0305HeapOOM {
    static List<Object> os = new ArrayList<>();

    public static void main(String[] args) {
        while (true) {
            os.add(new Object());
        }
    }
}

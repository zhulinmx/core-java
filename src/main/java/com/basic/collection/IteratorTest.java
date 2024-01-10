package com.basic.collection;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Iterator 测试
 *
 * fail-fast机制（保护机制）
 *
 *  * For example, it is not generally permissible for one thread to modify a Collection
 *  * while another thread is iterating over it.  In general, the results of the
 *  * iteration are undefined under these circumstances.  Some Iterator
 *  * implementations (including those of all the general purpose collection implementations
 *  * provided by the JRE) may choose to throw this exception if this behavior is
 *  * detected.  Iterators that do this are known as <i>fail-fast</i> iterators,
 *  * as they fail quickly and cleanly, rather that risking arbitrary,
 *  * non-deterministic behavior at an undetermined time in the future.
 *
 *
 */
public class IteratorTest {
    public static void main(String[] args) {
        // List 有序，可以重复
        List<String> list = new ArrayList<>();
        list.add("a");
        list.add("b");
        list.add("c");
        list.add("d");

        for (Iterator it = list.iterator(); it.hasNext(); ) {
            if ("d".equals(it.next())) {
                it.remove();
                //list.remove("d");  // 会抛出 java.util.ConcurrentModificationException 异常
            }
        }

        for (String str : list) {
            System.out.println(str);
        }
    }
}

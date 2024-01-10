package algorithm.structure;

import java.util.concurrent.ConcurrentSkipListMap;
import java.util.concurrent.ConcurrentSkipListSet;

/**
 * 跳跃表
 *  一种用于替代平衡树的数据结构
 *  原理：https://www.cl.cam.ac.uk/teaching/2005/Algorithms/skiplists.pdf
 *
 *  ConcurrentSkipListMap、ConcurrentSkipListSet都是基于跳跃表的实现
 */
public class SkipList {

    public static void main(String[] args) {

        // 创建一个ConcurrentSkipListSet实例，并指定自然顺序排序
        ConcurrentSkipListSet<Integer> set = new ConcurrentSkipListSet<>();
        set.add(1);
        set.add(3);
        set.add(2);
        set.add(5);
        set.add(4);

        // 遍历集合并打印元素
        for (Integer num : set) {
            System.out.println(num);
        }

        ConcurrentSkipListMap<Integer, String> map = new ConcurrentSkipListMap<>();
        map.put(5, "aaa");
        map.put(2, "bbb");
        map.put(1, "cc");
        map.put(10, "de");
        map.get(2);
        map.remove(2);

        System.out.println(map);
    }
}

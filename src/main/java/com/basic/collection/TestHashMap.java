package com.basic.collection;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 *
 * HashMap常考知识点
 * 1. structure of HashMap
 * 2. using power-of-two expansion： 方法：tableSizeFor   ---> https://zhuanlan.zhihu.com/p/114184134
 * 3. ways to iterator
 * 4. differences with HashTable:
 *   （1）HashTable是线程安全的，因为大多数方法都加了synchronized 而HashMap不是。
 *   （2）HashMap中允许存在 null 键和 null值，而 HashTable 中不允许
 * 5. fail-fast （快速失败）
 * 6. 为什么不安全？
       因为 HashMap 多线程操作导致死循环问题，主要原因在于并发下的 Rehash 会造成元素之间会形成⼀个循环链表。
 * 7. ConcurrentHashMap 和 Hashtable 的区别：
 * (1) 底层数据结构： JDK1.7 的 ConcurrentHashMap 底层采⽤ 分段的数组+链表 实现，JDK1.8 采
 * ⽤的数据结构跟 HashMap1.8 的结构⼀样，数组+链表/红⿊⼆叉树。Hashtable 和 JDK1.8 之前
 * 的 HashMap 的底层数据结构类似都是采⽤ 数组+链表 的形式，数组是 HashMap 的主体，链表
 * 则是主要为了解决哈希冲突⽽存在的；
 *
 *  (2)实现线程安全的⽅式（重要）：
 *  ① 在 JDK1.7 的时候，ConcurrentHashMap（分段锁） 对整个
 * 桶数组进⾏了分割分段(Segment)，每⼀把锁只锁容器其中⼀部分数据，多线程访问容器⾥不同
 * 数据段的数据，就不会存在锁竞争，提⾼并发访问率。 到了 JDK1.8 的时候已经摒弃了
 * Segment 的概念，⽽是直接⽤ Node 数组+链表+红⿊树的数据结构来实现，并发控制使⽤
 * synchronized 和 CAS 来操作。（JDK1.6 以后 对 synchronized 锁做了很多优化） 整个看起
 * 来就像是优化过且线程安全的 HashMap，虽然在 JDK1.8 中还能看到 Segment 的数据结构，但
 * 是已经简化了属性，只是为了兼容旧版本；
 * ② Hashtable(同⼀把锁) :使⽤ synchronized 来保
 * 证线程安全，效率⾮常低下。当⼀个线程访问同步⽅法时，其他线程也访问同步⽅法，可能会进
 * ⼊阻塞或轮询状态，如使⽤ put 添加元素，另⼀个线程不能使⽤ put 添加元素，也不能使⽤
 * get，竞争会越来越激烈效率越低。
 *
 * **********来自alibaba文档********
 * 【推荐】集合初始化时，指定集合初始值大小。
 * 说明：HashMap 使用构造方法 HashMap(int initialCapacity) 进行初始化时，如果暂时无法确定集合大小，那么指
 * 定默认值（16）即可。
 * 正例：initialCapacity = (需要存储的元素个数 / 负载因子) + 1。注意负载因子（即 loaderfactor）默认为 0.75，如果
 * 暂时无法确定初始值大小，请设置为 16（即默认值）。
 * 反例：HashMap 需要放置 1024 个元素，由于没有设置容量初始大小，随着元素增加而被迫不断扩容，resize() 方法
 * 总共会调用 8 次，反复重建哈希表和数据迁移。当放置的集合元素个数达千万级时会影响程序性能。
 *
 * 【推荐】使用 entrySet 遍历 Map 类集合 KV，而不是 keySet 方式进行遍历。
 * 说明：keySet 其实是遍历了 2 次，一次是转为 Iterator 对象，另一次是从 hashMap 中取出 key 所对应的 value。而
 * entrySet 只是遍历了一次就把 key 和 value 都放到了 entry 中，效率更高。如果是 JDK8，使用 Map.forEach 方法。
 * 正例：values() 返回的是 V 值集合，是一个 list 集合对象；keySet() 返回的是 K 值集合，是一个 Set 集合对象；
 * entrySet() 返回的是 K-V 值组合的 Set 集合。
 *
 *
 */
public class TestHashMap {

    /**
     * HashMap 遍历从大的方向来说，可分为以下 4 类：
     * 迭代器（Iterator）方式遍历；
     * For Each 方式遍历；
     * Lambda 表达式遍历（JDK 1.8+）;
     * Streams API 遍历（JDK 1.8+）。
     *
     *
     * 但每种类型下又有不同的实现方式，因此具体的遍历方式又可以分为以下 7 种：
     * 使用迭代器（Iterator）EntrySet 的方式进行遍历；
     * 使用迭代器（Iterator）KeySet 的方式进行遍历；
     * 使用 For Each EntrySet 的方式进行遍历；
     * 使用 For Each KeySet 的方式进行遍历；
     * 使用 Lambda 表达式的方式进行遍历；
     * 使用 Streams API 单线程的方式进行遍历；
     * 使用 Streams API 多线程的方式进行遍历。
     *
     *
     * for循环是基于下标来定位的循环，可以进行集合的增删操作，多用于数组的遍历
     * foreach是基于指针直接移动的，适用于没有下标的集合进行迭代，不能进行增删操作
     * foreach可以迭代数组和所有实现了Iterable的对象
     *
     */

    public static void main(String[] args) {

        Map<Integer, String> map = new HashMap<>(16);
        map.put(1, "Java");
        map.put(2, "JDK");
        map.put(null, null);

        // 1.迭代器 EntrySet
        Iterator<Map.Entry<Integer, String>> iterator = map.entrySet().iterator();
        while (iterator.hasNext()) {
            Map.Entry<Integer, String> entry = iterator.next();
            System.out.println(entry.getKey());
            System.out.println(entry.getValue());
        }

        // 2.迭代器 keySet
        Iterator<Integer> iterator1 = map.keySet().iterator();
        while (iterator1.hasNext()) {
            Integer key = iterator1.next();
            System.out.println(key);
            System.out.println(map.get(key));
        }

        // 3.ForEach EntrySet
        for (Map.Entry<Integer, String> entry : map.entrySet()) {
            System.out.println(entry.getKey());
            System.out.println(entry.getValue());
        }

        // 4.foreach keySet
        for (Integer key : map.keySet()) {
            System.out.println(key);
            System.out.print(map.get(key));
        }

        // 5.lambda
        map.forEach((key, value) -> {
            System.out.println(key);
            System.out.println(value);
        });

        // 6.Streams API 单线程
        map.entrySet().stream().forEach((entry) -> {
            System.out.println(entry.getKey());
            System.out.println(entry.getValue());
        });

        // 7.Streams API 多线程
        map.entrySet().parallelStream().forEach((entry) -> {
            System.out.println(entry.getKey());
            System.out.println(entry.getValue());
        });

    }



}

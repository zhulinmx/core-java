# ThreadLocal
参考https://juejin.cn/post/6844904151567040519#heading-1 

- ThreadLocal用途
   - Thread类有一个类型为ThreadLocal.ThreadLocalMap的实例变量threadLocals，也就是说每个线程有一个自己的ThreadLocalMap；
   - ThreadLocal线程局部变量, 做到线程隔离，隔离并非共享，ThreadLocal并不是为了解决多线程共享变量的问题；
   - ThreadLocal是空间换时间，synchronized是时间换空间；
   
- ThreadLocal的数据结构
   - ThreadLocal本身并不存储值
   - ThreadLocal并没有实现Map接口，而是自定义ThreadLocalMap，而且它的Entry是继承WeakReference的，也没有next指针，这样的设计使得ThreadLocal对象的生命周期不会比线程更长。   
   
```
   ///////////Entry结构////////
           /**
            * The entries in this hash map extend WeakReference, using
            * its main ref field as the key (which is always a
            * ThreadLocal object).  Note that null keys (i.e. entry.get()
            * == null) mean that the key is no longer referenced, so the
            * entry can be expunged from table.  Such entries are referred to
            * as "stale entries" in the code that follows.
            */
           static class Entry extends WeakReference<ThreadLocal<?>> {
               /** The value associated with this ThreadLocal. */
               Object value;
   
               Entry(ThreadLocal<?> k, Object v) {
                   super(k);
                   value = v;
               }
           }
```        
   
- GC 之后key是否为null？
  不一定，如果我们的强引用不存在的话，那么 key 就会被回收，也就是会出现我们 value 没被回收，key 被回收，导致 value 永远存在，出现内存泄漏。

- ThreadLocalMap Hash算法
  int i = key.threadLocalHashCode & (len-1);
  既然是Map结构，那么ThreadLocalMap当然也要实现自己的hash算法来解决散列表数组冲突问题。
  这里最关键的就是threadLocalHashCode值的计算，ThreadLocal中有一个属性为HASH_INCREMENT = 0x61c88647
   (这个值很特殊，它是斐波那契数 也叫 黄金分割数。hash增量为 这个数字，带来的好处就是 hash 分布非常均匀)

- ThreadLocalMap Hash解决冲突  
  冲突后，线性向后查找，一直找到Entry为null的槽位才会停止查找，将当前元素放入此槽位中。
  当然迭代过程中还有其他的情况，比如遇到了Entry不为null且key值相等的情况，还有Entry中的key值为null的情况等等都会有不同的处理，此时就会进行清理。

- ThreadLocalMap扩容机制
  采用的开放地址法来进行解决ThreadLocaMap的扩容机制也不同于HashMap，ThreadLocalMap的扩容阈值是长度的2/3，当表中的元素数量达到阈值时，不会立即进行扩容，
  而是会触发一次rehash操作清除过期数据，如果清除过期数据之后元素数量大于等于总容量的3/4才会进行真正意义上的扩容。

- ThreadLocalMap过期可以的两种清理方式：探测式清理(expungeStaleEntry())、启发式清理(cleanSomeSlots())
  - 探测式清理是以当前Entry往后清理，遇到值为null则结束清理，属于线性探测清理。
  - 而启发式清理被作者定义为：Heuristically scan some cells looking for stale entries.
  
- ThreadLocal使用场景
   - 比如Hibernate中session就是存放在ThreadLocal中的， 避免synchronized的使用。
   - zuul的filter链

- 内存泄漏
  - 强引用内存泄漏
    
    如果Entry的key为强引用，则会导致ThreadLocal实例在被创建它的线程销毁时，而无法被回收，从而导致严重的内存泄漏问题，因此Entry的key被声明为弱引用来避免这种问题
 
  - 弱引用内存泄露
    
    我们知道每一个线程都存在一个ThreadLocalMap，Map中的key为一个ThreadLocal实例。而且key到ThreadLocal实例的引用为虚引用，
    也就是说当ThreadLocal置为null时，没有任何强引用指向ThreadLocal实例，所以ThreadLocal实例会被GC回收。但是value却不能被回收，因为存在一条从当前线程连接过来的强引用（Thread Ref -> Thread -> ThreaLocalMap -> Entry -> value）
    
  - 针对上面的内存泄露问题，ThreadLocal在get和set时都会检测并清除key为null的Entry，从而尽可能的避免内存泄露；


- 使用建议
  1. 每次使用完ThreadLocal，都调用它的remove()方法，清除数据；
  2. 将ThreadLocal声明为private static，使它的生命周期与线程保持一致；<br>
  【参考】ThreadLocal 对象使用 static 修饰，ThreadLocal 无法解决共享对象的更新问题。
  说明：这个变量是针对一个线程内所有操作共享的，所以设置为静态变量，所有此类实例共享此静态变量，也就是说在类第一次被使用时装载，只分配一块存储空间，所有此类的对象（只要是这个线程内定义的）都可以操控这个变量。
  3. 适用于变量在线程间隔离，在方法中共享；
  
package com.basic.ch03;

/**
 *
 * 直接内存（Direct Memory）的容量大小可通过-XX：MaxDirectMemorySize参数来指定，
 * 如果不去指定，则默认与Java堆最大值（由-Xmx指定）一致”
 * 由直接内存导致的内存溢出，一个明显的特征是在Heap Dump文件中不会看见有什么明显的异常情况，
 * 如果读者发现内存溢出之后产生的Dump文件很小，而程序中又直接或间接使用了DirectMemory（典型的间接使用就是NIO），
 * 那就可以考虑重点检查一下直接内存方面的原因了。
 *
 * VM Args：-Xmx20M -XX:MaxDirectMemorySize=10M
 * @author zzm
 */
public class DirectMemoryOOM {

    private static final int _1MB = 1024 * 1024;

    public static void main(String[] args) throws Exception {
       /*
        Field unsafeField = Unsafe.class.getDeclaredFields()[0];
        unsafeField.setAccessible(true);
        Unsafe unsafe = (Unsafe) unsafeField.get(null);
        while (true) {
            unsafe.allocateMemory(_1MB);
        }
        */
    }
}
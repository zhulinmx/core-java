package com.basic.ch01;

/**
 *
 * 装箱：就是自动将基本数据类型转换为包装器类型（int-->Integer）；调用方法：Integer的valueOf(int)方法
 * 拆箱：就是自动将包装器类型转换为基本数据类型（Integer-->int）。调用方法：Integer的intValue方法
 *
 * 注意IntegerCache类
 *
 */
public class Ch0107AutoBox {
    public static void main(String[] args) {

        Integer i1 = 100; Integer i2 = 100;
        Integer i3 = 200; Integer i4 = 200;
        System.out.println(i1==i2);  //true
        System.out.println(i3==i4);  //false

        System.out.println(i1.equals(i2)); //true
        System.out.println(i3.equals(i4)); //true
        //i1和i2指向的是同一个对象，而i3和i4指向的是不同的对象。
        //因为在通过valueOf方法创建Integer对象的时候，如果数值在[-128,127]之间，便返回指向IntegerCache.cache中已经存在的对象的引用；否则创建一个新的Integer对象。


        Double i11 = 100.0; Double i21 = 100.0;
        Double i31 = 200.0; Double i41 = 200.0;
        System.out.println(i11==i21);  //false
        System.out.println(i31==i41);  //false
        System.out.println(i11.equals(i21)); //true
        System.out.println(i31.equals(i41)); //true
        //原因： 在某个范围内的整型数值的个数是有限的，而浮点数却不是。

        Float a = Float.valueOf(1.0f - 0.9f);
        Float b = Float.valueOf(0.9f - 0.8f);
        System.out.println(a == b); //false

        Integer c = null;
        Boolean flag = false;

        // a*b 的结果是 int 类型，那么 c 会强制拆箱成 int 类型，抛出 NPE 异常
        Integer result = (flag ? i1 * i2 : c); //java.lang.NullPointerException
        System.out.println(result);
    }
}

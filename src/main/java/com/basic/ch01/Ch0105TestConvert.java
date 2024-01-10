package com.basic.ch01;

/**
 *
 * 数据类型按容量大小(表示的数的大小)排序:
 *   byte, short, char -> int -> long -> float -> double
 *   byte, short, char之间不会相互转换，他们三者在计算前要先转换为 int.
 *
 * 整形，浮点型，字符型的数据在混合运算中相互转换，原则如下：
 * 1）容量小的数据类型自动转换为容量大的数据类型；
 * 2）容量大的数据类型转换为容量小的数据类型时，要加上强制转换符，但可能造成
 *    精度降低或溢出；
 * 3）有多种数据类型的数据进行混合运算时，系统首先自动将所有数据转换成容量最大
 *    的那一种数据类型，然后再进行计算；
 * 4）实数类型默认为 double；
 * 5）整数类型默认为 int；
 *
 */
public class Ch0105TestConvert {
    public static void main(String arg[]) {
        int i1 = 123;
        int i2 = 456;
        double d1 = (i1 + i2) * 1.2;// 系统将转换double型进行运算
        float f1 = (float) ((i1 + i2) * 1.2);// 需要加强制转换符号
        byte b1 = 67;
        byte b2 = 89;
        byte b3 = (byte) (b1 + b2);// 系统将转换为 int 进行运算，然后再转换回来
        System.out.println(b3);
        double d2 = 1e200;
        float f2 = (float) d2;// 产生溢出
        System.out.println(f2);

        float f3 = 1.23f;// 必须加f, 浮点数直接砍掉是不可以的
        long l1 = 123;
        long l2 = 30000000000L;// 必须加L
        float f = l1 + l2 + f3;// 系统将转换float型计算
        long l = (long) f;// 强制转换会舍去小数部分（非四舍五入）

        float f5 = (float) 0.1;  //0.1f
        float f6 = 123;
        double d5 = 2e20, d6 = 124;
        char c1 = 'a', c2 = 125;
        char c = (char) (c1 + c2 - 1);
        float f7 = f5 + f6;
        float f8 = (float) (f5 + f6 * 0.1);
        float f9 = (float) (d5 * 5 + d6);

    }
}
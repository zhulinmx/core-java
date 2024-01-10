package com.basic.ch02;

import java.text.MessageFormat;
import java.util.Date;

/**
 * StringBuilder/StringBuffer操作字符串
 * MessageFormat：占位符思想，format内部实现用到了StringBuffer
 *
 */
public class Ch0217StringBuffer {
    public static void main(String[] args) {
        String s = "Microsoft";
        char[] a = {'a', 'b', 'c'};
        StringBuilder sb = new StringBuilder(s);
        sb.append("/").append("IBM")
                .append("/").append("Sun");
        sb.insert(0, a);
        System.out.println(sb);
        //append、reverse等方法都是sync的，所以是线程安全的，而StringBuilder不是线程安全
        System.out.println(sb.reverse());

        System.out.println(s);
        StringBuffer sbf = new StringBuffer(s);
        sbf.append("123");
        sbf.insert(3, "000");
        System.out.println(sbf.toString());

        int planet = 7;
        String event = "a disturbance in the Force";
        String result = MessageFormat.format("At {1,time} on {1,date}, there was {2} on planet {0,number,integer}.",
                planet, new Date(), event);
        System.out.println(result);

    }
}

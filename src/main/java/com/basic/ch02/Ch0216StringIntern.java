package com.basic.ch02;

public class Ch0216StringIntern {

    public static void main(String[] args) {

        String str3 = "abc";
        String str6 = new String("abc");
        String str5 = str3.intern();
        String str66 = str6.intern();

        System.out.println(str3 == str5); //true
        System.out.println(str3 == str6); //false
        System.out.println(str6 == str66); //false
        System.out.println(str6.intern() == str66); //true

        /**
         *
         *
         *  而JDK 7（以及部分其他虚拟机，例如JRockit）的intern()方法实现就不需要再拷贝字符串的实例到永久代了，
         *  既然字符串常量池已经移到Java堆中，那只需要在常量池里记录一下首次出现的实例引用即可，
         *  因此intern()返回的引用和由StringBuilder创建的那个字符串实例就是同一个。而对str2比较返回false，
         *  这是因为“java”这个字符串在执行String-Builder.toString()之前就已经出现过了，字符串常量池中已经有它的引用，不符合intern()方法要求“首次遇到”的原则，
         *  “vaja”这个字符串则是首次出现的，因此结果返回true
         *
         *
         */

        String str7 = new StringBuilder("ja").append("va").toString();
        System.out.println(str7.intern() == str7); //false

        String str8 = new StringBuilder("va").append("ja").toString();
        System.out.println(str8.intern() == str8); //true

        String str9 = new String("vaja");
        System.out.println(str9.intern() == str9); //false vaja不是首次出现

        System.out.println(str9.intern() == "vaja"); //true

    }

}

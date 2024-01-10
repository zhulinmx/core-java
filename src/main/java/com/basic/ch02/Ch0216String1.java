package com.basic.ch02;

/**
 * String类的基本操作：
 *
 * length：返回字符串长度
 * isEmpty：是否为空
 * indexOf：包含子字符串
 * substring：截取
 * charAt：获得索引位置的字符
 * equals：是否相等（区分大小写）
 * equalsIgnoreCase：是否相等（不区分大小写）
 * split：字符串分割
 * replace：字符串替代
 * valueOf：转换成字符串
 * toUpperCase：转成大写
 * toLowerCase：转成小写
 * toCharArray：转成char数组
 * subSequence：
 * codePointAt: 返回指定位置字符的Unicode
 * concat: 字符串连接
 * trim：去除空格
 * startsWith: 是否以...开头
 * endsWith：是否以...结尾
 * getBytes：返回对应的bytes数组
 * 等等方法。。。
 *
 */
public class Ch0216String1 {
    public static void main(String[] args) {
        String s1 = "Sun java", s2 = "sun Java";

        String s4 = "hello";
        String s5 = "world";
        String s6 = "hello";
        System.out.println(s4 == s6);
        System.out.println(s4.equals(s6));

        System.out.println(s1.charAt(1));
        System.out.println(s2.length());
        System.out.println(s1.indexOf("java"));
        System.out.println(s1.indexOf("Java"));
        System.out.println(s1.equalsIgnoreCase(s2));

        String s3 = "我是程序员，我在学Java!";
        System.out.println(s3.replace("我", "你"));

        System.out.println(s1.codePointAt(0));
        System.out.println(s1.codePointBefore(1));
        System.out.println(s1.codePointCount(0, 2));
        System.out.println(s1.compareTo("Java world!"));
        System.out.println(s1.concat("+"));
        System.out.println(s1.contains("Java"));
        System.out.println(s1.contentEquals("Welcome to Java world!"));
        System.out.println(s1.toUpperCase());
        System.out.println(s1.toLowerCase());
        System.out.println(" Welcome to Java world  ".trim());
        System.out.println(s1.toCharArray().length);
        System.out.println(s1.substring(1));
        System.out.println(s1.substring(0, 1));
        System.out.println(s1.subSequence(0, 3));
        System.out.println(s1.startsWith("Welcome"));
        System.out.println(s1.startsWith("Welcome", 5));
        System.out.println(s1.endsWith("va"));

        //应用1：计算字符串中大小写字母数量
        String s = "abcdefghijklMMMmnopqrHstUuIvwxyzK";
        int lCount = 0, uCount = 0;
        for(int i=0; i<s.length(); i++) {
            char c = s.charAt(i);
            //进行判断
//            if(c >= 'a' && c <= 'z') {
//                lCount ++;
//            } else if (c >='A' && c <= 'Z') {
//                uCount ++;
//            }
            //直接用Character的方法判断
            if(Character.isLowerCase(c)) {
                lCount ++;
            } else if (Character.isUpperCase(c)) {
                uCount ++;
            }
        }

        System.out.println(lCount + " " + uCount);


        // 应用2：计算j是几位数
        int j = 1234657;
        String num = String.valueOf(j);
        System.out.println("j是" + num.length() + "位数.");

        // 应用3：切割字符串成数组
        String str = "java,python,js";
        System.out.println("数组长度："+str.split(",").length);



    }
}

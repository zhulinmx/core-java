package com.basic.ch02;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.util.Date;

/**
 * 时间
 * 格式
 * Instant/LocalDate使用(since java 8)
 *
 */
public class Ch0223DateFormat {
    private final static String PATTERN = "yyyy-MM-dd HH:mm:ss";

    /**
     * 月
     */
    private final static int MONTH = 1;

    /**
     * 日
     */
    private final static int DAY = 1;

    public static void main(String[] args) {
        DateFormat dateFormat = new SimpleDateFormat(PATTERN);
        System.out.println(dateFormat.format(new Date()));

        // 获取当前毫秒数
        System.out.println(System.currentTimeMillis());
        // 纳秒
        System.out.println(System.nanoTime());

        // 统计时间等场景
        // Instant
        /**
         * Instant类位于java.time包中，是Java 8引入的新特性之一。
         * Instant类的实例可以通过以下方式创建：
         * 使用静态方法now()获取当前时间的Instant对象。
         * 使用静态方法ofEpochMilli(long millis)根据指定的毫秒数创建一个Instant对象。
         * 使用静态方法ofEpochSecond(long seconds)根据指定的秒数创建一个Instant对象。
         * 使用静态方法ofLocal(LocalDateTime localDateTime)将LocalDateTime对象转换为Instant对象。
         * 使用静态方法parse(CharSequence text)将字符串解析为Instant对象。
         */
        System.out.println("----------Instant test---------");
        System.out.println(Instant.now());

        // 获取今年的天数
        int daysOfThisYear = LocalDate.now().lengthOfYear();
        System.out.println(daysOfThisYear);

        // 获取指定某年的天数
        System.out.println(getDaysOfCurrentYear(2016));
    }

    private static int getDaysOfCurrentYear(int currentYear) {
        return LocalDate.of(currentYear, MONTH, DAY).lengthOfYear();
    }
}
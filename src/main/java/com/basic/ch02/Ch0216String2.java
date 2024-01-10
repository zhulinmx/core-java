package com.basic.ch02;


/**
 * 学会String类的基本操作后，开始尝试写一些工具类，封装一些常用的方法。
 *
 */
public class Ch0216String2 {
    public static void main(String[] args) {
        String s3 = "asjljladjflAJSDLAJDSAJS,,,...,ASJDS";
        System.out.println("大写字符数: " + countUpperCase(s3));
        System.out.println("小写字符数：" + countLowerCase(s3));
        System.out.println("其它字符数：" + countOtherCharacter(s3));
        System.out.println(s3.length());
    }

    /**
     * 计算大写字符数量
     *
     * @param string
     * @return
     */
    private static int countUpperCase(String string) {
        char[] chars = string.toCharArray();
        int count = 0;
        for (char c : chars) {
            if ('A' <= c && c <= 'Z') {
                count ++;
            }
        }
        return count;
    }

    /**
     * 计算小写字符数量
     *
     * @param string
     * @return
     */
    private static int countLowerCase(String string) {
        int count = 0;
        for (int i=0; i<string.length(); i++) {
            char c = string.charAt(i); // 注意 charAt 方法的使用
            if (Character.isLowerCase(c)) {
                count ++;
            }
        }
        return count;
    }

    /**
     * 计算其他字符数个数
     *
     * @param string
     * @return
     */
    private static int countOtherCharacter(String string) {
        return string.length() - countUpperCase(string) - countLowerCase(string);
    }

}

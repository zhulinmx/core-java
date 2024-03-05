package algorithm;


class Solution {

    /**
     * leetcode LCR 002. 二进制求和
     *
     * @param a
     * @param b
     * @return
     */
    public static String addBinary(String a, String b) {
        int i = 0;
        int alen = a.length();
        int blen = b.length();
        int result = 0;
        StringBuffer sbf = new StringBuffer();
        while (i < alen || i < blen) {
            int m = (i < alen) ? Integer.valueOf(Character.toString(a.charAt(alen - i - 1))) : 0;
            int n = (i < blen) ? Integer.valueOf(Character.toString(b.charAt(blen - i - 1))) : 0;
            if (m + n + result > 2) {
                result = 1;
                sbf.append(1);
            } else if (m + n + result == 2) {
                result = 1;
                sbf.append(0);
            } else if (m + n + result == 1) {
                result = 0;
                sbf.append(1);
            } else {
                result = 0;
                sbf.append(0);
            }
            i++;
        }
        if (result > 0) {
            sbf.append(result);
        }
        return sbf.reverse().toString();
    }

    /**
     * 回文数
     */
    public static boolean isPalindrome(int x) {
        if (x < 0) return false;
        if (x < 10) return true;

        int m = x, n = 0, x1 = 0;
        while (m > 0) {
            n = m % 10;
            m = m / 10;
            x1 = x1 * 10 + n;
        }

        return (x1 == x) ? true : false;
    }

    /**
     * 输入一个整数，输出该数二进制表示中1的个数。其中负数用补码表示。
     * 思路：&与操作，再右移
     */
    public static int numberOf1(int n) {
        int count = 0;
        while (n != 0) {
            count += (n & 1);
            n = n>>1;
        }
        return count;
    }

    /**
     * 给定一个double类型的浮点数base和int类型的整数exponent。求base的exponent次方。
     * 不得使用库函数，不需要考虑大数问题
     *
     * 思路：不能用==比较两个浮点数是否相等，因为有误差。考虑输入值的多种情况。
     */
    public double Power(double base, int exponent) {
        double res = 0;
        if (equal(base, 0)) {
            return 0;
        }
        if (exponent == 0) {
            return 1.0;
        }
        if (exponent > 0) {
            res = mutiply(base, exponent);
        } else {
            //考虑exponent为负数
            res = mutiply(1 / base, -exponent);
        }
        return res;
    }

    public double mutiply(double base, int e) {
        double sum = 1;
        for (int i = 0; i < e; i++) {
            sum = sum * base;
        }
        return sum;
    }

    public boolean equal(double a, double b) {
        if (a - b < 0.000001 && a - b > -0.000001) {
            return true;
        }
        return false;
    }

    public static void main(String[] args) {

        System.out.println(addBinary("1010", "1011"));
        System.out.println(isPalindrome(1221));
        System.out.println(numberOf1(3));
        System.out.println(numberOf1(4));
    }

}

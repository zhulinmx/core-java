package algorithm;

/**
 *
 * 输入一个整数，输出该数二进制表示中1的个数。其中负数用补码表示。
 * 思路：&与操作，再右移
 *
 */
public class C11 {

    public static int numberOf1(int n) {
        int count = 0;
        while (n != 0) {
            count += (n & 1);
            n = n>>1;
        }
        return count;
    }

    public static void main(String[] args) {

        System.out.println(C11.numberOf1(3));
        System.out.println(C11.numberOf1(4));

    }
}

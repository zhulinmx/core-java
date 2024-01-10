package algorithm;

import java.util.*;

public class C1 {

    public static String PrintMinNumber(int[] numbers) {

        //输入一个正整数数组，把数组里所有数字拼接起来排成一个数，打印能拼接出的所有拼接数中最小的一个
        // 思路：先将整型数组转换成String数组，然后将String数组排序，最后将排好序的字符串数组拼接出来。
        //关键就是制定排序规则。或使用比较和快排的思想，将前面的数和最后的数比较，若小则放到最前面，最后再递归调用。

        if (numbers == null || numbers.length == 0) return "";
        int len = numbers.length;
        String[] str = new String[len];
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < len; i++) {
            str[i] = String.valueOf(numbers[i]);
        }
        System.out.println(Arrays.asList(str));

        Arrays.sort(str, new Comparator<String>() {

            public int compare(String s1, String s2) {
                String c1 = s1 + s2;
                String c2 = s2 + s1;
                return c1.compareTo(c2);
            }
        });
        for (int i = 0; i < len; i++) {
            sb.append(str[i]);
        }
        return sb.toString();

    }

    public static int PrintMinNumber1(int[] numbers) {
        Arrays.sort(numbers);
        int result = 0;

        for (int k = 0; k < numbers.length; k++) {
            System.out.println(numbers[k]);
            result += numbers[numbers.length-k-1] * Math.pow(10, k);
        }

        return result;
    }

    public static void main(String[] args) {

        int[] a1 = {7, 2, 3};
        System.out.println(C1.PrintMinNumber(a1));
        int[] a2 = {1, 4, 3};
        System.out.println(C1.PrintMinNumber(a2));
        int[] a3 = {1, 8, 3};
        System.out.println(C1.PrintMinNumber1(a3));

        //反转链表
        int[] a4 = {4, 9, 3};

        List<Integer> arr = new ArrayList<Integer>() {{{add(1); add(9); add(4);}}};
        for (Integer ii : arr)
            System.out.println(ii);

    }

}

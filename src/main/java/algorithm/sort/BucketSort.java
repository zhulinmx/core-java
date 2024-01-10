package algorithm.sort;

import java.util.Arrays;

/**
 *
 * ******桶排序********
 *
 * 最简单最快，但是空间复杂度大
 *
 */
public class BucketSort {

    public static void bucketSort(int[] arr) {
        int[] array = new int[11];
        for (int i : arr) {
            array[i]++; //数字对应新的数组的下标
        }
        for (int m = 0; m < array.length; m++) {
            System.out.println("数字：" + m + " 出现次数：" + array[m]);
        }
        System.out.println(Arrays.toString(array));
    }

    public static void main(String[] args) {
        //数字1-10 之间
        int[] a = {9, 1, 4, 6, 1, 9, 8, 7};
        BucketSort.bucketSort(a);
    }

}

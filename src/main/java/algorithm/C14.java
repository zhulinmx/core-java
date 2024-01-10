package algorithm;

import java.util.Arrays;

/**
 * 选择排序
 *
 * 我们熟知的选择排序，其实采用的即为贪心策略。
 *
 * 它所采用的贪心策略即为每次从未排序的数据中选取最小值，并把最小值放在未排序数据的起始位置，直到未排序的数据为0，则结束排序
 *
 */
public class C14 {

    public static void swap(int[] arr, int i, int j) {
        int tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }

    public static void selectSort(int[] arr) {
        //i: 未排序数据的起始位置
        for(int i = 0; i < arr.length; ++i)
        {
            int minIdx = i;
            //从所有未排序的数据中找最小值的索引
            for(int j = i + 1; j < arr.length; ++j){
                if(arr[j] < arr[minIdx])
                    minIdx = j;
            }
            if (minIdx != i)
                swap(arr, minIdx, i); //下标不同，进行交换
        }
    }

    public static void main(String[] args) {
        int[] arr = {10, 3, 6, 0, 8};
        selectSort(arr);
        System.out.println(Arrays.toString(arr));
        Arrays.sort(arr);
        //System.out.println(Arrays.toString(arr));

    }
}

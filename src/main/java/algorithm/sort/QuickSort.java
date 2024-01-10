package algorithm.sort;

import java.util.Arrays;

/**
 *
 * ******快速排序********
 *
 * 1、时间复杂度
 *
 * 快速排序的最差时间复杂度和冒泡排序是一样的，都是 O(N2)，它的平均时间复杂度为 O (NlogN)。其实快速排序是基于一种叫做“二分”的思想
 *
 * 2、空间复杂度
 *
 * 快速排序的空间复杂度为O(log n)
 *
 *  快速排序的应用：
 *  在以下情况下使用Quicksort算法
 *     1. 编程语言适合递归
 *     2. 时间复杂度很重要
 *
 */
public class QuickSort {

    public static void quickSort(int[] array, int low, int high) {
        if (low < high) {
            // 获取划分子数组的位置

            if(low > high)
                return;

            int temp = array[low]; //temp中存的就是基准数
            int i = low, j = high; //哨兵i负责找最小，哨兵j负责找最大

            while(i!=j)
            {
                //顺序很重要，要先从右往左找
                while (array[j] >= temp && i < j)
                    j--;
                //再从左往右找
                while (array[i] <= temp && i < j)
                    i++;
                //交换两个数在数组中的位置
                if(i<j && array[i] > array[j])//当哨兵i和哨兵j没有相遇时
                {
                    int t = array[i];
                    array[i] = array[j];
                    array[j] = t;
                }
            }

            //已经找到基准数temp的位置，并交换
            array[low] = array[i];
            array[i] = temp;
            System.out.println(Arrays.toString(array));
            // 左子数组递归调用
            quickSort(array, low, i -1);
            // 右子数组递归调用
            quickSort(array, i + 1, high);
        }
    }

    public static void main(String[] args) {
        int[] array = {6,72,113,11,23};
        quickSort(array, 0, array.length -1);
        System.out.println("排序后的结果");
        System.out.println(Arrays.toString(array));
    }
}

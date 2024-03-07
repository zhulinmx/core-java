package com.basic.ch02;

import java.util.Arrays;

/**
 * 工具类Arrays可以用来操作数组：排序、搜索（查找）、复制、转字符串等
 *
 */
public class Ch0214ArrayTools {
    public static void main(String[] args) {
        int[] arrs = {8, 2, 0, 4, 5};

        //排序：内部借助TimSort、DualPivotQuickSort(双轴快速排序是快速排序的改进版本)、ComparableTimSort实现
        Arrays.sort(arrs);

        //转字符串
        System.out.println(Arrays.toString(arrs));

        //搜索（查找）
        System.out.println("二分查找结果是="+Arrays.binarySearch(arrs, 4));

        //复制
        int[] arrsCopy = Arrays.copyOf(arrs, 3);
        System.out.println(Arrays.toString(arrsCopy));

    }
}
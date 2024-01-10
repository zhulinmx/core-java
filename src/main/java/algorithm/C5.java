package algorithm;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class C5 {

    /**
     * 在一个二维数组中，每一行都按照从左到右递增的顺序排序，每一列都按照从上到下递增的顺序排序。
     * 请完成一个函数，输入这样的一个二维数组和一个整数，判断数组中是否含有该整数。
     */
    public boolean findNum(int[][] array, int target) {

        if (array == null) {
            return false;
        }

        int row = 0;
        int column = array[0].length - 1;

        while (row < array.length && column >= 0) {
            if (array[row][column] == target) {
                return true;
            }
            if (array[row][column] > target) {
                column--;
            } else {
                row++;
            }
        }
        return false;
    }

    /**
     * leetcode 287. 寻找重复数
     * @param nums
     * @return
     */
    public static int findDuplicate(int[] nums) {
        Set<Integer> set = new HashSet();
        for (int i = 0; i < nums.length; i++) {
            int length = set.size();
            set.add(nums[i]);
            if (set.size() == length) {
                return nums[i];
            }
        };
        return 0;
    }

    /**
     * leetcode 136. 只出现一次的数字
     * 给你一个 非空 整数数组 nums ，除了某个元素只出现一次以外，其余每个元素均出现两次。找出那个只出现了一次的元素。
     * 你必须设计并实现线性时间复杂度的算法来解决此问题，且该算法只使用常量额外空间。
     * @param nums
     */
    public static int singleNumber(int[] nums) {
        Arrays.sort(nums);
        int i = 0;
        while (i < nums.length - 1) {
            if (nums[i] != nums[i + 1]) return nums[i];
            i += 2;
        }
        return nums[i];
    }

    /**
     * 同理 leetcode 137. 只出现一次的数字 II
     * 除某个元素仅出现 一次 外，其余每个元素都恰出现 三次 。
     *
     * @param nums
     */
    public static int singleNumber3(int[] nums) {
        Arrays.sort(nums);
        int i = 0;
        while (i < nums.length - 1) {
            if (nums[i] != nums[i + 1]) return nums[i];
            i += 3;
        }
        return nums[i];
    }

    /**
     * leetcode 75. 颜色分类
     * 使用整数 0、 1 和 2 分别表示红色、白色和蓝色。
     * 必须在不使用库内置的 sort 函数的情况下。
     *
     * @param nums
     */
    public static void sortColors(int[] nums) {
        if(nums.length == 1) return;
        //三指针
        int index0 = 0, index1 = 0, index2 = 0;
        for(int i = 0; i < nums.length; i++) {
            if(nums[i] == 0) {
                //同时index1和index2往后移
                nums[index2++] = 2;
                nums[index1++] = 1;
                nums[index0++] = 0;
            }else if(nums[i] == 1) {
                //同时index2往后移
                nums[index2++] = 2;
                nums[index1++] = 1;
            }else {
                nums[index2++] = 2;
            }
//            System.out.println(Arrays.toString(nums));
//            System.out.println("index0="+index0 + "index1="+index1 + "index2="+index2);
        }
    }


    public static void main(String[] args) {
        int[] nums1 = {1,3,4,2,2};
        System.out.println(findDuplicate(nums1));

        int[] nums2 = {4,1,2,1,2};
        System.out.println(singleNumber(nums2));

        int[] nums3 = {0,1,0,1,0,1,99};
        System.out.println(singleNumber3(nums3));

        int[] nums4 = {2,0,2,1,1,0};
        sortColors(nums4);
    }
}

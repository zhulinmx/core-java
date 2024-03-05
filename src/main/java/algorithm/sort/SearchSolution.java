package algorithm.sort;


import java.util.*;

/**
 * 搜索算法建立在排序算法的基础之上
 * 二分法查找算法
 *
 */
public class SearchSolution {

    /**
     * 最简单的搜索O(n)
     * @param a
     * @param num
     * @return
     */
//    public static int search(int[] a, int num) {
//        for (int i = 0; i < a.length; i++) {
//            if (a[i] == num) return i;
//        }
//        return -1;
//    }

    /**
     *
     * leetcode 34. 在排序数组中查找元素的第一个和最后一个位置
     * @param nums
     * @param target
     * @return
     *
     */
    public static int[] searchRange(int[] nums, int target) {
        int[] arrs = {-1, -1};
        if (nums.length == 0) return arrs;
        if (nums.length == 1 && nums[0] == target) {
            arrs[0] = 0;
            arrs[1] = 0;
            return arrs;
        }
        int pre = 0;
        int last = nums.length - 1;
        int m = (pre + last) / 2;

        while (pre <= last) {
            if (nums[pre] > target || nums[last] < target) break;

            if (nums[pre] == target) {
                arrs[0] = pre;
                if (pre == last) break;
                if (nums[last] == target) {
                    arrs[1] = last;
                    break;
                } else if (pre == last - 1) break;
            } else if (nums[m] < target) {
                pre = m;
            } else
                pre++;

            if (nums[last] == target) {
                arrs[1] = last;
                if (pre == last) break;
                if (nums[pre] == target) {
                    arrs[0] = pre;
                    break;
                } else if (pre == last - 1) break;
            } else if (nums[m] > target) {
                last = m;
            } else
                last--;

            m = (pre + last) / 2;
        }

        if (arrs[0] != -1 && arrs[1] == -1) arrs[1] = arrs[0];
        if (arrs[0] == -1 && arrs[1] != -1) arrs[0] = arrs[1];

        return arrs;
    }

    /**
     * leetcode 153. 寻找旋转排序数组中的最小值
     * 数组元素值互不相同，必须设计一个时间复杂度为 O(log n)
     *
     * 思路：看到O(log n)的时间复杂度，就想到二分查找
     *
     * @param nums
     * @return
     */
    public static int findMin(int[] nums) {
        if (nums.length == 1) return nums[0];

        int left = 0, right = nums.length - 1;
        int mid = (left + right) / 2;

        while (left < right) {
            if(left + 1 == right) return Math.min(nums[left], nums[right]);
            if(mid == 0 || mid == nums.length-1) break;
            if (nums[mid] < nums[mid - 1] && nums[mid] < nums[mid + 1]) break;
            if (nums[mid] < nums[right]) right = mid;
            if (nums[mid] > nums[right]) left = mid;
            mid = (left + right) / 2;
        }

        return nums[mid];
    }

    /**
     * leetcode 4. 寻找两个正序数组的中位数
     * 给定两个大小分别为 m 和 n 的正序（从小到大）数组 nums1 和 nums2。请你找出并返回这两个正序数组的 中位数 。
     *
     * @param nums1
     * @param nums2
     * @return
     */
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        if (nums1.length == 0 && nums2.length == 0) return 0.0;
        if (nums1.length == 1 && nums2.length == 0) return nums1[0];
        if (nums2.length == 1 && nums1.length == 0) return nums2[0];

        int[] nums = new int[nums1.length + nums2.length];
        System.arraycopy(nums1, 0, nums, 0, nums1.length);
        System.arraycopy(nums2, 0, nums, nums1.length, nums2.length);
        Arrays.sort(nums);
        if (nums.length % 2 == 0)
            return (nums[(nums.length) / 2 - 1] + nums[(nums.length) / 2]) / 2.0;
        else
            return nums[(nums.length) / 2];
    }

    /**
     * leetcode 35. 搜索插入位置
     * 二分查找
     *
     * @param nums
     * @param target
     * @return
     */
    public int searchInsert(int[] nums, int target) {
        int left = 0, right = nums.length - 1;
        while (left <= right) {
            int mid = (left + right) / 2;
            if (nums[mid] == target) {
                return mid;
            } else if (nums[mid] < target) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return left;
    }

    /**
     * 搜索插入位置
     * 递归
     *
     * @param nums
     * @param target
     * @return
     */
    public int searchInsert(int[] nums, int target, int left, int right) {

        if (nums[left] >= target) return left;
        if (nums[right] < target) return right + 1;
        if (nums[right] == target) return right;

        int mid = (right - left) / 2 + left;
        if (nums[mid] > target) {
            return searchInsert(nums, target, left, mid - 1);
        } else if (nums[mid] < target) {
            return searchInsert(nums, target, mid + 1, right);
        }
        return mid;
    }


    public static void main(String[] args) {
        SearchSolution solution = new SearchSolution();

        System.out.println(searchRange(new int[] {1,1}, 2));
        System.out.println(findMin(new int[]{4, 5, 6, 7, 0, 1, 2}));
        System.out.println(findMin(new int[]{11, 13, 15, 17}));
        System.out.println(findMin(new int[]{1, 3, 5, 7}));
        System.out.println(findMin(new int[]{2, 3, 1}));

        System.out.println(solution.findMedianSortedArrays(new int[]{1, 2}, new int[]{3, 4}));
        System.out.println(solution.findMedianSortedArrays(new int[]{1, 2}, new int[]{3}));
        System.out.println(solution.findMedianSortedArrays(new int[]{}, new int[]{3}));

        System.out.println(solution.searchInsert(new int[]{1, 3, 5, 7}, 4));
        System.out.println(solution.searchInsert(new int[]{1, 3, 5, 7}, 3, 0, 2));

    }

}
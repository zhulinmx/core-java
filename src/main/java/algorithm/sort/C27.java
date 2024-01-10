package algorithm.sort;


/**
 * 给定一个排序数组和一个目标值，在数组中找到目标值，并返回其索引。如果目标值不存在于数组中，返回它将会被按顺序插入的位置。
 * 请必须使用时间复杂度为 O(log n) 的算法
 *
 输入: nums = [1,3,5,6], target = 4
 输出: 2

 看到O(log n) 就是二分思想了，但是leetcode上参数设置不合理，如果有左右指针操作更方便！！
 *
 */
public class C27 {

    private int left = 0;
    private int right = 0;
    private boolean init = true;

    public int searchInsert(int[] nums, int target) {
        if(init) {
            right = nums.length-1;
            init = false;
        }

        if(nums[left] >= target) return left;
        if(nums[right] < target) return right+1;
        if(nums[right] == target) return right;

        int mid = (right - left) / 2 + left;
        if(nums[mid] > target) {
            right = mid-1;
            return searchInsert(nums, target);
        } else if(nums[mid] < target) {
            left = mid+1;
            return searchInsert(nums, target);
        }
        return mid;
    }

    public int searchInsert(int[] nums, int target, int left, int right) {

        if(nums[left] >= target) return left;
        if(nums[right] < target) return right+1;
        if(nums[right] == target) return right;

        int mid = (right - left) / 2 + left;
        if(nums[mid] > target) {
            return searchInsert(nums, target, left, mid-1);
        } else if(nums[mid] < target) {
            return searchInsert(nums, target, mid+1, right);
        }
        return mid;
    }

    public static void main(String[] args) {
        int[] a = {1, 3, 5, 6};
        System.out.println(new C27().searchInsert(a, 2));
        System.out.println(new C27().searchInsert(a, 5, 0, 3));

    }
}

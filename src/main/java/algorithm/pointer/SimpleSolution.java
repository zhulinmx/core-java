package algorithm.pointer;


public class SimpleSolution {

    /**
     * leetcode 283. 移动零
     * 给定一个数组 nums，编写一个函数将所有 0 移动到数组的末尾，同时保持非零元素的相对顺序。
     *
     * 请注意 ，必须在不复制数组的情况下原地对数组进行操作。
     *
     * 输入: nums = [0,1,0,3,12]
     * 输出: [1,3,12,0,0]
     * @param nums
     */
    public static void moveZeroes(int[] nums) {

        if (nums.length <= 1) return;
        int index0 = -1;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == 0) {
                if (index0 == -1)
                    index0 = i;
            } else {
                if (i > 0 && index0 > -1 && index0 < i) {
                    //swap
                    nums[index0] = nums[i];
                    nums[i] = 0;
                    index0++;
                }
            }
        }
    }

    public static void main(String[] args) {
        moveZeroes(new int[]{0, 1});
    }
}

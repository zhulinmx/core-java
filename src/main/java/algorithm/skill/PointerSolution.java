package algorithm.skill;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 指针
 */
public class PointerSolution {

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

    /**
     * leetcode 11. 盛最多水的容器
     * 给定一个长度为 n 的整数数组 height 。有 n 条垂线，第 i 条线的两个端点是 (i, 0) 和 (i, height[i]) 。
     *
     * @param height
     * @return
     */
    public static int maxArea(int[] height) {
        if (height == null || height.length <= 1) return 0;

        int start = 0, end = height.length - 1;
        int max = 0;

        while (start < end) {
            int he = Math.min(height[end], height[start]);
            max = Math.max((end - start) * he, max);
            if (height[end] > height[start]) {
                start++;
            } else {
                end--;
            }
        }
        return max;
    }

    /**
     * leetcode 15. 三数之和
     * 给你一个整数数组 nums ，判断是否存在三元组 [nums[i], nums[j], nums[k]] 满足 i != j、i != k 且 j != k ，同时还满足 nums[i] + nums[j] + nums[k] == 0 。
     *
     * 输入：nums = [-1,0,1,2,-1,-4]
     * 输出：[[-1,-1,2],[-1,0,1]]
     *
     * @param nums
     * @return
     */
    public static List<List<Integer>> threeSum(int[] nums) {
        Arrays.sort(nums);
        List<List<Integer>> res = new ArrayList<>();

        for (int k = 0; k < nums.length - 2; k++) {
            if (nums[k] > 0) break;
            if (k > 0 && nums[k] == nums[k - 1]) continue;
            int i = k + 1, j = nums.length - 1;

            while (i < j) {
                int sum = nums[k] + nums[i] + nums[j];
                if (sum < 0) {
                    while (i < j && nums[i] == nums[++i]) ;
                } else if (sum > 0) {
                    while (i < j && nums[j] == nums[--j]) ;
                } else {
                    res.add(List.of(nums[k], nums[i], nums[j]));
                    while (i < j && nums[i] == nums[++i]) ;
                    while (i < j && nums[j] == nums[--j]) ;
                }
            }
        }
        return res;
    }

    /**
     * leetcode 75. 颜色分类
     * 使用整数 0、 1 和 2 分别表示红色、白色和蓝色。
     * 必须在不使用库内置的 sort 函数的情况下。
     *
     * @param nums
     */
    public static void sortColors(int[] nums) {
        if (nums.length == 1) return;
        //三指针
        int index0 = 0, index1 = 0, index2 = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == 0) {
                //同时index1和index2往后移
                nums[index2++] = 2;
                nums[index1++] = 1;
                nums[index0++] = 0;
            } else if (nums[i] == 1) {
                //同时index2往后移
                nums[index2++] = 2;
                nums[index1++] = 1;
            } else {
                nums[index2++] = 2;
            }
        }
    }

    /**
     * leetcode 264. 丑数
     *
     * 技巧：三指针
     *
     * @param n
     * @return
     */
    public static int nthUglyNumber(int n) {
        if (n == 1) return 1;

        int[] dp = new int[n];
        dp[0] = 1;
        int a = 0, b = 0, c = 0;

        for (int i = 1; i < n; i++) {
            int uglyA = dp[a] * 2, uglyB = dp[b] * 3, uglyC = dp[c] * 5;
            dp[i] = Math.min(Math.min(uglyA, uglyB), uglyC);
            if (uglyA == dp[i]) a++;
            if (uglyB == dp[i]) b++;
            if (uglyC == dp[i]) c++;
        }

        return dp[n - 1];
    }

    /**
     * leetcode 42. 接雨水
     *
     * 思路：双指针
     *
     * @param height
     * @return
     */
    public static int trap(int[] height) {
        int left = 0, right = height.length - 1;
        int res = 0, maxLeft = 0, maxRight = 0;

        while (left < right) {
            maxLeft = Math.max(height[left], maxLeft);
            maxRight = Math.max(height[right], maxRight);
            //小的那一头指针往前走
            if (maxLeft >= maxRight) {
                res += (maxRight - height[right]);
                right--;
            } else {
                res += (maxLeft - height[left]);
                left++;
            }
        }

        return res;
    }

    public static void main(String[] args) {
        moveZeroes(new int[]{0, 1});

        System.out.println("-----------------maxArea---------------");
        System.out.println(maxArea(new int[]{1, 8, 6, 2, 5, 4, 8, 3, 7}));
        System.out.println(maxArea(new int[]{1, 1}));

        System.out.println("-----------------threeSum---------------");
        System.out.println(threeSum(new int[]{-1, 0, 1, 2, -1, -4}));
        System.out.println(threeSum(new int[]{0, 0, 0, 0, 0}));
        System.out.println(threeSum(new int[]{0, 0, 0}));
        System.out.println(threeSum(new int[]{0, 1, 1}));
        System.out.println(threeSum(new int[]{-1, 0, 1, 2, -1, -4, -2, -3, 3, 0, 4}));

        System.out.println("-----------------sortColors---------------");
        sortColors(new int[]{2, 0, 2, 1, 1, 0});

        System.out.println("-----------------nthUglyNumber---------------");
        System.out.println(nthUglyNumber(10));
        System.out.println(nthUglyNumber(4));

        System.out.println("-----------------trap---------------");
        System.out.println(trap(new int[]{0, 1, 0, 2, 1, 0, 1, 3, 2, 1, 2, 1}));
        System.out.println(trap(new int[]{4, 2, 0, 3, 2, 5}));

    }
}

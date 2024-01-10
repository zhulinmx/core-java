package algorithm;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class DPSolution {


    /**
     * 现在要求输入一个整数n，请你输出斐波那契数列的第n项。
     * F(1)=1, F(n)=F(n - 1)+F(n - 2)（n ≥ 2，n ∈ N*）
     */
    public static long fibonacci(int n) {
        long result = 0;
        long preOne = 1; //前一个数
        long preTwo = 0; //前两个数
        if (n == 0) {
            return result;
        }
        if (n == 1) {
            return preOne;
        }
        for (int i = 2; i <= n; i++) {
            result = preOne + preTwo;
            preTwo = preOne;
            preOne = result;
        }
        return result;
    }

    /**
     * leetcode 118. 杨辉三角
     *
     * @param numRows
     * @return
     */
    public static List<List<Integer>> generate(int numRows) {
        List<List<Integer>> list = new ArrayList<>();
        int i = 1;
        while (i <= numRows) {
            if (i == 1) list.add(List.of(1));
            if (i == 2) list.add(List.of(1, 1));
            if (i > 2) {
                List<Integer> pre = list.get(i - 1 -1);
                List<Integer> nums = new ArrayList<>();
                nums.add(1);
                for (int j = 0; j < pre.size() - 1; j++) {
                    nums.add(pre.get(j) + pre.get(j + 1));
                }
                nums.add(1);
                list.add(nums);
            }
            i++;
        }
        return list;
    }

    /**
     * leetcode 70. 爬楼梯
     *
     * 假设你正在爬楼梯。需要 n 阶你才能到达楼顶。
     * 每次你可以爬 1 或 2 个台阶。你有多少种不同的方法可以爬到楼顶呢？
     * @param n
     * @return
     */
    public static int climbStairs(int n) {
        if (n <= 1) {
            return 1;
        }
        int[] dp = new int[n];
        dp[0] = 1;
        dp[1] = 2;
        int i = 2;
        while (i < n) {
            dp[i] = dp[i - 1] + dp[i - 2];
            i++;
        }
        return dp[n - 1];
    }

    /**
     * leetcode 198. 打家劫舍 不能连续偷一家
     *
     * 输入：[2,7,9,3,1]
     * 输出：12
     * 解释：偷窃 1 号房屋 (金额 = 2), 偷窃 3 号房屋 (金额 = 9)，接着偷窃 5 号房屋 (金额 = 1)。
     *      偷窃到的最高金额 = 2 + 9 + 1 = 12 。
     *
     * @param nums
     * @return
     *
     */
    public static int rob(int[] nums) {
        if(nums.length == 1) return nums[0];
        if(nums.length == 2) return Math.max(nums[0], nums[1]);
        int[] dp = new int[nums.length];
        dp[0] = nums[0];
        dp[1] = Math.max(nums[0], nums[1]);
        int n = 2;
        while (n < nums.length) {
            dp[n] = Math.max(nums[n]+dp[n-2], dp[n-1]);
            n++;
        }
        return dp[nums.length-1];
    }

    /**
     * leetcode 739. 每日温度
     * 给定一个整数数组 temperatures ，表示每天的温度，返回一个数组 answer ，其中 answer[i] 是指对于第 i 天，下一个更高温度出现在几天后。如果气温在这之后都不会升高，请在该位置用 0 来代替。
     *
     * 示例 1:
     *
     * 输入: temperatures = [73,74,75,71,69,72,76,73]
     * 输出: [1,1,4,2,1,1,0,0]
     *
     *   - 若temperatures[i] < temperatures[i+1]，results[i]=1；
     *   - 若temperatures[i] > temperatures[i+1]
     *   - results[i+1]=0，results[i]=0;
     *   - results[i+1]!=0，那就比较results[i]和temperatures[i+1+results[i+1]]（即将第i天的温度与比第i+1天大的那天的温度进行比较）
     *
     * @param temperatures
     * @return
     */
    public static int[] dailyTemperatures(int[] temperatures) {
        int[] results = new int[temperatures.length];
        if(temperatures.length < 1) return results;

        results[temperatures.length - 1] = 0;
        for (int i = temperatures.length - 2; i >= 0; i--) {
            for (int j = i + 1; j < temperatures.length; j += results[j]) {
                if (temperatures[i] < temperatures[j]) {
                    results[i] = j - i;
                    break;
                } else if (results[j] == 0) {
                    results[i] = 0;
                    break;
                }
            }
        }

        return results;
    }

    public static void main(String[] args) {
        System.out.println(fibonacci(10));
        System.out.println(rob(new int[]{2,7,9,3,1}));
        System.out.println(climbStairs(2));
        System.out.println(Arrays.toString(dailyTemperatures(new int[] {73,74,75,71,69,72,76,73})));

        System.out.println("-----------------generate---------------");
        System.out.println(generate(2));
        System.out.println(generate(4));
    }
}

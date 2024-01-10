package algorithm;


public class GreedySolution {

    /**
     * leetcode 55. 跳跃游戏
     * 给你一个非负整数数组 nums ，你最初位于数组的 第一个下标 。数组中的每个元素代表你在该位置可以跳跃的最大长度。
     *
     * 判断你是否能够到达最后一个下标，如果可以，返回 true ；否则，返回 false 。
     * @param nums
     * @return
     */
    public static boolean canJump(int[] nums) {
        if (nums == null) {
            return false;
        }
        //前n-1个元素能够跳到的最远距离
        int k = 0;
        for (int i = 0; i <= k; i++) {
            //第i个元素能够跳到的最远距离
            int temp = i + nums[i];
            //更新最远距离
            k = Math.max(k, temp);
            //如果最远距离已经大于或等于最后一个元素的下标,则说明能跳过去,退出. 减少循环
            if (k >= nums.length - 1) {
                return true;
            }
        }
        //最远距离k不再改变,且没有到末尾元素
        return false;
    }

    /**
     * leetcode 45. 跳跃游戏 II
     * 与上面的方法思想类似，求最少跳跃数
     * @param nums
     * @return
     */
    public static int jump(int[] nums) {
        int length = nums.length;
        int end = 0; //前一个能跳的最远距离
        int maxPosition = 0;
        int steps = 0;
        for (int i = 0; i < length - 1; i++) {
            maxPosition = Math.max(maxPosition, i + nums[i]);
            if(maxPosition >= nums.length) return steps+1;
            if (i == end) {
                end = maxPosition;
                steps++;
            }
        }
        return steps;
    }


    /**
     * leetcode 121. 买卖股票的最佳时机
     *
     * 给定一个数组 prices ，它的第 i 个元素 prices[i] 表示一支给定股票第 i 天的价格。
     * 你只能选择 某一天 买入这只股票，并选择在 未来的某一个不同的日子 卖出该股票。设计一个算法来计算你所能获取的最大利润。
     * 返回你可以从这笔交易中获取的最大利润。如果你不能获取任何利润，返回 0 。
     *
     * 输入：[7,1,5,3,6,4]
     * 输出：5
     * 解释：在第 2 天（股票价格 = 1）的时候买入，在第 5 天（股票价格 = 6）的时候卖出，最大利润 = 6-1 = 5 。
     *      注意利润不能是 7-1 = 6, 因为卖出价格需要大于买入价格；同时，你不能在买入前卖出股票。
     *
     * @param prices
     * @return
     */
    public static int maxProfit(int[] prices) {
        if(prices.length == 1) return 0;
        /*
        //暴力解法
        for (int i = 0; i < len; i++) {
            for(int j = i; j < len; j++) {
                if(prices[j] - prices[i] > max) {
                    max = prices[j] - prices[i];
                }
            }
        }
        */
        int min = prices[0];
        int profit = 0;
        for (int i = 1; i < prices.length; i++) {
            if(prices[i] < min) min = prices[i];
            if(prices[i] - min > profit) profit = prices[i] - min;
        }
        return profit;
    }


    public static void main(String[] args) {
        int[] ns = {3,0,8,2,0,0,1};
        System.out.println(canJump(ns));
        System.out.println(jump(ns));
        System.out.println(maxProfit(new int[] {7,1,5,3,6,4}));
    }
}

package algorithm.skill;

import java.util.ArrayList;
import java.util.List;

/**
 * 贪心
 */
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
     * 平衡字符串
     *
     * 输入：s = "RLLLLRRRLR"
     * 输出：3
     * 解释：s 可以分割为 "RL"、"LLLRRR"、"LR" ，每个子字符串中都包含相同数量的 ‘L’ 和 ‘R’ 。
     *
     * 解题思路: 不要有嵌套的平衡，只要达到平衡，就立即分割(贪心策略).
     *
     * @param s
     * @return
     */
    public int balancedStringSplit(String s) {
        int cnt = 0;
        int balance = 0;
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == 'L')
                balance--;
            if (s.charAt(i) == 'R')
                balance++;
            if (balance == 0)
                cnt++;
        }
        return cnt;
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


    /**
     * leetcode 763. 划分最多的字母区间
     * 给你一个字符串 s 。我们要把这个字符串划分为尽可能多的片段，同一字母最多出现在一个片段中。
     *
     * 输入：s = "ababcbacadefegdehijhklij"
     * 输出：[9,7,8]
     * 解释：
     * 划分结果为 "ababcbaca"、"defegde"、"hijhklij" 。
     * 每个字母最多出现在一个片段中。
     * 像 "ababcbacadefegde", "hijhklij" 这样的划分是错误的，因为划分的片段数较少。
     *
     * @param s
     * @return
     */
    public static List<Integer> partitionLabels(String s) {
        if (s.length() <= 1) return List.of(s.length());

        List<Integer> lenList = new ArrayList<>();
        int next = -1; //记录区间内重复出现的最远位置
        int pre = -1; //前一个区间的终点
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            int inx = s.lastIndexOf(c);
            if (i == next || (next == -1 && inx == i)) {
                lenList.add(i - pre);
                pre = i;
                next = -1;
                continue;
            }
            next = Math.max(inx, next); //区间扩大
        }

        return lenList;
    }

    /**
     * 假设1元、2元、5元、10元、20元、50元、100元的纸币分别有c0, c1, c2, c3, c4, c5, c6张。
     * 现在要用这些钱来支付K元，至少要用多少张纸币？
     * 贪心策略: 用贪心算法的思想，很显然，每一步尽可能用面值大的纸币即可.
     */
    public static int solve(int money, int[][] moneyCount) {
        int num = 0;
        //首先选择最大面值的纸币
        for (int i = moneyCount.length - 1; i >= 0; i--) {
            //需要的当前面值与面值数量取最小
            int c = Math.min(money / moneyCount[i][0], moneyCount[i][1]);
            money = money - c * moneyCount[i][0];
            num += c;
        }
        if (money > 0) num = -1;

        return num;
    }

    public static void main(String[] args) {
        int[] ns = {3,0,8,2,0,0,1};
        System.out.println(canJump(ns));
        System.out.println(jump(ns));
        System.out.println(maxProfit(new int[] {7,1,5,3,6,4}));

        System.out.println("-----------------partitionLabels---------------");
        System.out.println(partitionLabels("ababcbacadefegdehijhklij"));
        System.out.println(partitionLabels("eccbbbbdec"));
        System.out.println(partitionLabels("caedbdedda"));

        System.out.println(partitionLabels("e"));
        System.out.println(partitionLabels(""));
        System.out.println(partitionLabels("ee"));

        System.out.println("-----------------solve---------------");
        int[][] moneyCount = { { 1, 3 }, { 2, 1 }, { 5, 4 }, { 10, 3 }, { 20, 0 } ,{50, 1}, { 100, 10 } };
        System.out.println(solve(255, moneyCount));

    }
}

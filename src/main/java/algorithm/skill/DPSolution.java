package algorithm.skill;


import java.util.*;

/**
 * DP问题
 */
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

    /**
     * leetcode 279. 完全平方数
     * 给你一个整数 n ，返回 和为 n 的完全平方数的最少数量 。
     * 输入：n = 13  输出：2  解释：13 = 4 + 9

     * @param n
     * @return
     */
    public static int numSquares(int n) {
        //记录数量
        int[] f = new int[n+1];

        for (int i = 1; i <= n; i++) {
            int minn = n;
            for (int j = 1; j * j <= i; j++) {
                //最少数量
                minn = Math.min(minn, f[i - j * j]);
            }
            f[i] = minn + 1;
        }

        return f[n];
    }


    /**
     * leetcode 322. 零钱兑换
     * 给你一个整数数组 coins ，表示不同面额的硬币；以及一个整数 amount ，表示总金额。
     * 计算并返回可以凑成总金额所需的 最少的硬币个数 。如果没有任何一种硬币组合能组成总金额，返回 -1 。
     *
     * 输入：coins = [1, 2, 5], amount = 11  输出：3   解释：11 = 5 + 5 + 1
     * 输入：coins = [2], amount = 3  输出：-1
     *
     * 本题还可以用回溯解决，总之，解法不止一种
     *
     */
    public static int coinChange(int[] coins, int amount) {
        if (amount == 0) return 0;
        if (amount > 0 && coins.length < 1) return -1;

        int[] f = new int[amount + 1];
        int count = 0;
        for (int coin : coins) {
            if (coin > amount) continue;
            f[coin] = 1;
            if (amount == coin) return 1;
            count++;
            if(coin % 2 == 0) count--;
        }

        if(count == 0 && amount % 2 > 0) return -1;

        for (int i = 1; i <= amount; i++) {
            if (f[i] == 1) continue;
            int minCount = Integer.MAX_VALUE;
            for (int j = 0; j < coins.length; j++) {
                //最少数量
                if (i - coins[j] > 0 && f[i - coins[j]] > 0)
                    minCount = Math.min(minCount, f[i - coins[j]]);
            }
            if (minCount == Integer.MAX_VALUE)
                f[i] = -1;
            else
                f[i] = minCount+1;
        }

        return f[amount];
    }

    /**
     * 0/1背包问题
     * 状态转移的选择：物品放不放进背包
     * dp[i][j]表示：对于当前第i个物品，当前背包容量为j时，最大价值为dp[i][j]。
     *
     * @param w 背包容量
     * @param n 可容纳几个物品
     * @param wt 给定的每个物品重量
     * @param val 给定的每个物品价值
     * @return
     */
    public static int maxKnapsack(int w, int n, int[] wt, int[] val) {
        int[][] dp = new int[n+1][w+1];

        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= w; j++) {
                if (j < wt[i - 1]) {
                    //背包容量不够，不放
                    dp[i][j] = dp[i - 1][j];
                } else {
                    //放进背包或者不放进背包，择优选择
                    //没放前的价值：dp[i - 1][j - wt[i - 1]]
                    dp[i][j] = Math.max(dp[i - 1][j - wt[i - 1]] + val[i - 1], dp[i - 1][j]);
                }
            }
        }

        return dp[n][w];
    }

    /**
     * leetcode 416. 分割等和子集
     * 给你一个只包含正整数的 非空数组 nums。请你判断是否可以将这个数组分割成两个子集，使得两个子集的元素和相等。
     *
     * @param nums
     * @return
     */
    public static boolean canPartition(int[] nums) {
        int sum = Arrays.stream(nums).sum();
        if (sum % 2 != 0) return false;
        sum = sum / 2;

        boolean[] dp = new boolean[sum + 1];
        Arrays.fill(dp, false);
        dp[0] = true;

        for (int i = 0; i < nums.length; i++) {
            for (int j = sum; j >= nums[i]; j--) {
                dp[j] = dp[j] || dp[j - nums[i]];
            }
        }
        return dp[sum];
    }


    /**
     * leetcode 300. 最长递增子序列
     *
     * @param nums
     * @return
     */
    public static int lengthOfLIS(int[] nums) {
        if (nums.length <= 1) return nums.length;
        int[] dp = new int[nums.length];
        Arrays.fill(dp, 1);
        int max = 1;

        for (int i = 0; i < nums.length; i++) {
            for (int j = 0; j < i; j++) {
                if (nums[i] > nums[j]) {
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
            }
            max = Math.max(dp[i], max);
        }

        return max;
    }

    /**
     * leetcode 139. 单词拆分
     * 给你一个字符串 s 和一个字符串列表 wordDict 作为字典。请你判断是否可以利用字典中出现的单词拼接出 s 。
     *
     * 注意：不要求字典中出现的单词全部都使用，并且字典中的单词可以重复使用。
     * @param s
     * @param wordDict
     * @return
     */
    public static boolean wordBreak(String s, List<String> wordDict) {
        if (s.isEmpty()) return false;
        int len = s.length();
        if (wordDict == null || wordDict.size() < 1) return false;

        int[][] dp = new int[len][len];
        for (String word : wordDict) {
            if (s.equals(word)) return true;
            int i = s.indexOf(word);
            if (i >= 0) {
                int j = i + word.length() - 1;
                dp[i][j] = 1;
                int k = i + 1;
                if (j < len && k + word.length() <= len) {
                    boolean flag = true;
                    while (flag) {
                        int ii = s.indexOf(word, k);
                        if (ii < 0) {
                            flag = false;
                            continue;
                        }
                        dp[ii][ii + word.length() - 1] = 1;
                        k++;
                    }
                }
            }
        }
        Map<Integer, Integer> map = new HashMap<>();

        for (int m = 0; m < len; m++) {
            for (int n = 0; n < len; n++) {
                if (m > n || dp[m][n] < 1)
                    continue;
                // dp[m][n] = 1
                if (m == 0) {
                    if (n == len - 1) return true;
                    map.put(n, 1);
                } else {
                    if (map.containsKey(m - 1)) {
                        if (n == len - 1) return true;
                        else map.put(n, 1);
                    }
                }
            }
        }

        return false;
    }

    public static void main(String[] args) {
        System.out.println(fibonacci(10));
        System.out.println(rob(new int[]{2,7,9,3,1}));
        System.out.println(climbStairs(2));
        System.out.println(Arrays.toString(dailyTemperatures(new int[] {73,74,75,71,69,72,76,73})));

        System.out.println("-----------------generate---------------");
        System.out.println(generate(2));
        System.out.println(generate(4));

        System.out.println("-----------------numSquares---------------");
        System.out.println(numSquares(12));
        System.out.println(numSquares(13));
        System.out.println(numSquares(3));

        System.out.println("-----------------coinChange---------------");
        System.out.println(coinChange(new int[]{2}, 4));
        System.out.println(coinChange(new int[]{1}, 2));
        System.out.println(coinChange(new int[]{2}, 1));
        System.out.println(coinChange(new int[]{1, 2, 5}, 8));
        System.out.println(coinChange(new int[]{1, 2, 5}, 11));
        System.out.println(coinChange(new int[]{2, 4, 6}, 999));


        System.out.println("-----------------maxKnapsack---------------");
        System.out.println(maxKnapsack(4, 3, new int[]{2, 1, 3}, new int[]{4, 2, 3}));

        System.out.println("-----------------canPartition---------------");
        System.out.println(canPartition(new int[]{1, 5, 11, 5}));
        System.out.println(canPartition(new int[]{1, 3, 2, 5}));

        System.out.println("-----------------lengthOfLIS---------------");
        System.out.println(lengthOfLIS(new int[]{0, 1, 0, 3, 2, 3}));
        System.out.println(lengthOfLIS(new int[]{10, 9, 2, 5, 3, 7, 101, 18}));
        System.out.println(lengthOfLIS(new int[]{7, 7, 7, 7, 7, 7, 7}));
        System.out.println(lengthOfLIS(new int[]{4, 10, 4, 3, 8, 9}));

        System.out.println("-----------------wordBreak---------------");
        System.out.println(wordBreak("ab", List.of("a", "b")));
        System.out.println(wordBreak("applepenapple", List.of("apple", "pen")));
        System.out.println(wordBreak("catsandog", List.of("cats", "dog", "sand", "and", "cat")));

    }
}

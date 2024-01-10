package algorithm;

import java.util.*;

/**
 * 滑动窗口相关
 */
public class C22 {

    /**
     * 给定一个数组和滑动窗口的大小，找出所有滑动窗口里数值的最大值
     * 二次循环，滑动窗口个数和窗口内循环
     * @param nums 给定一个数组
     * @param k 滑动窗口大小
     */
    public static int[] maxSlidingWindow(int[] nums, int k) {
        if (k >= nums.length) {
            Arrays.sort(nums);
            return new int[]{nums[nums.length - 1]};
        }
        if (k == 1) return nums;

        int times = nums.length - k + 1;
        if (times < 0) return null;
        int[] maxs = new int[times];
        for (int i = 0; i < times; i++) {
            int max = nums[i];
            //max[i-1]
            //nums[i-1]
            //nums[i+k-1]
            if (i > 0 && maxs[i - 1] > nums[i - 1]) {
                maxs[i] = (maxs[i - 1] > nums[i + k - 1] ? maxs[i - 1] : nums[i + k - 1]);
                continue;
            }
            for (int j = i; j < i + k; j++) {
                if (nums[j] > max) {
                    max = nums[j];
                }

            }
            maxs[i] = max;
        }
        return maxs;
    }

    /**
     * 优化：使用优先级队列
     * @param nums
     * @param k
     * @return
     */
    public static int[] maxSlidingWindowWithQueue(int[] nums, int k) {
        int n = nums.length;
        // 优先级队列，自定义排序器，首先按照nums元素值进行降序排序，如果元素值相等，则按照数组下标值进行降序排序
        PriorityQueue<int[]> pq = new PriorityQueue<int[]>(new Comparator<int[]>() {
            @Override
            public int compare(int[] pair1, int[] pair2) {
                return pair1[0] != pair2[0] ? pair2[0] - pair1[0] : pair2[1] - pair1[1];
            }
        });
        // 前k个元素入队
        for (int i = 0; i < k; i++) {
            pq.offer(new int[]{nums[i], i});
        }
        // 初始化结果数组
        int[] ans = new int[n - k + 1];
        ans[0] = pq.peek()[0];
        // 开始滑动窗口
        for (int i = k; i < n; i++) {
            // 新的元素入队
            pq.offer(new int[]{nums[i], i});
            // 因为已经排好序，因此可以通过peek剔除掉当前队列中为最大值但非窗口中的的元素，循环结束后则队首元素为当前队列中为最大值且是窗口中的元素
            while (pq.peek()[1] <= i - k) {
                pq.poll();
            }
            ans[i - k + 1] = pq.peek()[0];
        }
        return ans;
    }

    /**
     * leetcode-438. 找到字符串中所有字母异位词
     * 给定两个字符串 s 和 p，找到 s 中所有 p 的 异位词 的子串，返回这些子串的起始索引。不考虑答案输出的顺序。
     * <p>
     * 异位词 指由相同字母重排列形成的字符串（包括相同的字符串）。
     * <p>
     * 输入: s = "cbaebabacd", p = "abc"
     * 输出: [0,6]
     * 解释:
     * 起始索引等于 0 的子串是 "cba", 它是 "abc" 的异位词。
     * 起始索引等于 6 的子串是 "bac", 它是 "abc" 的异位词。
     *
     * @param s
     * @param p
     * @return
     */
    public static List<Integer> findAnagrams(String s, String p) {
        List<Integer> list = new ArrayList<>();
        char[] pchar = p.toCharArray();
        Arrays.sort(pchar);
        for (int i = 0; i <= s.length() - p.length(); i++) {
            String cs = s.substring(i, i + p.length());
            char[] tmp = cs.toCharArray();
            Arrays.sort(tmp);
            if (Arrays.equals(pchar, tmp)) {
                list.add(i);
            }
        }
        return list;
    }

    public static void main(String[] args) {
        int[] a = {1, 3, -1, -3, 5, 3, 6, 7};
        System.out.println(Arrays.toString(maxSlidingWindow(a, 3)));
        System.out.println(Arrays.toString(maxSlidingWindowWithQueue(a, 3)));
        System.out.println("-----------------------");
        System.out.println(findAnagrams("cbaebabacd", "abc"));
    }
}

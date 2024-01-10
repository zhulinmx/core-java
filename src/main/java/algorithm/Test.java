package algorithm;

import java.util.*;
import java.util.stream.Collectors;

public class Test {


    public boolean verifyPostorder(int[] postorder) {
        if(postorder == null) return true;
        if(postorder.length <= 2) return true;
        int root = postorder[postorder.length-1];
        int pos = -1;
        for(int i=0; i<postorder.length-1; i++) {
            if(postorder[i] < root && pos < i && pos >= 0) return false;
            if(postorder[i] > root && pos == -1) pos = i;
        }
        int[] left = null;
        int[] right = null;
        if(pos > 1) left = Arrays.copyOfRange(postorder, 0, pos-1);
        if(pos == -1) left = Arrays.copyOfRange(postorder, 0, postorder.length-1);;
        if(pos >= 0) right = Arrays.copyOfRange(postorder, pos, postorder.length-1);

        if(verifyPostorder(left) && verifyPostorder(right)) return true;
        else return false;
    }

    public int[] getLeastNumbers(int[] arr, int k) {
        if(arr==null || arr.length==0 || k==0) return null;
        if(arr.length == 1 && k==1) return arr;

        int[] mins = new int[k];
        for(int i=0; i<k; i++) {
            for(int j=i+1; j<arr.length; j++) {
                if(arr[i] > arr[j]) {
                    int temp = arr[i];
                    arr[i] = arr[j];
                    arr[j] = temp;
                }
            }
            mins[i] = arr[i];
        }
        return mins;
    }

    public int solution(int[][] A) {
        int m = A.length, n = A[0].length;

        int[][] row = new int[m][n+1];
        int[][] col = new int[m+1][n];

        for(int i=0; i<m; i++) {
            for(int j=0; j<n; j++) {
                row[i][j+1] = row[i][j] + A[i][j];
                col[i+1][j] = col[i][j] + A[i][j];
            }
        }

        for(int len = Math.min(m,n) ; len>1; len--) {
            for(int i=0; i+len<=m; i++) {
                for(int j=0; j+len<=n; j++) {
                    boolean isMagic = true;
                    int sum = row[i][j+len] - row[i][j], dia1 = 0, dia2 = 0;
                    for(int k=0; k<len; k++) {
                        if(row[i+k][j+len] - row[i+k][j] != sum || col[i+len][j+k] - col[i][j+k] != sum) {
                            isMagic = false;
                            break;
                        }
                        dia1 += A[i+k][j+k];
                        dia2 += A[i+len-1-k][j+k];
                    }
                    if(isMagic == true && dia1 == sum && dia2 == sum) {
                        return len;
                    }
                }
            }
        }
        return 1;
    }

    /**
     * leetcode 169. 多数元素
     * 给定一个大小为 n 的数组 nums ，返回其中的多数元素。多数元素是指在数组中出现次数 大于 ⌊ n/2 ⌋ 的元素。
     *
     * 输入：nums = [2,2,1,1,1,2,2]   输出：2
     *
     * @param nums
     * @return
     */
    public static int majorityElement(int[] nums) {
        int len = nums.length;
        Map<Integer, Integer> map = new HashMap();
        for (int num: nums) {
            map.put(num, map.getOrDefault(num, 0)+1);
            if(map.get(num) > nums.length/2) return num;
        }
        return map.get(nums[0]);
    }


    /**
     * leetcode 215. 数组中的第K个最大元素
     */
    public static int findKthLargest(int[] nums, int k) {
        PriorityQueue<Integer> queue = new PriorityQueue<>(k);
        for(int n: nums) {
            queue.offer(n);
            if (queue.size() > k) {
                queue.poll();
            }
        }
        return queue.peek();
    }

    /**
     * leetcode 347. 前 K 个高频元素
     * 利用map特性，并借助stream的各种方法
     * @param nums
     * @return
     */
    public static int[] topKFrequent(int[] nums, int k) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int n : nums) {
            if (map.get(n) == null) map.put(n, 1);
            else map.put(n, map.get(n) + 1);
        }

        // 将Map的key转换为List
        int[] sortedByValue = map.entrySet().stream()
                .sorted(Map.Entry.<Integer, Integer>comparingByValue().reversed())
                .limit(k)
                .mapToInt(Map.Entry::getKey)
                .toArray();

        return sortedByValue;
    }

    /**
     * leetcode 560. 和为 K 的子数组
     * 给你一个整数数组 nums 和一个整数 k ，请你统计并返回 该数组中和为 k 的子数组的个数 。
     * 子数组是数组中元素的连续非空序列。
     *
     * 输入：nums = [1,1,1], k = 2  输出：2
     * 输入：nums = [1,2,3], k = 3  输出：2
     *
     * 解法：前缀和
     *
     * @param nums
     * @param k
     * @return
     */
    public static int subarraySum(int[] nums, int k) {
        // key：前缀和，value：前缀和的个数
        Map<Integer, Integer> preSumFreq = new HashMap<>();
        // 对于下标为 0 的元素，前缀和为 0，个数为 1
        int preSum = 0;
        preSumFreq.put(preSum, 1);
        int count = 0;

        for (int num : nums) {
            preSum += num;
            // 先获得前缀和为 preSum-k 的个数，加到计数变量里
            if (preSumFreq.containsKey(preSum - k)) {
                count += preSumFreq.get(preSum - k);
            }
            preSumFreq.put(preSum, preSumFreq.getOrDefault(preSum, 0) + 1);
        }

        return count;
    }

    /**
     * leetcode 53. 最大子数组和
     * 给你一个整数数组 nums ，请你找出一个具有最大和的连续子数组（子数组最少包含一个元素），返回其最大和。
     * 子数组 是数组中的一个连续部分。
     *
     * 输入：nums = [-2,1,-3,4,-1,2,1,-5,4]   输出：6
     * 解释：连续子数组 [4,-1,2,1] 的和最大，为6 。
     *
     * @param nums
     * @return
     */
    public static int maxSubArray(int[] nums) {
        if(nums.length == 1) return nums[0];
        //前缀和最大值，前缀和最小值
        int maxSum = nums[0], minSum = nums[0];
        int preSum = nums[0];

        for (int i = 1; i < nums.length; i++) {
            preSum += nums[i];
            if(preSum > maxSum) {
                maxSum = preSum;
            }
            if(preSum - minSum > maxSum) {
                maxSum = preSum - minSum;
            }
            if(preSum < minSum) {
                minSum = preSum;
            }
        }

        return maxSum;
    }

    /**
     * leetcode 56. 合并区间
     * 以数组 intervals 表示若干个区间的集合，其中单个区间为 intervals[i] = [starti, endi]。请你合并所有重叠的区间，并返回 一个不重叠的区间数组，该数组需恰好覆盖输入中的所有区间 。
     *
     * 输入：intervals = [[1,3],[2,6],[8,10],[15,18]]
     * 输出：[[1,6],[8,10],[15,18]]
     * 解释：区间 [1,3] 和 [2,6] 重叠, 将它们合并为 [1,6].
     *
     * @param intervals
     * @return
     */
    public static int[][] merge(int[][] intervals) {
        //先排序
        Arrays.sort(intervals, (o1, o2) -> o1[0] - o2[0]);
        List<int[]> list = new ArrayList<>();
        list.add(intervals[0]);
        int len = 1;
        for (int i = 1; i < intervals.length; i++) {
            int[] temp = list.get(len - 1); //前一个区间
            //当前区间的左边界小于等于list最后一个区间的右边界
            if (intervals[i][0] <= temp[1]) {
                if (intervals[i][1] > temp[1]) {
                    temp[1] = intervals[i][1];
                }
            } else {
                list.add(intervals[i]);
                len++; //新加区间
            }
        }

        return list.toArray(new int[len][]);
    }


    /**
     * leetcode 41. 缺失的第一个正数
     *
     * 给你一个未排序的整数数组 nums ，请你找出其中没有出现的最小的正整数。
     * 请你实现时间复杂度为 O(n) 并且只使用常数级别额外空间的解决方案。
     *
     * 输入：nums = [1,2,0]   输出：3
     * 输入：nums = [3,4,-1,1]   输出：2
     *
     * @param nums
     * @return
     */
    public static int firstMissingPositive(int[] nums) {
        int min = nums[0];
        if(nums.length == 1) return (min == 1) ? 2 : 1;


        return min;
    }

    /**
     * leetcode 31. 下一个排列
     * 整数数组的一个排列，就是将其所有成员以序列或线性顺序排列。
     * 【必须 原地 修改，只允许使用额外常数空间】
     *
     * @param nums
     */
    public void nextPermutation(int[] nums) {
        if (nums == null || nums.length == 0) return;
        int firstIndex = -1;
        for (int i = nums.length - 2; i >= 0; i--) {
            if (nums[i] < nums[i + 1]) {
                firstIndex = i;
                break;
            }
        }
        //第一个最大索引
        if (firstIndex == -1) {
            reverse(nums, 0, nums.length - 1);
            return;
        }
        //第二个最大索引
        int secondIndex = -1;
        for (int i = nums.length - 1; i >= 0; i--) {
            if (nums[i] > nums[firstIndex]) {
                secondIndex = i;
                break;
            }
        }
        //交换
        swap(nums, firstIndex, secondIndex);
        //反转
        reverse(nums, firstIndex + 1, nums.length - 1);
        return;
    }

    private void reverse(int[] nums, int i, int j) {
        while (i < j) {
            swap(nums, i++, j--);
        }
    }

    private void swap(int[] nums, int i, int i1) {
        int tmp = nums[i];
        nums[i] = nums[i1];
        nums[i1] = tmp;
    }


    public static void main(String[] args) {
        System.out.println(majorityElement(new int[] {2,2,1,1,1,2,2}));

        System.out.println(new Test().getLeastNumbers(new int[]{1, 0, 7, 4, 5}, 3));
        System.out.println(findKthLargest(new int[]{3,2,3,1,2,4,5,5,6}, 4));
        System.out.println(Arrays.toString(topKFrequent(new int[]{3,2,3,1,2,4,5,5,6}, 3)));

        System.out.println(subarraySum(new int[]{1,1,1}, 2));
        System.out.println(subarraySum(new int[]{1,2,3}, 3));

        System.out.println(maxSubArray(new int[]{-2,1,-3,4,-1,2,1,-5,4}));
        System.out.println(maxSubArray(new int[]{5,4,-1,7,8}));
        System.out.println(maxSubArray(new int[]{-2, 1, -4}));

        merge(new int[][]{{1,6},{8,10},{15,18}});
    }

}

package algorithm;

import java.util.*;

public class ArraySolution {

    /**
     * leetcode 287. 寻找重复数
     * @param nums
     * @return
     */
    public static int findDuplicate(int[] nums) {
        Set<Integer> set = new HashSet();
        for (int i = 0; i < nums.length; i++) {
            int length = set.size();
            set.add(nums[i]);
            if (set.size() == length) {
                return nums[i];
            }
        };
        return 0;
    }

    /**
     * leetcode 136. 只出现一次的数字
     * 给你一个 非空 整数数组 nums ，除了某个元素只出现一次以外，其余每个元素均出现两次。找出那个只出现了一次的元素。
     * 你必须设计并实现线性时间复杂度的算法来解决此问题，且该算法只使用常量额外空间。
     * @param nums
     */
    public static int singleNumber(int[] nums) {
        Arrays.sort(nums);
        int i = 0;
        while (i < nums.length - 1) {
            if (nums[i] != nums[i + 1]) return nums[i];
            i += 2;
        }
        return nums[i];
    }

    /**
     * 同理 leetcode 137. 只出现一次的数字 II
     * 除某个元素仅出现 一次 外，其余每个元素都恰出现 三次 。
     *
     * @param nums
     */
    public static int singleNumber3(int[] nums) {
        Arrays.sort(nums);
        int i = 0;
        while (i < nums.length - 1) {
            if (nums[i] != nums[i + 1]) return nums[i];
            i += 3;
        }
        return nums[i];
    }

    /**
     * leetcode 189. 轮转数组
     * 给定一个整数数组 nums，将数组中的元素向右轮转 k 个位置，其中 k 是非负数。
     *
     * 使用辅助数组最简单，但是如果要求空间复杂度为 O(1)，则不能选择辅助数组，翻转实现
     *
     * @param nums
     * @param k
     */
    public void rotate(int[] nums, int k) {
        k = k % nums.length;
        reverse(nums, 0, nums.length - 1);
        reverse(nums, 0, k - 1);
        reverse(nums, k, nums.length - 1);

        Arrays.stream(nums).forEach(x -> System.out.println(x));
    }

    /**
     * 把一个数组最开始的若干个元素搬到数组的末尾，我们称之为数组的旋转。
     * 输入一个非递减排序的数组的一个旋转，输出旋转数组的最小元素。
     * 例如数组{3,4,5,1,2}为{1,2,3,4,5}的一个旋转，该数组的最小值为1。
     * NOTE：给出的所有元素都大于0，若数组大小为0，请返回0
     * 思路：
     * 利用二分法，找到中间的数，然后和最左边的值进行比较，若大于最左边的数，则最左边从mid开始，
     * 若小于最右边值，则最右边从mid开始。
     * 若左中右三值相等，则取mid前后值中较小的数。
     *
     * @param array
     * @return
     */
    public static int minNumberInRotateArray(int[] array) {
        if (array == null || array.length == 0) return 0;

        int left = 0;
        int right = array.length - 1;
        int mid = 0;
        while (array[left] >= array[right]) {
            if (right - left <= 1) {
                mid = right;
                break;
            }
            mid = (left + right) / 2;
            if (array[left] == array[mid] && array[mid] == array[right]) {
                if (array[left + 1] != array[right - 1]) {
                    mid = array[left + 1] < array[right - 1] ? left + 1 : right - 1;
                } else {
                    left++;
                    right--;
                }
            } else {
                if (array[left] <= array[mid]) {
                    left = mid;
                } else {
                    right = mid;
                }
            }
        }
        return array[mid];
    }

    /**
     * 输入一个整数数组，实现一个函数来调整该数组中数字的顺序，使得所有的奇数位于数组的前半部分，
     * 所有的偶数位于位于数组的后半部分，并保证奇数和奇数，偶数和偶数之间的相对位置不变
     * 思路：每次只和前面一个数交换位置。或者利用辅助数组
     *
     * @param array
     * @return
     */
    public static int[] reOrderArray(int[] array) {

        if (array == null) return null;
        //自第二个数开始，逐个和前一个数进行比较
        for (int i = 1; i < array.length; i++) {
            int temp = array[i];
            int j = i - 1;
            if (array[i] % 2 != 0) {
                while (j >= 0) {
                    if (array[j] % 2 != 0) {
                        break;
                    }
                    if (array[j] % 2 == 0) {
                        int t = array[j + 1];
                        array[j + 1] = array[j];
                        array[j] = t;
                        j--;
                    }
                }
            }
            array[j + 1] = temp;
        }
        return array;
    }

    /**
     * 接上题，使用辅助数组
     * @param array
     * @return
     */
    public static int[] reOrderArray1(int[] array) {
        if (array.length == 0) return null;
        int[] arrTemp = new int[array.length];
        int j = 0;
        for (int i = 0; i < array.length; i++) {
            if(array[i] % 2 != 0) {
                arrTemp[j] = array[i];
                j++;
            }
        }
        for (int i = 0; i < array.length; i++) {
            if(array[i] % 2 == 0) {
                arrTemp[j] = array[i];
                j++;
            }
        }
        return arrTemp;
    }

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
     * 给你一个未排序的整数数组 nums ，请你找出其中没有出现的最小的正整数。
     *
     * 请你实现时间复杂度为 O(n) 并且只使用常数级别额外空间的解决方案。
     * 思路：桶排序，本题可以用桶排序是因为要求的是正数，数组下标不能为负。
     *
     * 输入：nums = [1,2,0]   输出：3
     * 输入：nums = [3,4,-1,1]   输出：2
     *
     * @param nums
     * @return
     */
    public int firstMissingPositive(int[] nums) {
        if (nums.length == 1) return (nums[0] == 1) ? 2 : 1;

        int i = 0, n = nums.length;
        while (i < n) {
            int index = nums[i];
            // 如果元素值在1-n之间，并且当前元素的值和下标不对应，需要交换的数字的下标不在正确的位置上，则执行替换
            // 如[4,2,3,4] 数组最后一个元素4已经在正确位置上了，不需要替换
            if (index > 0 && index <= n && i != index - 1 && index != nums[index - 1]) {
                swap(nums, i, index - 1);
            } else {
                i++;
            }
        }
        for (int j = 0; j < n; j++) {
            // 下标没有对应上，直接返回i+1为最小正整数
            if (j + 1 != nums[j]) {
                return j + 1;
            }
        }
        // 都对应上了最小正整数就是数组长度+1 如[1,2,3] 结果就是4
        return n + 1;
    }

    /**
     * leetcode 128. 最长连续序列
     * 给定一个未排序的整数数组 nums ，找出数字连续的最长序列（不要求序列元素在原数组中连续）的长度。
     *
     * 请你设计并实现时间复杂度为 O(n) 的算法解决此问题。
     *
     * @param nums
     * @return
     */
    public int longestConsecutive(int[] nums) {
        if (nums.length <= 1) return nums.length;

        Set<Integer> set = new HashSet<>();
        //  加入hash表，去重
        for (int num : nums) {
            set.add(num);
        }
        int max_len = 0;

        for (int num : set) {
            if (!set.contains(num - 1)) {
                int curr = num;
                int len = 1;

                while (set.contains(curr + 1)) {
                    curr += 1;
                    len += 1;
                }

                max_len = Math.max(max_len, len);
            }
        }

        return max_len;
    }


    /**
     * 152. 乘积最大子数组
     * 给你一个整数数组 nums ，请你找出数组中乘积最大的非空连续子数组（该子数组中至少包含一个数字），并返回该子数组所对应的乘积。
     * @param nums
     * @return
     */
    public int maxProduct(int[] nums) {
        int max = nums[0];

//        //最容易想到的二维动态规划超时
//        int[][] dp = new int[nums.length][nums.length];
//        for (int i = 0; i < nums.length; i++) {
//            for (int j = i; j < nums.length; j++) {
//                if (i == j) dp[i][j] = nums[i];
//                else {
//                    dp[i][j] = dp[i][j - 1] * nums[j];
//                }
//                max = Math.max(dp[i][j], max);
//            }
//        }

        //另一种方法，当前最大值和最小值和前一个最大值最小值关系
        int pre_max = nums[0];
        int pre_min = nums[0];
        for (int i = 1; i < nums.length; i++) {
            int cur_max = Math.max(Math.max(pre_max * nums[i], pre_min * nums[i]), nums[i]);
            int cur_min = Math.min(Math.min(pre_max * nums[i], pre_min * nums[i]), nums[i]);
            max = Math.max(max, cur_max);
            pre_max = cur_max;
            pre_min = cur_min;
        }

        return max;
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

    /**
     * leetcode 238. 除自身以外数组的乘积
     * 给你一个整数数组 nums，返回 数组 answer ，其中 answer[i] 等于 nums 中除 nums[i] 之外其余各元素的乘积 。
     * 题目数据 保证 数组 nums之中任意元素的全部前缀元素和后缀的乘积都在  32 位 整数范围内。
     *
     * 不要使用除法，且在 O(n) 时间复杂度内完成此题。
     * 思路：计算 前缀积（左边）和 后缀积（右边），再乘在一起。
     *
     * @param nums
     * @return
     */
    public int[] productExceptSelf(int[] nums) {

        int[] res = new int[nums.length];
        int[] pre = new int[nums.length];
        int[] back = new int[nums.length];

        pre[0] = 1;
        back[nums.length-1] = 1;

        for (int i = nums.length-2; i>=0; i--) {
            back[i] = back[i+1] * nums[i+1];
        }

        for (int i = 0; i < nums.length; i++) {
            if(i > 0)
                pre[i] = pre[i-1] * nums[i-1];
            res[i] = pre[i] * back[i];
        }

        return res;
    }

    /**
     * 翻转数组区间
     *
     * @param nums
     * @param i
     * @param j
     */
    private void reverse(int[] nums, int i, int j) {
        while (i < j) {
            swap(nums, i++, j--);
        }
    }

    /**
     * 交换数组中两个位置的值
     *
     * @param nums
     * @param i
     * @param i1
     */
    private void swap(int[] nums, int i, int i1) {
        int tmp = nums[i];
        nums[i] = nums[i1];
        nums[i1] = tmp;
    }


    public static void main(String[] args) {

        ArraySolution solution = new ArraySolution();

        System.out.println(findDuplicate(new int[]{1, 3, 4, 2, 2}));
        System.out.println(singleNumber(new int[]{4, 1, 2, 1, 2}));
        System.out.println(singleNumber3(new int[]{0, 1, 0, 1, 0, 1, 99}));

        System.out.println("-----------------rotate---------------");
        new ArraySolution().rotate(new int[]{1, 2, 3, 4, 5, 6, 7}, 3);
        new ArraySolution().rotate(new int[]{-1, -100, 3, 99}, 2);

        System.out.println(minNumberInRotateArray(new int[]{3, 4, 5, 1, 2, 3}));
        System.out.println(reOrderArray(new int[] {1, 4, 6, 7, 3, 9}));

        System.out.println(majorityElement(new int[] {2,2,1,1,1,2,2}));

        System.out.println(new ArraySolution().getLeastNumbers(new int[]{1, 0, 7, 4, 5}, 3));
        System.out.println(findKthLargest(new int[]{3,2,3,1,2,4,5,5,6}, 4));
        System.out.println(Arrays.toString(topKFrequent(new int[]{3,2,3,1,2,4,5,5,6}, 3)));

        System.out.println(subarraySum(new int[]{1,1,1}, 2));
        System.out.println(subarraySum(new int[]{1,2,3}, 3));

        System.out.println(maxSubArray(new int[]{-2,1,-3,4,-1,2,1,-5,4}));
        System.out.println(maxSubArray(new int[]{5,4,-1,7,8}));
        System.out.println(maxSubArray(new int[]{-2, 1, -4}));

        merge(new int[][]{{1,6},{8,10},{15,18}});

        System.out.println("-----------------firstMissingPositive---------------");
        System.out.println(solution.firstMissingPositive(new int[]{1, 2, 0}));
        System.out.println(solution.firstMissingPositive(new int[]{3, 4, -1, 1}));

        System.out.println("-----------------longestConsecutive---------------");
        System.out.println(solution.longestConsecutive(new int[]{100, 4, 200, 1, 3, 2}));
        System.out.println(solution.longestConsecutive(new int[]{0, 3, 7, 2, 5, 8, 4, 6, 0, 1}));

        System.out.println("-----------------maxProduct---------------");
        System.out.println(solution.maxProduct(new int[]{2, 3, -2, 4}));
        System.out.println(solution.maxProduct(new int[]{-2, 0, -1}));
        System.out.println(solution.maxProduct(new int[]{-2, 3, -4}));

        System.out.println("-----------------productExceptSelf---------------");
        System.out.println(Arrays.toString(solution.productExceptSelf(new int[]{1, 2, 3, 4})));
        System.out.println(Arrays.toString(solution.productExceptSelf(new int[]{-1, 1, 0, -3, 3})));

    }

}

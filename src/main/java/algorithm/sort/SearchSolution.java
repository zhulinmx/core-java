package algorithm.sort;

/**
 * 搜索算法建立在排序算法的基础之上
 * 二分法查找算法
 *
 */
public class SearchSolution {

    /**
     * 最简单的搜索O(n)
     * @param a
     * @param num
     * @return
     */
//    public static int search(int[] a, int num) {
//        for (int i = 0; i < a.length; i++) {
//            if (a[i] == num) return i;
//        }
//        return -1;
//    }

    /**
     *
     * leetcode 34. 在排序数组中查找元素的第一个和最后一个位置
     * @param nums
     * @param target
     * @return
     *
     */
    public static int[] searchRange(int[] nums, int target) {
        int[] arrs = {-1, -1};
        if(nums.length==0) return arrs;
        if(nums.length==1 && nums[0]==target) {
            arrs[0] = 0;
            arrs[1] = 0;
            return arrs;
        }
        int pre = 0;
        int last = nums.length-1;
        int m = (pre + last) / 2;

        while(pre <= last) {
            if(nums[pre] > target || nums[last] < target) break;

            if(nums[pre] == target) {
                arrs[0] = pre;
                if(pre==last) break;
                if(nums[last] == target) { arrs[1] = last; break;}
                else if(pre==last-1) break;
            }else if(nums[m] < target) {
                pre = m;
            }else pre++;

            if(nums[last] == target) {
                arrs[1] = last;
                if(pre==last) break;
                if(nums[pre] == target) { arrs[0] = pre; break;}
                else if(pre==last-1) break;
            }else if(nums[m] > target) {
                last = m;
            }else last--;

            m = (pre + last) / 2;
        }
        if(arrs[0] != -1 && arrs[1] == -1)  arrs[1] = arrs[0];
        if(arrs[0] == -1 && arrs[1] != -1)  arrs[0] = arrs[1];
        return arrs;
    }

    /**
     * 二分查找
     * @param a
     * @param num
     * @return 数组下标
     */
    public static int binarySearch(int[] a, int num) {
        if (a.length == 0) return -1;

        int startPos = 0;
        int endPos = a.length - 1;
        int m = (startPos + endPos) / 2;

        while (startPos <= endPos) {
            if (num == a[m]) return m;
            if (num > a[m]) {
                startPos = m + 1;
            }
            if (num < a[m]) {
                endPos = m - 1;
            }
            m = (startPos + endPos) / 2;
        }
        return -1;
    }

    /**
     * leetcode 153. 寻找旋转排序数组中的最小值
     * 数组元素值互不相同，必须设计一个时间复杂度为 O(log n)
     *
     * 思路：看到O(log n)的时间复杂度，就想到二分查找
     *
     * @param nums
     * @return
     */
    public static int findMin(int[] nums) {
        if (nums.length == 1) return nums[0];

        int left = 0, right = nums.length - 1;
        int mid = (left + right) / 2;

        while (left < right) {
            if(left + 1 == right) return Math.min(nums[left], nums[right]);
            if(mid == 0 || mid == nums.length-1) break;
            if (nums[mid] < nums[mid - 1] && nums[mid] < nums[mid + 1]) break;
            if (nums[mid] < nums[right]) right = mid;
            if (nums[mid] > nums[right]) left = mid;
            mid = (left + right) / 2;
        }
        return nums[mid];
    }

    /**
     *
     * @param nums
     * @param target
     * @return
     */
    public static int search(int[] nums, int target) {
        if (nums.length == 1) return nums[0];

        int left = 0, right = nums.length - 1;
        int mid = (left + right) / 2;

        while (left < right) {
            if(left + 1 == right) return Math.min(nums[left], nums[right]);
            if(mid == 0 || mid == nums.length-1) break;
            if (nums[mid] < nums[mid - 1] && nums[mid] < nums[mid + 1]) break;
            if (nums[mid] < nums[right]) right = mid;
            if (nums[mid] > nums[right]) left = mid;
            mid = (left + right) / 2;
        }
        return nums[mid];
    }

    public static void main(String[] args) {
        int a[] = {1,1};
        int i = 2;
        //System.out.println(search(a, i));
        //System.out.println(binarySearch(a, i));
        System.out.println(searchRange(a, i));
        System.out.println(findMin(new int[] {4,5,6,7,0,1,2}));
        System.out.println(findMin(new int[] {11,13,15,17}));
        System.out.println(findMin(new int[] {1,3,5,7}));
        System.out.println(findMin(new int[] {2,3,1}));
    }

}
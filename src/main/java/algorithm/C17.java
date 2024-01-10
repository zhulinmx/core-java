package algorithm;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Scanner;

public class C17 {

    static class myComparator implements Comparator<int[]> {
        //按最早结束递增排序
        public int compare(int[] a, int[] b) {
            return a[1] - b[1];
        }
    }

    /**
     * 给定一个区间的集合，找到需要移除区间的最小数量，使剩余区间互不重叠。
     * @param intervals
     * @return
     */
    public int eraseOverlapIntervals(int[][] intervals) {
        if (intervals.length == 0) {
            return 0;
        }
        //按照结束进行递增排序
        Arrays.sort(intervals, new myComparator());
        //贪婪策略：每次选择最早结束的活动
        int num = 1, i = 0;
        for (int j = 1; j < intervals.length; j++)
        {
            if (intervals[j][0] >= intervals[i][1])
            {
                i = j;
                num++;
            }
        }
        return intervals.length - num;
    }


    public static int greedyActivitySelector(int[][] act)
    {
        //按照活动截止时间从小到大排序
        Arrays.sort(act, new myComparator());
        //贪婪策略：每次选择最早结束的活动
        int num = 1, i = 0;
        for (int j = 1; j < act.length; j++)
        {
            if (act[j][0] >= act[i][1])
            {
                i = j;
                num++;
            }
        }
        return num;
    }


    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int number = scanner.nextInt();
        int[][] act = new int[number][2];
        for (int i = 0; i < act.length; ++i)
        {
            act[i][0] = scanner.nextInt();
            act[i][1] = scanner.nextInt();
        }
        int ret = greedyActivitySelector(act);
        System.out.println(ret);
    }

}

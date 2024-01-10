package algorithm;

import java.util.*;


public class PermuteSolution {

    public List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> listList = new ArrayList<>();

        for(int i=0; i<nums.length; i++) {
            int num = nums[i];
            if(nums.length == 1) {
                List l = new ArrayList();
                l.add(num);
                listList.add(l);
                return listList;
            }
            int[] arrs = new int[nums.length-1];
            System.arraycopy(nums, 0, arrs, 0, i);
            System.arraycopy(nums, i+1, arrs, i, nums.length-i-1);
            for(List<Integer> list : permute(arrs)){
                list.add(num);
                listList.add(list);
            }
        }
        return listList;
    }

    public List<List<Integer>> permuteUnique(int[] nums) {
        Set<List<Integer>> set = new HashSet<>();

        for(int i=0; i<nums.length; i++) {
            int num = nums[i];
            if(nums.length == 1) {
                List l = new ArrayList();
                l.add(num);
                set.add(l);
                List<List<Integer>> listList = new ArrayList<>(set);
                return listList;
            }
            int[] arrs = new int[nums.length-1];
            System.arraycopy(nums, 0, arrs, 0, i);
            System.arraycopy(nums, i+1, arrs, i, nums.length-i-1);
            for(List<Integer> list : permuteUnique(arrs)){
                list.add(num);
                set.add(list);
            }
        }
        List<List<Integer>> listList = new ArrayList<>(set);

        return listList;
    }

    /**
     * N 皇后：基于集合的回溯
     *
     * 为了判断一个位置所在的列和两条斜线上是否已经有皇后，
     * 使用三个集合 columns, diagonals1, diagonals2 分别记录每一列以及两个方向的每条斜线上是否有皇后。
     *
     */
    public List<List<String>> solveNQueens(int n) {
        List<List<String>> solutions = new ArrayList<List<String>>();
        int[] queens = new int[n];
        //初始值为-1
        Arrays.fill(queens, -1);
        Set<Integer> columns = new HashSet<Integer>();
        Set<Integer> diagonals1 = new HashSet<Integer>();
        Set<Integer> diagonals2 = new HashSet<Integer>();
        backtrack(solutions, queens, n, 0, columns, diagonals1, diagonals2);
        return solutions;
    }

    public void backtrack(List<List<String>> solutions, int[] queens, int n, int row, Set<Integer> columns, Set<Integer> diagonals1, Set<Integer> diagonals2) {
        if (row == n) {
            List<String> board = generateBoard(queens, n);
            solutions.add(board);
        } else {
            for (int i = 0; i < n; i++) {
                //进行三次集合判断
                if (columns.contains(i)) {
                    continue;
                }
                int diagonal1 = row - i;
                if (diagonals1.contains(diagonal1)) {
                    continue;
                }
                int diagonal2 = row + i;
                if (diagonals2.contains(diagonal2)) {
                    continue;
                }
                queens[row] = i;
                columns.add(i);
                diagonals1.add(diagonal1);
                diagonals2.add(diagonal2);
                backtrack(solutions, queens, n, row + 1, columns, diagonals1, diagonals2);
                queens[row] = -1;
                columns.remove(i);
                diagonals1.remove(diagonal1);
                diagonals2.remove(diagonal2);
            }
        }
    }

    public List<String> generateBoard(int[] queens, int n) {
        List<String> board = new ArrayList<String>();
        for (int i = 0; i < n; i++) {
            char[] row = new char[n];
            Arrays.fill(row, '.');
            row[queens[i]] = 'Q';
            board.add(new String(row));
        }
        return board;
    }


    /**
     * leetcode 62. 不同路径
     * 我个人觉得这就是一个数学问题
     * 譬如m=7，n=3，需要向下跳3-1=2个，向左跳7-1=6个，因此这是2+6，即8个元素的全排列
     * @param m
     * @param n
     * @return
     */
    public static int uniquePaths(int m, int n) {
        if (m == 1 || n == 1) return 1;
        int k = m + n - 2;
        long count = 1;
        int times = Math.min(m, n);
        for (int i = 1; i <= times-1; i++) {
            count = count * k / i;
            k--;
        }
        return (int) count;
    }


    public static void main(String[] args) {
        int[] t = new int[] {1, 1, 2};
        //List<List<Integer>> lst = new PermuteSolution().permute(t);
        List<List<Integer>> lst = new PermuteSolution().permuteUnique(t);
        System.out.println(lst);

        List<List<String>> lst1 = new PermuteSolution().solveNQueens(4);
        System.out.println(lst1);

        System.out.println(uniquePaths(51, 9));
    }
}

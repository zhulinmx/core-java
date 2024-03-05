package algorithm.skill;


import java.util.*;

public class MatrixSolution {

    /**
     * leetcode 73. 矩阵置零
     * 给定一个 m x n 的矩阵，如果一个元素为 0 ，则将其所在行和列的所有元素都设为 0 。请使用 原地 算法。
     *
     * 输入：matrix = [[1,1,1],[1,0,1],[1,1,1]]
     * 输出：[[1,0,1],[0,0,0],[1,0,1]]
     *
     * @param matrix
     *
     */
    public static void setZeroes(int[][] matrix) {
        List<Integer> rows = new ArrayList<>();
        List<Integer> colmns = new ArrayList<>();

        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                if(matrix[i][j] == 0) {
                    //为0的行和列
                    rows.add(i);
                    colmns.add(j);
                }
            }
        }

        for (int i = 0; i < matrix.length; i++) {
            if(rows.contains(i)) {
                Arrays.fill(matrix[i], 0);
            }

            for (int j = 0; j < colmns.size(); j++) {
                matrix[i][colmns.get(j)] = 0;
            }
        }

    }


    /**
     * leetcode 240. 搜索二维矩阵 II
     *
     * 编写一个高效的算法来搜索 m x n 矩阵 matrix 中的一个目标值 target 。该矩阵具有以下特性：
     * 每行的元素从左到右升序排列。
     * 每列的元素从上到下升序排列。
     *
     * @param matrix
     * @param target
     * @return
     */
    public static boolean searchMatrix(int[][] matrix, int target) {
        if (matrix == null || matrix.length == 0) return false;
        int m = 0;
        int n = matrix[0].length - 1;
        //如果从第一列开始需要很多判断
        while (m < matrix.length && n >= 0) {
            if(matrix[m][n] == target) {
                return true;
            }else if(matrix[m][n] < target) {
                m++;
            }else {
                n--;
            }
        }
        return false;
    }


    /**
     * leetcode 54. 螺旋矩阵
     * 给你一个 m 行 n 列的矩阵 matrix ，请按照 顺时针螺旋顺序 ，返回矩阵中的所有元素。
     * 输入：matrix = [[1,2,3],[4,5,6],[7,8,9]]   输出：[1,2,3,6,9,8,7,4,5]
     *
     * 思路：顺时针四个走势（右、下、左、上），每个走势对应着将当前的行、列加入list，因此用四个指针记录每次走到的行或者列。
     * @param matrix
     * @return
     */
    public static List<Integer> spiralOrder(int[][] matrix) {
        int startRow = 0, endRow = matrix.length-1;
        int startColumn = 0, endColumn = matrix[0].length-1;

        List<Integer> list = new ArrayList<>();
        //顺时针四个走向
        int direction = 1;

        while (startRow <= endRow && startColumn <= endColumn) {
            if (direction % 4 == 1) {
                for (int i = startColumn; i <= endColumn; i++) {
                    list.add(matrix[startRow][i]);
                }
                startRow++;
            }
            if (direction % 4 == 2) {
                for (int i = startRow; i <= endRow; i++) {
                    list.add(matrix[i][endColumn]);
                }
                endColumn--;
            }
            if (direction % 4 == 3) {
                for (int i = endColumn; i >= startColumn; i--) {
                    list.add(matrix[endRow][i]);
                }
                endRow--;
            }
            if (direction % 4 == 0) {
                for (int i = endRow; i >= startRow; i--) {
                    list.add(matrix[i][startColumn]);
                }
                startColumn++;
            }
            direction++;
        }
        return list;
    }


    /**
     * leetcode 48. 旋转矩阵
     * n × n 的二维矩阵 matrix 表示一个图像。请你将图像顺时针旋转 90 度。
     * 在原有矩阵上直接修改
     *
     * @param matrix
     */
    public static void rotate(int[][] matrix) {
        List<int[]> list = new ArrayList<>();
        for (int i = 0; i < matrix.length; i++) {
            int[] row = Arrays.copyOf(matrix[i], matrix.length);
            list.add(row);
        }

        for (int i = matrix.length - 1; i >= 0; i--) {
            int[] row = list.get(matrix.length - 1 - i);
            for (int j = 0; j < row.length; j++) {
                matrix[j][i] = row[j];
            }
        }
    }

    /**
     * 输入一个矩阵，按照从外向里以顺时针的顺序依次打印出每一个数字，
     * 例如，如果输入如下矩阵：
     * 1 2 3 4
     * 5 6 7 8
     * 9 10 11 12
     * 13 14 15 16
     * 则依次打印出数字
     * 1,2,3,4,8,12,16,15,14,13,9,5,6,7,11,10.
     *
     * 思路：终止行号大于起始行号，终止列号大于起始列号
     *
     */
    public static ArrayList<Integer> printMatrix(int[][] matrix) {
        ArrayList<Integer> list = new ArrayList<>();
        if (matrix == null) return list;
        int start = 0;
        while (matrix[0].length > start * 2 && matrix.length > start * 2) {
            printOneCircle(matrix, start, list);
            start++;
        }
        return list;
    }


    private static void printOneCircle(int[][] matrix, int start, ArrayList<Integer> list) {
        int endX = matrix[0].length - 1 - start;// 列
        int endY = matrix.length - 1 - start;// 行
        // 从左往右
        for (int i = start; i <= endX; i++) list.add(matrix[start][i]); // 从上往下
        if (start < endY) {
            for (int i = start + 1; i <= endY; i++)
                list.add(matrix[i][endX]);
        }
        // 从右往左（判断是否会重复打印）
        if (start < endX && start < endY) {
            for (int i = endX - 1; i >= start; i--)
                list.add(matrix[endY][i]);
            // 从下往上（判断是否会重复打印）
            if (start < endX && start < endY - 1) {
                for (int i = endY - 1; i >= start + 1; i--)
                    list.add(matrix[i][start]);
            }
        }
    }


    /**
     * leetcode 207. 课程表
     * 先修课程对 [0, 1] 表示：想要学习课程 0 ，你需要先完成课程 1
     *
     * numCourses = 2, prerequisites = [[1,0]]   输出：true
     * 解释：总共有 2 门课程。学习课程 1 之前，你需要完成课程 0 。这是可能的。
     *
     * numCourses = 2, prerequisites = [[1,0],[0,1]]  输出：false
     * 解释：总共有 2 门课程。学习课程 1 之前，你需要先完成​课程 0 ；并且学习课程 0 之前，你还应先完成课程 1 。这是不可能的。
     *
     * 思路：有向图有没有环 + BFS（广度优先遍历）
     * 构建入度表、邻接表
     *
     * @param numCourses
     * @param prerequisites
     * @return
     */
    public static boolean canFinish(int numCourses, int[][] prerequisites) {
        int[] indegrees = new int[numCourses];
        List<List<Integer>> adjacency = new ArrayList<>();
        Queue<Integer> queue = new LinkedList<>();
        for (int i = 0; i < numCourses; i++)
            adjacency.add(new ArrayList<>());
        // Get the indegree and adjacency of every course.
        for (int[] cp : prerequisites) {
            indegrees[cp[0]]++;
            adjacency.get(cp[1]).add(cp[0]);
        }
        if (Arrays.stream(indegrees).allMatch(x -> x > 0)) return false;
        // Get all the courses with the indegree of 0.
        System.out.println(Arrays.toString(indegrees));
        for (int i = 0; i < numCourses; i++)
            if (indegrees[i] == 0) queue.add(i);
        // BFS TopSort.
        while (!queue.isEmpty()) {
            int pre = queue.poll();
            numCourses--;
            for (int cur : adjacency.get(pre))
                if (--indegrees[cur] == 0) queue.add(cur);
        }
        return numCourses == 0;
    }

    /**
     * leetcode 200. 岛屿数量
     * 给你一个由 '1'（陆地）和 '0'（水）组成的的二维网格，请你计算网格中岛屿的数量。
     *
     * 思路：把握住 DFS 的时机
     * 非常好的总结：岛屿类问题的通用解法、DFS 遍历框架 -> https://leetcode.cn/problems/number-of-islands/solutions/211211/dao-yu-lei-wen-ti-de-tong-yong-jie-fa-dfs-bian-li-/
     *
     * @param grid
     * @return
     */
    public int numIslands(char[][] grid) {
        int num = 0;
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i][j] == '1') {
                    dfs(grid, i, j);
                    num++;
                }
            }
        }
        return num;
    }

    public void dfs(char[][] grid, int r, int c) {
        //NOT IN area
        if (r < 0 || r >= grid.length || c < 0 || c >= grid[0].length) {
            return;
        }

        if (grid[r][c] != '1') {
            return;
        }

        //mark already dfs
        grid[r][c] = '2';

        dfs(grid, r - 1, c);
        dfs(grid, r, c - 1);
        dfs(grid, r + 1, c);
        dfs(grid, r, c + 1);
    }


    /**
     * leetcode 994. 腐烂的橘子
     * 值 0 代表空单元格；
     * 值 1 代表新鲜橘子；
     * 值 2 代表腐烂的橘子。
     * 每分钟，腐烂的橘子 周围 4 个方向上相邻 的新鲜橘子都会腐烂。返回 直到单元格中没有新鲜橘子为止所必须经过的最小分钟数。如果不可能，返回 -1 。
     *
     * 思路：BFS
     *
     * @param grid
     * @return
     */
    public int orangesRotting(int[][] grid) {

        //1.定义2个int数组，2个一组来记录腐烂橘子的上下左右位置。腐烂橘子(0，0)
        //在矩阵中 上{-1,0}   下{1,0}  左{0,-1}   右{0,1}
        int[] dx = {-1, 1, 0, 0};
        int[] dy = {0, 0, -1, 1};
        int step = 0;//感染次数
        int flash = 0;//新鲜橘子数（后面用于判定是否为-1）

        int row = grid.length;//所给矩阵行
        int col = grid[0].length;//列

        Queue<int[]> queue = new ArrayDeque<>();

        //2.遍历矩阵 将所有的腐烂橘子入队，并且记录初始新鲜橘子数
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                if (grid[i][j] == 1) {
                    flash++;
                }
                if (grid[i][j] == 2) {
                    queue.offer(new int[]{i, j});
                }
            }
        }

        //3.遍历所有腐烂橘子，同时感染四周
        while (flash > 0 && !queue.isEmpty()) {//有橘子且队列不空
            step++;
            //队列中现有的所有腐烂橘子都要进行一次感染
            int size = queue.size();
            for (int s = 0; s < size; s++) {
                int[] poll = queue.poll();//腐烂橘子
                for (int i = 0; i < 4; i++) {
                    //4个位置dx[i] dy[i]  ， xy 为要感染的橘子位置
                    int x = poll[0] + dx[i];//第x行
                    int y = poll[1] + dy[i];//第y列
                    if ((x >= 0 && x < row) && (y >= 0 && y < col) && grid[x][y] == 1) {
                        //xy不越界，并且要感染的地方是 新鲜橘子
                        grid[x][y] = 2;
                        //把被感染的橘子 入队
                        queue.offer(new int[]{x, y});
                        //新鲜橘子-1
                        flash--;
                    }
                }
            }
        }

        //感染完了之后如果还有新鲜橘子
        if (flash > 0) {
            return -1;
        } else {
            return step;
        }
    }


    public static void main(String[] args) {

        int[][] nums = {{1, 2, 3, 4}, {5, 6, 7, 8}, {9, 10, 11, 12}, {13, 14, 15, 16}};
        System.out.println(printMatrix(nums));

        setZeroes(new int[][]{{1, 1, 1}, {1, 0, 1}, {1, 1, 1}});
        System.out.println(searchMatrix(new int[][]{{1, 4, 7, 11, 15}, {2, 5, 8, 12, 19}, {3, 6, 9, 16, 22}, {10, 13, 14, 17, 24}, {18, 21, 23, 26, 30}}, 0));

        System.out.println("-----------------spiralOrder-----------------------");
        System.out.println(spiralOrder(new int[][]{{1, 2}, {4, 5}, {0, 6}, {7, 8}}));
        System.out.println(spiralOrder(new int[][]{{1, 2, 3, 4}}));
        System.out.println(spiralOrder(new int[][]{{1, 2}, {4, 5}}));
        System.out.println(spiralOrder(new int[][]{{1, 2, 3}, {4, 5, 6}, {7, 8, 9}}));

        System.out.println("-----------------rotate-----------------------");
        rotate(new int[][]{{1, 2, 3}, {4, 5, 6}, {7, 8, 9}});

        System.out.println("-----------------canFinish-----------------------");
        System.out.println(canFinish(2, new int[][]{{1, 0}, {0, 1}}));
        System.out.println(canFinish(2, new int[][]{{1, 0}}));
        System.out.println(canFinish(4, new int[][]{{1, 0}, {0, 2}, {1, 2}, {1, 3}}));

        System.out.println("-----------------numIslands-----------------------");
        System.out.println(new MatrixSolution().numIslands(new char[][]{{'1', '0'}, {'0', '1'}, {'1', '0'}, {'1', '1'}}));
        System.out.println(new MatrixSolution().numIslands(new char[][]{{'1', '0'}, {'0', '1'}, {'1', '1'}, {'1', '1'}}));

        System.out.println("-----------------orangesRotting-----------------------");
        System.out.println(new MatrixSolution().orangesRotting(new int[][]{{2, 1, 1}, {1, 1, 0}, {0, 1, 1}}));
        System.out.println(new MatrixSolution().orangesRotting(new int[][]{{2, 1, 1}, {1, 0, 0}, {0, 1, 0}}));
        System.out.println(new MatrixSolution().orangesRotting(new int[][]{{0, 2}}));

    }

}

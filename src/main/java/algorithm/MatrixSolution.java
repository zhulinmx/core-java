package algorithm;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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

    }

}

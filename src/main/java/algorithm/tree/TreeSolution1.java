package algorithm.tree;

import java.util.*;

public class TreeSolution1 {
    /**
     * leetcode 543. 二叉树的直径
     *
     * @param root
     * @return
     */
    int maxd = 0;

    public int diameterOfBinaryTree(TreeNode root) {
        depth(root);
        return maxd;
    }

    private int depth(TreeNode node) {
        if (node == null) {
            return 0;
        }
        int Left = depth(node.left);
        int Right = depth(node.right);
        maxd = Math.max(Left + Right, maxd);//将每个节点最大直径(左子树深度+右子树深度)当前最大值比较并取大者
        return Math.max(Left, Right) + 1;//返回节点深度
    }

    /**
     * leetcode 102. 二叉树的层序遍历
     * 给你二叉树的根节点 root ，返回其节点值的 层序遍历。（即逐层地，从左到右访问所有节点，BFS）。
     *
     * @param root
     * @return
     */
    public List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> listList = new ArrayList<>();
        if (root == null) return listList;

        LinkedList<TreeNode> q = new LinkedList();
        q.add(root);

        while (!q.isEmpty()) {
            List<Integer> list = new ArrayList<>();
            int size = q.size();
            for (int i = 0; i < size; i++) {
                TreeNode t = q.poll();
                if (t.left != null) q.addLast(t.left);
                if (t.right != null) q.addLast(t.right);
                list.add(t.val);
            }
            listList.add(list);
        }
        return listList;
    }

    /**
     * leetcode 199. 二叉树的右视图
     *
     * 思路：BFS，每层最右侧节点
     *
     * @param root
     * @return
     */
    public List<Integer> rightSideView(TreeNode root) {
        List<Integer> res = new ArrayList();
        if(root == null) return res;

        LinkedList<TreeNode> q = new LinkedList();
        q.add(root);

        while (!q.isEmpty()) {
            int size = q.size();
            if (size > 0) res.add(q.getLast().val);
            for (int i = 0; i < size; i++) {
                TreeNode t = q.poll();
                if (t.left != null) q.addLast(t.left);
                if (t.right != null) q.addLast(t.right);
            }
        }

        return res;
    }

    /**
     * leetcode 437. 路径总和 III
     * 给定一个二叉树的根节点 root ，和一个整数 targetSum ，求该二叉树里节点值之和等于 targetSum 的 路径 的数目。
     * 路径 不需要从根节点开始，也不需要在叶子节点结束，但是路径方向必须是向下的（只能从父节点到子节点）。
     *
     * 思路：DFS + 前缀和 + 回溯
     *
     * @param root
     * @param targetSum
     * @return
     */
    Map<Long, Integer> map = new HashMap<>();//sum:count
    int cns, target;

    public int pathSum(TreeNode root, int targetSum) {
        if (root == null) return 0;
        target = targetSum;
        map.put(0L, 1);
        dfs(root, root.val);
        return cns;
    }

    void dfs(TreeNode root, long val) {
        if (map.containsKey(val - target))
            cns += map.get(val - target);

        map.put(val, map.getOrDefault(val, 0) + 1);
        if (root.left != null) dfs(root.left, val + root.left.val);
        if (root.right != null) dfs(root.right, val + root.right.val);
        //回溯
        map.put(val, map.getOrDefault(val, 0) - 1);
    }

    /**
     * leetcode 124. 二叉树中的最大路径和
     * 给你一个二叉树的根节点 root ，返回其 最大路径和 。
     * 二叉树中的 路径 被定义为一条节点序列，序列中每对相邻节点之间都存在一条边。同一个节点在一条路径序列中 至多出现一次 。该路径 至少包含一个 节点，且不一定经过根节点。
     *
     * 路径和 是路径中各节点值的总和。
     *
     * @param root
     * @return
     */
    int max = 0;

    public int maxPathSum(TreeNode root) {
        this.max = root.val;
        dfs(root);
        return max;
    }

    private int dfs(TreeNode root) {
        if (root == null) {
            return 0;
        }

        //计算左边分支最大值，左边分支如果为负数还不如不选择
        int leftMax = Math.max(0, dfs(root.left));
        //计算右边分支最大值，右边分支如果为负数还不如不选择
        int rightMax = Math.max(0, dfs(root.right));
        //left->root->right 作为路径与已经计算过历史最大值做比较
        max = Math.max(max, root.val + leftMax + rightMax);
        // 返回经过root的单边最大分支给当前root的父节点计算使用
        return root.val + Math.max(leftMax, rightMax);

    }

    public static void main(String[] args) {
        TreeSolution1 solution = new TreeSolution1();

        TreeNode tt1 = new TreeNode(8);
        TreeNode tt2 = new TreeNode(7);
        TreeNode tt3 = new TreeNode(1, tt1, tt2);
        TreeNode tt4 = new TreeNode(4);
        TreeNode tt = new TreeNode(4, tt4, tt3);

        System.out.println("-----------------diameterOfBinaryTree---------------");
        System.out.println(solution.diameterOfBinaryTree(tt));

        System.out.println("-----------------rightSideView---------------");
        TreeNode n2 = new TreeNode(2, null, new TreeNode(5));
        TreeNode n3 = new TreeNode(3, null, new TreeNode(4));
        TreeNode n1 = new TreeNode(1, n2, n3);
        System.out.println(solution.rightSideView(n1));
        System.out.println(solution.rightSideView(new TreeNode(1, null, new TreeNode(3))));
        System.out.println(solution.rightSideView(tt));

        System.out.println("-----------------rightSideView---------------");
        System.out.println(solution.levelOrder(tt));

        System.out.println("-----------------pathSum---------------");
        System.out.println(solution.pathSum(n1, 7));

        System.out.println("-----------------maxPathSum---------------");
        TreeNode tree1 = new TreeNode(-10, new TreeNode(9), new TreeNode(20, new TreeNode(15), new TreeNode(7)));
        System.out.println(solution.maxPathSum(tree1));
        System.out.println(solution.maxPathSum(new TreeNode(1, new TreeNode(-2), new TreeNode(3))));


    }
}

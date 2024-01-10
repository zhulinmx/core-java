package algorithm.tree;

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

    public int depth(TreeNode node) {
        if (node == null) {
            return 0;
        }
        int Left = depth(node.left);
        int Right = depth(node.right);
        maxd = Math.max(Left + Right, maxd);//将每个节点最大直径(左子树深度+右子树深度)当前最大值比较并取大者
        return Math.max(Left, Right) + 1;//返回节点深度
    }

    public static void main(String[] args) {
        TreeSolution1 t1 = new TreeSolution1();
        TreeNode tt1 = new TreeNode(8);
        TreeNode tt2 = new TreeNode(7);
        TreeNode tt3 = new TreeNode(1, tt1, tt2);
        TreeNode tt4 = new TreeNode(4);
        TreeNode tt = new TreeNode(4, tt4, tt3);


        System.out.println("-----------------diameterOfBinaryTree---------------");
        System.out.println( t1.diameterOfBinaryTree(tt));

    }
}

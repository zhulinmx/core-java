package algorithm.tree;

import algorithm.tree.TreeNode;

/**
 * 给定一颗二叉搜索树，请找出其中的第k大的结点
 *
 * 思路：二叉搜索树按照中序遍历的顺序打印出来正好就是排序好的顺序，第k个结点就是第K大的节点，
 * 分别递归查找左右子树的第K个节点，或使用非递归借用栈的方式查找，当count=k时返回根节点。
 */
public class C23 {

    private static int count;

    public static TreeNode KthNode (TreeNode pRoot, int k) {
        if (pRoot == null || k < 1) return null;
        count ++;

        if(count == k) return pRoot;

        TreeNode leftNode = KthNode(pRoot.getLeft(),k);
        if (leftNode != null) return leftNode;

        TreeNode rightNode = KthNode(pRoot.getRight(),k);
        if (rightNode != null) return rightNode;

        return null;
    }

}

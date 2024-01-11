package algorithm.tree;

import com.sun.source.tree.Tree;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class TreeSolution2 {

    public List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> listList = new ArrayList<>();
        if(root == null) return listList;

        LinkedList<TreeNode> q = new LinkedList();
        q.add(root);

        while (!q.isEmpty()) {
            List<Integer> list = new ArrayList<>();
            int size = q.size();
            for(int i=0; i<size; i++) {
                TreeNode t = q.poll();
                if(t.left != null) q.addLast(t.left);
                if(t.right != null) q.addLast(t.right);
                list.add(t.val);
            }
            listList.add(list);
        }
        return listList;
    }

    /**
     * leetcode 111. 二叉树的最小深度
     *
     * @param root
     * @return
     */
    public static int minDepth(TreeNode root) {
        if(root == null) return 0;

        if(root.left == null && root.right != null) {
            return 1 + minDepth(root.right);
        }else if(root.left != null && root.right == null) {
            return 1 + minDepth(root.left);
        }else{
            return 1 + Math.min(minDepth(root.left), minDepth(root.right));
        }
    }

    /**
     * leetcode 104. 二叉树的最大深度
     *
     * @param root
     * @return
     */
    public static int maxDepth(TreeNode root) {
        if (root == null) return 0;

        if (root.left == null && root.right != null) {
            return 1 + maxDepth(root.right);
        } else if (root.left != null && root.right == null) {
            return 1 + maxDepth(root.left);
        } else {
            return 1 + Math.max(maxDepth(root.left), maxDepth(root.right));
        }
    }


    /**
     * leetcode 226. 翻转二叉树
     *
     * @param root
     * @return
     */
    public static TreeNode invertTree(TreeNode root) {
        if(root != null) {
            TreeNode left = root.left;
            root.left = invertTree(root.right);
            root.right = invertTree(left);
        }
        return root;
    }


    /**
     * 判断是否为平衡二叉树
     *
     * @param root
     * @return
     */
    public boolean isBalanced(TreeNode root) {
        if (root == null) {
            return true;
        } else {
            return Math.abs(height(root.left) - height(root.right)) <= 1 && isBalanced(root.left) && isBalanced(root.right);
        }
    }

    public int height(TreeNode root) {
        if (root == null) {
            return 0;
        } else {
            return Math.max(height(root.left), height(root.right)) + 1;
        }
    }

    /**
     * 108. 将有序数组转换为平衡二叉搜索树
     * @param nums
     * @return
     */
    public TreeNode sortedArrayToBST(int[] nums) {
        return build(nums, 0, nums.length - 1);
    }

    private TreeNode build(int[] nums, int l, int r) {
        return l > r ? null : new TreeNode(nums[(l + r) / 2], build(nums, l, (l + r) / 2 - 1), build(nums, (l + r) / 2 + 1, r));
    }


    /**
     * leetcode 236. 二叉树的最近公共祖先
     *
     * DFS
     *
     * @param root 最近的共同祖先
     * @param p
     * @param q
     * @return
     */
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        //System.out.println(root);
        if (root == null || root == p || root == q) return root;

        TreeNode left = lowestCommonAncestor(root.left, p, q);
        TreeNode right = lowestCommonAncestor(root.right, p, q);
        if (left == null) return right;
        if (right == null) return left;

        return root;
    }

    /**
     * leetcode 114. 二叉树展开为链表
     * 先序，把左节点搬到右节点上
     *
     * @param root
     */
    public void flatten(TreeNode root) {
        dfs(root);
    }

    private TreeNode dfs(TreeNode root) {
        if (root == null || root.left == null && root.right == null) {
            return root;
        }

        TreeNode right = dfs(root.right);
        TreeNode left = dfs(root.left);

        if (left != null) {
            left.right = root.right;
            root.right = root.left;
            root.left = null;
        }
        return right == null ? left : right;
    }

    public boolean isValidBST(TreeNode root) {
        List<Long> list = new ArrayList();
        dfs(list, root);
        for (int i = 0; i < list.size() - 1; i++) {
            if (list.get(i) >= list.get(i + 1)) return false;
        }
        return true;
    }

    public List<Long> dfs(List<Long> list, TreeNode root) {
        if (root != null) {
            if (root.left != null) dfs(list, root.left);
            list.add(Long.valueOf(root.val));
            if (root.right != null) dfs(list, root.right);
        }
        return list;
    }

    /**
     * 更好的解法
     */
    long pre = Long.MIN_VALUE;

    public boolean isValidBST2(TreeNode root) {
        if (root == null) {
            return true;
        }
        // 访问左子树
        if (!isValidBST2(root.left)) {
            return false;
        }
        // 访问当前节点：如果当前节点小于等于中序遍历的前一个节点，说明不满足BST，返回 false；否则继续遍历。
        if (root.val <= pre) {
            return false;
        }
        pre = root.val;
        // 访问右子树
        return isValidBST2(root.right);
    }


    int ans = 0;
    public int sumNumbers(TreeNode root) {
        dfs(root, 0);
        return ans;
    }

    private void dfs(TreeNode x, int base) {
        int newBase = base * 10 + x.val;
        if (x.left == null && x.right == null) {
            ans += newBase;
            return;
        }
        if (x.left != null) dfs(x.left, newBase);
        if (x.right != null) dfs(x.right, newBase);
    }

    public static void main(String[] args) {
        TreeNode t = new TreeNode(2);
        t.setLeft(new TreeNode(1));
        t.setRight(new TreeNode(4));
        //System.out.println(new TreeSolution().isBalanced(t));
        System.out.println(new TreeSolution2().isValidBST(t));
        System.out.println(new TreeSolution2().sumNumbers(t));


        TreeNode tt1 = new TreeNode(8);
        TreeNode tt2 = new TreeNode(7);
        TreeNode tt3 = new TreeNode(1, tt1, tt2);
        TreeNode tt4 = new TreeNode(4);
        TreeNode tt = new TreeNode(0, tt4, tt3);

        System.out.println("-----------------tree Depth---------------");
        System.out.println(maxDepth(tt));
        System.out.println(minDepth(tt));

        System.out.println(new TreeSolution2().lowestCommonAncestor(tt, tt2, tt4));

        new TreeSolution2().flatten(tt);
        System.out.println(tt);

        System.out.println("-----------------invertTree---------------");
        TreeNode tree = new TreeNode(1, new TreeNode(7), new TreeNode(8, new TreeNode(3), new TreeNode(4)));
        System.out.println(invertTree(tree));

    }


}

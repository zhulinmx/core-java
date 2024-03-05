package algorithm.tree;


import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class TreeSolution2 {

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
     * git rebase的原理
     *
     * 思路：后序遍历，p和q从下往上走，第一次相遇的节点就是最近祖先
     *
     * @param root 最近的共同祖先
     * @param p
     * @param q
     * @return
     */
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null || root == p || root == q) return root;

        TreeNode left = lowestCommonAncestor(root.left, p, q);
        TreeNode right = lowestCommonAncestor(root.right, p, q);
        if (left == null) return right;
        if (right == null) return left;

        return root;
    }

    /**
     * leetcode 114. 二叉树展开为链表
     *
     * 思路：先序，把左节点搬到右节点上
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

    /**
     * leetcode 105. 从前序与中序遍历序列构造二叉树
     * 给定两个整数数组 preorder 和 inorder ，其中 preorder 是二叉树的先序遍历， inorder 是同一棵树的中序遍历，请构造二叉树并返回其根节点。
     *
     * 局部相似
     *
     * @param preorder
     * @param inorder
     * @return
     */
    public TreeNode buildTree(int[] preorder, int[] inorder) {
        if (preorder.length == 0) {
            return null;
        }

        int pre = 0, in = 0;
        //先序遍历第一个值作为根节点
        TreeNode curRoot = new TreeNode(preorder[pre]);
        TreeNode root = curRoot;
        Stack<TreeNode> roots = new Stack<>();
        roots.push(curRoot);
        pre++;

        //遍历前序遍历的数组
        while (pre < preorder.length) {
            //出现了当前节点的值和中序遍历数组的值相等，寻找是谁的右子树
            if (curRoot.val == inorder[in]) {
                //每次进行出栈，实现倒着遍历
                while (!roots.isEmpty() && roots.peek().val == inorder[in]) {
                    curRoot = roots.pop();
                    in++;
                }
                //设为当前的右孩子
                curRoot.right = new TreeNode(preorder[pre]);
                //更新 curRoot
                curRoot = curRoot.right;
            } else {
                //否则的话就一直作为左子树
                curRoot.left = new TreeNode(preorder[pre]);
                curRoot = curRoot.left;
            }
            roots.push(curRoot);
            pre++;
        }

        return root;
    }


    /**
     * leetcode 230. 二叉搜索树中第K小的元素
     *
     * 思路：二叉搜索树的中序遍历为递增序列，即本题旨在求中序遍历的第k个节点。
     *
     * @param root
     * @param k
     * @return
     */
    int res, k;

    public int kthSmallest(TreeNode root, int k) {
        this.k = k;
        dfsMiddle(root);
        return res;
    }

    void dfsMiddle(TreeNode root) {
        if (root == null) return;
        dfsMiddle(root.left);
        if (k == 0) return;
        if (--k == 0) res = root.val;
        dfsMiddle(root.right);
    }

    public static void main(String[] args) {
        TreeSolution2 solution = new TreeSolution2();

        TreeNode t = new TreeNode(2);
        t.setLeft(new TreeNode(1));
        t.setRight(new TreeNode(4));
        //System.out.println(new TreeSolution().isBalanced(t));
        System.out.println(solution.isValidBST(t));
        System.out.println(solution.sumNumbers(t));

        TreeNode tt1 = new TreeNode(8);
        TreeNode tt2 = new TreeNode(7);
        TreeNode tt3 = new TreeNode(1, tt1, tt2);
        TreeNode tt4 = new TreeNode(4);
        TreeNode tt = new TreeNode(0, tt4, tt3);

        System.out.println("-----------------tree Depth---------------");
        System.out.println(maxDepth(tt));
        System.out.println(minDepth(tt));

        System.out.println("-----------------lowestCommonAncestor---------------");
        System.out.println(solution.lowestCommonAncestor(tt, tt2, tt4));

        System.out.println("-----------------flatten---------------");
        solution.flatten(tt);
        System.out.println(tt);

        System.out.println("-----------------invertTree---------------");
        TreeNode tree = new TreeNode(1, new TreeNode(7), new TreeNode(8, new TreeNode(3), new TreeNode(4)));
        System.out.println(invertTree(tree));

        System.out.println("-----------------buildTree---------------");
        System.out.println(solution.buildTree(new int[]{3, 9, 20, 15, 7}, new int[]{20, 9, 15, 3, 7}));

        System.out.println("-----------------kthSmallest---------------");
        TreeNode tree1 = new TreeNode(6, new TreeNode(2), new TreeNode(8, new TreeNode(7), new TreeNode(11)));
        System.out.println(solution.kthSmallest(tree1, 4));
    }


}

package algorithm.skill;

import java.util.*;

public class TrackBackSolution {
    /**
     * leetcode 494. 目标和
     * 给你一个非负整数数组 nums 和一个整数 target 。
     * 向数组中的每个整数前添加 '+' 或 '-' ，然后串联起所有整数，可以构造一个 表达式，其运算结果等于 target 的不同 表达式 的数目。
     * nums = [1,1,1,1,1], target = 3
     *
     * @param nums
     * @param target
     * @return
     */
    int count = 0; //目标和数量

    public int findTargetSumWays(int[] nums, int target) {
        if (nums.length == 0) return 0;
        backtrace(nums, 0, target);
        return count;
    }
    /**
     * 回溯算法模版
     */
    void backtrace(int[] nums, int i, int result) {
        //base case
        if (i == nums.length) {
            //刚好凑出target
            if (result == 0) {
                count++;
            }
            return;
        }
        //选择+或-，在选择+或-上很像二叉树的左子树、右子树
        backtrace(nums, i + 1, result + nums[i]);
        backtrace(nums, i + 1, result - nums[i]);
    }


    /**
     * leetcode 78. 子集
     * 给你一个整数数组 nums ，数组中的元素 互不相同 。返回该数组所有可能的子集（幂集）。
     *
     * @param nums
     * @return
     */
    public List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();

        for (int i = 0; i < nums.length; i++) {
            int num = nums[i];
            if (i == 0) {
                res.add(new ArrayList());
                res.add(List.of(num));
            } else {
                List<List<Integer>> curr = new ArrayList<>();
                Iterator<List<Integer>> iterator = res.iterator();

                while (iterator.hasNext()) {
                    List list = new ArrayList(iterator.next());
                    list.add(num);
                    curr.add(list);
                }
                res.addAll(curr);
            }
        }

        return res;
    }

    /**
     * leetcode 90. 子集 II
     * 给你一个整数数组 nums ，其中可能包含重复元素，请你返回该数组所有可能的子集（幂集）。
     *
     * @param nums
     * @return
     */
    List<List<Integer>> res = new ArrayList<>();
    LinkedList<Integer> path = new LinkedList<>();

    public List<List<Integer>> subsetsWithDup(int[] nums) {
        Arrays.sort(nums);
        // 开始回溯
        // start参数防止重复
        subsetsWithDupHelper(nums, 0);
        return res;
    }


    private void subsetsWithDupHelper(int[] nums, int start) {
        res.add(new ArrayList<>(path));

        for (int i = start; i < nums.length; i++) {
            // 跳过当前树层使用过的、相同的元素
            if (i > start && nums[i - 1] == nums[i]) {
                continue;
            }
            path.add(nums[i]);
            subsetsWithDupHelper(nums, i + 1);
            path.removeLast();
        }
    }

    /**
     * leetcode 17. 电话号码的字母组合
     * 给定一个仅包含数字 2-9 的字符串，返回所有它能表示的字母组合。答案可以按 任意顺序 返回。
     *
     */
    List<String> letters = new ArrayList<>();
    Map<Character, char[]> map = new HashMap<>();

    public List<String> letterCombinations(String digits) {
        if(digits == null || digits.length() == 0) return letters;

        map.put('2', new char[] {'a', 'b', 'c'});
        map.put('3', new char[] {'d', 'e', 'f'});
        map.put('4', new char[] {'g', 'h', 'i'});
        map.put('5', new char[] {'j', 'k', 'l'});
        map.put('6', new char[] {'m', 'n', 'o'});
        map.put('7', new char[] {'p', 'q', 'r', 's'});
        map.put('8', new char[] {'t', 'u', 'v'});
        map.put('9', new char[] {'w', 'x', 'y', 'z'});

        dfs(null, digits, 0);

        return letters;
    }

    private void dfs(char[] cs, String digits, int index) {
        if(index == 0) {
            cs = new char[digits.length()];
        }
        if(index == digits.length() || map.get(digits.charAt(index)) == null) {
            letters.add(String.valueOf(cs));
            return;
        } else {
            for(char c: map.get(digits.charAt(index))) {
                cs[index] = c;
                dfs(cs, digits, index+1);
            }
        }
    }

    /**
     * 接上题，使用队列
     *
     * @param digits
     * @return
     */
    public List<String> letterCombinations1(String digits) {
        if (digits == null || digits.length() == 0) {
            return new ArrayList<String>();
        }
        //一个映射表，第二个位置是"abc“,第三个位置是"def"。。。
        //这里也可以用map，用数组可以更节省点内存
        String[] letter_map = {
                " ", "*", "abc", "def", "ghi", "jkl", "mno", "pqrs", "tuv", "wxyz"
        };
        List<String> res = new ArrayList<>();
        //先往队列中加入一个空字符
        res.add("");

        for (int i = 0; i < digits.length(); i++) {
            //由当前遍历到的字符，取字典表中查找对应的字符串
            String letters = letter_map[digits.charAt(i) - '0'];
            int size = res.size();
            //计算出队列长度后，将队列中的每个元素挨个拿出来
            for (int j = 0; j < size; j++) {
                //每次都从队列中拿出第一个元素
                String tmp = res.remove(0);
                //然后跟"def"这样的字符串拼接，并再次放到队列中
                for (int k = 0; k < letters.length(); k++) {
                    res.add(tmp + letters.charAt(k));
                }
            }
        }
        return res;

    }


    /**
     * leetcode 39. 组合总和
     * 给你一个 无重复元素 的整数数组 candidates 和一个目标整数 target ，找出 candidates 中可以使数字和为目标数 target 的 所有 不同组合 ，并以列表形式返回。你可以按 任意顺序 返回这些组合。
     * candidates 中的 同一个 数字可以 无限制重复被选取 。如果至少一个数字的被选数量不同，则两种组合是不同的。
     *
     * @param candidates
     * @param target
     * @return
     */
    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        int len = candidates.length;
        List<List<Integer>> res = new ArrayList<>();
        if (len == 0) {
            return res;
        }

        // 排序是剪枝的前提
        Arrays.sort(candidates);
        Deque<Integer> path = new ArrayDeque<>();
        backtrace(candidates, 0, len, target, path, res);
        return res;
    }

    void backtrace(int[] candidates, int begin, int len, int target, Deque<Integer> path, List<List<Integer>> res) {
        //System.out.println(path);
        // 由于进入更深层的时候，小于 0 的部分被剪枝，因此递归终止条件值只判断等于 0 的情况
        if (target == 0) {
            res.add(new ArrayList<>(path));
            return;
        }

        for (int i = begin; i < len; i++) {
            // 如果target < candidates[i]，则表示再减下去target为负了，不需要往下走了
            if (target < candidates[i]) {
                break;
            }
            //路径做选择
            path.addLast(candidates[i]);
            //递归回溯
            backtrace(candidates, i, len, target - candidates[i], path, res);
            //撤销选择
            path.removeLast();
        }
    }

    /**
     * leetcode 22. 括号生成
     * 数字 n 代表生成括号的对数，请你设计一个函数，用于能够生成所有可能的并且 有效的 括号组合。
     *
     * @param n
     * @return
     */
    public List<String> generateParenthesis(int n) {
        List<String> res = new ArrayList<>();
        if (n == 0) {
            return res;
        }
        //左右括号合为n个
        StringBuffer sb = new StringBuffer();
        backtrace(n, n, sb, res);
        return res;
    }

    void backtrace(int left, int right, StringBuffer path, List<String> res) {
        //正好左右括号都用完，有效括号
        if (left == 0 && right == 0) {
            res.add(path.toString());
            return;
        }
        //不合法的情况
        if (left < 0 || right < 0 || left > right) return;

        path.append("(");
        backtrace(left - 1, right, path, res);
        path.deleteCharAt(path.length()-1);

        path.append(")");
        backtrace(left, right - 1, path, res);
        path.deleteCharAt(path.length()-1);
    }

    /**
     * leetcode 79. 单词搜索
     * 给定一个 m x n 二维字符网格 board 和一个字符串单词 word 。如果 word 存在于网格中，返回 true ；否则，返回 false 。
     * 单词必须按照字母顺序，通过相邻的单元格内的字母构成，其中“相邻”单元格是那些水平相邻或垂直相邻的单元格。同一个单元格内的字母不允许被重复使用。
     *
     * @param board
     * @param word
     * @return
     */
    public boolean exist(char[][] board, String word) {
        char[] words = word.toCharArray();

        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                if (backtrace(board, words, i, j, 0))
                    return true;
            }
        }
        return false;
    }

    boolean backtrace(char[][] board, char[] word, int r, int c, int index) {

        if (r < 0 || r >= board.length || c < 0 || c >= board[0].length || word[index] != board[r][c])
            return false;

        if (index == word.length - 1)
            return true;

        board[r][c] = '\0';
        boolean res = backtrace(board, word, r - 1, c, index + 1)
                || backtrace(board, word, r, c - 1, index + 1)
                || backtrace(board, word, r + 1, c, index + 1)
                || backtrace(board, word, r, c + 1, index + 1);
        board[r][c] = word[index];

        return res;
    }


    /**
     * leetcode 131. 分割回文串
     * 给你一个字符串 s，请你将 s 分割成一些子串，使每个子串都是 回文串 。返回 s 所有可能的分割方案。
     * 回文串 是正着读和反着读都一样的字符串。
     *
     * @param s
     * @return
     */
    public List<List<String>> partition(String s) {
        char[] words = s.toCharArray();
        List<List<String>> lists = new ArrayList<>();
        if(words.length < 1) return lists;
        List<String> path = new LinkedList<>();
        backtrace(words, path, lists, 0);
        return lists;
    }

    void backtrace(char[] words, List<String> path, List<List<String>> res, int index) {
        if (index == words.length) {
            res.add(new ArrayList<>(path));
            return;
        }

        for (int i = index; i < words.length; i++) {
            if (isPalindrome(words, index, i)) {
                path.add(new String(words, index, i + 1 - index));
                backtrace(words, path, res, i + 1);
                path.remove(path.size() - 1);
            }
        }
    }

    ///判断是否是回文
    public boolean isPalindrome(char[] arr, int left, int right) {
        while (left < right) {
            if (arr[left] != arr[right]) {
                return false;
            }
            left++;
            right--;
        }
        return true;
    }


    public static void main(String[] args) {
        System.out.println("-----------------findTargetSumWays---------------");
        System.out.println(new TrackBackSolution().findTargetSumWays(new int[]{1,1,1,1,1}, 3));
        System.out.println(new TrackBackSolution().findTargetSumWays(new int[]{1}, 1));

        System.out.println("-----------------subsets---------------");
        System.out.println(new TrackBackSolution().subsets(new int[]{0}));
        System.out.println(new TrackBackSolution().subsets(new int[]{1, 2, 3}));

        System.out.println(new TrackBackSolution().subsetsWithDup(new int[]{1, 2, 2, 3}));

        System.out.println("-----------------letterCombinations---------------");
        System.out.println(new TrackBackSolution().letterCombinations("9"));
        System.out.println(new TrackBackSolution().letterCombinations("234"));
        System.out.println(new TrackBackSolution().letterCombinations1("23"));

        System.out.println("-----------------combinationSum---------------");
        System.out.println(new TrackBackSolution().combinationSum(new int[]{2, 3, 6, 7}, 7));
        System.out.println(new TrackBackSolution().combinationSum(new int[]{2, 3, 5}, 8));

        System.out.println("-----------------generateParenthesis---------------");
        System.out.println(new TrackBackSolution().generateParenthesis(3));

        System.out.println("-----------------exist---------------");
        System.out.println(new TrackBackSolution().exist(new char[][]{{'A','B','C','E'}, {'S','F','C','S'}, {'A','D','E','E'}}, "SEE"));

        System.out.println("-----------------partition---------------");
        System.out.println(new TrackBackSolution().partition("aab"));
        System.out.println(new TrackBackSolution().partition("ab"));


    }
}

package algorithm;

import java.util.*;

public class StringSolution {

    /**
     * 字符串组合
     * @param s
     * @return
     */
    public String[] permutation(String s) {
        if(s == null || s.length()==0) return null;
        Set<String> ss = new HashSet<>();
        if(s.length() == 1) return new String[] {s};
        for(int i=0; i<s.length(); i++) {
            String subStr = s.substring(0, i) + s.substring(i+1);
            for (String j: permutation(subStr)) {
                ss.add(s.substring(i, i+1) + j);
            }
        }
        return ss.toArray(new String[ss.size()]);
    }

    /**
     * 全排序算法
     *
     * 递归解析：
     *
     * 终止条件： 当 x = len(c) - 1 时，代表所有位已固定（最后一位只有 111 种情况），则将当前组合 c 转化为字符串并加入 res ，并返回；
     * 递推参数： 当前固定位 x ；
     * 递推工作： 初始化一个 Set ，用于排除重复的字符；将第 x 位字符与 i ∈\in∈ [x, len(c)] 字符分别交换，并进入下层递归；
     * 剪枝： 若 c[i] 在 Set​ 中，代表其是重复字符，因此 “剪枝” ；
     * 将 c[i] 加入 Set​ ，以便之后遇到重复字符时剪枝；
     * 固定字符： 将字符 c[i] 和 c[x] 交换，即固定 c[i] 为当前位字符；
     * 开启下层递归： 调用 dfs(x + 1) ，即开始固定第 x + 1 个字符；
     * 还原交换： 将字符 c[i] 和 c[x] 交换（还原之前的交换）；
     *
     * @param s
     * @return
     */

    List<String> res = new LinkedList<>();
    char[] c;

    public String[] permutation1(String s) {
        c = s.toCharArray();
        dfs(0);
        return res.toArray(new String[res.size()]);
    }

    void dfs(int x) {
        if(x == c.length - 1) {
            res.add(String.valueOf(c));      // 添加排列方案
            return;
        }
        HashSet<Character> set = new HashSet<>();
        for(int i = x; i < c.length; i++) {
            if(set.contains(c[i])) continue; // 重复，因此剪枝
            set.add(c[i]);
            swap(i, x);                      // 交换，将 c[i] 固定在第 x 位
            dfs(x + 1);                      // 开启固定第 x + 1 位字符
            swap(i, x);                      // 恢复交换
        }
    }

    void swap(int a, int b) {
        char tmp = c[a];
        c[a] = c[b];
        c[b] = tmp;
    }


    /**
     * 字符串去重
     * @param s
     * @return
     */
    public String unRepeatStr(String s) {
        StringBuffer sbf = new StringBuffer();
        Set<String> ss = new HashSet<>();
        for(int i = 0; i<s.length(); i++) {
            int pre_size = ss.size();
            String c = String.valueOf(s.charAt(i));
            ss.add(c);
            if(ss.size() > pre_size) {
                sbf.append(c);
            }
        }
        return sbf.toString();
    }

    /**
     * leetcode49. 字母异位词分组
     * 给你一个字符串数组，请你将 字母异位词 组合在一起。可以按任意顺序返回结果列表。
     * 字母异位词 是由重新排列源单词的所有字母得到的一个新单词。
     *
     * 输入: strs = ["eat", "tea", "tan", "ate", "nat", "bat"]
     * 输出: [["bat"],["nat","tan"],["ate","eat","tea"]]
     *
     * @param strs
     * @return
     */
    public List<List<String>> groupAnagrams(String[] strs) {
        if (strs == null || strs.length == 0) return null;
        if (strs.length == 1) return List.of(List.of(strs[0]));

        Map<String, List<String>> map = new HashMap<>();
        for (String str : strs) {
            char[] charArray = str.toCharArray();
            Arrays.sort(charArray);
            String s = String.valueOf(charArray);
            if (map.containsKey(s)) {
                List<String> list = map.get(s);
                list.add(str);
                map.put(s, list);
            } else {
                map.put(s, new ArrayList<>(Arrays.asList(str)));
            }
        }
        List<List<String>> res = new ArrayList<>(map.values());
        return res;
    }


    /**
     * leetcode 5. 最长回文子串
     * 给你一个字符串 s，找到 s 中最长的回文子串。
     * 如果字符串的反序与原始字符串相同，则该字符串称为回文字符串。
     *
     * 思路：中心扩散
     *
     * @param s
     * @return
     */
    public String longestPalindrome(String s) {
        int len = s.length();
        int maxStart = 0;
        int maxLength = 0;

        for (int i = 0; i < len; i++) {
            //中心节点有可能是1个或者1个，即长度是奇数或偶数两种情况
            for (int j = 0; j <= 1; j++) {
                //左右两边
                int l = i;
                int r = i + j;

                while (l >= 0 && r < len && s.charAt(l) == s.charAt(r)) {
                    l--;
                    r++;
                }

                // 回溯到回文字符串的起始和结束位置
                l++;
                r--;

                // 比较并保存最长的字符串起始位置和长度
                if (maxLength < r - l + 1) {
                    maxLength = r - l + 1;
                    maxStart = l;
                }
            }
        }

        return s.substring(maxStart, maxStart + maxLength);

    }

    /**
     * leetcode 65. 有效数字
     *
     * 思路：有限状态机（DFA）
     *
     * @param s
     * @return
     */
    public boolean isNumber(String s) {
        int state = 0;
        int finals = 0b101101000;
        int[][] transfer = new int[][]{
                {0, 1, 6, 2, -1},
                {-1, -1, 6, 2, -1},
                {-1, -1, 3, -1, -1},
                {8, -1, 3, -1, 4},
                {-1, 7, 5, -1, -1},
                {8, -1, 5, -1, -1},
                {8, -1, 6, 3, 4},
                {-1, -1, 5, -1, -1},
                {8, -1, -1, -1, -1}
        };
        char[] ss = s.toCharArray();
        for (int i = 0; i < ss.length; ++i) {
            int id = make(ss[i]);
            if (id < 0) return false;
            state = transfer[state][id];
            if (state < 0) return false;
        }
        return (finals & (1 << state)) > 0;
    }

    private int make(char c) {
        switch (c) {
            case ' ':
                return 0;
            case '+':
            case '-':
                return 1;
            case '.':
                return 3;
            case 'E':
            case 'e':
                return 4;
            default:
                if (c >= 48 && c <= 57) return 2;
        }
        return -1;
    }


    public static void main(String[] args) {

        StringSolution solution = new StringSolution();
        //System.out.println(new StringSolution().unRepeatStr("abbd"));
        System.out.println(new StringSolution().permutation1("abc") );
        for(String str: new StringSolution().permutation1("abc")) {
            System.out.println(str);
        }

        System.out.println("-----------------groupAnagrams---------------");
        System.out.println(solution.groupAnagrams(new String[]{"eat", "tea", "tan", "ate", "nat", "bat"}));
        System.out.println(solution.groupAnagrams(new String[]{"a"}));
        System.out.println(solution.groupAnagrams(new String[]{""}));

        System.out.println("-----------------longestPalindrome---------------");
        System.out.println(solution.longestPalindrome("babad"));
        System.out.println(solution.longestPalindrome("cbbd"));

        System.out.println("-----------------isNumber---------------");
        System.out.println(solution.isNumber("0"));
        System.out.println(solution.isNumber("e9"));
        System.out.println(solution.isNumber("."));
    }
}

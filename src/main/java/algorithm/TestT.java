package algorithm;

import java.util.*;

public class TestT {

    /**
     * leetcode 139. 单词拆分
     * 给你一个字符串 s 和一个字符串列表 wordDict 作为字典。请你判断是否可以利用字典中出现的单词拼接出 s 。
     *
     * 注意：不要求字典中出现的单词全部都使用，并且字典中的单词可以重复使用。
     * @param s
     * @param wordDict
     * @return
     */
    public static boolean wordBreak(String s, List<String> wordDict) {
        if (s.isEmpty()) return false;
        int len = s.length();
        if (wordDict == null || wordDict.size() < 1) return false;
        // dp[i][j] = k
        int[][] dp = new int[len][len];
        for (String word : wordDict) {
            if(s.equals(word)) return true;
            int i = s.indexOf(word);
            if (i >= 0) {
                int j = i + word.length()-1;
                dp[i][j] = 1;
                int k = i + 1;
                if (j < len && k + word.length() <= len) {
                    boolean flag = true;
                    while (flag) {
                        int ii = s.indexOf(word, k);
                        if(ii<0) {
                            flag = false;
                            continue;
                        }
                        dp[ii][ii + word.length()-1] = 1;
                        k++;
                    }
                }
            }
        }
        Map<Integer, Integer> map = new HashMap<>();

        for (int m = 0; m < len; m++) {
            for (int n = 0; n < len; n++) {
                if (m > n || dp[m][n] < 1)
                    continue;
                // dp[m][n] = 1
                if (m == 0) {
                    if (n == len - 1) return true;
                    map.put(n, 1);
                } else {
                    if (map.containsKey(m-1)) {
                        if (n == len - 1) return true;
                        else map.put(n, 1);
                    }
                }
            }
        }

        return false;
    }


    public static int wordBreak3(String s, Set<String> dict) {
        // Write your code here
        int count = 0;
        if (s.isEmpty()) return count;
        int len = s.length();
        if (dict == null || dict.size() < 1) return count;
        // dp[i][j] = k
        int[][] dp = new int[len][len];
        for (String word : dict) {
            int i = s.toLowerCase().indexOf(word.toLowerCase());
            if (i >= 0) {
                int j = i + word.toLowerCase().length() - 1;
                dp[i][j] = 1;
            }
        }
        Map map = new HashMap<Integer, Integer>();

        for (int m = 0; m < len; m++) {
            for (int n = 0; n < len; n++) {
                if (m >= n || dp[m][n] < 1)
                    continue;
                if (m == 0) map.put(n, 1);
                else {
                    if (map.containsKey(m-1)) {
                        if (n == len - 1) count++;
                        else map.put(n, 1);
                    }
                }
            }
        }

        return count;
    }


    public static void main(String[] args) {
        var set = new HashSet<String>();
        set.add("Cat");
        set.add("mat");
        set.add("Do");
        //System.out.println(TestT.wordBreak3("CatMat", set));
        System.out.println(TestT.wordBreak("ab", List.of("a", "b")));
        System.out.println(TestT.wordBreak("applepenapple", List.of("apple", "pen")));
        System.out.println(TestT.wordBreak("catsandog", List.of("cats", "dog", "sand", "and", "cat")));

    }
}

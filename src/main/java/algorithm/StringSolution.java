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

    public static void main(String[] args) {
        //System.out.println(new StringSolution().unRepeatStr("abbd"));
        System.out.println(new StringSolution().permutation1("abc") );
        for(String str: new StringSolution().permutation1("abc")) {
            System.out.println(str);
        }
    }
}

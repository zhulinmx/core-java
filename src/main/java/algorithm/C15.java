package algorithm;

/**
 *
 * 贪心算法
 *
 * https://blog.csdn.net/TuttuYYDS/article/details/124636914
 *
 * 输入：s = "RLLLLRRRLR"
 * 输出：3
 * 解释：s 可以分割为 "RL"、"LLLRRR"、"LR" ，每个子字符串中都包含相同数量的 ‘L’ 和 ‘R’ 。
 *
 * 解题思路: 不要有嵌套的平衡，只要达到平衡，就立即分割(贪心策略).
 * 我们假设 ‘R’ == 1, ‘L’ == -1 .只要累加等于 0 就算分割一次.
 *
 */
public class C15 {

    public int balancedStringSplit(String s) {
        int cnt = 0;
        int balance = 0;
        for(int i = 0; i < s.length(); i++){
            if(s.charAt(i) == 'L')
                balance--;
            if(s.charAt(i) == 'R')
                balance++;
            if(balance == 0)
                cnt++;
        }
        return cnt;
    }

}

package algorithm.structure;

import java.util.Stack;

/**
 * 栈相关算法
 * 利用栈的特性：先进后出
 */
public class StackSolution {
    /**
     * 输入两个整数序列，第一个序列表示栈的压入顺序，请判断第二个序列是否为该栈的弹出顺序。假设压入栈的所有数字均不相等。
     *
     * 例如序列1,2,3,4,5是某栈的压入顺序，序列4,5,3,2,1是该压栈序列对应的一个弹出 序列，但4,3,5,1,2就不可能是该压栈序列的弹出序列。（注意：这两个序列的长度是相等的）
     * 思路：用栈来压入弹出元素，相等则出栈。
     */
    public static boolean isPopOrder(int[] pushA, int[] popA) {
        if (pushA == null || popA == null) {
            return false;
        }
        Stack<Integer> stack = new Stack<>();
        int index = 0;

        for (int i = 0; i < pushA.length; i++) {
            stack.push(pushA[i]);
            while (!stack.isEmpty() && stack.peek() == popA[index]) {
                stack.pop();
                index++;
            }
        }
        return stack.isEmpty();
    }

    /**
     * leetcode 20. 有效的括号
     * 给定一个只包括 '('，')'，'{'，'}'，'['，']' 的字符串 s ，判断字符串是否有效。（经典栈）
     *
     * @param s
     * @return
     */
    public static boolean isValid(String s) {
        Stack<Character> stack = new Stack();
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            switch (c) {
                case ')': {
                    if (stack.size() > 0 && stack.peek().equals('(')) stack.pop();
                    else return false;
                    break;
                }
                case '}': {
                    if (stack.size() > 0 && stack.peek().equals('{')) stack.pop();
                    else return false;
                    break;
                }
                case ']': {
                    if (stack.size() > 0 && stack.peek().equals('[')) stack.pop();
                    else return false;
                    break;
                }
                default:
                    stack.push(c);
            }
        }
        return stack.size() == 0 ? true : false;
    }


    /**
     * leetcode 394. 字符串解码
     * 给定一个经过编码的字符串，返回它解码后的字符串。
     * 编码规则为: k[encoded_string]，表示其中方括号内部的 encoded_string 正好重复 k 次。注意 k 保证为正整数。
     *
     * 输入：s = "3[a]2[bc]"      输出："aaabcbc"
     * 输入：s = "3[a2[c]]"       输出："accaccacc"
     * 输入：s = "2[abc]3[cd]ef"  输出："abcabccdcdcdef"
     *
     * @param s
     * @return
     */
    public static String decodeString(String s) {
        Stack stack = new Stack();
        StringBuffer str = new StringBuffer();

        int first = s.indexOf('[');
        int last = s.lastIndexOf(']');

        if(first < 0) return s;
        if(last < 0) return str.toString();

        for(int i = 0; i<=last; i++) {
            char c = s.charAt(i);
            if(c!=']')
                stack.push(c);
            else {
                StringBuffer inStr = new StringBuffer();
                StringBuffer cbf = new StringBuffer();
                while (!"[".equals(String.valueOf(stack.peek())))
                    inStr.insert(0, stack.pop());

                stack.pop();

                while (!stack.isEmpty() && String.valueOf(stack.peek()).matches("-?[0-9]+(\\\\.[0-9]+)?")) {
                    cbf.insert(0, stack.pop());
                }
                int count = Integer.valueOf(cbf.toString());
                while (count > 0) {
                    stack.push(inStr.toString());
                    count--;
                }
            }
        }

        if(last < s.length()-1)
            str.append(s.substring(last+1));

        while(!stack.isEmpty())
            str.insert(0, stack.pop());

        return str.toString();
    }


    public static void main(String[] args) throws Exception {
        System.out.println(isPopOrder(new int[] {1,2,3,4,5}, new int[] {4,5,3,2,1}));
        System.out.println(isPopOrder(new int[] {1,2,3,4,5}, new int[] {4,3,5,1,2}));

        System.out.println("-----------------isValid-------------");
        System.out.println(isValid("()[]{}"));
        System.out.println(isValid("(}"));
        System.out.println(isValid("(("));

        System.out.println("-----------------decodeString-------------");
        System.out.println(decodeString("3[a]2[bc]"));
        System.out.println(decodeString("2[abc]3[cd]ef"));
    }
}

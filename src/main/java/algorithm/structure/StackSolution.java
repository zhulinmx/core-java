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

    public static void main(String[] args) throws Exception {
        System.out.println(isPopOrder(new int[] {1,2,3,4,5}, new int[] {4,5,3,2,1}));
        System.out.println(isPopOrder(new int[] {1,2,3,4,5}, new int[] {4,3,5,1,2}));

        System.out.println("-----------------isValid-------------");
        System.out.println(isValid("()[]{}"));
        System.out.println(isValid("(}"));
        System.out.println(isValid("(("));
    }
}

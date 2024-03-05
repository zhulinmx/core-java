package algorithm.structure;

import java.util.Stack;

/**
 * 最小栈
 *
 * 思路：定义两个栈，一个存放入的值。另一个存最小值
 */
public class MinStack1 {

    private Stack<Integer> stack1 = new Stack<>();
    private Stack<Integer> stack2 = new Stack<>();

    public void push(int node) {
        stack1.push(node);
        if (stack2.isEmpty()) {
            stack2.push(node);
        } else if (stack2.peek() > node) {
            stack2.push(node);
        }
    }

    public void pop() {
        //stack1 pop之后，判断和stack2的top值是否一样，如果一样，则也要pop top值
        if (stack1.pop().equals(stack2.peek()))
            stack2.pop();
    }

    public int top() {
        return stack1.peek();
    }

    public int getMin() {
        return stack2.peek();
    }

    public static void main(String[] args) {
        MinStack1 minStack = new MinStack1();
        minStack.push(-2);
        minStack.push(0);
        minStack.push(-3);
        System.out.println(minStack.getMin());  // 返回 -3
        minStack.pop();
        System.out.println(minStack.top());      // 返回 0.
        System.out.println(minStack.getMin());   // 返回 -2.
    }
}

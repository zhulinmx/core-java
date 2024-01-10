package algorithm.structure;

import java.util.*;

/**
 * leetcode 155. 最小栈
 * 设计一个支持 push ，pop ，top 操作，并能在常数时间内检索到最小元素的栈。
 *
 * 实现 MinStack 类:
 *
 * MinStack() 初始化堆栈对象。
 * void push(int val) 将元素val推入堆栈。
 * void pop() 删除堆栈顶部的元素。
 * int top() 获取堆栈顶部的元素。
 * int getMin() 获取堆栈中的最小元素。
 *
 */
public class MinStack {
    private Integer min;
    private List<Integer> list = new ArrayList();

    public MinStack() {

    }

    public void push(int val) {
        if (min == null || (min != null && min > val)) min = val;
        list.add(val);
    }

    public void pop() {
        if(list.size()>0) {
            int topValue = top();
            list.remove(list.size()-1);
            if(topValue == min) {
                min = (list.size() == 0) ? null : Collections.min(list);
            }
        }
    }

    public int top() {
        return (int)list.get(list.size()-1);
    }

    public int getMin() {
        return min;
    }

    public static void main(String[] args) {
        MinStack minStack = new MinStack();
        minStack.push(-2);
        minStack.push(0);
        minStack.push(-3);
        System.out.println(minStack.getMin());  // 返回 -3
        minStack.pop();
        System.out.println(minStack.top());      // 返回 0.
        System.out.println(minStack.getMin());   // 返回 -2.
    }
}

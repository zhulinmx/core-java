package algorithm.structure;

import java.util.Arrays;
import java.util.Stack;

/**
 * 两个栈实现队列
 */
public class QueueStack {
    Stack<Integer> stack1 = new Stack<Integer>();
    Stack<Integer> stack2 = new Stack<Integer>();

    public void in(Integer val) {
        stack1.push(val);

    }
    public Integer out() throws Exception {
        if (stack1.isEmpty() && stack2.isEmpty()) {
            throw new Exception("栈为空！");
        }

        if (stack2.isEmpty()) {
            while (!stack1.isEmpty()) {
                stack2.push(stack1.pop());
            }
        }
        return stack2.pop();
    }

    public static void main(String[] args) throws Exception {

        QueueStack queue = new QueueStack();
        Arrays.stream((new int[]{1, 2, 3})).forEach(queue::in);
        System.out.println(queue.out());
        System.out.println(queue.out());
        queue.in(5);
        queue.in(6);
        System.out.println(queue.out());
        System.out.println(queue.out());
        System.out.println(queue.out());
    }
}

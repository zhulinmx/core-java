package algorithm.structure;


import java.util.ArrayList;
import java.util.Stack;

/**
 * 输入一个链表，从尾到头打印链表每个节点的值
 * 借助栈实现
 *
 */
public class C6 {

    public ArrayList<Integer> printListFromTailToHead(ListNode listNode) {
        if(listNode == null)
            return null;
        Stack<ListNode> stack = new Stack<ListNode>();
        ArrayList<Integer> arr = new ArrayList<Integer>();
        while (listNode != null) {
            stack.push(listNode);
            listNode = listNode.getNext();
        }

        while (!stack.empty()) {
            arr.add(stack.pop().getVal());
        }
        return arr;

    }
}

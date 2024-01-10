package algorithm.structure;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class C2 {

    /**
     * leetcode 206. 反转链表
     *
     * @param head
     * @return
     */
    public static ListNode reverseList(ListNode head) {
        ListNode prev = null;
        ListNode curr = head;

        while (curr != null) {
            ListNode nextTemp = curr.next; // 保存下一个节点
            curr.next = prev;
            prev = curr; // 保存当前节点
            curr = nextTemp;
        }
        return prev;
    }


    /**
     * 有序链表去重
     *
     * @param head
     * @return
     */
    public static ListNode unRepeatList(ListNode head) {
        if(head==null||head.next==null) return head;

        ListNode curr = head;
        while (curr != null) {
            ListNode nextTemp = curr.next;
            if(nextTemp != null && curr.val == nextTemp.val) curr.next = nextTemp.next;
            curr = curr.next;
        }
        return head;
    }


    /**
     * leetcode 160. 相交链表
     *
     * @param headA
     * @param headB
     * @return
     */
    public static ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        if (headA == null || headB == null) return null;
        Set<ListNode> nodes = new HashSet<>();
        while (headA != null) {
            nodes.add(headA);
            headA = headA.next;
        }
        while (headB != null) {
            int size = nodes.size();
            nodes.add(headB);
            if (size == nodes.size()) return headB;
            else headB = headB.next;
        }
        return null;
    }

    /**
     * leetcode 234. 回文链表
     *
     * @param head
     * @return
     */
    public static boolean isPalindrome(ListNode head) {
        List<Integer> vals = new ArrayList<>();

        // 将链表的值复制到list中
        ListNode currentNode = head;
        while (currentNode != null) {
            vals.add(currentNode.val);
            currentNode = currentNode.next;
        }

        // 使用双指针判断是否回文
        int front = 0;
        int back = vals.size() - 1;
        while (front < back) {
            if (!vals.get(front).equals(vals.get(back))) {
                return false;
            }
            front++;
            back--;
        }
        return true;
    }

    public static void main(String[] args) {
        ListNode n1 = new ListNode(2, null);
        ListNode n2 = new ListNode(3, n1);
        ListNode n3 = new ListNode(6, n2);
        ListNode n4 = new ListNode(6, n3);
        ListNode n5 = new ListNode(7, n4);

        // 6 > 3 > 2 > null
        System.out.println(C2.reverseList(n3));
        System.out.println(C2.unRepeatList(n5));

        ListNode l1 = new ListNode(1);
        ListNode l2 = new ListNode(2);
        ListNode l3 = new ListNode(3);
        ListNode l4 = new ListNode(4);
        ListNode l5 = new ListNode(5);


        System.out.println("--------------getIntersectionNode--------------");
        System.out.println(getIntersectionNode(ListNode.buildSequenceList(l1, l2, l3, l4), ListNode.buildSequenceList(l2, l3, l4, l5)));
        System.out.println("--------------isPalindrome--------------");
        System.out.println(isPalindrome(ListNode.buildSequenceList(1, 2, 2, 1)));
        System.out.println(isPalindrome(ListNode.buildSequenceList(1, 2, 3)));
    }
}

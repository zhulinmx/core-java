package algorithm.structure;


import java.util.*;

public class ListSolution1 {

    /**
     * leetcode 21. 合并两个有序链表
     * 合并两个有序链表
     *
     * 递归
     */
    public static ListNode mergeTwoLists(ListNode list1, ListNode list2) {
        ListNode head;

        if (null == list1) return list2;
        if (null == list2) return list1;

        if (list1.val <= list2.val) {
            head = list1;
            head.next = mergeTwoLists(list1.next, list2);
        } else {
            head = list2;
            head.next = mergeTwoLists(list1, list2.next);
        }

        return head;
    }

    /**
     * 判断链表有没有环
     * 快慢链表 O(1)
     */
    public static boolean hasCycle(ListNode head) {

        if(head == null || head.getNext() == null)
            return  false;

        ListNode fast = head;
        ListNode slow = head;
        while (fast != null && fast.getNext() != null) {
            fast = fast.getNext().getNext();
            slow = slow.getNext();

            if (fast == slow)
                return true;

        }

        return false;
    }

    /**
     * 142. 环形链表 II
     * 找链表开始入环的第一个节点
     *
     * 解法1：利用set特性
     *
     * @param head
     * @return
     */
    public static ListNode detectCycle(ListNode head) {
        ListNode curr = head;
        Set<ListNode> set = new HashSet<>();
        while (curr != null) {
            int size = set.size();
            set.add(curr);
            if (set.size() == size)
                return curr;
            curr = curr.next;
        }

        return curr;
    }

    /**
     * 142. 环形链表 II
     * 找链表开始入环的第一个节点
     *
     * 解法2：快慢链表
     * 定义快慢两个指针，相遇后（环中相汇点）将快指针指向pHead 然后一起走，每次往后挪一位，相遇的节点即为所求。
     * 详细分析：相遇即slow==fast时，fast所经过节点数为2x, slow所经过节点数为x, 设环中有n个节点, fast比slow多走一圈有2x=n+x; n=x;
     * 可以看出slow实际走了一个环的步数，再让fast指向链表头部，slow位置不变，每次走一步直到slow==fast; 此时slow指向环的入口。
     *
     * @param head
     * @return
     */
    public static ListNode detectCycle2(ListNode head) {
        if (head == null || head.next == null)
            return null;
        ListNode slow = head;
        ListNode fast = head;

        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
            if (slow == fast) {
                fast = head;
                while (fast != slow) {
                    fast = fast.next;
                    slow = slow.next;
                }
                if (slow == fast)
                    return slow;
            }
        }
        return null;
    }

    /**
     * leetcode 19. 删除链表的倒数第 N 个结点
     *
     * @param head
     * @param n
     * @return 链表的头节点
     */
    public static ListNode removeNthFromEnd(ListNode head, int n) {
        if (n < 1) return head;
        List<ListNode> list = new ArrayList<>();
        ListNode curr = head;
        while (curr != null) {
            list.add(curr);
            curr = curr.next;
        }
        if (n > list.size()) return head;
        if (n == list.size()) return head.next;

        ListNode pre = list.get(list.size() - n - 1);
        ListNode node = list.get(list.size() - n);
        pre.next = node.next;

        return head;
    }

    /**
     * leetcode 148. 排序链表
     * 给你链表的头结点 head ，请将其按 升序 排列并返回
     *
     * @param head
     * @return 排序后的链表
     */
    public ListNode sortList(ListNode head) {
        // 1、递归结束条件
        if (head == null || head.next == null) {
            return head;
        }

        // 2、找到链表中间节点并断开链表 & 递归下探
        ListNode midNode = middleNode(head);
        ListNode rightHead = midNode.next;
        midNode.next = null;

        ListNode left = sortList(head);
        ListNode right = sortList(rightHead);

        // 3、当前层业务操作（合并有序链表）
        return mergeTwoLists(left, right);
    }

    /**
     * leetcode 876. 链表的中间结点
     *
     * @param head
     * @return
     */
    private ListNode middleNode(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode slow = head;
        ListNode fast = head;

        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }

        return slow;
    }

    /**
     * leetcode 24. 两两交换链表中的节点
     *
     * @param head
     * @return 交换后链表的头节点
     */
    public static ListNode swapPairs(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode phead = head.next;
        ListNode curr = head;
        ListNode pre = null;

        while (curr != null && curr.next != null) {
            ListNode tmp = curr.next;
            ListNode nextCurr = curr.next.next;
            if (pre != null) pre.next = tmp;
            tmp.next = curr;
            curr.next = nextCurr;
            pre = curr;
            curr = nextCurr;
        }

        return phead;
    }


    /**
     * leetcode 23. 合并 K 个升序链表
     * 给你一个链表数组，每个链表都已经按升序排列。
     * 请你将所有链表合并到一个升序链表中，返回合并后的链表。
     *
     * @param lists
     * @return
     */
    public static ListNode mergeKLists(ListNode[] lists) {
        if (lists == null || lists.length == 0) return null;

        /*
        // 最简单的方法：依次两两merge，时间复杂度高
        ListNode root = lists[0];
        for (int i = 1; i < lists.length; i++) {
            root = mergeTwoLists(root, lists[i]);
        }
        return root;
        */

        // 优先队列：按照值从小到大排序
        PriorityQueue<ListNode> queue = new PriorityQueue<>((o1, o2) -> o1.val - o2.val);
        ListNode root = new ListNode(-1); //排序后的链表的头节点
        ListNode l1 = root;

        for (ListNode i : lists)    //将链表数组中每个链表加入队列中
            if (i != null) queue.add(i);

        while (!queue.isEmpty()) {
            //弹出优先队列最小值（即最小链表）加入root
            ListNode min = queue.poll();
            l1.next = min;
            //遍历下一个节点
            l1 = l1.next;
            //如果该链表不为空，将最小链表的下一个节点加入优先队列
            if(min.next != null) queue.add(min.next);
        }

        return root.next;
    }


    public static void main(String[] args) {
        ListNode n1 = new ListNode(1);
        ListNode n2 = new ListNode(2);
        ListNode n3 = new ListNode(3);
        ListNode n4 = new ListNode(4);
        ListNode n5 = new ListNode(5);
        ListNode n6 = new ListNode(6);

        ListNode list1 = ListNode.buildSequenceList(n2, n1, n3, n1);
        ListNode list2 = ListNode.buildSequenceList(n4, n5, n6);

        System.out.println("-----------------Cycle List---------------");
        System.out.println(hasCycle(list1));
        System.out.println(hasCycle(list2));

        System.out.println(detectCycle(list1));
        System.out.println(detectCycle2(list1));

        System.out.println("-----------------mergeTwoLists---------------");
        System.out.println(mergeTwoLists(ListNode.buildSequenceList(1, 3, 5), ListNode.buildSequenceList(2, 4, 6)));

        System.out.println("-----------------removeNthFromEnd---------------");
        ListNode ll1 = ListNode.buildSequenceList(1, 2, 3, 4, 5);
        System.out.println(removeNthFromEnd(ll1, 1));

        System.out.println("-----------------swapPairs---------------");
        System.out.println(swapPairs(ListNode.buildSequenceList(1, 2, 3, 4, 5)));
        System.out.println(swapPairs(ListNode.buildSequenceList(1)));
        System.out.println(swapPairs(ListNode.buildSequenceList(1, 2)));
        System.out.println(swapPairs(ListNode.buildSequenceList(1, 2, 3, 4)));

        System.out.println("-----------------mergeKLists---------------");
        System.out.println(mergeKLists(new ListNode[]{ListNode.buildSequenceList(1, 4, 5), ListNode.buildSequenceList(1, 3, 4), ListNode.buildSequenceList(2, 6)}));

    }

}

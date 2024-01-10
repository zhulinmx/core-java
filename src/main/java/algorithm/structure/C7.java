package algorithm.structure;

import java.util.HashMap;

public class C7 {

    /**
     *leetcode 138. 随机链表的复制
     *
     * @param head
     * @return
     */
    public static Node copyRandomList(Node head) {
        if (head == null) {
            return null;
        }
        Node cur = head;
        // 先用一个循环把新旧链表对应的两个结点捆绑在map里
        // K: old  V: new
        HashMap<Node, Node> map = new HashMap<>();
        while (cur != null) {
            map.put(cur, new Node(cur.val));
            cur = cur.next;
        }
        // 然后再用一个循环完成对新链表每个结点的next域和random域的赋值
        cur = head;
        while (cur != null) {
            Node newNode = map.get(cur);
            newNode.next = map.get(cur.next);
            newNode.random = map.get(cur.random);
            cur = cur.next;
        }
        return map.get(head);
    }

    public static void main(String[] args) {
        Node n1 = new Node(1);
        Node n2 = new Node(2);
        n1.next = n2;
        n1.random = n2;
        n2.next = null;
        n2.random = n2;
        copyRandomList(n1);

    }
}

class Node {
    int val;
    Node next;
    Node random;

    public Node(int val) {
        this.val = val;
        this.next = null;
        this.random = null;
    }
}

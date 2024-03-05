package algorithm.structure;


public class ListNode {

    public int val;
    public ListNode next = null;

    public ListNode(int data) {
        this.val = data;
    }

    public ListNode(int data, ListNode next) {
        this.val = data;
        this.next = next;
    }

    /**
     * 构建链表
     * @param datas
     * @return
     */
    public static ListNode buildSequenceList(int ...datas) {
        ListNode head = null;
        ListNode pre = null;
        int i = 0;
        for (int data : datas) {
            if(i == 0) {
                head = new ListNode(data, null);
                pre = head;
            } else {
                ListNode curr = new ListNode(data, null);
                pre.next = curr;
                pre = pre.next;
            }
            i++;
        }
        return head;
    }

    /**
     * 构建链表
     * @param nodes
     * @return
     */
    public static ListNode buildSequenceList(ListNode ...nodes) {
        ListNode head = null;
        ListNode pre = null;
        int i = 0;
        for (ListNode node : nodes) {
            if(i == 0) {
                head = node;
                pre = head;
            } else {
                pre.next = node;
                pre = pre.next;
            }
            i++;
        }
        return head;
    }

    public int getVal() {
        return val;
    }

    public void setVal(int val) {
        this.val = val;
    }

    public ListNode getNext() {
        return next;
    }

    public void setNext(ListNode next) {
        this.next = next;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(this.val);
        ListNode ln = this.next;
        while (ln != null) {
            sb.append(ln.val);
            ln = ln.next;
        }
        return "ListNode{" +
                "data=" + sb.toString() +
                '}';
    }

}

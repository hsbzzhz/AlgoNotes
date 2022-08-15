package 基础数据结构操作;

public class ListNode {
    /**
     * 链表初始化类
     */
    public int val;
    public ListNode next;
    public ListNode() {}
    public ListNode(int val) { this.val = val; }
    public ListNode(int val, ListNode next) { this.val = val; this.next = next; }
}

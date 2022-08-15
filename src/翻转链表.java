import 基础数据结构操作.ListNode;

public class 翻转链表 {
    public ListNode reverseList(ListNode head){
        if(head==null || head.next==null){
            return head;
        }
        ListNode newHead = reverseList(head.next);
        head.next.next = head;
        head.next=null;

        return newHead;
    }
}

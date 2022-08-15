import 基础数据结构操作.ListNode;

public class 删除排序链表中的重复元素 {
    public ListNode deleteDuplicates(ListNode head) {
        if (head == null) {
            return head;
        }

        ListNode dummyHead = head;
        while(dummyHead.next != null){
            if(dummyHead.val == dummyHead.next.val){
                dummyHead.next = dummyHead.next.next;
            }else {
                dummyHead = dummyHead.next;
            }
        }
        return head;
    }


    public static void main(String[] args) {

    }
}

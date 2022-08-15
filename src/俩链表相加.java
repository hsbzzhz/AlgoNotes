import 基础数据结构操作.ListNode;

public class 俩链表相加 {
    /**两个链表相加**/
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode dummy = new ListNode(0);  // 返回节点
        ListNode cur = dummy;  // 实际操作赋值节点
        int carry = 0;
        while (l1 != null || l2 != null) {
            int n1 = l1 != null ? l1.val : 0;  // if l1 != null return l1.val else return 0
            int n2 = l2 != null ? l2.val : 0;
            int sum = n1 + n2 + carry;

            carry = sum / 10;
            sum = sum % 10;
            cur.next = new ListNode(sum);  // 这里为什么是cur.next节点呢
            cur = cur.next;
            // 计算完开始挪动 l1 和 l2 的指针
            if (l1 != null) {
                l1 = l1.next;
            }
            if (l2 != null) {
                l2 = l2.next;
            }
        }
        // 两个链表相加完，如果还有进位，当然这个进位最多就是1
        if (carry > 0) {
            cur.next = new ListNode(carry);
        }
        return dummy.next;
    }

}

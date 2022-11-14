import 基础数据结构操作.ListNode;

import java.io.InputStream;
import java.util.*;
import java.util.concurrent.atomic.AtomicIntegerArray;
import java.util.stream.Collectors;

public class Solution {
    private static AtomicIntegerArray integerArray = new AtomicIntegerArray(new int[]{1,2,3});

    public ListNode reverseList(ListNode head) {
        if (head == null) return head;

        ListNode newHead = reverseList(head.next);
        head.next.next = head;
        head.next = null;

        return newHead;
    }
    public ListNode getKthFromEnd(ListNode head, int k) {
        int i = 0;
        ListNode fast = head, slow = head;
        while (i < k) {
            fast = fast.next;
            i++;
        }

        while (fast.next!=null) {
            fast = fast.next;
            slow = slow.next;
        }
        return slow;
    }
    public static void main(String[] args) {

    }
}

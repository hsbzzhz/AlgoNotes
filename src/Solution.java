import 基础数据结构操作.ListNode;
import 基础数据结构操作.TreeNode;

import java.util.*;
import java.util.stream.Collectors;

public class Solution {
    public ListNode detectCycle(ListNode head) {
        if (head == null) {
            return null;
        }
        ListNode slow = head;
        ListNode fast = head;
        while (fast!=null && fast.next!=null) {
            slow = slow.next;
            fast = fast.next.next;
            if(fast == slow) {
                break;
            }
        }

        slow = head;
        while (slow != fast) {
            fast = fast.next;
            slow = slow.next;
        }
        return slow;
    }

    public ListNode deleteNode(ListNode head, int val) {
        ListNode cur = head;
        while (cur.next !=null) {
            if (cur.next.val == val) {
                System.out.println();
                cur = cur.next.next;
                break;
            }
            cur = cur.next;
        }
        return head;
    }

    public static void main(String[] args) {

    }
}

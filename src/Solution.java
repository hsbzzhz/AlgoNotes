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

    public int[][] insert(int[][] intervals, int[] newInterval) {
        int left = newInterval[0];
        int right = newInterval[1];
        boolean  placed = false;
        List<int[]> res = new ArrayList<>();
        for (int i = 0; i < intervals.length; i++) {
            if (intervals[i][0] > right) {
                if (!placed) {
                    res.add(new int[]{left, right});
                    placed = true;
                }
                res.add(intervals[i]);
            } else if (left > intervals[i][1]) {
                res.add(intervals[i]);
            } else {
                left = Math.min(intervals[i][0], left);
                right = Math.max(intervals[i][1], right);
            }
        }
        if (!placed) {
            res.add(new int[]{left, right});
        }

        return res.toArray(new int[res.size()][]);
    }

    public int networkDelayTime(int[][] times, int n, int k) {
        final int INF = Integer.MAX_VALUE / 2;
        int [][] g = new int[n][n];

        for (int i = 0; i < n; ++i) {
            Arrays.fill(g[i], INF);
        }
        for (int[] t : times) {
            int x = t[0] - 1, y = t[1] - 1;
            g[x][y] = t[2];
        }

        int[] dist = new int[n];
        Arrays.fill(dist, INF);
        dist[k - 1] = 0; //

        boolean[] visited = new boolean[n];

        for (int i = 0; i < n; i++) {
            int cur = -1;
            for (int j =0; j < n; j++) {
                if (!visited[j] && (cur == -1 || dist[j] < dist[cur])) {
                    cur = j;
                }
            }

            visited[cur] = true;
            for (int m = 0; m < n; m++) {
                dist[m] = Math.min(dist[m], dist[cur] + g[cur][m]);
            }
        }
        int ans = Arrays.stream(dist).max().getAsInt();
        return ans == INF ? -1 : ans;

    }

    public static void main(String[] args) {

    }
}

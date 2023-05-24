import 基础数据结构操作.ListNode;
import 基础数据结构操作.TreeNode;
import java.util.*;
public class Solution {
    int res = 0;
    public int getImportance(List<Employee> employees, int id) {
        Employee currentEmp = employees.get(id);
        if (currentEmp.subordinates.isEmpty()) {
            return res;
        }
        res += currentEmp.importance;
        for (Integer subId: currentEmp.subordinates) {
            getImportance(employees, subId);
        }
        return res;
    }

    public static void main(String[] args) {
        new Solution();
    }
}
class Employee {
    public int id;
    public int importance;
    public List<Integer> subordinates;
};

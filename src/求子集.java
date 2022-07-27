import java.util.ArrayList;
import java.util.List;

/*
 * 求子集
 * 比如输入：nums = [1,2,3]
 * 输出 [ [],[1],[2],[3],[1,2],[1,3],[2,3],[1,2,3] ]
 *
 * ref. https://labuladong.gitee.io/algo/1/7/
 */
public class 求子集 {
    List<List<Integer>> res = new ArrayList<>();
    // 记录回溯算法的递归路径
    List<Integer> track = new ArrayList<>();

    public List<List<Integer>> subsets(int[] nums) {
        backtrack(nums, 0);
        return res;
    }

    // 回溯算法核心函数，遍历子集问题的回溯树
    void backtrack(int[] nums, int start) {
        // 前序位置，每个节点的值都是一个子集
        res.add(new ArrayList<>(track));
        // 回溯算法标准框架
        for (int i = start; i < nums.length; i++) {
            // 做选择
            track.add(nums[i]);
            // 通过 start 参数控制树枝的遍历，避免产生重复的子集
            backtrack(nums, i + 1);
            // 撤销选择
            track.remove(track.size()-1);
        }
    }

    public static void main(String[] args) {
        int[] nums = new int[]{1,2,3};
        求子集 demo = new 求子集();
        System.out.println(demo.subsets(nums).toString());
    }
}

import java.util.ArrayList;
import java.util.List;

public class 螺旋矩阵 {
    /**
     * 按圈向内收缩
     * 题目梗概：一个矩阵，要求按照顺时针访问元素
     * ref. https://leetcode.cn/problems/spiral-matrix/solution/xiang-jie-xing-zhuang-jie-fa-fang-xiang-3qmhf/
     */
    private List<Integer> res = new ArrayList<>();
    public List<Integer> spiralOrder(int[][] matrix) {
        int m = matrix.length, n = matrix[0].length;
        calRound(matrix, 0, 0, m-1, n-1);

        System.out.println(res);
        return res;
    }

    public void calRound(int[][] matrix, int x1, int y1, int x2, int y2) {
        if (x2 < x1 || y2 < y1) {  // 递归出口
            return;
        }
        // 额外处理最后一行或一列，因为此时x 或 y已经重合，这一行或列没有在循环体里计算
        // 只有一行时，按「行」遍历
        if (x1 == x2) {
            for (int i = y1; i <= y2; i++) res.add(matrix[x1][i]);
            return;
        }
        // 只有一列时，按「列」遍历
        if (y1 == y2) {
            for (int i = x1; i <= x2; i++) res.add(matrix[i][y2]);
            return;
        }

        /*
        递归结构内的计算步骤
         */
        for(int i = y1; i<y2;i++) res.add(matrix[x1][i]); // 1. 向右
        for(int i = x1; i<x2;i++) res.add(matrix[i][y2]); // 2. 向下
        for(int i = y2; i>y1;i--) res.add(matrix[x2][i]); // 3. 向左
        for(int i = x2; i>x1;i--) res.add(matrix[i][y1]); // 4. 向上

        calRound(matrix, x1+1, y1+1, x2-1,y2-1);    // 向内收缩，递归
    }

    public static void main(String[] args) {
        int[][] matrix = {{1,2,3},{4,5,6},{7,8,9}};
        new 螺旋矩阵().spiralOrder(matrix);
    }
}

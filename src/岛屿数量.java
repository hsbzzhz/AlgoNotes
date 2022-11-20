public class 岛屿数量 {
    /*
     * 200. https://leetcode.cn/problems/number-of-islands/
     * 求岛屿的数量 - dfs
     * 题目：给m*n个网格，其中0 代表海洋，1代表岛屿，求连起来岛屿的数量
     *
     * 详见dfs模板
     */

    public int numIslands(char[][] grid) {
        int count =0;
        for (int i =0; i < grid.length; i++){
            for(int j=0; j < grid[0].length;j++){
                if(grid[i][j]=='1'){
                    // 思路就是：如果碰到岛能一直dfs下去，那么就把走过岛岛变成'2'（visited）
                    dfs(grid, i, j);
                    count++;
                }
            }
        }
        return count;
    }

    public void dfs(char[][] grid, int row, int col){
        // 判断边界
        if(row<0 || row>=grid.length || col < 0 || col >= grid[0].length) {
            return;
        }
        //
        if (grid[row][col] != '1') {
            return;
        }
        // 改成访问过的
        grid[row][col] = '2';

        // 访问上、下、左、右四个相邻结点
        dfs(grid, row - 1, col);
        dfs(grid, row + 1, col);
        dfs(grid, row, col - 1);
        dfs(grid, row, col + 1);
    }

    public static void main(String[] args) {
        // todo 加一点测试用例
    }
}


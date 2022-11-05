public class 岛屿数量 {
    /*
     * 200. https://leetcode.cn/problems/number-of-islands/
     * ref. https://leetcode.cn/problems/number-of-islands/solution/dao-yu-lei-wen-ti-de-tong-yong-jie-fa-dfs-bian-li-/
     */

    // 解体模板
    void dfs(int[][] grid, int row, int col) {
        // 判断 base case
        // 如果坐标 (r, c) 超出了网格范围，直接返回
        if (!inArea(grid, row, col)) {
            return;
        }

        // 如果格子不是岛屿，直接返回
        if (grid[row][col] != 1) {
            return;
        }

        // 标记岛屿，表明已遍历过
        grid[row][col] = 2;

        // 访问上、下、左、右四个相邻结点
        dfs(grid, row - 1, col);
        dfs(grid, row + 1, col);
        dfs(grid, row, col - 1);
        dfs(grid, row, col + 1);
    }

    // 判断坐标 (r, c) 是否在网格中
    boolean inArea(int[][] grid, int r, int c) {
        return 0 <= r && r < grid.length // r在 [0. length) 范围内
                && 0 <= c && c < grid[0].length; // c在 [0. length) 范围内
    }

    /*
     * Solution is here!
     * 求岛屿的数量 - dfs
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
        // 边界改成 ||
        if(row<0 || row>=grid.length || col < 0 || col >= grid[0].length) {
            return;
        }

        if (grid[row][col] != '1') {
            return;
        }

        grid[row][col] = '2';

        // 访问上、下、左、右四个相邻结点
        dfs(grid, row - 1, col);
        dfs(grid, row + 1, col);
        dfs(grid, row, col - 1);
        dfs(grid, row, col + 1);
    }
}


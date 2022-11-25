public class 被围绕的区域 {
    public void numIslands(char[][] board) {
        // 把四周访问一遍，
        int m = board.length;
        int n = board[0].length;
        for (int i =0; i < n; i++) {
            dfs(board, i, 0);
            dfs(board, i, m-1);
        }

        for (int i = 1; i < m - 1; i++) {
            dfs(board, 0, i);
            dfs(board, n-1, i);
        }

        // 遍历整个，把 A 改成 O， 剩下都O就是 X
        for (int i =0; i < m; i++){
            for(int j=0; j < n;j++){
                if (board[i][j] == 'A') {
                    board[i][j] = 'O';
                } else if (board[i][j] == 'O') {
                    board[i][j] = 'X';
                }
            }
        }
    }

    public void dfs(char[][] grid, int row, int col){
        // 从O开始访问，能访问到的，都设成 A
        if(row<0 || row>=grid.length || col < 0 || col >= grid[0].length) {  // 超出边界了，
            return;
        }

        if (grid[row][col] != 'O') {
            return;
        }

        grid[row][col] = 'A';

        // 访问上、下、左、右四个相邻结点
        dfs(grid, row - 1, col);
        dfs(grid, row + 1, col);
        dfs(grid, row, col - 1);
        dfs(grid, row, col + 1);
    }

    public static void main(String[] args) {

    }
}

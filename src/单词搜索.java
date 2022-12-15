public class 单词搜索 {
    /**
     * https://leetcode.cn/problems/word-search/
     * 题目：就是给一个二维数组和一个字符串，判断word是否在这个二维网格中
     * p.s. 解法有点慢，因为没有很好的剪枝
     */

    public boolean exist(char[][] board, String word) {
        int m = board.length;
        int n = board[0].length;
        boolean[][] used = new boolean[m][n];

        for (int i = 0; i < m; i++) { // 需要从每个字母开始都向四周遍历一次
            for (int j = 0; j < n; j++) {
                boolean res = dfs(board, used, i, j, word, 0); // 只需要给一个起始位置
                if (res) { // todo 这里为什么是 if return boolean，而不是直接返回
                    System.out.println(true);
                    return true;
                }
            }
        }

        return false;
    }

    public boolean dfs(char[][] board, boolean[][] visited, int x, int y, String word, int index) {
        if (x<0||x>board.length-1||y<0||y>board[0].length-1) { // 超出边界的情况
            return false;
        }

        if (board[x][y]!=word.charAt(index)) { // 碰到字符不匹配的情况
            return false;
        }

        if (visited[x][y]) { // todo 访问过了，也要false？
            return false;
        }

        if (index == word.length()-1) { // 当所有 word 都匹配成功了
            return true;
        }

        visited[x][y] = true; // 选择
        int[][] directions = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}}; // 向四个方向进行探索
        for (int[] dir : directions) {
            int x1 = x + dir[0], y1 = y + dir[1];
            boolean flag = dfs(board, visited, x1, y1, word, index + 1); // word 也需要向前一步
            if (flag) {
                return true;
            }
        }
        visited[x][y] = false; // 回撤
        return false;
    }

    public static void main(String[] args) {
        char[][] board = {{'A','B','C','E'},{'S','F','C','S'},{'A','D','E','E'}};
        new 单词搜索().exist(board, "ABCCED");
    }
}

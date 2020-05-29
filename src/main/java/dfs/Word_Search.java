package dfs;

public class Word_Search {

    public boolean exist(char[][] board, String word) {

        int row = board.length;
        int col = board[0].length;

        int[][] mark = new int[row][col];

        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                mark[i][j] = 0;
            }
        }

        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                if (dfs(word, 0, board, i, j, mark)) {
                    return true;
                }
            }
        }

        return false;

    }

    /**
     *
     * @param word
     * @param p 要查找的字符
     * @param board
     * @param i 在i， j 位置查找
     * @param j
     * @param mark 标记走过的位置
     * @return 是否找到单词了
     */
    private boolean dfs(String word, int p, char[][] board, int i, int j, int[][] mark) {


        if (board[i][j] == word.charAt(p)) {

            if (p == word.length() - 1) return true;

            mark[i][j] = 1;

            boolean exist = false;
            // 按顺时针方向

            // 向上
            if (i > 0 && mark[i - 1][j] != 1) {
                exist = dfs(word, p + 1, board, i - 1, j, mark);
            }

            if (!exist) {
                if (j < board[0].length - 1 && mark[i][j + 1] != 1) {
                    exist = dfs(word, p + 1, board, i, j + 1, mark);
                }
            }

            if (!exist) {
                if (i < board.length - 1 && mark[i + 1][j] != 1) {
                    exist = dfs(word, p + 1, board, i + 1, j, mark);
                }
            }

            if (!exist) {
                if (j > 0 && mark[i][j - 1] != 1) {
                    exist = dfs(word, p + 1, board, i, j - 1, mark);
                }
            }

            if (!exist) {
                // 说明从该结点出发不行
                mark[i][j] = 0;
                return false;
            } else {
                return true;
            }
        } else {
            return false;
        }
    }

}

package dfs;

import java.util.ArrayList;
import java.util.List;

/**
 * 有多少空其实就是有多少步
 * 每一步都有几个可以填的数
 *
 * 其实也是一个棋盘问题
 */
public class Sudoku_solver {

    public void solveSudoku(char[][] board) {

        Cell first = getNextBlankCell(board);

        List<Character> candidates = generateCandidates(board, first.row, first.col);

        boolean findAnswer = false;
        for (char c : candidates) {
            findAnswer = visit(board, c, first.row, first.col);
            if (findAnswer) break;
        }
    }


    /**
     * 可以想象成一个人拿value去填充board[row][col] find代表有没有找到最终答案， 由调用visit的地方可知，value总是有效的
     * @param board
     * @param value
     * @param row
     * @param col
     * @return 代表有没有找到答案
     */
    private boolean visit(char[][] board,
                          char value,
                          int row,
                          int col) {

        board[row][col] = value;

        //得到下一个要填空的坐标
        Cell nextBlankCell = getNextBlankCell(board);

        if (nextBlankCell != null) {
            //得到所有候选值
            List<Character> candidates = generateCandidates(board, nextBlankCell.row, nextBlankCell.col);

            boolean findAnswer = false;
            for(char c : candidates) {
                findAnswer = visit(board, c, nextBlankCell.row, nextBlankCell.col);

                // 如果找到的答案，就停止试下一个，直接返回
                if (findAnswer) {
                    return true;
                }
            }

            // 走到这里说明试了所有的，都没找到答案，那么就还原
            board[row][col] = '.';
            return false;

        } else {
            // 如果下一个坐标为空，说明填完了，找到了最终答案
            return true;
        }

    }



    private Cell getNextBlankCell(char[][] board) {

        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (board[i][j] == '.') {
                    Cell cell = new Cell();
                    cell.row = i;
                    cell.col = j;
                    return cell;
                }
            }
        }

        return null;
    }


    private List<Character> generateCandidates(char[][] board, int row, int col) {
        List<Character> result = new ArrayList<>();
        for (char c = '1'; c <= '9'; c++) {
            if (isValid(board, row, col, c)) {
                result.add(c);
            }
        }
        return result;
    }


    // 判断能否把c放入到board[row][col]
    private boolean isValid(char[][] board, int row, int col, char c){
        for(int i = 0; i < 9; i++) {
            if(board[i][col] != '.' && board[i][col] == c) return false; //check row
            if(board[row][i] != '.' && board[row][i] == c) return false; //check column
            if(board[3 * (row / 3) + i / 3][ 3 * (col / 3) + i % 3] != '.' &&
                    board[3 * (row / 3) + i / 3][3 * (col / 3) + i % 3] == c) return false; //check 3*3 block
        }
        return true;
    }


    static class Cell {
        int row = -1;
        int col = -1;
    }
}

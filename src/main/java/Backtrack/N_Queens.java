package Backtrack;

import run.elder.Category;
import run.elder.Leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Leetcode(
        title = "N-Queens",
        link = "https://leetcode.com/problems/n-queens/",
        category = Category.ARRAY,
        how2SolveIt = """
                其实就是一步一步的放棋子
                
                一共N步, 每一步有多种选择. 
                
                放完最后一步就找到一个解, 中间过程中, 如果不能放了, 就往回走, 选择另一条路线.
                
                这里最关键的就是, 如何快速确定某个棋子和其它的有没有冲突?
                
                可以考虑染色的方法
                
                这题其实并不复杂, 就是细节有些多
                
                耐心一些, 然后 leetcode 一遍过
                """,
        relatedQuestions = {}
)
public class N_Queens {


    public List<List<String>> solveNQueens(int n) {

        // 不需要横向的, 因为下棋时, 就决定了不会在同一行
        int[] cols = new int[n];                        //
        int[] leftSlash = new int[2 * n - 1];        //左斜对角线
        int[] rightSlash = new int[2 * n - 1];       //右斜对角线

        List<char[]> qipan = new ArrayList<>(n);

        List<List<String>> result = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            qipan.add(new char[n]);
            Arrays.fill(qipan.get(i), '.');
        }

        for (int i = 0; i < n; i++) {
            walk(cols, leftSlash, rightSlash, 0, i, n, qipan, result);
        }

        return result;

    }

    /**
     * 以下四个用于染色
     * @param cols
     * @param leftSlash
     * @param rightSlash
     * @param i 走到i
     * @param j 走到j
     * @param n 棋盘
     * @param qipan, 落子所在的列坐标, 横坐标其实就是index
     */
    private void walk(int[] cols,
                      int[] leftSlash,
                      int[] rightSlash,
                      int i,
                      int j,
                      int n,
                      List<char[]> qipan,
                      List<List<String>> result
    ) {

        // 1. 要在i, j落子, 先判断能不能落子 (就是染色冲不冲突)
        // 2. 不冲突就继续染色, 然后往下走
        // 3. 冲突就不在这个位置落子
        // 注意左右对角线的标记方法, 画个图, 如果确定公式的呢? 就是找同的对角线上的位置坐标共同点
        if (conflict(i, j, n, cols, leftSlash, rightSlash)) {
            // 如果落子位置冲突了, 就不再落子
            return;
        } else {
            // 落子
            qipan.get(i)[j] = 'Q';

            // 如果落了最后一个子, 收集结果
            if (i == (n - 1)) {
                result.add(qipan.stream().map(String::new).collect(Collectors.toList()));
                // 撤销落子
                qipan.get(i)[j] = '.';
            } else {
                // 落的不是最后一个子
                mark(i, j, n, cols, leftSlash, rightSlash);
                for (int k = 0; k < n; k++) {
                    walk(cols, leftSlash, rightSlash, i + 1, k, n, qipan, result);
                }
                qipan.get(i)[j] = '.';
                unmark(i, j, n, cols, leftSlash, rightSlash);
            }
        }
    }

    private boolean conflict(int i, int j, int n, int[] cols, int[] leftSlash, int[] rightSlash) {
        return cols[j] == 1 || leftSlash[i + j] == 1 || rightSlash[i + (n - 1 - j)] == 1;
    }

    private void mark(int i, int j, int n, int[] cols, int[] leftSlash, int[] rightSlash) {
        cols[j] = 1;
        leftSlash[i + j] = 1;
        rightSlash[i + (n - 1 - j)] = 1;
    }

    private void unmark(int i, int j, int n, int[] cols, int[] leftSlash, int[] rightSlash) {
        cols[j] = 0;
        leftSlash[i + j] = 0;
        rightSlash[i + (n - 1 - j)] = 0;
    }






}

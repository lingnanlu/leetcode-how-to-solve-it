package dfs;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 其实依然是棋盘问题
 *
 * 每一步都可能有n多种可能，
 *
 * 最后其实是在一个完整的n叉树中找到符合要求的所有路径
 *
 * 像这种问题就是在所有穷举的结果中，找可能的解
 *
 * 而穷举的过程是分步的，每一步有n多种可能
 *
 */
public class N_Queens {

    public List<List<String>> solveNQueens(int n) {

        List<Integer> path = new ArrayList<>(n);
        List<List<String>> allPaths = new ArrayList<>();

        for (int i = 1; i <= n; i++) {
            visit(path, allPaths, n, i);
        }

        return allPaths;
    }

    private void visit(List<Integer> path, List<List<String>> allPaths, int n, int position) {

        if (canBePlaced(path, position)) {

            path.add(position);

            // 如果是最后一行
            if (path.size() == n) {
                // 将path转化成字符串表示的棋盘
                List<String> realPath = convertToRealPath(path, n);
                allPaths.add(realPath);
            } else {
                for (int i = 1; i <= n; i++) {
                    visit(path, allPaths, n, i);
                }
            }

            // 离开该节点时，就要把加入的路径删除
            path.remove(path.size() - 1);
        }

    }

    private List<String> convertToRealPath(List<Integer> path, int n) {

        List<String> realPath = new ArrayList<>(n);
        char[] charArray = new char[n];
        for (int i : path) {
            Arrays.fill(charArray, '.');
            charArray[i - 1] = 'Q';
            realPath.add(new String(charArray));
        }
        return realPath;
    }

    /**
     * 判断能否放置到第i个位置
     *
     * @param path     已经放置的状态
     * @param position 放置到第i个位置
     * @return
     */
    private boolean canBePlaced(List<Integer> path, int position) {

        if (path.size() == 0) return true;

        // 判断是否在同一列
        for (int i : path) {
            if (i == position) {
                return false;
            }
        }

        // 判断是否处于对角线
        int currentRow = path.size() + 1;
        for (int row = 1; row <= path.size(); row++) {
            if ((currentRow - row) == Math.abs(path.get(row - 1) - position)) {
                return false;
            }
        }

        return true;
    }

    public static void main(String[] args) {
        N_Queens test = new N_Queens();
        test.solveNQueens(4);
    }
}

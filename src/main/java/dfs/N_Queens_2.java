package dfs;

import java.util.ArrayList;
import java.util.List;

public class N_Queens_2 {

    public int totalNQueens(int n) {

        List<Integer> path = new ArrayList<>(n);

        Counter counter = new Counter();
        for (int i = 1; i <= n; i++) {
            visit(path, counter, n, i);
        }

        return counter.value();
    }

    private void visit(List<Integer> path, Counter counter, int n, int position) {

        if (canBePlaced(path, position)) {

            path.add(position);

            // 如果是最后一行
            if (path.size() == n) {
                counter.increase();
            } else {
                for (int i = 1; i <= n; i++) {
                    visit(path, counter, n, i);
                }
            }

            // 离开该节点时，就要把加入的路径删除
            path.remove(path.size() - 1);
        }

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

    static class Counter {
        int count;
        public void increase() {
            count++;
        }

        public int value() {
            return count;
        }
    }

}

package dfs;

import java.util.ArrayList;
import java.util.List;

public class Combination_Sum_3 {

    public List<List<Integer>> combinationSum3(int k, int n) {

        List<Integer> path = new ArrayList<>(k);
        List<List<Integer>> result = new ArrayList<>();

        for(int i = 1; i <= 9; i++) {
            visit(k, 1, n, i, path, result);
        }

        return result;
    }

    private void visit(int k,
                       int layer,
                       int target,
                       int value,
                       List<Integer> path,
                       List<List<Integer>> result) {

        if (layer == k && value == target) {
            path.add(value);
            result.add(new ArrayList<>(path));
            path.remove(path.size() - 1);
            return;
        }

        if (layer == k && value != target) {
            return;
        }

        // 以下为layer < k的情况
        if (value >= target) {
            return;
        }

        path.add(value);
        for (int i = value + 1; i <= 9; i++) {
            visit(k, layer + 1, target - value, i, path, result);
        }
        path.remove(path.size() - 1);

    }
}

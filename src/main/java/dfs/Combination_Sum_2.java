package dfs;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Combination_Sum_2 {

    public List<List<Integer>> combinationSum2(int[] candidates, int target) {

        // 关键点1
        Arrays.sort(candidates);

        List<Integer> collect = new ArrayList<>();
        List<List<Integer>> result = new ArrayList<>();

        for (int i = 0; i < candidates.length; i++) {
            if (i > 0 && candidates[i] == candidates[i - 1]) continue;
            visit(candidates, i, target, collect, result);
        }

        return result;

    }

    private void visit(int[] candidates,
                       int index,
                       int target,
                       List<Integer> collect,
                       List<List<Integer>> results
    ) {
        if (candidates[index] > target) {
            return;

        } else if (candidates[index] == target) {

            collect.add(candidates[index]);
            results.add(new ArrayList<>(collect));
            collect.remove(collect.size() - 1);

        } else {

            collect.add(candidates[index]);

            for (int i = index + 1; i < candidates.length; i++) {

                // 关键点2
                // 关键是这里，排完序后，相同的元素只挑第一个，跳过重复元素
                if (i > index + 1 && candidates[i] == candidates[i - 1]) continue;

                visit(candidates, i, target - candidates[index], collect, results);
            }

            collect.remove(collect.size() - 1);
        }


    }
}

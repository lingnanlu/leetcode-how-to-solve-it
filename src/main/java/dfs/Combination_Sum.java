package dfs;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Combination_Sum {

    public List<List<Integer>> combinationSum(int[] candidates, int target) {


        List<Integer> collect = new ArrayList<>();
        List<List<Integer>> result = new ArrayList<>();

        for (int i = 0; i < candidates.length; i++) {
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

            for (int i = index; i < candidates.length; i++) {


                visit(candidates, i, target - candidates[index], collect, results);
            }

            collect.remove(collect.size() - 1);
        }


    }

}

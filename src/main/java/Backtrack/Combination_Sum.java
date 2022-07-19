package Backtrack;

import run.elder.Category;
import run.elder.Leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Leetcode(
        title = "Combination Sum",
        link = "https://leetcode.com/problems/combination-sum/",
        category = Category.ARRAY,
        how2SolveIt = """
                方法一:
                
                其实也差不多, 关键还是自己先画个图模拟一遍
                
                不同的地方就是一个数本身可以选择多次
                
                这里不知走几步, 一直走, 走到和等于target或大于target.
                
                方法二:
                
                一中 candidates是无序的, 那么, 如果有序的话怎样? 
                
                画个图, 发现可以做更多剪枝了.
                
                启示:
                
                1. 如果数组是无序的, 排序后可能有更高的效率.
                2. 遇到树, 想办法剪枝
                
                """,
        relatedQuestions = {}
)
public class Combination_Sum {

    static class First {
        public List<List<Integer>> combinationSum(int[] candidates, int target) {
            List<List<Integer>> result = new ArrayList<>();
            List<Integer> path = new ArrayList<>();
            for (int i = 0; i < candidates.length ; i++) {
                walk(i, candidates, 0, target, path, result);
            }
            return result;
        }


        /**
         * @param i 要访问的结点索引
         * @param sum 访问结点时, 累加的值
         * @param target 目标值.
         */
        private void walk(int i, int[] candidates , int sum, int target, List<Integer> path, List<List<Integer>> result) {

            sum += candidates[i];
            path.add(candidates[i]);
            // 找到了
            if (sum == target) {
                result.add(new ArrayList<>(path));
            } else if (sum < target) {

                // j = i, 表示还可以包含自身
                for (int j = i; j < candidates.length; j++) {
                    walk(j, candidates, sum, target, path, result);
                }
            } else {
                // sum > target , 不再往下走
                // do nothing
            }
            // 还原
            // 注意sum不需要还原, 因为walk一颗树时, 并没有修改sum, 从walk返回后, sum值不变
            path.remove(path.size() - 1);
        }
    }

    static class Second {

        public List<List<Integer>> combinationSum(int[] candidates, int target) {
            List<List<Integer>> result = new ArrayList<>();
            List<Integer> path = new ArrayList<>();

            Arrays.sort(candidates);

            for (int i = 0; i < candidates.length ; i++) {
                walk(i, candidates, 0, target, path, result);
            }
            return result;
        }


        /**
         * @param i 要访问的结点索引
         * @param sum 访问结点时, 累加的值
         * @param target 目标值.
         *
         * @return 遍历到该结点是不是sum >= target, 如果是, 则不用再遍历后序结点
         */
        private boolean walk(int i, int[] candidates , int sum, int target, List<Integer> path, List<List<Integer>> result) {

            sum += candidates[i];
            path.add(candidates[i]);
            // 找到了
            if (sum == target) {
                result.add(new ArrayList<>(path));
                path.remove(path.size() - 1);
                return true;
            } else if (sum < target) {
                // j = i, 表示还可以包含自身

                boolean sumGreaterOrEqualThenTarget = false;
                for (int j = i; j < candidates.length && !sumGreaterOrEqualThenTarget; j++) {
                    sumGreaterOrEqualThenTarget = walk(j, candidates, sum, target, path, result);
                }

                // 还原
                // 注意sum不需要还原, 因为walk一颗树时, 并没有修改sum, 从walk返回后, sum值不变
                path.remove(path.size() - 1);
                return false;

            } else {
                // sum > target , 不再往下走
                // do nothing
                path.remove(path.size() - 1);
                return true;
                // 如果走到一个i, 使用sum > target, 那么i后面的结点也不用遍历了.
                // 而遍历i后面的结点其实上一层结点控制的, 所以要想办法通知上一层.
            }

        }

    }

}

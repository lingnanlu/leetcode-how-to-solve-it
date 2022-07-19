package Backtrack;

import run.elder.Category;
import run.elder.Leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Leetcode(
        title = "Combination Sum II",
        link = "https://leetcode.com/problems/combination-sum-ii/",
        category = Category.ARRAY,
        how2SolveIt = """
                
                方法一:
                又是一个类似的题, 这题的难点与变化在与
                
                要求结果不重复
                
                最容易想到的, 就是先不管有没有重复, 得到一个集合, 然后集合再去重.
                
                如果不想这样做, 仔细观察一下DFS过程.
                
                如果在DFS过程中, 要求相同的元素不能在同一层, 可不可以? 好像可以哎.
                
                方法二:
                
                方法一中有部分代码有重复了, 如果一处忘记修改, 就很容易出错, 我们看能不能消除这种重复
                
                之所以有这种重复, 是因为我们walk的是一个森林, 那么, 如何把一个森林变成树呢? 其实就是在上加一个虚结点.
                
                要想明白遍历这个虚结点时, 各个参数的值是多少.
                
                https://programmercarl.com/0040.%E7%BB%84%E5%90%88%E6%80%BB%E5%92%8CII.html#%E5%9B%9E%E6%BA%AF%E4%B8%89%E9%83%A8%E6%9B%B2
                
                网上的这个树和你构建的类似, 网上这种构建虽然不需要虚结点, 但其实不太一样, 还是感觉自己构建的直观. 
                """,
        relatedQuestions = {}
)
public class Combination_Sum_II {

    static class First {
        public List<List<Integer>> combinationSum2(int[] candidates, int target) {
            List<List<Integer>> result = new ArrayList<>();
            List<Integer> path = new ArrayList<>();

            Arrays.sort(candidates);

            boolean sumGreaterOrEqualThenTarget = false;
            int j = 0;
            while(j < candidates.length && !sumGreaterOrEqualThenTarget) {
                sumGreaterOrEqualThenTarget = walk(j, candidates, 0, target, path, result);

                int current = candidates[j];
                while (j < candidates.length && candidates[j] == current) {
                    j++;
                }
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

                boolean sumGreaterOrEqualThenTarget = false;

                // j 指向下一个不重复的元素.
                int j = i + 1;
                while(j < candidates.length && !sumGreaterOrEqualThenTarget) {
                    sumGreaterOrEqualThenTarget = walk(j, candidates, sum, target, path, result);

                    int current = candidates[j];
                    while (j < candidates.length && candidates[j] == current) {
                        j++;
                    }

                    // 此时, 要不 j == candidates.length, 要不就是canddates[j] != current 说明j指向的对了.
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

    static class Second {

        public List<List<Integer>> combinationSum2(int[] candidates, int target) {
            List<List<Integer>> result = new ArrayList<>();
            List<Integer> path = new ArrayList<>();

            Arrays.sort(candidates);

            walk(-1, candidates, 0, target, path, result);

            return result;
        }


        private boolean walk(int i, int[] candidates , int sum, int target, List<Integer> path, List<List<Integer>> result) {

            if (i == -1) {
               // do nothing
                // 虚结点并不用来访问, 其存在的唯一目的就是要进入第一次循环.
            } else {
                sum += candidates[i];
                path.add(candidates[i]);
            }
            // 找到了
            // 题目中限制了target不为0
            if (sum == target) {
                result.add(new ArrayList<>(path));
                path.remove(path.size() - 1);
                return true;
            } else if (sum < target) {


                boolean sumGreaterOrEqualThenTarget = false;

                // j 指向下一个不重复的元素.
                int j = i + 1;
                while(j < candidates.length && !sumGreaterOrEqualThenTarget) {
                    sumGreaterOrEqualThenTarget = walk(j, candidates, sum, target, path, result);

                    int current = candidates[j];
                    while (j < candidates.length && candidates[j] == current) {
                        j++;
                    }

                    // 此时, 要不 j == candidates.length, 要不就是canddates[j] != current 说明j指向的对了.
                }


                // 还原
                // 注意sum不需要还原, 因为walk一颗树时, 并没有修改sum, 从walk返回后, sum值不变
                // 同样, 虚结点不需要还原
                if (i != -1) {
                    path.remove(path.size() - 1);
                }
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

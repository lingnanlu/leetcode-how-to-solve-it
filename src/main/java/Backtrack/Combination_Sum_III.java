package Backtrack;

import run.elder.Category;
import run.elder.Leetcode;

import java.util.ArrayList;
import java.util.List;

@Leetcode(
        title = "Combination Sum III",
        link = "https://leetcode.com/problems/combination-sum-iii/",
        category = Category.ARRAY,
        how2SolveIt = """
                
                选择k个数, 要求
                
                1. 它们之间的和是n
                2. 它们之间互不相同
                3. 每个数的值 在[1, 9]
                
                假设k = 2, 可以使用两层循环
                k = 3, 使用三层.
                
                由此可知, 还是不能使用循环.
                
                选择k个数, 就是k步, 每一步又有多个候选集, 那么, 又想到了树.
                """,
        relatedQuestions = {}
)
public class Combination_Sum_III {

    public List<List<Integer>> combinationSum3(int k, int n) {
        List<List<Integer>> result = new ArrayList<>();
        List<Integer> path = new ArrayList<>();
        for (int i = 1; i <= 9 ; i++) {
            walk(i, k, 0, n, path, result);
        }
        return result;
    }

    /**
     * @param i 要访问的结点值 [1, 9[
     * @param k 访问结点时, 剩余的选择个数
     * @param sum 访问结点时, 累加的值
     * @param n 目标值.
     */
    private void walk(int i, int k, int sum, int n, List<Integer> path, List<List<Integer>> result) {

        sum += i;
        path.add(i);
        // 访问结点时, 还剩余最后一个, 说明该结点就是最后一个.
        if (k == 1) {
            if (n == sum) {
                result.add(new ArrayList<>(path));
            }
        } else {
            // sum >= n 就不用往下走了.
            if (sum < n) {
                for (int j = i + 1; j <= 9; j++) {
                    walk(j, k - 1, sum, n, path, result);
                }
            }

        }
        // 还原
        // 注意sum不需要还原, 因为walk一颗树时, 并没有修改sum, 从walk返回后, sum值不变
        path.remove(path.size() - 1);
    }
}

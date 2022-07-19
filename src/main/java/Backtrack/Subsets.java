package Backtrack;

import run.elder.Category;
import run.elder.Leetcode;

import java.util.ArrayList;
import java.util.List;

@Leetcode(
        title = "Subsets",
        link = "https://leetcode.com/problems/subsets/",
        category = Category.ARRAY,
        how2SolveIt = """
                
                返回一个集合的子集.
                
                对于每一个元素, 都有先或不先两种.
                
                所以, 可以看出. 这是一个n步, 每一步有两种情况的问题. 选择或不选择
                
                老样子, 还是要明确, 结点的含义是什么.
           
                """,
        relatedQuestions = {}
)
public class Subsets {

    public List<List<Integer>> subsets(int[] nums) {

        if (nums.length == 0) {
            return new ArrayList<>();
        }

        List<Integer> path = new ArrayList<>();
        List<List<Integer>> result = new ArrayList<>();

        walk(nums, 0, true, path, result);
        walk(nums, 0, false, path, result);

        return result;

    }

    /**
     *
     * @param nums
     * @param i 走到第i个元素
     * @param select 选择该元素或不选择该元素, 两种情况
     * @param path 记录路径
     * @param result 记录结果.
     */
    private void walk(int[] nums, int i, boolean select, List<Integer> path, List<List<Integer>> result) {

        //
        if (select) {
            path.add(nums[i]);
        }

        // 如果是最后一个结点
        if (i == nums.length - 1) {
            result.add(new ArrayList<>(path));
        } else {
            walk(nums, i + 1, true, path, result);
            walk(nums, i + 1, false, path, result);
        }

        if (select) {
            path.remove(path.size() - 1);
        }

    }
}



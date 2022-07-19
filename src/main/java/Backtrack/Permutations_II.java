package Backtrack;

import run.elder.Category;
import run.elder.Leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Leetcode(
        title = "Permutations II",
        link = "https://leetcode.com/problems/permutations-ii/",
        category = Category.ARRAY,
        how2SolveIt = """
               
               变化的地方就是多了重复元素.
               
               同样在每一层的时候, 去重
              
                """,
        relatedQuestions = {}
)
public class Permutations_II {

    public List<List<Integer>> permuteUnique(int[] nums) {

        if (nums.length == 0) {
            return new ArrayList<>();
        }

        List<Integer> path = new ArrayList<>();
        List<List<Integer>> result = new ArrayList<>();

        int[] selected = new int[21];
        int[] used = new int[nums.length];
        for (int i = 0; i < nums.length; i++) {
            if (selected[nums[i] + 10] == 0) {
                selected[nums[i] + 10] = 1;
                walk(nums, i, path, result, used);

            }
        }

        return result;

    }

    /**
     *
     * @param nums
     * @param i
     * @param path
     * @param result
     * @param used 标记i个元素是否选择过
     */
    private void walk(int[] nums, int i, List<Integer> path, List<List<Integer>> result, int[] used) {

        path.add(nums[i]);
        if (path.size() == nums.length) {
            result.add(new ArrayList<>(path));
            path.remove(path.size() - 1);
        } else {
            // mark as used
            used[i] = 1;

            int[] selected = new int[21];
            for (int j = 0; j < nums.length; j++) {

                // 选择剩余的, 且没有重复的.
                if (used[j] == 0) {
                    if (selected[nums[j] + 10] == 0) {
                        selected[nums[j] + 10] = 1;
                        walk(nums, j, path, result, used);
                    }
                }
            }

            used[i] = 0;
            path.remove(path.size() - 1);
        }
    }
}

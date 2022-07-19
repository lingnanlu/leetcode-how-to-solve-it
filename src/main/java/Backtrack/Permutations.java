package Backtrack;

import run.elder.Category;
import run.elder.Leetcode;

import java.util.ArrayList;
import java.util.List;

@Leetcode(
        title = "Permutations",
        link = "https://leetcode.com/problems/permutations/",
        category = Category.ARRAY,
        how2SolveIt = """
               
               方法一: DFS
               全排列和组合的区别就是, 顺序不同, 就是不同排列
               
               方法二: 递归
               
               在画图过程中, 感觉有点递归的意思
              
                """,
        relatedQuestions = {}
)
public class Permutations {

    static class First {
        public List<List<Integer>> permute(int[] nums) {
            if (nums.length == 0) {
                return new ArrayList<>();
            }

            List<Integer> path = new ArrayList<>();
            List<List<Integer>> result = new ArrayList<>();

            int[] used = new int[nums.length];
            for (int i = 0; i < nums.length; i++) {
                walk(nums, i, path, result, used);
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
                for (int j = 0; j < nums.length; j++) {
                    if (used[j] == 0) {
                        walk(nums, j, path, result, used);
                    }
                }

                used[i] = 0;
                path.remove(path.size() - 1);
            }
        }
    }

    static class Second {

        public List<List<Integer>> permute(int[] nums) {
            return null;
        }

    }





}

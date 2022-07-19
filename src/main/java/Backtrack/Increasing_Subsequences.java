package Backtrack;

import run.elder.Category;
import run.elder.Leetcode;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Leetcode(
        title = "Increasing Subsequences",
        link = "https://leetcode.com/problems/increasing-subsequences/",
        category = Category.ARRAY,
        how2SolveIt = """
                
                依然是一个多步问题, 这个多步问题可以有多种看法, 从而形成不同的思路 
                
                假设一共有n个元素
                
                方法一:
                
                1. 一共有n步, 每一步选一个元素
                2. 每一步有选或不选两种.
                
                这种分法理论可行, 但因为题目又要求不能有重复的, 感觉太麻烦了, 所以换一种划分方法
                
                方法二:
                
                1. 第一步选择某个start元素, 有n种可能
                2. 第二步从start后面元素中选择, 最多 n -1种可能
                3. 重复一, 二步, 直到没有元素可选
                
                这种方法比较好, 可以在同一层方便去重复
                
                启示:
                
                1. 其实分步, 分情况的角度可以有多种, 有的简单, 有的复杂一些
                如果你的划分方法复杂了, 可以试着换一种方式.
                
                2. 题目中, 每个元素的值在 [-100, 100]之间, 所以可以使用数组来标记某个值是否使用过
                这也是仔细分析数据的一个例子.
                """,
        relatedQuestions = {}
)
public class Increasing_Subsequences {

    static class First {
        public List<List<Integer>> findSubsequences(int[] nums) {
            return null;
        }


        /**
         *
         * @param nums
         * @param i 要选择的每i个元素,
         * @param select 是否要选择
         * @param path
         * @param result
         */
        private void walk(int[] nums, int i, boolean select, List<Integer> path, List<List<Integer>> result) {

            if (i == nums.length) {
                if (path.size() >= 2) {
                    result.add(new ArrayList<>(path));
                }
            } else {

                if (select) {
                    path.add(nums[i]);
                }

                // 找下一个比它小的
                int p = i + 1;
                while(p != nums.length && nums[p] < nums[i]) {
                    p++;
                }

                walk(nums, p, true, path, result);
                walk(nums, p, false, path, result);

                if (select) {
                    path.remove(path.size() - 1);
                }
            }
        }
    }

    static class Second {
        public List<List<Integer>> findSubsequences(int[] nums) {

            if (nums.length == 0) {
                return new ArrayList<>();
            }

            List<Integer> path = new ArrayList<>();
            List<List<Integer>> result = new ArrayList<>();


            Set<Integer> set = new HashSet<>();
            for (int j = 0; j < nums.length; j++) {
                if (!set.contains(nums[j])) {
                    set.add(nums[j]);
                    walk(nums, j, path, result);
                }
            }

            return result;

        }


        private void walk(int[] nums, int i, List<Integer> path, List<List<Integer>> result) {

            // 上层控制的, 该结点肯定是满足要求的结点, 所以一进来就可以添加进去.
            path.add(nums[i]);
            if (path.size() >= 2) {
                result.add(new ArrayList<>(path));
            }

            // 往下层走. 要求
            // 1. 下层不能出现重复元素, 因为不能排序, 所以使用一个set来记录.
            // 2. 下层的元素要 >= nums[i]

            Set<Integer> set = new HashSet<>();
            for (int j = i + 1; j < nums.length; j++) {
                if (nums[j] < nums[i]) {
                    continue;
                } else {
                    if (!set.contains(nums[j])) {
                        set.add(nums[j]);
                        walk(nums, j, path, result);
                    } else {
                        // skip
                    }
                }

            }

            path.remove(path.size() - 1);


        }

    }


}

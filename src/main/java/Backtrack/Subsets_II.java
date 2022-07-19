package Backtrack;

import run.elder.Category;
import run.elder.Leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Leetcode(
        title = "Subsets II",
        link = "https://leetcode.com/problems/subsets-ii/",
        category = Category.ARRAY,
        how2SolveIt = """
                
                各子集一类似, 只是多了重复元素
                
                方法一:
                
                往result中添加时, 判断result中是否已经有了, 有了就不添加.
                但这种判断是否已经有了的方法感觉太慢了.
                
                方法二:
                
                想办法剪枝, 那么, 怎么剪呢? 
                
                我们审视一下, 如果两个元素相同, 在DFS过程中, 是有什么识别特征的.
                
                如, 有[3, 3]
                
                对于每一种, 有选与不选两种情况, 总共有四种情况, 那么, [0, 3]是重复的, 所以一共有
                
                [0, 0] 选0个
                [0, 3] 选一个
                [3, 3] 选两个
                
                一共3种
                
                而对于 [3, 3, 3]的话, 有
                
                [0, 0, 0] 选0个
                [0, 0, 3] 选1个
                [0, 3, 3] 选2个
                [3, 3, 3] 选3个
                
                一共四种
                
                归纳可知, 对于重复元素, 如果有n个,其实, 最终有n + 1种情况, (如何一个1元素没有重复, 有2种情况可看成特例)
                
                由以上特例, 我们可以看出, 对于重复元素, 可以看成一个整体, 然后, 这个整体有 n + 1种情况.
                
                这同样符合, 多步, 多选项的特征.
                
                于是, 可以写代码了.
                
                启示:
                
                无论什么问题, 如何可以抽象到
                
                1. 多步
                2. 每一步多种选择
                
                就可以正确的写出来
                
                子集一是子集二的特殊化, 所以, 把子集二的解放到子集一是同样适用的.
                
                """,
        relatedQuestions = {}
)
public class Subsets_II {

    static class Second {
        public List<List<Integer>> subsetsWithDup(int[] nums) {
            if (nums.length == 0) {
                return new ArrayList<>();
            }

            Arrays.sort(nums);
            List<Integer> path = new ArrayList<>();
            List<List<Integer>> result = new ArrayList<>();

            // 得到下一个i和p
            int i = 0;      // 下一个与当前不同的元素index

            int q = i;
            while (q != nums.length && nums[q] == nums[i]) {
                q++;
            }
            int n = q - i;

            for (int j = 0; j <= n; j++) {
                walk(nums, i, n, j, path, result);
            }

            return result;
        }



        /**
         *
         * @param nums
         * @param i 走到第i个元素
         * @param p 假如第i个元素有n个重复, p指向它们组成的第p种情况, 第0种为全不选, 最后一种为全选
         * @param total 当前元素的总重复次数
         * @param path 记录路径
         * @param result 记录结果.
         */
        private void walk(int[] nums, int i, int total, int p, List<Integer> path, List<List<Integer>> result) {

            if (i == nums.length) {
                result.add(new ArrayList<>(path));
                return; // do nothing
            }

            // 统计第i个元素一共有几个重复
            // 对于n个重复, walk n + 1种情况
            // 往path中添加p个元素
            for (int j = 0; j < p; j++) {
                path.add(nums[i]);
            }

            // 得到下一个i和p
            int nextI = i + total;      // 下一个与当前不同的元素index

            int q = nextI;
            while (q != nums.length && nums[q] == nums[nextI]) {
                q++;
            }

            // q - nextI为下一个与当前不同元素重复的次数
            int nextTotal = q - nextI;

            for (int j = 0; j <= nextTotal; j++) {
                walk(nums, nextI, nextTotal, j, path, result);
            }

            // 删除后p个元素
            for (int j = 0; j < p; j++) {
                path.remove(path.size() - 1);
            }


        }
    }


}

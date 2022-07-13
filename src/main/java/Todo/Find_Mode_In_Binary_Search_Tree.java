package Todo;

import Tree.TreeNode;
import run.elder.Category;
import run.elder.Leetcode;

import java.util.ArrayList;
import java.util.List;

@Leetcode(
        title = "Find Mode in Binary Search Tree",
        link = "https://leetcode.com/problems/find-mode-in-binary-search-tree/",
        category = Category.TREE,
        how2SolveIt = """
                方法一:
                
                最直观的就是使用一个hashmap来保存统计信息, 使用dfs遍历所有节点, 这个方法没有利用二叉树性质. 而且使用额外空间
                
                方法二:
                
                既然是二叉搜索树, 那么就要利用它的性质. 中序遍历是一个从小到大的数组.
                
                而对于一个从小到大的数组, 我们如何在不使用额外空间的情况下, 找到其most frequency element呢?
                
                比如 
                
                [1, 1, 2, 2, 3, 3, 3, 3, 4, 4, 4, 4]
                
                这里要求出现频率最高, 其实一个类似的问题就是求最大, 两者是类似的, 先写一个处理数组的.
                
                怎么能不利用额外空间来寻找出现频率最大的呢?
                
                
                """,
        relatedQuestions = {}
)
public class Find_Mode_In_Binary_Search_Tree {

    static class First {

    }

    static class Second {
        public int[] findMode(TreeNode root) {
            return null;
        }

        public int[] findMode(int[] nums) {

            // 记录已找到的最大频率
            int maxFrequency = 0;
            int curFrequency = 0;

            List<Integer> result = new ArrayList<>();

            // 统计[1, nums.length) 之前的频率
            for (int i = 1; i < nums.length; i++) {
                if (nums[i] == nums[i - 1]) {
                    curFrequency++;
                } else {
                    // 数字变了,
                }
            }

            return null;

        }
    }
}

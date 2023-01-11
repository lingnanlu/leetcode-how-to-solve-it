package Tree;

import java.util.ArrayList;
import java.util.List;

/**
 * 可以先解决一个简单的问题
 *
 * 在有序数组中(包含重复元素), 判断是否存在两个数, 和为k
 *
 * 最简单的就是穷举所有情况.
 *
 * 但这没有利用上有序这一条件.
 *
 * 另一种方法就是先确定一个数a, 然后在剩余的数中判断 k - a 是不是存在的. 这可以利用二分查找.(这样就利用上了有序性), 或者hash
 *
 * 即然是数组, 能不能有一个O(n)的方案来?
 *
 * 前面方法的本质还是使用两个指针, 但都从一个方向到另一个方向.
 *
 * 能不能利用两个指针, 一个指向开头, 另一个指向结尾, 然后一遍遍历呢?
 *
 * 感觉似乎能行. 但其实这是要证明的.
 *
 * 穷举是把所有的解空间可能的解都试了一遍, 而这种夹逼其实是忽略了很多的解的.
 *
 * 所以可以使用夹逼, 先转换成数组, 再夹逼
 *
 * 方法二:
 *
 * 方法一是先转化成数组, 能不能直接在树上进行夹逼?
 *
 * 其实要想在树上夹逼就是解决两个问题
 *
 * 1. 找到头结点.
 * 2. 找到尾结点.
 * 3. 想办法找头和尾的下一个结点.
 *
 * 这考察的是自己对树操作的熟练度
 *
 * 方法三:
 * https://leetcode.cn/problems/two-sum-iv-input-is-a-bst/solution/by-ac_oier-zr4o/
 * 这个里面的递归搜索, 我感觉并不直观, 虽然代码短, 但我感觉自己想不出来.
 */
public class Two_Sum_IV {

    static class First {
        public boolean findTarget(TreeNode root, int k) {

            List<Integer> sorted = new ArrayList<>();
            dfs(root, sorted);

            int i = 0, j = sorted.size() - 1;

            while (i != j) {
                int sum = sorted.get(i) + sorted.get(j);
                if (sum == k) {
                    return true;
                } else if (sum > k) {
                    j--;
                } else {
                    i++;
                }
            }

            return false;
        }

        private void dfs(TreeNode node, List<Integer> sorted) {
            if (node == null) return;
            dfs(node.left, sorted);
            sorted.add(node.val);
            dfs(node.right, sorted);
        }
    }

    static class Second {
        public boolean findTarget(TreeNode root, int k) {

            // 指向头尾结点
            TreeNode left = null;
            TreeNode right = null;

            return true;
        }

        private TreeNode getNext(TreeNode root, TreeNode p) {
            return null;
        }

        private TreeNode getPrevious(TreeNode root, TreeNode p) {
            return null;
        }
    }

}

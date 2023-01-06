package Tree;

/**
 * 方法一:
 * 众数, 显然就是遍历一遍, 然后统计个数, 再找到出现次数最多的.
 *
 * 方法二:
 * 题目要求不使用额外空间, 怎么做呢? 看看条件, 有哪些是已知, 但我们没有利用上的呢?
 * 方法一其实并没有利用二叉搜索树的性质. 任意树都可以利用方法一的方法.
 * 这题已知的特殊的地方就是这个二叉搜索树. 想想怎么利用它.
 *
 * 另外, 好像避免不了统计每个数出现的次数, 但又不能使用额外空间, 这个次数要放在哪里呢?
 *
 * 这里唯一可用的空间就是二叉树本身了.
 */
public class Find_Mode_In_Binary_Search_Tree {
    static class First {
        public int[] findMode(TreeNode root) {
            return null;
        }
    }
}

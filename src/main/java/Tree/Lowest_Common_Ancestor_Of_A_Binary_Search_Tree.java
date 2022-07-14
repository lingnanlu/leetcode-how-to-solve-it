package Tree;

import run.elder.Category;
import run.elder.Leetcode;

@Leetcode(
        title = "Lowest Common Ancestor of a Binary Search Tree",
        link = "https://leetcode.com/problems/lowest-common-ancestor-of-a-binary-search-tree/",
        category = Category.TREE,
        how2SolveIt = """
                二叉搜索树也是一种特殊的二叉树, 使用一般化的找公共祖先的方法是没问题的
                但, 这样就没有利用二叉搜索树的性质了.
                
                我们能不能利用其性质?
                
                我们现在已知
                
                1. 两个结点
                2. 树是一个二叉搜索树
                
                求
                
                一个满足要求的结点
                
                其实, 这就是一个搜索问题, 在一堆候选集中, 找出这样一个结点, 它满足
                
                1. 它是两个已知节点的祖先
                2. 它离两者最近.
                
                如果找到满足以上两个条件的结点, 那么, 就是找到了解.
                
                但, 这两个条件并不好描述, 但我们现在已知, 其是一个二叉搜索树.
                
                能不能利用这个已知, 将以上的两个条件转化成一种容易描述的
                
                如果在一个二叉搜索树中, 一个结点是另两个结点的祖先, 那么它一定满足
                
                1. 它的值处于两者之间.
                2. 如果一个结点的值在这两者之间, 但不是p或q, 那么小的一定在其左边, 大的一定在其右边
                3. 如果一个结点是p或q, 由搜索过程可知, p或q就是公共组先(即另一个一定是其子孙, 如果不是, 说明另一个在旁支, 而搜索过程中,
                是肯定先搜索到其祖先的, 所以一定不在旁支)
                """,
        relatedQuestions = {}
)
public class Lowest_Common_Ancestor_Of_A_Binary_Search_Tree {

    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        TreeNode node = root;

        // 这里的逻辑就是遍历树, 直到找到某个满足条件的结点为止
        while (node != null && (!between(node, p, q) && node.val != p.val && node.val != q.val)) {
            if (greater(node, p, q)) {
                node = node.left;
            } else {
                node = node.right;
            }
        }

        // 由不变式
        // node 要么 等于null
        // 要么就是 between(node, p, q) || node == p || node == q
        return node;

    }

    private boolean between(TreeNode node, TreeNode p, TreeNode q) {
        return Math.min(p.val, q.val) < node.val && node.val < Math.max(p.val, q.val);
    }

    private boolean greater(TreeNode node, TreeNode p, TreeNode q) {
        return node.val > Math.max(p.val, q.val);
    }


}

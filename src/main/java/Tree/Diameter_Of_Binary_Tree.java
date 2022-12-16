package Tree;

import run.elder.Category;
import run.elder.Leetcode;

@Leetcode(
        title = "Diameter of Binary Tree",
        link = "https://leetcode.cn/problems/diameter-of-binary-tree/",
        category = Category.TREE,
        how2SolveIt = """
               
               这题一看, 没什么思路, 就算不写代码, 使用人工的方式来找, 也不是一眼就能明白的.
               
               这里的关键就是明白
               
               > 树中任意两点的最远距离
               
               直接从这个表述来看, 有两个难点
               
               1. 如何穷举任意
               2. 两点间的距离如何计算
               
               所以, 我们要想法把这个问题变换一下, 变成
               
               > 所谓的两点的最远距离, 其实就是xxxx
               
               而xxxx是很好求的.
               
               我们看题中的例子, 这里看一下两点间距离怎么计算的, 
               
               4, 3的距离 = 4先到公共祖先 + 3到公共祖先
               
               而4先公共祖先的距离, 其实就是其深度.
               
               3也一样.
               
               这里好像距离和深度有关? 是不是其实就是一个树的左右子树深度的和? 
               
               这是一个猜测, 而我感觉就是这样的
               
               其实这题就变成了
               
               > 求二叉树的左右子树深度和的最大值.
               
               注: 
               
               这题的关键是把一个问题转换一下, 转换成一种更容易使用求解的方式, 原问题不好解
               另一个考察的就是DFS
               
                
                """,
        relatedQuestions = {}
)
public class Diameter_Of_Binary_Tree {

    int max = 0;
    public int diameterOfBinaryTree(TreeNode root) {
        // 对每一个节点进行遍历, 求其左右子树的深度和
        depth(root);
        return max;
    }

    /**
     * 可以想象使用dfs以某种顺序遍历结点, 每遍历一个结点, 就更新一次max
     */
    private int depth(TreeNode node) {

        if(node == null) return 0;

        int leftDepth = depth(node.left);
        int rightDepth = depth(node.right);

        // 遍历了一个结点后, 更新该结点
        max = Math.max(leftDepth + rightDepth, max);
        return Math.max(leftDepth, rightDepth) + 1;
    }




}

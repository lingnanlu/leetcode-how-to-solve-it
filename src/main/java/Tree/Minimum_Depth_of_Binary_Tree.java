package Tree;

import run.elder.Category;
import run.elder.Leetcode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

@Leetcode(
        title = "Minimum Depth of Binary Tree",
        link = "https://leetcode.com/problems/minimum-depth-of-binary-tree/",
        category = Category.TREE,
        how2SolveIt = """
                方法一: 
                
                仔细观察, 最小深度的层的特点是什么, 那就是第一次出现叶子的层数, 所以依然使用层序遍历
                
                方法二:
                
                最大深度可以使用递归, 那最小深度呢? 看似一样, 其实不同, 如果只有左或只有右子树, 那么最小深度就是左子树或右子树的深度
                
                启示:
                
                对于这个题可以分析两种方式的时间复杂度, 层序遍历, 即直观, 时间复杂度也好, 如果是递归, 要分别计算出所有子树的高度才行.
                而层序遇到第一个叶子结点就可以终止了.
                
                所以, 遇到一个题即可以是递归也可以是迭代时, 要去分析一下时间复杂度
                """,
        relatedQuestions = {}
)
public class Minimum_Depth_of_Binary_Tree {

    static class First {
        public int minDepth(TreeNode root) {
            int depth = 0;
            if (root != null) {

                Queue<TreeNode> queue = new LinkedList<>();

                // queue保存的是当前将要遍历的层的结点
                queue.add(root);

                // 依然是每一次处理一层的情况
                // 不变式
                // 1. queue记录将要遍历的层的结点
                // 2. 记录已遍历的层的深度
                while (queue.size() != 0) {
                    // queue保存的是当前将要遍历的层的结点, count就是该层结点数
                    int count = queue.size();

                    // 以下是遍历一层
                    boolean currentLevelHasLeaf = false;
                    while (count != 0) {
                        TreeNode node = queue.poll();
                        if (node.left != null) {
                            queue.add(node.left);
                        }

                        if (node.right != null) {
                            queue.add(node.right);
                        }

                        if (node.left == null && node.right == null) {
                            currentLevelHasLeaf = true;
                        }
                        count--;
                    }
                    depth++;

                    // 如果当前层有叶子结点, 就不再继续
                    // 有不变式可知, depth就是结果
                    if (currentLevelHasLeaf) {
                        break;
                    }
                }
            }

            return depth;



        }
    }

    static class Second {

        public int minDepth(TreeNode root) {
            if (root == null) {
                return 0;
            }

            // 只有右
            if (root.left == null && root.right != null) {
                return 1 + minDepth(root.right);
            }

            // 只有左
            if (root.left != null && root.right == null) {
                return 1 + minDepth(root.left);
            }

            int leftMinDepth = minDepth(root.left);
            int rightMinDepth = minDepth(root.right);
            return Math.min(leftMinDepth, rightMinDepth) + 1;

        }

    }

}

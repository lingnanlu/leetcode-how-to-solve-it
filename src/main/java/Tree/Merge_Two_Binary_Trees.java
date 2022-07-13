package Tree;

import run.elder.Category;
import run.elder.Leetcode;

@Leetcode(
        title = "Merge Two Binary Trees",
        link = "https://leetcode.com/problems/merge-two-binary-trees/",
        category = Category.TREE,
        how2SolveIt = """
                
                方法一:
                
                依然是同步DFS, 所以可以使用递归来做
                
                想想BFS行不行? 并不那么直观, 所以, 还是使用DFS
                
                这题和那个镜像树有点类似, 都是对两个树进行同步DFS, 但那个题是一个完全二叉树, 不用考虑节点不匹配的问题
                只要考虑对应的值是不是相等.
                
                所以, 那一题可以BFS, DFS, 而这一题还要创建一个树, 所以DFS更直观
                
                这里最关键的是, 如果一个结点为null, 如何合并.
                
                方法二:
                
                不直接copy, 而是传入null
                
                注:
                看leetcode上的解法, 比如当root1不为null, root2为null时, 返回的是root1, 这其实不是一个新的tree
                """,
        relatedQuestions = {}
)
public class Merge_Two_Binary_Trees {

    static class First {
        public TreeNode mergeTrees(TreeNode root1, TreeNode root2) {

            if (root1 == null && root2 == null) return null;

            // 一边为空就copy另一边
            if (root1 == null) {
                return copy(root2);
            }

            if (root2 == null) {
                return copy(root1);
            }

            TreeNode merged = new TreeNode(root1.val + root2.val);

            merged.left = mergeTrees(root1.left, root2.left);
            merged.right = mergeTrees(root1.right, root2.right);
            return merged;
        }

        private boolean isLeaf(TreeNode node) {
            return node.left == null && node.right == null;
        }

        // 复制一颗树
        private TreeNode copy(TreeNode node) {
            if (node == null) return null;

            TreeNode copyed = new TreeNode(node.val);
            copyed.left = copy(node.left);
            copyed.right = copy(node.right);
            return copyed;
        }
    }

    static class Second {
        public TreeNode mergeTrees(TreeNode root1, TreeNode root2) {

            if (root1 == null && root2 == null) return null;

            TreeNode node = new TreeNode((root1 == null ? 0 : root1.val) + (root2 == null ? 0 : root2.val));

            node.left = mergeTrees(root1 == null ? null : root1.left, root2 == null ? null : root2.left);
            node.right = mergeTrees(root1 == null ? null : root1.right, root2 == null ? null : root2.right);
            return node;
        }

    }


}

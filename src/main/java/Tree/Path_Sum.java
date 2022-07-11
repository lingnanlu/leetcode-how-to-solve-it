package Tree;

import run.elder.Category;
import run.elder.Leetcode;

@Leetcode(
        title = "Path Sum",
        link = "https://leetcode.com/problems/path-sum/",
        category = Category.TREE,
        how2SolveIt = """
                还是DFS
                """,
        relatedQuestions = {}
)
public class Path_Sum {

    public boolean hasPathSum(TreeNode root, int targetSum) {
        if (root == null) return false;
        return walk(root, targetSum);
    }

    private boolean walk(TreeNode node, int remainSum) {

        if (isLeaf(node)) {
           return node.val == remainSum;
        } else {
            // 如果往左走找到了, 就直接返回
            if (node.left != null && walk(node.left, remainSum - node.val)) {
                return true;
            }

            // 否则往右走, 返回右边结果
            if (node.right != null) {
                return walk(node.right, remainSum - node.val);
            } else {
                return false;
            }
        }
    }


    private boolean isLeaf(TreeNode node) {
        return node.left == null && node.right == null;
    }
}

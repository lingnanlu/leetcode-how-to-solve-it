package Tree;

import run.elder.Category;
import run.elder.Leetcode;

import java.util.ArrayList;
import java.util.List;

@Leetcode(
        title = "Path Sum II",
        link = "https://leetcode.com/problems/path-sum-ii/",
        category = Category.TREE,
        how2SolveIt = """
                还是DFS, 带有回溯
                """,
        relatedQuestions = {}
)
public class Path_Sum_II {

    public List<List<Integer>> pathSum(TreeNode root, int targetSum) {
        List<List<Integer>> result = new ArrayList<>();
        List<Integer> path = new ArrayList<>();

        if (root != null) {
            walk(root, targetSum, path, result);
        }

        return result;

    }

    private void walk(TreeNode node, int remainSum, List<Integer> currentPath, List<List<Integer>> paths) {
        if (isLeaf(node)) {
            // 说明找到一条路径
            if (remainSum == node.val) {
                currentPath.add(node.val);
                // 注意把路径复制一份, 不要使用引用
                paths.add(new ArrayList<>(currentPath));

                // 不再往下走, 从该结点返回, 要更新currentPath, 删除最后一个
                currentPath.remove(currentPath.size() - 1);
            } else {
                // 到该叶子结点的路径不符合要求, 什么都不做
            }
        } else {
            // 还要往下走
            currentPath.add(node.val);
            // 左子树有内容就往左走
            if (node.left != null) {
                walk(node.left, remainSum - node.val, currentPath, paths);
            }

            if (node.right != null) {
                walk(node.right, remainSum - node.val, currentPath, paths);
            }

            // 从该节点往上走, 要从当前路径中删除该节点
            currentPath.remove(currentPath.size() - 1);
        }
    }


    private boolean isLeaf(TreeNode node) {
        return node.left == null && node.right == null;
    }
}

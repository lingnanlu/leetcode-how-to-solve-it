package Tree;

import run.elder.Category;
import run.elder.Leetcode;

import java.util.ArrayList;
import java.util.List;

@Leetcode(
        title = "Binary Tree Paths",
        link = "https://leetcode.com/problems/binary-tree-paths/",
        category = Category.TREE,
        how2SolveIt = """
                要寻找从根到叶子的所有路径, 那么, 一看就是DFS, 注意walk与visit就ok了
                """,
        relatedQuestions = {}
)
public class Binary_Tree_Paths {


    public List<String> binaryTreePaths(TreeNode root) {

        List<String> paths = new ArrayList<>();
        StringBuilder currentPath = new StringBuilder();

        if (root != null) {
            walk(root, currentPath, paths);
        }

        return paths;
    }

    // walk的过程可以控制不走到null结点, 这样可以简化操作
    private void walk(TreeNode node, StringBuilder currentPath, List<String> paths) {

        visit(node, currentPath, paths);

        // 要向左走, 从左返回时, 除了paths中增加了相关路径之外, currentPath就像没发生过一样
        if (node.left != null) {
            // 决定向左走了, 路径上加一个->表示要向左
            currentPath.append("->");
            walk(node.left, currentPath, paths);
            // 从左子树返回了, currentPath就像没变化过一样
            currentPath.delete(currentPath.length() - 2, currentPath.length());
        }

        // 从左子树返回时
        // paths包含了左子树中, 所有根到叶子结点的路径
        // currentPath应该没有变化
        if (node.right != null) {
            currentPath.append("->");
            walk(node.right, currentPath, paths);
            currentPath.delete(currentPath.length() - 2, currentPath.length());
        }


        // 从右子树返回时, paths包含了所有右子树中叶子结点的路径
        // 此时要从本结点返回, 要从路径中删除它, 因为它已经不在DFS的遍历路径中.

        String strOfVal = String.valueOf(node.val);
        currentPath.delete(currentPath.length() - strOfVal.length(), currentPath.length());
    }

    // walk的过程可以控制不走到null结点, 所以不考虑null
    private void visit(TreeNode node, StringBuilder currentPath, List<String> paths) {
        currentPath.append(node.val);
        if (isLeaf(node)) {
            paths.add(currentPath.toString());
        }
    }

    private boolean isLeaf(TreeNode node) {
        return node.left == null && node.right == null;
    }
}

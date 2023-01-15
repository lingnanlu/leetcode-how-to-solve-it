package Tree;

import java.util.ArrayList;
import java.util.List;

/**
 * 这题步骤不难
 *
 * 1. 找到两者的叶值序列
 * 2. 比较两个叶值序列
 *
 * 关键就是如何找叶值序列.
 *
 * 仔细看了一个, dfs中遇到的叶子的顺序正好满足条件.
 */
public class Leaf_Similar_Trees {

    public boolean leafSimilar(TreeNode root1, TreeNode root2) {
        List<Integer> first = new ArrayList<>();
        List<Integer> second = new ArrayList<>();
        walk(root1, first);
        walk(root2, second);

        if (first.size() != second.size()) return false;

        for (int i = 0; i < first.size(); i++) {
            if (!first.get(i).equals(second.get(i))) {
                return false;
            }
        }

        return true;
    }

    private void walk(TreeNode node, List<Integer> leafs) {

        if (node == null) return;

        if (node.left == null && node.right == null) {
            leafs.add(node.val);
            return;
        }

        walk(node.left, leafs);
        walk(node.right, leafs);
    }
}

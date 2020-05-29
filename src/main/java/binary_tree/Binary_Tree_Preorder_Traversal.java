package binary_tree;

import java.util.ArrayList;
import java.util.List;

public class Binary_Tree_Preorder_Traversal {
    public List<Integer> preorderTraversal(TreeNode root) {

        List<Integer> preorder = new ArrayList<>();

        walk(root, preorder);

        return preorder;
    }

    private void walk(TreeNode node, List<Integer> preorder) {

        if (node == null) return;

        preorder.add(node.val);

        walk(node.left, preorder);
        walk(node.right, preorder);
    }
}

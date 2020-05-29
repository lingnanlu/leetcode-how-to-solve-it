package binary_tree;

import java.util.ArrayList;
import java.util.List;

public class Binary_Tree_Inorder_Traversal {

    public List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> inorder = new ArrayList<>();

        walk(root, inorder);

        return inorder;
    }

    private void walk(TreeNode node, List<Integer> inorder) {

        if (node == null) return;

        walk(node.left, inorder);
        inorder.add(node.val);
        walk(node.right, inorder);
    }
}

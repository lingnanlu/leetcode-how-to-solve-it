package binary_tree;


// r所
public class Symmetric_Tree {

    public boolean isSymmetric(TreeNode root) {
        return root == null || isSymmetricHelper(root.left, root.right);
    }

    // 判断两个树是不是镜像的
    private boolean isSymmetricHelper(TreeNode root1, TreeNode root2) {

        if (root1 == null && root2 == null) return true;
        if (root1 == null || root2 == null) return false;
        return root1.val == root2.val &&
                isSymmetricHelper(root1.left, root2.right) &&
                isSymmetricHelper(root1.right, root2.left);
    }
}

package easy;

public class Symmetric_Tree {

    public boolean isSymmetric(TreeNode root) {

        if(root == null) return true;
        return isMirror(root.left, root.right);
    }

    private boolean isMirror(TreeNode left, TreeNode right) {
        if (left != null && right != null) {
            return isMirror(left.right, right.left) && isMirror(left.left, right.right) && left.val == right.val;
        } else if (left == null && right == null) {
            return true;
        } else {
            return false;
        }
    }
}

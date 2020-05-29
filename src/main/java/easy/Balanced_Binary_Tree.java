package easy;

public class Balanced_Binary_Tree {
    public boolean isBalanced(TreeNode root) {

        if(root == null) return true;

        //左右都是平衡两叉树, 且左右深度不差1
        return  isBalanced(root.left) &&
                isBalanced(root.right) &&
                Math.abs(depth(root.left) - depth(root.right)) <= 1;

    }

    public int depth(TreeNode root) {

        if(root == null) return 0;
        return Math.max(depth(root.left), depth(root.right)) + 1;

    }
}

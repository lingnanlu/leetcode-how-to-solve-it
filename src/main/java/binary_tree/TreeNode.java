package binary_tree;

public class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    TreeNode(int val) {this.val = val;}

    public boolean isLeaf() {
        return left == null && right == null;
    }

}

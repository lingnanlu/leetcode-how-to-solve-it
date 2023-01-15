package Tree;

public class Univalued_Binary_Tree {

    public boolean isUnivalTree(TreeNode root) {
        if (root == null) return false;

        // 要比较的值
        int val = root.val;

        return !walk(root, val);
    }

    // 遍历node, 看有没有和val不一样的节点
    // 返回值为true时, 说明有, 为false时, 说明没有
    private boolean walk(TreeNode node, int val) {

        if (node == null) return false;

        if (node.val != val) return true;

        if (walk(node.left, val)) return true;
        else return walk(node.right, val);
    }
}

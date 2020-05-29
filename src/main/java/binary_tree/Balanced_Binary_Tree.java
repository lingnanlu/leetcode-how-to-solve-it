package binary_tree;

public class Balanced_Binary_Tree {

    // 这种方式不好， 其实每一个节点的height会计算多遍
    // 所以慎用无脑递归
    public boolean isBalanced(TreeNode root) {

        if(root == null) return true;

        return isBalanced(root.left) && isBalanced(root.right) &&
                Math.abs(height(root.left) - height(root.right)) <= 1;
    }

    private int height(TreeNode root) {
        if(root == null) return 0;
        return Math.max(height(root.left), height(root.right)) + 1;
    }

    /*
     第二种方式使用DFS, DFS保证了每个结点只访问一遍, 访问得到的结果就是树的高度
     这里的一个技巧就是， 当发现树不是平衡二叉树时， 就给个标志， 该标志会中止DFS
     -1 表示不是平衡
      */
    private int walk(TreeNode node) {

        if (node == null) return 0;

        int leftHeight = walk(node.left);

        // 如果左边的是非平衡二叉树， 就提前中止遍历
        if (leftHeight == -1) return -1;

        int rightHeight = walk(node.right);
        if (rightHeight == -1) return -1;

        int heightAbs = Math.abs(leftHeight - rightHeight);

        if (heightAbs > 1) return -1;

        return Math.max(leftHeight, rightHeight) + 1;
    }

    public boolean isBalanced2(TreeNode root) {

        if(root == null) return true;

        return walk(root) != -1;
    }
}

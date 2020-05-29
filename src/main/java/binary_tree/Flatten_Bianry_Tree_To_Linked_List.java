package binary_tree;

// 依然是一个DFS问题
public class Flatten_Bianry_Tree_To_Linked_List {

    // 访问完这个结点后， 它已经打平
    public void flatten(TreeNode root) {

        if (root == null) return;

        flatten(root.left);
        flatten(root.right);

        // 此时左子树已经打平， 找到左子树最右边的结点
        TreeNode pre = null;
        TreeNode node = root.left;
        while(node != null) {
            pre = node;
            node = node.right;
        }

        // 此时pre是最右边的结点
        if (pre != null) {
            pre.right = root.right;
            root.right = root.left;
            root.left = null;
        }

        // 如果左子树没有结点， 就什么也不做


    }


}

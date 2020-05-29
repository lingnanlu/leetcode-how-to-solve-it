package binary_tree;

import java.util.LinkedList;
import java.util.Queue;

// 其实可以使用层序遍历， 对每一层的遍历都是交换其左右结点
public class Invert_Binary_Tree {

    public TreeNode invertTree(TreeNode root) {

        Queue<TreeNode> queue = new LinkedList<>();

        if (root != null) queue.offer(root);

        while(!queue.isEmpty()) {
            TreeNode node = queue.poll();
            TreeNode left = node.left;
            TreeNode right = node.right;

            node.left = right;
            node.right = left;

            if (node.left != null) queue.offer(node.left);
            if (node.right != null) queue.offer(node.right);
        }

        return root;

    }


}

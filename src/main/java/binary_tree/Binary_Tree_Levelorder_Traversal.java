package binary_tree;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class Binary_Tree_Levelorder_Traversal {

    public List<List<Integer>> levelOrder(TreeNode root) {

        List<List<Integer>> result = new ArrayList<>();

        Queue<TreeNode> queue = new LinkedList<>(); // 保存将要处理的层的节点

        if (root != null) queue.offer(root);

        // 一层一层的处理
        while(!queue.isEmpty()) {
            List<Integer> level = new ArrayList<>();                // 保存这一层的值
            int size = queue.size();                                // 这一层要处理的结点数
            while(size != 0) {
                TreeNode node = queue.poll();
                level.add(node.val);

                if(node.left != null) queue.offer(node.left);
                if(node.right != null) queue.offer(node.right);

                size--;
            }

            // 此时处理完一层
            result.add(level);
        }

        return result;
    }
}

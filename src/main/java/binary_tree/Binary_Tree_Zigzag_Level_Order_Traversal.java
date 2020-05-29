package binary_tree;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

// 单数层从左往右收集， 双数层从右往左收集
public class Binary_Tree_Zigzag_Level_Order_Traversal {

    public List<List<Integer>> zigzagLevelOrder(TreeNode root) {

        List<List<Integer>> result = new ArrayList<>();

        Queue<TreeNode> queue = new LinkedList<>(); //保存的是下一层要处理的元素

        if (root != null) queue.offer(root);

        boolean collectFromLeft = true;

        // 一层一层的处理
        while(!queue.isEmpty()) {
            LinkedList<Integer> level = new LinkedList<>();                // 保存这一层的值

            int size = queue.size();                                // 这一层要处理的结点数

            // 处理这一层
            while(size != 0) {
                TreeNode node = queue.poll();
                level.add(node.val);

                if(node.left != null) queue.offer(node.left);
                if(node.right != null) queue.offer(node.right);

                size--;
            }

            if (!collectFromLeft) {
                result.add(reverse(level));
            } else {
                result.add(level);
            }

            collectFromLeft = !collectFromLeft;

        }

        return result;
    }

    // 将一个list 反转
    private LinkedList<Integer> reverse(LinkedList<Integer> level) {
        LinkedList<Integer> reversed = new LinkedList<>();
        for (int e : level) {
            reversed.addFirst(e);
        }
        return reversed;
    }
}

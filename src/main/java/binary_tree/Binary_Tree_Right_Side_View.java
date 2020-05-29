package binary_tree;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;


// 其实就是保存每一层的最后一个结点值， 层序遍历
public class Binary_Tree_Right_Side_View {
    public List<Integer> rightSideView(TreeNode root) {

        List<Integer> result = new ArrayList<>();

        Queue<TreeNode> queue = new LinkedList<>(); // 保存将要处理的层的节点

        if (root != null) queue.offer(root);

        // 一层一层的处理
        while(!queue.isEmpty()) {
            int size = queue.size();                                // 这一层要处理的结点数
            while(size != 0) {
                TreeNode node = queue.poll();
                if(node.left != null) queue.offer(node.left);
                if(node.right != null) queue.offer(node.right);
                size--;

                // 说明刚才处理的是最后一个结点, 对最后的结点有一个额外操作
                if (size == 0) {
                    result.add(node.val);
                }
            }
        }

        return result;
    }
}

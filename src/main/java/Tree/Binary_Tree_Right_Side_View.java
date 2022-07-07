package Tree;

import run.elder.Category;
import run.elder.Leetcode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

@Leetcode(
        title = "Binary Tree Right Side View",
        link = "https://leetcode.com/problems/binary-tree-right-side-view/",
        category = Category.TREE,
        how2SolveIt = """
                其实就是找每一层的最后一个节点, 这当然和层序遍历有关了.
                """,
        relatedQuestions = {}
)
public class Binary_Tree_Right_Side_View {

    public List<Integer> rightSideView(TreeNode root) {

        List<Integer> result = new ArrayList<>();

        if (root != null) {

            Queue<TreeNode> queue = new LinkedList<>();

            // queue保存的是当前将要遍历的层的结点
            queue.add(root);

            // 依然是每一次处理一层的情况
            while (queue.size() != 0) {
                List<Integer> list = new ArrayList<>();
                int count = queue.size();

                // 跳过count - 1个结点,只剩下最后一个
                while (count != 1) {
                    TreeNode node = queue.poll();
                    if (node.left != null) {
                        queue.add(node.left);
                    }

                    if (node.right != null) {
                        queue.add(node.right);
                    }
                    count--;
                }

                // 不要忘记最后一个结点也要遍历
                TreeNode last = queue.poll();
                if (last.left != null) {
                    queue.add(last.left);
                }
                if (last.right != null) {
                    queue.add(last.right);
                }

                result.add(last.val);

            }
        }

        return result;

    }
}

package Tree;

import run.elder.Category;
import run.elder.Leetcode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

@Leetcode(
        title = "Symmetric Tree",
        link = "https://leetcode.com/problems/symmetric-tree/",
        category = Category.TREE,
        how2SolveIt = """
                观察一下这种树的特点.
                
                如果一个Tree是Symmetric的, 但它的子树并不Symmetric, 所以, 没有递归的性质.
                
                再看每一层, 其实都是对称的, 所以可以考虑使用层次遍历. 
                """,
        relatedQuestions = {}
)
public class Symmetric_Tree {

    public boolean isSymmetric(TreeNode root) {

        if (root != null) {

            Queue<TreeNode> queue = new LinkedList<>();

            // queue保存的是当前将要遍历的层的结点
            queue.add(root);

            // 依然是每一次处理一层的情况
            while (queue.size() != 0) {
                List<Integer> list = new ArrayList<>();
                int count = queue.size();
                while (count != 0) {
                    TreeNode node = queue.poll();
                    list.add(node.val);
                    if (node.left != null) {
                        queue.add(node.left);
                    }

                    if (node.right != null) {
                        queue.add(node.right);
                    }
                    count--;
                }
                result.add(list);
            }
        }

        return true;


    }
}

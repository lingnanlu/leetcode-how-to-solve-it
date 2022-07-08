package Tree;

import run.elder.Category;
import run.elder.Leetcode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

@Leetcode(
        title = "Find Largest Value in Each Tree Row",
        link = "https://leetcode.com/problems/find-largest-value-in-each-tree-row/",
        category = Category.TREE,
        how2SolveIt = """
                没什么好说的, 层序遍历
                """,
        relatedQuestions = {}
)
public class Find_Largest_Value_In_Each_Tree_Row {

    public List<Integer> largestValues(TreeNode root) {

        List<Integer> result = new ArrayList<>();

        if (root != null) {

            Queue<TreeNode> queue = new LinkedList<>();

            // queue保存的是当前将要遍历的层的结点
            queue.add(root);

            // 依然是每一次处理一层的情况
            while (queue.size() != 0) {
                int max = Integer.MIN_VALUE;
                // queue保存的是当前将要遍历的层的结点, count就是该层结点数
                int count = queue.size();
                while (count != 0) {
                    TreeNode node = queue.poll();
                    max = Math.max(max, node.val);
                    if (node.left != null) {
                        queue.add(node.left);
                    }

                    if (node.right != null) {
                        queue.add(node.right);
                    }
                    count--;
                }
                result.add(max);
            }
        }

        return result;


    }
}

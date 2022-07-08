package Tree;

import run.elder.Category;
import run.elder.Leetcode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

@Leetcode(
        title = "Average of Levels in Binary Tree",
        link = "https://leetcode.com/problems/average-of-levels-in-binary-tree/",
        category = Category.TREE,
        how2SolveIt = """
                没什么好说的
                """,
        relatedQuestions = {}
)
public class Average_Of_Levels_in_Binary_Tree {

    public List<Double> averageOfLevels(TreeNode root) {

        List<Double> result = new ArrayList<>();

        if (root != null) {

            Queue<TreeNode> queue = new LinkedList<>();

            // queue保存的是当前将要遍历的层的结点
            queue.add(root);

            // 依然是每一次处理一层的情况
            while (queue.size() != 0) {
                // queue保存的是当前将要遍历的层的结点, count就是该层结点数
                long sum = 0;
                int count = queue.size();
                int levelNodeCount = queue.size();
                while (count != 0) {
                    TreeNode node = queue.poll();
                    sum += node.val;
                    if (node.left != null) {
                        queue.add(node.left);
                    }

                    if (node.right != null) {
                        queue.add(node.right);
                    }
                    count--;
                }
                result.add(sum / (levelNodeCount + 0.0));
            }
        }

        return result;


    }
}

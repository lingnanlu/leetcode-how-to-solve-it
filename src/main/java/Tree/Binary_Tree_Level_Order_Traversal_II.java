package Tree;

import run.elder.Category;
import run.elder.Leetcode;

import java.util.*;

@Leetcode(
        title = "Binary Tree Level Order Traversal II",
        link = "https://leetcode.com/problems/binary-tree-level-order-traversal-ii/",
        category = Category.TREE,
        how2SolveIt = """
                其实就是基础遍历的相反顺序, 那么, 使用一个栈就行了
                
                """,
        relatedQuestions = {}
)
public class Binary_Tree_Level_Order_Traversal_II {

    public List<List<Integer>> levelOrderBottom(TreeNode root) {


        List<List<Integer>> result = new ArrayList<>();

        Stack<List<Integer>> stack = new Stack<>();
        if (root != null) {

            Queue<TreeNode> queue = new LinkedList<>();

            // queue保存的是当前将要遍历的层的结点
            queue.add(root);

            // 依然是每一次处理一层的情况
            while (queue.size() != 0) {
                List<Integer> list = new ArrayList<>();
                // queue保存的是当前将要遍历的层的结点, count就是该层结点数
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
                stack.add(list);
            }
        }

        while (!stack.empty()) {
            result.add(stack.pop());
        }
        return result;


    }
}

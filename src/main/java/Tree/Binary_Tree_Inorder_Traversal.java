package Tree;

import run.elder.Category;
import run.elder.Leetcode;

import java.util.*;

@Leetcode(
        title = "Binary Tree Inorder Traversal",
        link = "https://leetcode.com/problems/binary-tree-inorder-traversal/",
        category = Category.STRING,
        how2SolveIt = """
                基础题 DFS 的一般化
                """,
        relatedQuestions = {}
)
public class Binary_Tree_Inorder_Traversal {

    static class First {
        public List<Integer> inorderTraversal(TreeNode root) {
            List<Integer> result = new ArrayList<>();
            walk(root, result);
            return result;
        }

        private void walk(TreeNode root, List<Integer> order) {
            if (root == null) return;
            walk(root.left, order);
            visit(root, order);
            walk(root.right, order);
        }

        private void visit(TreeNode node, List<Integer> order) {
            order.add(node.val);
        }
    }

    static class Second {

        enum Status {
            BEFORE_LEFT,   // 进入左结点之前
            MIDDLE,        // 进入左结点之后, 右结点之前
            AFTER_RIGHT    // 从右结点返回了.
        }

        public List<Integer> inorderTraversal(TreeNode root) {


            List<Integer> result = new ArrayList<>();

            if (root != null) {
                Stack<TreeNode> stack = new Stack<>();
                stack.push(root);

                Map<TreeNode, Status> status = new HashMap<>();
                status.put(root, Status.BEFORE_LEFT);

                while(!stack.isEmpty()) {
                    TreeNode node = stack.peek();
                    switch (status.get(node)) {
                        case BEFORE_LEFT -> {

                            // 标记出下一次遇到该结点的状态.
                            status.put(node, Status.MIDDLE);

                            // walk左
                            if (node.left != null) {
                                stack.push(node.left);
                                status.put(node.left, Status.BEFORE_LEFT);
                            }
                        }
                        case MIDDLE -> {
                            // visit
                            result.add(node.val);

                            // walk右
                            status.put(node, Status.AFTER_RIGHT);
                            if (node.right != null) {
                                stack.push(node.right);
                                status.put(node.right, Status.BEFORE_LEFT);
                            }
                        }
                        case AFTER_RIGHT -> stack.pop();
                    }

                }

            }

            return result;


        }
    }

}

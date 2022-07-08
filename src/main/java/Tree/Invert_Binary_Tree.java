package Tree;

import run.elder.Category;
import run.elder.Leetcode;

import java.util.*;

@Leetcode(
        title = "Invert Binary Tree",
        link = "https://leetcode.com/problems/invert-binary-tree/",
        category = Category.TREE,
        how2SolveIt = """
                方法一:
                
                递归方法, 发现, 是先将左子树翻转, 再将右子树翻转, 然后再对左右子树交换
              
                是一个后序遍历
                
                方法二:
                
                使用后序遍历的迭代模板
                
                
                方法三:
                
                关于树的还有一种遍历, 就是层序遍历, 这题能不能层序遍历? 发现也是可以的.
                
                因为其实就是对每一个结点, 交换左右孩子, 那么我们层序遍历时, 对每一个遍历到的结点, 交换左右孩子就好.
                """,
        relatedQuestions = {}
)
public class Invert_Binary_Tree {

    static class First {
        public TreeNode invertTree(TreeNode root) {

            if (root == null) return null;

            TreeNode invertedLeftTree = invertTree(root.left);
            TreeNode invertedRightTree = invertTree(root.right);

            TreeNode temp = root.left;
            root.left = root.right;
            root.right = temp;

            return root;
        }
    }

    static class Second {

        enum Status {
            BEFORE_LEFT,   // 进入左结点之前
            MIDDLE,        // 进入左结点之后, 右结点之前
            AFTER_RIGHT    // 从右结点返回了.
        }

        public TreeNode invertTree(TreeNode root) {

            if (root != null) {
                Stack<TreeNode> stack = new Stack<>();
                stack.push(root);

                Map<TreeNode,Status> status = new HashMap<>();
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
                            // walk右
                            status.put(node, Status.AFTER_RIGHT);
                            if (node.right != null) {
                                stack.push(node.right);
                                status.put(node.right, Status.BEFORE_LEFT);
                            }
                        }
                        case AFTER_RIGHT -> {
                            // visit, 交换左右子树
                            TreeNode temp = node.left;
                            node.left = node.right;
                            node.right = temp;

                            stack.pop();
                        }
                    }

                }

            }

            return root;
        }
    }

    static class Third {
        public TreeNode invertTree(TreeNode root) {

            if (root != null) {

                Queue<TreeNode> queue = new LinkedList<>();

                // queue保存的是当前将要遍历的层的结点
                queue.add(root);

                // 依然是每一次处理一层的情况
                while (queue.size() != 0) {
                    // queue保存的是当前将要遍历的层的结点, count就是该层结点数
                    int count = queue.size();
                    while (count != 0) {
                        TreeNode node = queue.poll();

                        // 先交换左右孩子, 再入队, 因为下一层的结点顺序其实是变了的

                        TreeNode temp = node.left;
                        node.left = node.right;
                        node.right = temp;

                        if (node.left != null) {
                            queue.add(node.left);
                        }

                        if (node.right != null) {
                            queue.add(node.right);
                        }
                        count--;
                    }
                }
            }

            return root;


        }
    }
}

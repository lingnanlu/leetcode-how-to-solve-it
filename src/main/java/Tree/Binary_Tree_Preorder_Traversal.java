package Tree;

import run.elder.Category;
import run.elder.Leetcode;

import java.util.*;

@Leetcode(
        title = "Binary Tree Preorder Traversal",
        link = "https://leetcode.com/problems/binary-tree-preorder-traversal/submissions/",
        category = Category.STRING,
        how2SolveIt = """
                方法一:
                基础题 DFS 的一般化
                
                方法二:
                不用递归, 使用栈, 因为递归本质上也是栈
                
                另外, 注意, 如果使用栈, 要明确
                
                1. 栈中元素是什么含义
                2. 栈空和非空是什么含义
                3. 压栈是什么含义
                4. 出栈是什么含义
                5. 栈顶是什么含义
                6. 非栈顶的元素的含义
                7. 栈中相邻两元素的关系
                
                其实思路依然是DFS, 栈顶的元素就是你在DFS中遇到的元素, 而对于二叉树来说, 时机有三种.
                我们只要根据时机, 来做不同的操作就行了.
                
                所以, 不管是递归还是迭代, 把它们都看出DFS就是很有帮助的.
                
                
                启示: 
                
                对于栈来说, 其实入栈的顺序就是DFS的顺序, 至少什么时候出栈, 就看是不是对它进行visit了, 如果已经visit,并且安排好了它的孩子
                的入栈顺序符合DFS, 就可以出栈了.
                
                """,
        relatedQuestions = {}
)
public class Binary_Tree_Preorder_Traversal {

    static class First {
        public List<Integer> preorderTraversal(TreeNode root) {
            List<Integer> result = new ArrayList<>();
            walk(root, result);
            return result;
        }

        private void walk(TreeNode root, List<Integer> order) {
            if (root == null) return;
            visit(root, order);
            walk(root.left, order);
            walk(root.right, order);

        }

        private void visit(TreeNode node, List<Integer> order) {
            order.add(node.val);
        }
    }

    static class Second {

        public List<Integer> preorderTraversal(TreeNode root) {

            List<Integer> result = new ArrayList<>();

            if (root != null) {
                Stack<TreeNode> stack = new Stack<>();
                stack.push(root);

                Map<TreeNode, Status> status = new HashMap<>();
                status.put(root, Status.BEFORE_LEFT);

                while(!stack.isEmpty()) {

                    // 相当于走到了这里
                    TreeNode node = stack.peek();
                    switch (status.get(node)) {
                        case BEFORE_LEFT -> {
                            // visit
                            result.add(node.val);
                            // 标记出下一次遇到该结点的状态.
                            status.put(node, Status.MIDDLE);

                            // walk左
                            if (node.left != null) {
                                stack.push(node.left);
                                status.put(node.left, Status.BEFORE_LEFT);
                            }
                        }
                        case MIDDLE -> {
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

        enum Status {
            BEFORE_LEFT,   // 进入左结点之前
            MIDDLE,        // 进入左结点之后, 右结点之前
            AFTER_RIGHT    // 从右结点返回了.
        }


    }

}

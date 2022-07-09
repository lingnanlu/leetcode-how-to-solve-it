package Tree;

import com.sun.source.tree.Tree;
import run.elder.Category;
import run.elder.Leetcode;

import java.util.*;

@Leetcode(
        title = "Balanced Binary Tree",
        link = "https://leetcode.com/problems/balanced-binary-tree/",
        category = Category.TREE,
        how2SolveIt = """
                这题主要是读懂平衡二叉树的定义
                
                平衡二叉树定义为
                
                1. 每个子树都是平衡二叉树
                2. 且左右子树的高度绝对差 <= 1
                
                方法一: 
                一看就是利用递归
                
                方法二:
                
                第一种是DFS嵌套DFS, 在递归调用isBalanced过程中, 其实已经计算了子树的高度, 然后在显示调用height时, 又一次计算了高度,
                
                所以高度的计算是重复的.
                
                把这种重复缓存下来.
                
                这里使用一个额外的map来保存,
                
                其实如果结点的val不重要的话, 可以把高度缓存在这里.
                
                方法三:
                
                其实方法二这种先把所有高度计算出来也不好, 因为在DFS过程中, 只要有一个子树是非平衡的, 就可以不再dfs了
                那么我们看看, 能不能在计算高度的过程中, 进行优化, 因为判断平衡需要高度, 所以我们就在求高度的过程中, 判断是否是平衡的.
                
                方法四:
                
                网上说迭代复杂, 我觉得未必, 试着用迭代来实现方法三
                
                nice, 也是一遍过!
                
                """,
        relatedQuestions = {}
)
public class Balanced_Binary_Tree {

    static class First {
        public boolean isBalanced(TreeNode root) {

            if (root == null) return true;

            return isBalanced(root.left)
                    && isBalanced(root.right)
                    && Math.abs(height(root.left) - height(root.right)) <= 1;

        }

        private int height(TreeNode root) {

            if (root == null) {
                return 0;
            } else {
                int leftMaxDepth = height(root.left);
                int rightMaxDepth = height(root.right);
                return Math.max(leftMaxDepth, rightMaxDepth) + 1;
            }
        }
    }

    static class Second {

        public boolean isBalanced(TreeNode root) {
            if (root == null) return true;

            Map<TreeNode, Integer> heights = new HashMap<>();

            calHeights(root, heights);

            // 现在所有结点的高度已经找到了, 不用在遍历了.

            return _isBalanced(root, heights);
        }

        public boolean _isBalanced(TreeNode root, Map<TreeNode, Integer> heights) {
            if (root == null) return true;

            return _isBalanced(root.left, heights)
                    && _isBalanced(root.right, heights)
                    && Math.abs(heights.getOrDefault(root.left, 0) - heights.getOrDefault(root.right, 0)) <= 1;
        }



        /**
         * @param heights 保存所有node树中所有结点的高度.
         */
        private int calHeights(TreeNode node, Map<TreeNode, Integer> heights) {
            if (node == null) {
                return 0;
            } else {
                int leftMaxDepth = calHeights(node.left, heights);
                int rightMaxDepth = calHeights(node.right, heights);
                int height = Math.max(leftMaxDepth, rightMaxDepth) + 1;
                heights.put(node, height);
                return height;
            }
        }

    }

    static class Third {
        public boolean isBalanced(TreeNode root) {
            return height(root) != -1;
        }

        /**
         * 这里遍历一个节点时, 要返回两个结果
         *
         * -1 可以表示这个结果
         *
         * -1 表示不平衡
         * != -1 表示平衡
         * 1. 该结点是不是平衡的
         * 2. 如果是, 就返回高度.
         */
        private int height(TreeNode node) {
            if (node == null) {
                return 0;
            } else {
                int leftHeight = height(node.left);

                if (leftHeight == -1) {
                    // 左边不平衡, 不用再DFS
                    return -1;
                }
                int rightHeight = height(node.right);

                if (rightHeight == -1) {
                    return -1;
                }

                if (Math.abs(leftHeight - rightHeight) > 1) {
                    return -1;
                } else {
                    return Math.max(leftHeight, rightHeight) + 1;
                }
            }
        }
    }

    static class Fourth {
        public boolean isBalanced(TreeNode root) {
            return height(root) != -1;
        }

        /**
         * 这里遍历一个节点时, 要返回两个结果
         *
         * -1 可以表示这个结果
         *
         * -1 表示不平衡
         * != -1 表示平衡
         * 1. 该结点是不是平衡的
         * 2. 如果是, 就返回高度.
         */
        private int height(TreeNode root) {

            Map<TreeNode, Integer> heights = new HashMap<>();
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

                            // 从左结点返回了, 要判断左结点是否是平衡的. 再决定要不要向右走
                            // 1. 左结点为null, 是平衡的
                            // 2. 左结点不为null, 但有了高度, 则也是平衡的.
                            if (node.left == null || heights.get(node.left) != -1) {
                                // walk右
                                status.put(node, Status.AFTER_RIGHT);
                                if (node.right != null) {
                                    stack.push(node.right);
                                    status.put(node.right, Status.BEFORE_LEFT);
                                }
                            } else {
                                // 左结点不平衡, 不用往右走了.
                                status.put(node, Status.AFTER_RIGHT);
                            }
                        }
                        case AFTER_RIGHT -> {

                            // 1. 如果左结点不平衡, 就直接标记不平衡, 返回上一级
                            // 2. 如果左结点平衡, 右结点不平衡, 也直接标记不平衡, 返回上一级
                            // 3. 左右都平衡, 计算出该结点平不平衡,返回上一级

                            if (node.left != null && heights.get(node.left) == -1) {
                                heights.put(node, -1);
                            } else if (node.right != null && heights.get(node.right) == -1) {
                                heights.put(node, -1);
                            } else {
                                int leftHeight = heights.getOrDefault(node.left, 0);
                                int rightHeight = heights.getOrDefault(node.right, 0);
                                if (Math.abs(leftHeight - rightHeight) > 1) {
                                    heights.put(node, -1);
                                } else {
                                    heights.put(node, Math.max(leftHeight, rightHeight) + 1);
                                }
                            }
                            stack.pop();
                        }
                    }

                }

            }

            return heights.getOrDefault(root, 0);

        }

        enum Status {
            BEFORE_LEFT,   // 进入左结点之前
            MIDDLE,        // 进入左结点之后, 右结点之前
            AFTER_RIGHT    // 从右结点返回了.
        }
    }


}

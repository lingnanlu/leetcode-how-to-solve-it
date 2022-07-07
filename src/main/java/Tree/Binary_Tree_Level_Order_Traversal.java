package Tree;

import run.elder.Category;
import run.elder.Leetcode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

@Leetcode(
        title = "Binary Tree Level Order Traversal",
        link = "https://leetcode.com/problems/binary-tree-level-order-traversal/",
        category = Category.TREE,
        how2SolveIt = """
                二叉树层次遍历, 基础题
                
                其实要明白
                
                1. 遍历具体指的是什么
                2. 遍历结点时, 产生什么影响
                
                其实也就不变式.
                
                遍历过程中, 要做两个方面的事件
                
                1. 将遍历到的节点值放入到该层List中
                2. 遍历完一层时, 产生下一层的遍历结点顺序
                
                
                方法一:
                
                遍历第一层时, 产生第二层的遍历结点, 遍历第二层时, 产生第三层, 遍历第i层时, 产生第i + 1层.
                
                该方法使用了两个队列进行交换, 很直观
                
                方法二:
                
                能不能只用一个队列, 其实最关键的, 是如何处理一层的结束, 即层与层之间的分隔
                因为结果是按照层进行分组的, 所以必须这样.
                
                可以使用一个分隔结点来试试表示某一层的结尾
                
                方法三:
                
                方法二是使用了哨兵, 我们在处理一层的过程中, 其实中知道该层中的结点个数的, 利用个数也可以区分层
                """,
        relatedQuestions = {}
)
public class Binary_Tree_Level_Order_Traversal {

    static class First {
        public List<List<Integer>> levelOrder(TreeNode root) {

            List<List<Integer>> result = new ArrayList<>();

            if (root != null) {
                // 保存将要遍历的某一层的结点
                Queue<TreeNode> queue = new LinkedList<>();

                // 保存遍历过程中, 产生的下一层的结点
                Queue<TreeNode> next = new LinkedList<>();

                queue.add(root);

                // 每一次循环, 就是遍历一层结点
                // 产生两个效果
                // 1. 生成该层结点的list
                // 2. 生成下一层要遍历的节点
                while (queue.size() != 0) {
                    // 保存本层的遍历的值
                    List<Integer> list = new ArrayList<>();

                    TreeNode node;
                    while ((node = queue.poll()) != null) {
                        list.add(node.val);
                        if (node.left != null) {
                            next.add(node.left);
                        }

                        if (node.right != null) {
                            next.add(node.right);
                        }
                    }

                    // 此时
                    // 1. 生成了该层结点的list
                    // 2. 生成了下一层要遍历的节点
                    result.add(list);
                    Queue<TreeNode> temp = queue;
                    queue = next;
                    next = temp; // 此时queue是空的.
                }
            }

            return result;

        }
    }

    static class Second {

        public List<List<Integer>> levelOrder(TreeNode root) {

            List<List<Integer>> result = new ArrayList<>();

            if (root != null) {

                Queue<TreeNode> queue = new LinkedList<>();

                // 表示一个某一层的结尾
                TreeNode end = new TreeNode();
                queue.add(root);
                queue.add(end);

                // 依然是每一次处理一层的情况
                // 处理完最后一层的话, queue中就一个end了
                while (queue.size() != 1) {
                    // 保存本层的遍历的值
                    List<Integer> list = new ArrayList<>();

                    TreeNode node;
                    while ((node = queue.poll()) != end) {
                        list.add(node.val);
                        if (node.left != null) {
                            queue.add(node.left);
                        }

                        if (node.right != null) {
                            queue.add(node.right);
                        }
                    }

                    result.add(list);
                    queue.add(end);

                }
            }

            return result;

        }

    }

    static class Third {


        public List<List<Integer>> levelOrder(TreeNode root) {

            List<List<Integer>> result = new ArrayList<>();

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
                    result.add(list);
                }
            }

            return result;

        }


    }

}

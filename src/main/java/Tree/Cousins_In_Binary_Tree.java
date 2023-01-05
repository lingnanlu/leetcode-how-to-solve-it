package Tree;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

/**
 * 判断两个结点是不是堂兄弟结点.
 *
 * 题目中并没有给出判断方法, 只给出了堂兄弟结点的性质.
 *
 * 1. 同一深度
 * 2. 父结点不同
 *
 * 可以简化一下问题, 比如判断两个结点是不是同一深度.
 *
 * 而同一深度其实就是同一层数, 所以可以使用层次遍历来进行判断, 这样就解决了第一个问题
 *
 * 那么, 如果是同一深度, 怎么确定父结点是不是同一个?
 * 第一步已经找到两个结点. 而父结点是上一层的结点. 所以应该使用层次遍历也可以进行判断.
 *
 * 大概就是两次层次遍历.
 *
 * 方法二:
 *
 * 方法一虽然是一次过, 但这两次遍历代码写的太长了, 那么能不能一次层序遍历? 我感觉行.
 *
 * 方法三:
 *
 * 使用层次遍历还是写的代码有点多.
 * 我们知道对于树这种结构, 还有一种很重要的遍历, 就是DFS, 利用DFS能解决这个问题么?
 *
 * 其实也行, 就是DFS搜索, 找到x结点时, 返回其深度和父结点
 * 对y也是, 这样
 *
 * 其实就是两遍dfs.
 *
 * 但我感觉dfs不如bfs直观.
 *
 * 方法四
 *
 * 对方法二可以进行一些优化.
 *
 * 疑惑:
 *
 * 力扣上的DFS可以100%, 不知道为什么? 感觉时间复杂度一样啊
 */
public class Cousins_In_Binary_Tree {

    static class First {
        public boolean isCousins(TreeNode root, int x, int y) {

            // 保存将要遍历的下一层结点
            Queue<TreeNode> queue = new ArrayDeque<>();
            queue.add(root);

            // 用来指向x, y结点.
            TreeNode xNode = null;
            TreeNode yNode = null;

            Queue<TreeNode> copied = new ArrayDeque<>();

            while (!queue.isEmpty()) {
                // 取出当前层结点
                copied.addAll(queue);
                queue.clear();

                // 用来保存下一个要遍历的结点
                TreeNode node;
                /**
                 * 对当前层结点遍历, 遍历其实就是做以下事情
                 * 1. 看这个结点是不是x或y
                 * 2. 将下一层结点放到queue中
                 * 3. 判断这一层中是不是有x和y.
                 */
                while (!copied.isEmpty()) {
                    // 取一个结点进行遍历
                    node = copied.poll();

                    if (node.val == x) {
                        xNode = node;
                    } else if (node.val == y) {
                        yNode = node;
                    }

                    if (node.left != null) {
                        queue.add(node.left);
                    }

                    if (node.right != null) {
                        queue.add(node.right);
                    }
                }

                // 此时遍历完一层了
                if (xNode == null || yNode == null) {
                    // 说明x和y至少有一个不在这一层.
                    xNode = null;
                    yNode = null;
                } else {
                    // 说明xy在同一层, 不用再遍历了.
                    queue.clear();
                    break;
                }
            }

            // 由第一遍的层序遍历可知, xNode和yNode在同一层, 现在进行第二次层序遍历
            if (xNode != null && yNode != null) {

                // 记录x, y的父母.
                TreeNode xParent = null, yParent = null;
                queue.add(root);
                while (!queue.isEmpty()) {
                    // 取出当前层结点
                    copied.addAll(queue);
                    queue.clear();

                    // 用来保存下一个要遍历的结点
                    TreeNode node;
                    /**
                     * 对当前层结点遍历, 遍历其实就是做以下事情
                     * 1. 判断其是不是x, y的父母.
                     * 2. 将下一层结点放到queue中
                     */
                    while (!copied.isEmpty()) {
                        // 取一个结点进行遍历
                        node = copied.poll();

                        if (node.left != null) {
                            if (node.left.val == x) {
                                xParent = node;
                            } else if (node.left.val == y) {
                                yParent = node;
                            }
                        }

                        if (node.right != null) {
                            if (node.right.val == x) {
                                xParent = node;
                            } else if (node.right.val == y) {
                                yParent = node;
                            }
                        }

                        if (node.left != null) {
                            queue.add(node.left);
                        }

                        if (node.right != null) {
                            queue.add(node.right);
                        }
                    }

                    // 此时遍历完一层
                    if (xParent != null && yParent != null && xParent.val != yParent.val) {
                        return true;
                    } else {
                        xParent = null;
                        yParent = null;
                    }
                }

                return false;
            } else {
                return false;
            }

        }
    }

    static class Second {

        // x是不是node的孩子
        private boolean nodeHasChild(TreeNode node, int x) {
            if (node.left != null && node.left.val == x) {
                return true;
            } else {
                return node.right != null && node.right.val == x;
            }
        }
        public boolean isCousins(TreeNode root, int x, int y) {

            // 保存将要遍历的下一层结点
            Queue<TreeNode> queue = new ArrayDeque<>();
            queue.add(root);

            // 用来指向x, y结点.
            TreeNode xNode = null;
            TreeNode yNode = null;

            // 记录x, y的父母.
            TreeNode xParent = null, yParent = null;
            Queue<TreeNode> copied = new ArrayDeque<>();

            while (!queue.isEmpty()) {
                // 取出当前层结点
                copied.addAll(queue);
                queue.clear();

                // 用来保存下一个要遍历的结点
                TreeNode node;
                /**
                 * 对当前层结点遍历, 遍历其实就是做以下事情
                 * 1. 看这个结点的孩子是不是x或y
                 * 2. 将下一层结点放到queue中
                 */
                while (!copied.isEmpty()) {
                    // 取一个结点进行遍历
                    node = copied.poll();

                    if (nodeHasChild(node, x)) {
                        xParent = node;
                    }

                    if (nodeHasChild(node, y)) {
                        yParent = node;
                    }

                    if (node.left != null) {
                        queue.add(node.left);
                    }

                    if (node.right != null) {
                        queue.add(node.right);
                    }
                }

                // 此时遍历完一层了
                if (xParent != null && yParent != null && xParent.val != yParent.val) {
                    // 说明父母在同一层, 且不是同一父母.
                    return true;
                } else {
                    // 这一层没找到
                    xParent = null;
                    yParent = null;

                }
            }
            return false;

        }

    }

    static class Forth {


        // x是不是node的孩子
        private boolean nodeHasChild(TreeNode node, int x) {
            if (node.left != null && node.left.val == x) {
                return true;
            } else {
                return node.right != null && node.right.val == x;
            }
        }
        public boolean isCousins(TreeNode root, int x, int y) {

            // 保存将要遍历的下一层结点
            Queue<TreeNode> queue = new ArrayDeque<>();
            queue.add(root);

            // 用来标记一层的结束
            TreeNode guard = new TreeNode(0);
            queue.add(guard);

            // 记录x, y的父母.
            TreeNode xParent = null, yParent = null;

            while (!queue.isEmpty()) {

                // 用来保存下一个要遍历的结点
                TreeNode node = null;
                /**
                 * 对当前层结点遍历, 遍历其实就是做以下事情
                 * 1. 看这个结点的孩子是不是x或y
                 * 2. 将下一层结点放到queue中
                 */
                while ((node = queue.poll()) != guard) {

                    if (nodeHasChild(node, x)) {
                        xParent = node;
                    }

                    if (nodeHasChild(node, y)) {
                        yParent = node;
                    }

                    if (node.left != null) {
                        queue.add(node.left);
                    }

                    if (node.right != null) {
                        queue.add(node.right);
                    }
                }

                // 此时遍历完一层了, 此时node == guard, queue中是下一层要遍历的结点.
                if (xParent != null && yParent != null && xParent.val != yParent.val) {
                    // 说明父母在同一层, 且不是同一父母.
                    return true;
                } else {
                    // 这一层没找到, 遍历下一层
                    if (queue.isEmpty()) {
                        // 下一层没结点了, 不用再遍历了
                        break;
                    } else {
                        xParent = null;
                        yParent = null;
                        queue.add(guard);
                    }
                }


            }
            return false;

        }


    }

}

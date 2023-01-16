package Tree;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * 找第二小.
 * 初看没什么思路, 没遇到过啊.
 * 但仔细看下, 你遇到过类似问题吗?
 *
 * 有, 就是对于数组中, 找第二小的
 * 那么, 可不可以利用在数组中的方法?
 * 我觉得可以, 就是把树遍历一遍, 记录最小和第二小.
 * 好, 这个作为方法一
 *
 * 方法一:
 * 使用dfs遍历一遍历, 在这个过程中, 找最小和第二小.
 *
 * 方法二:
 * 方法一因为不变式以及假设问题, 使得这个解不好说. 所以可能要换一种思路了.
 *
 * 第二小可能不好找, 但我们学过最小啊, 能不能找出最小?
 *
 * 由该树的性质可得, 最小一定是为树根.
 *
 * 那么, 能不能遍历树, 再找和树根不一样的第二小呢? 我觉得没问题.
 *
 * 方法三:
 * 我觉得没有充分利用已知, 已知是什么?
 *
 * 1. 每个子节点, 要不两个, 要不0个
 * 2. 每个节点是等于子节点的较小.
 *
 * 那么, 由以上性质能得出, 根是最小, 那么能不能利用以上性质找第二小呢?
 * 根是最小, 那么, 第二小肯定在根的两个孩子当中. 所以要检查每一个孩子.
 *
 * 如果两个孩子值一样, 那么, 要从左到右检查每个孩子的子结点.
 * 如果两个孩子值不一样, 那么, 第二小会在哪一颗树中呢? 其实不知道.
 * 所以还得遍历完
 *
 * 你会发现, 是一层一层的检查.
 *
 * 所以是层序遍历.
 *
 * 启示:
 *
 * 在方法一的过程中, 没有注意到解答的假设, 方法二注意到了, 但是没注意全
 *
 * 一定要注意到背后的假设才行. 别忘记了假设
 */
public class Second_Minimum_Node_In_A_Binary_Tree {

    // 这个方法错误, 题目中的要求其实是第二小的不重复的值.
    static class Wrong {

        int min = Integer.MAX_VALUE;
        int second2min = Integer.MAX_VALUE;

        public int findSecondMinimumValue(TreeNode root) {
            dfs(root);
            return second2min;
        }

        // 遍历一遍, 不再乎访问顺序
        private void dfs(TreeNode node) {
            if (node == null) return;

            if (node.val < min) {
                second2min = min;
                min = node.val;
            } else if (node.val < second2min) {
                second2min = node.val;
            }

            dfs(node.left);
            dfs(node.right);
        }
    }

    static class First {

        /**
         * 不变式, min是目前找到的最小
         * second2min是找到的次小
         * 且两者要不一样.
         *
         * 但这里其实是有一个问题的.
         * 这里有一个假设, 就是树中节点的值都 <= second2min, 这样, 才能正常的迭代下去.
         * 所以对于异常情况, 要单独处理
         *
         * 这里还有一个假设, 就是如果迭代了, 那么, min和second2min必被更新为树中的节点值.
         *
         * 但可能只更新其中一个.
         *
         * 比如, 会不会只更新second2min而不更新min呢? 此时, min的值其实不是树中的最小的值.
         * 那么, second2min也就不是真正的第二小的值了.
         *
         * 会不会只更新min而不更新second2min呢? 这个情况不会, 因为不会有比min更小的, 如果与min相同.
         *
         * 由以上来看, 使用这种方式对本题来说, 有点复杂, 主要的问题是这个第二小的定义与普通的理解不一样.
         *
         * 以下的代码没完全考虑以上的假设.
         */
        int min = Integer.MAX_VALUE;
        int second2min = Integer.MAX_VALUE - 1;

        public int findSecondMinimumValue(TreeNode root) {

            // 由题目中该二叉树的性质可知, 没有最小.
            if (root.val == Integer.MAX_VALUE) return -1;


            dfs(root);
            return second2min;
        }

        // 遍历一遍, 不再乎访问顺序.
        // 注意保持不变式的性质.
        private void dfs(TreeNode node) {

            if (node == null) return;

            if (node.val < min) {
                second2min = min;
                min = node.val;
            } else if (node.val > min && node.val < second2min) {
                second2min = node.val;
            }

            dfs(node.left);
            dfs(node.right);
        }

    }

    static class Second {

        // 0表示还没赋过值. 如果遍历完一遍一后也没赋过值, 说明没有第二小.
        int second = 0;
        public int findSecondMinimumValue(TreeNode root) {
            int min = root.val;
            dfs(root, min);
            return second == 0 ? -1 : second;
        }

        // 遍历整个树, 找一个比min大的最小值.
        private void dfs(TreeNode node, int min) {
            if (node == null) return;

            if (node.val > min) {
                // 第二小的值还没赋过值.
                if (second == 0) {
                    second = node.val;
                } else {
                    second = Math.min(second, node.val);
                }
            }

            dfs(node.left, min);
            dfs(node.right, min);
        }

    }

    static class Third {

        public int findSecondMinimumValue(TreeNode root) {
            int min = root.val;

            // 0表示还没赋过值. 如果遍历完一遍一后也没赋过值, 说明没有第二小.
            int second = 0;
            // 待检查的下一层结点.
            Queue<TreeNode> layer = new LinkedList<>();
            if (root.left != null) {
                layer.add(root.left);
                layer.add(root.right);
            }

            while (layer.size() != 0) {
                int count = layer.size(); // 当前层的结点数. 一次处理一层

                while (count != 0) {
                    TreeNode node = layer.poll();
                    if (node.val > min) {
                        // 第二小的值还没赋过值.
                        if (second == 0) {
                            second = node.val;
                        } else {
                            second = Math.min(second, node.val);
                        }
                    }

                    if (node.left != null) {
                        layer.add(node.left);
                        layer.add(node.right);
                    }

                    count--;
                }

                // 检查完了一层, 看看有没有找到第二小, 找到了就不用再找了.
                // 这里的理解是错误的, 可能更小的还在下面一层.
//                if (second != 0) {
//                    return second;
//                }
            }

            return second == 0 ? -1 : second;

        }
    }


}

package Tree;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * 方法一:
 * 众数, 显然就是遍历一遍, 然后统计个数, 再找到出现次数最多的.
 *
 * 方法二:
 * 题目要求不使用额外空间, 怎么做呢? 看看条件, 有哪些是已知, 但我们没有利用上的呢?
 * 方法一其实并没有利用二叉搜索树的性质. 任意树都可以利用方法一的方法.
 * 这题已知的特殊的地方就是这个二叉搜索树. 想想怎么利用它.
 *
 * 另外, 好像避免不了统计每个数出现的次数, 但又不能使用额外空间, 这个次数要放在哪里呢?
 *
 * 这里唯一可用的空间就是二叉树本身了.
 * 但是这里又要保存频率, 又要保存值, 利用二叉树本身是不可能的了.
 *
 * 所以, 看来是不能保存每个值的频率了.
 *
 * 那么,还是回到二叉搜索树本身的特征, 就是可以是一个有序的数组.
 *
 * 那么, 我们先简化一下问题, 给你一个有序的数组, 看能不能找到众数. 比如
 *
 * [1, 2, 2, 3, 3, 3, 4, 4, 5, 5, 5]
 *
 * 我觉得是没问题的.
 *
 * 那么, 我们可以利用这个类似题目的结果, 你现在解决了一个类似的题目, 所以, 我们先把二叉搜索数转化成一个有序数组.
 *
 * 方法三:
 *
 * 方法二中, 我们是先将其转换成一个有序数组, 再解决的, 其实这里最关键的就是对有序数组的遍历.
 * 而遍历过程中, 其实就是想办法把方法二中的, 前一个结点, i, j, lastMode之类的信息弄进去
 */
public class Find_Mode_In_Binary_Search_Tree {
    static class First {
        public int[] findMode(TreeNode root) {
            return null;
        }
    }

    static class Second {

        public int[] findMode(TreeNode root) {

            // 第一步将二叉树转换成有序数组.
            List<Integer> sorted = new ArrayList<>();
            walk(root, sorted);

            // 第二步遍历数组, 找众数.
            List<Integer> mode = new ArrayList<>(); // 用来保存众数

            /**
             * 基本算法思想
             *
             * 统计连续出现的数的个数, 如果比上一个连续出现的数的个数多, 该数就是新的众数
             * 如果相同, 也是众数
             * 如果少, 则下一个.
             */

            // i表示下一个数第一次出现的位置, j表示要检测的下一个数的位置
            int i = 0, j = 1;

            // 上一个众数的频率, 这里没有上一个众数, 可以0是有意义的.
            int lastMode = 0;

            while (i != sorted.size()) {

                // 1. 移动j, 使其到下一个不同的数的位置
                while (j != sorted.size() && Objects.equals(sorted.get(j), sorted.get(j - 1))) {
                    j++;
                }

                // 此时, i, j之间的数为相同的.
                if ((j - i) > lastMode) {
                    // 新的众数
                    mode.clear();
                    mode.add(sorted.get(i));
                    lastMode = j - i;  // 使不变式为真
                } else if ((j - i) == lastMode) {
                    mode.add(sorted.get(i));
                }

                // 使不变式为真
                i = j;
                j = i + 1;
            }

            int[] result = new int[mode.size()];

            for (int k = 0; k < result.length; k++) {
                result[k] = mode.get(k);
            }

            return result;


        }

        private void walk(TreeNode node, List<Integer> sorted) {
            if (node == null) return;
            walk(node.left, sorted);
            sorted.add(node.val);
            walk(node.right, sorted);
        }

    }

    // todo
    static class Third {

        public int[] findMode(TreeNode root) {
            return null;
        }




    }
}

package Tree;

import java.util.ArrayList;
import java.util.List;

/**
 * 方法一:
 *
 * 先中序遍历一遍, 得到一个有序数组, 然后再把该有序数组变成一个二叉搜索树.
 *
 * 方法二:
 *
 * 能不能一边遍历一边构建? 就要想清楚, dfs从左边返回时的消息是什么, 从右边返回时的信息是什么.
 *
 * 方法二并不难, 但难就难在, 在整个dfs过程中, 一定要保持p, q的不变式性质为真.
 * 这需要仔细考虑每一步执行完成后的p, q的状态.
 *
 * 可见不变式不仅仅在循环过程有用, 在dfs中照样可以发挥用途.
 */
public class Increasing_Order_Search_Tree {

    static class First {

        public TreeNode increasingBST(TreeNode root) {

            List<Integer> sorted = new ArrayList<>();
            dfs(root, sorted);

            // 一个辅助node, 用来挂第一个节点.
            TreeNode assist = new TreeNode();

            // p指向最右的结点.
            TreeNode p = assist;

            for (int n : sorted) {
                TreeNode node = new TreeNode(n);
                p.right = node;
                p = node;
            }

            return assist.right;

        }

        private void dfs(TreeNode node, List<Integer> list) {
            if (node == null) return;

            dfs(node.left, list);
            list.add(node.val);
            dfs(node.right, list);

        }
    }

    static class Second {
        TreeNode p = null; // 用来在遍历过程中, 从左子树遍历完后生成的子树的root
        TreeNode q = null; // 用来在遍历过程中, 从左子树遍历完后生成的子树的最右端结点.
        public TreeNode increasingBST(TreeNode root) {
            walk(root);
            return p;
        }

        /**
         * 遍历之后, p是中序遍历的node的新树的根
         * q是最右边的.
         */
        private void walk(TreeNode node) {
            if (node == null) {
                // 注意要保证不变式的真
                p = null;
                q = null;
                return;
            }

            walk(node.left);
            /**
             *
             * 此时走到了node结点.对其实进行访问, 要做的事
             * 1. 将left设置为null
             * 2. 将右子树返回的新的子树的最右结点的右子树与node连接.
             */
            node.left = null;

            if (q != null) {
                q.right = node;
            } else {
                // 说明左子树为空的, 那么, 该结点即是根也是最右
                p = node;
                q = node;
            }

            // 此时p指向新树的根, node本身是新树的最右边结点.

            // 因为之后的遍历右子树会修改p, 所以先保存p.
            TreeNode saveP = p;

            walk(node.right);

            /**
             * 此时p是右子树新树的根结点
             * q是右子树新树的最右
             *
             * saveP是这前包含node的新树的根
             * node是之前包含node的新树的最右.
             *
             */
            node.right = p;
            // 恢复p
            p = saveP;

            //说明没有新的右子树. 那么, node其实就是最右边结点了. 否则q就是整个树的最右边结点.
            if (q == null) {
                q = node;
            }
        }

    }


}

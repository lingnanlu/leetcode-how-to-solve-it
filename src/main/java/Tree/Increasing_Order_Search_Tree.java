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
 * 能不能一边遍历一边构建? todo
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

        public TreeNode increasingBST(TreeNode root) {
            return null;
        }

    }


}

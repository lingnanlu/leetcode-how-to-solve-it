package Tree;

import run.elder.Category;
import run.elder.Leetcode;

@Leetcode(
        title = "Convert BST to Greater Tree",
        link = "https://leetcode.com/problems/convert-bst-to-greater-tree/",
        category = Category.TREE,
        how2SolveIt = """
                方法一:
                
                利用DFS的思想试试, 其实就是要把walk函数的
                
                1. 参数与返回值的含义
                2. 递归终止时的含义搞清楚
                
                这个递归还是有些巧妙, 不如方法二直观.
                
                
                方法二:
                
                其实就是一个中序遍历, 但是递减, 如果不累加就是
                
                [x1, x2, x3, .... xn] x1 > x2 > x3 > x4
                
                但累加就是
                
                [x1, x1 + x2, x1 + x2 + x3, ....]
                
                使用一个指针, 指向的就是中序遍历的元素
                
                方法二是一种遍历的思想
                
                方法三:
                
                node其实就是cur, 所以cur可以省略
                
                这里不修改结点之间的关系, 所以pre可以为前一结点的值, 这样就不需要指向前一结点了.
               
                """,
        relatedQuestions = {}
)
public class Convert_BST_To_Greater_Tree {

    static class First {
        public TreeNode convertBST(TreeNode root) {
            if (root == null) return null;
            walk(root, 0);
            return root;
        }

        /**
         * @param node 要遍历的树
         * @param base 一个累加的基数值.
         * @return
         *
         * 1. node树中每个结点都累加了比它的值大的结点和
         * 2. 返回值为node树中, 最左边结点(即最小结点)的值
         */
        private int walk(TreeNode node, int base) {

            if (node == null) return base;

            int rightSum = walk(node.right, base);
            node.val += rightSum;

            return walk(node.left, node.val);
        }
    }

    static class Second {

        TreeNode pre = null;
        TreeNode cur = null;
        public TreeNode convertBST(TreeNode root) {
            walk(root);
            return root;
        }


        private void walk(TreeNode node) {
            if (node == null) return;

            walk(node.right);

            // cur指向当前遍历的结点.
            cur = node;

            // 第一个节点时, pre为null
            cur.val += pre == null ? 0 : pre.val;

            // 将该结点给了pre, 下次到这里时, cur指向下一个.
            pre = cur;
            walk(node.left);

        }

    }

    static class Third {
        int pre = 0;
        public TreeNode convertBST(TreeNode root) {
            walk(root);
            return root;
        }


        private void walk(TreeNode node) {
            if (node == null) return;

            walk(node.right);

            // 第一个节点时, pre为null
            node.val += pre;

            // 将该结点给了pre, 下次到这里时, cur指向下一个.
            pre = node.val;
            walk(node.left);

        }
    }


}

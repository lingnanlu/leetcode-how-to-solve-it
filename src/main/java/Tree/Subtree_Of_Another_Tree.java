package Tree;

import run.elder.Category;
import run.elder.Leetcode;

@Leetcode(
        title = "Subtree of Another Tree",
        link = "https://leetcode.com/problems/subtree-of-another-tree/",
        category = Category.TREE,
        how2SolveIt = """
                方法一:
                
                看到sub, 想到字符串子串, 或者数组子数组, 可以将两者转化成数组表示, 然后就是数组子数组的问题了.
                
                方法二:
                
                依然对root进行DFS, 这里的visit就是判断是否是同一个树, 如果是就不再DFS
             
                方法三:
                
                仔细分析一下, isSame好像就可以看成walk了, 对方法二DFS模板进行简化
                """,
        relatedQuestions = {}
)
public class Subtree_Of_Another_Tree {

    static class First {

    }

    static class Second {
        public boolean isSubtree(TreeNode root, TreeNode subRoot) {

            return walk(root, subRoot);
        }

        /**
         * subRoot是否是node的子树
         */
        private boolean walk(TreeNode node, TreeNode subRoot) {

            // 先判断以node为根的, 是否与subRoot相同, 相同就不再往下走, 直接返回
            if (isSameTree(node, subRoot)) return true;

            // 不同, 但是没有子树了, 所以也不能往下走了, 直接返回false
            if (node == null) return false;

            // 不同就往左走, 看左子树是不是相同
            boolean leftSame = walk(node.left, subRoot);

            // 左子树相同, 就直接返回, 不再往右走
            if (leftSame) return true;

            // 左子树也不同, 就看右子树
            return walk(node.right, subRoot);

        }


        private boolean isSameTree(TreeNode p, TreeNode q) {
            if (p == null && q == null) {
                return true;
            } else if (p == null || q == null) {
                return false;
            } else if (p.val != q.val) {
                return false;
            } else {
                return isSameTree(p.left, q.left) && isSameTree(p.right, q.right);
            }
        }
    }

    static class Third {
        public boolean isSubtree(TreeNode root, TreeNode subRoot) {

            // 先判断以node为根的, 是否与subRoot相同, 相同就不再往下走, 直接返回
            if (isSameTree(root, subRoot)) return true;

            // 不同, 但是没有子树了, 所以也不能往下走了, 直接返回false
            if (root == null) return false;

            // 不同就往左走, 看左子树是不是相同
            boolean leftSame = isSubtree(root.left, subRoot);

            // 左子树相同, 就直接返回, 不再往右走
            if (leftSame) return true;

            // 左子树也不同, 就看右子树
            return isSubtree(root.right, subRoot);

        }

        private boolean isSameTree(TreeNode p, TreeNode q) {
            if (p == null && q == null) {
                return true;
            } else if (p == null || q == null) {
                return false;
            } else if (p.val != q.val) {
                return false;
            } else {
                return isSameTree(p.left, q.left) && isSameTree(p.right, q.right);
            }
        }
    }
}

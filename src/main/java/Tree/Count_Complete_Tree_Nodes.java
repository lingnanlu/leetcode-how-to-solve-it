package Tree;

import run.elder.Category;
import run.elder.Leetcode;

@Leetcode(
        title = "Count Complete Tree Nodes",
        link = "https://leetcode.com/problems/count-complete-tree-nodes/",
        category = Category.TREE,
        how2SolveIt = """
                方法一:
                
                其实就是统计二叉树的结点数, DFS和BFS都行, 但这并没有利用到它是一个完全二叉树的事实
                
                方法二:
                
                既然是一个完全二叉树, 那么就要想办法利用其性质, 有什么性质呢?
                
                完全二叉树的每一层结点个数都是关于层数的函数, 如果我们知道层数, 那么也就接近了.
                
                怎么知道层数呢? 发现最左边的结点所在层数就是最后一层
                
                好, 现在知道最左边结点所在层了, 这一层往上的结点数可以得知, 那么, 这一层的结点数呢?
                
                再仔细观察, 上面我们使用了最左结点, 那么, 还有什么没有使用? 最右结点呢, 能不能用上?
                
                最右边结点所在层数要不和最左一样, 此时是满二叉树
                要不就是少一层
                
                对于满二叉树, 可以直接使用公式来计算
                如果是非满二叉树呢? 仔细观察这种情况, 没有办法得出最后一层最后一个结点.
                
                于是这种方式也放弃
                
                方法三:
                
                我们看看能不能找到最后一个结点, 看下该结点有什么特征
                
                1. 它在最后一层
                2. 它是这一层的最后一个叶子结点.
                
                
                现在, 最后一层我们可以通过最左结点层数确定
                
                那么这一层的最后一个叶子结点怎么确定呢? 
                
                直觉告诉我们, 要尽量往右走, 如果右边没有了, 再往左走, 直到走到最后一层, 且是叶子结点, 那么, 就是最后一层的叶子结点了.
                
                另外, 根据层和左右结点, 我们知道结点的编号, 而对于完全二叉树来说, 结点的编号就是结点数, 所以走的过程中, 再记录编号就行了.
                
                这里的DFS其实是比较复杂的, 其过程做了很多剪枝, 而这些剪枝是利用了完全二叉树的性质以及DFS过程中的性质, 如
                
                1. 如果一个结点右子树不为空, 则左子树一定不为空
                2. 不可能走到最后一层, 且结点为非叶子结点.
                3. 如果右子树不为空, 则不存在最后一个结点, 那么, 一定在左子树上
               
                方法四:
                
                满二叉树是可以使用公式计算的, 那么, 可以DFS一个非满的完全二叉树, 当访问到某一子树且判断出其为满二叉树时, 就可以不再DFS
                直接使用公式就可以计算了.
                
                非满二叉树都是由满二叉树组成的.
                
                这里其实就是在DFS到某一个结点时, 先判断是不是满二叉树, 如果是, 就不再DFS, 而是直接利用公式计算
                
                注意移位运算符的含义和优先级
                
                启示:
                
                当在DFS过程中, 要求不要O(n), 当遍历到某一node时, 某些结果可以不再靠继续DFS得到, 而是使用其它方式直接得到
                """,
        relatedQuestions = {}
)
public class Count_Complete_Tree_Nodes {

    static class First {
        public int countNodes(TreeNode root) {
            return 0;
        }
    }

    static class Second {
        public int countNodes(TreeNode root) {
            return 0;
        }
    }

    static class Third {
        public int countNodes(TreeNode root) {

            if (root == null) return 0;
            // 1. 先得到层数

            int level = calLevel(root);

            // 2. 从根开始DFS, 找到最后一层的最右叶结点, 走的过程中, 记录下编号.
            return dfs2(root, 1, 1, level);

        }

//        /**
//         * @param node 将要访问的结点
//         * @param level 将要访问的结点所在的level
//         * @param index 将要访问的结点的编号
//         *
//         * return -1表示没找到, != -1表示找到了, 且为值
//         */
//        private int dfs(TreeNode node, int level, int index, int totalLevel) {
//
//            // 如果是最后一层的叶结点, 那么, 由DFS过程可知, 就是最后一个结点, 此结点的编号就是结点个数了.
//            if (level == totalLevel && isLeaf(node)) {
//                return index;
//            } else if (level < totalLevel && isLeaf(node)) { // 这种情况可能发生
//                return -1;
//            } else if (level == totalLevel && !isLeaf(node)) { // 这种不可能出现
//                return -1;
//            } else { // level < totalLevel && !isLeaf(node)
//
//                // 试着往右走
//                if (node.right != null) {
//
//                    int total = -1;
//                    total= dfs(node.right, level + 1, index * 2 + 2, totalLevel);
//                    if (total != -1) {
//                        // 找不到, 不走了, 直接返回向上
//                        return total;
//                    } else {
//                        // 右边没有, 往左走
//                        total = dfs(node.left, level + 1, index * 2 + 1, totalLevel);
//
//                        if (total != -1) {
//                            return total;
//                        } else {
//                            // 左边也没有, 其实这种情况是不会发生的.
//                            return -1;
//                        }
//                    }
//                }
//            }
//
//        }

        /**
         *
         * @param node 将要访问的结点
         * @param level 将要访问的结点所在的level
         * @param index 将要访问的结点的编号
         *
         * return -1表示没找到, != -1表示找到了, 且为值
         */
        private int dfs2(TreeNode node, int level, int index, int totalLevel) {

            // 如果是最后一层的叶结点, 那么, 由DFS过程可知, 就是最后一个结点, 此结点的编号就是结点个数了.
            // dfs过程以及完全二叉树的性质保证了node不可能为null
            if (level == totalLevel && isLeaf(node)) {
                return index;
            } else if (level < totalLevel && isLeaf(node)) { // 这种情况可能发生, 可能是倒数第二层的最后一个结点.
                return -1;
            } else { // level < totalLevel && !isLeaf(node) // 这里是中间层的结点.
                // 优先往右走
                if (node.right != null) {
                    int result= dfs2(node.right, level + 1, index * 2 + 1, totalLevel);
                    if (result != -1) {
                        // 最后一个结点在右子树, 就直接返回
                        return result;
                    } else {
                        // 右边没有, 由二叉树的性质决定, 左子树一定不为null, 看看在不在左边
                        return dfs2(node.left, level + 1, index * 2, totalLevel);
                    }
                } else {
                    // 右边为空的话, 看看在不在左边
                    return dfs2(node.left, level + 1, index * 2, totalLevel);
                }
            }

        }

        private boolean isLeaf(TreeNode node) {
            return node.left == null && node.right == null;
        }

        private int calLevel(TreeNode root) {
            if (root == null) {
                return 0;
            }

            // p指向第level层
            TreeNode p = root;
            int level = 1;
            while (p != null) {
                p = p.left;
                level++;
            }

            return level - 1;
        }
    }

    static class Fourth {

        public int countNodes(TreeNode root) {

            if (root == null) return 0;

            // 再继续走下去之前, 先判断是不是满二叉树.

            int leftDepth = getLeftDepth(root);
            int rightDepth = getRightDepth(root);

            if (leftDepth == rightDepth) {
                // 是满二叉树, 不再往下走, 直接利用公式计算, 注意这个优先级
                return (2 << (leftDepth - 1)) - 1;
            } else {
                int leftTreeNodes = countNodes(root.left);
                int rightTreeNodes = countNodes(root.right);
                return leftTreeNodes + rightTreeNodes + 1;
            }
        }


        // 得到最左边结点的层数
        private int getLeftDepth(TreeNode node) {
            if (node == null) {
                return 0;
            }

            // p指向第level层
            TreeNode p = node;
            int level = 1;
            while (p != null) {
                p = p.left;
                level++;
            }

            return level - 1;
        }

        // 得到最右边结点的层数
        private int getRightDepth(TreeNode node) {
            if (node == null) {
                return 0;
            }

            // p指向第level层
            TreeNode p = node;
            int level = 1;
            while (p != null) {
                p = p.right;
                level++;
            }

            return level - 1;
        }
    }


}

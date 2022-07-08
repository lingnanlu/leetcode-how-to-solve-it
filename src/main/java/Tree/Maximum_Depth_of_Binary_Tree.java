package Tree;

import run.elder.Category;
import run.elder.Leetcode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

@Leetcode(
        title = "Maximum Depth of Binary Tree",
        link = "https://leetcode.com/problems/maximum-depth-of-binary-tree/",
        category = Category.TREE,
        how2SolveIt = """
                方法一: 
                
                深度就是层数, 所以可以使用层序遍历
                
                方法二:
                
                深度就是左右子树最大深度 + 1, 可使用递归
                
                这个无论是BFS还是DFS, 都会遍历到所有的结点, 所以时间复杂度是一样的.
                而对于最小深度, DFS还是会遍历到所有结点, 而BFS就不会.
                
                而且, 递归的方法其实就是相当于后序遍历, 先从左子树计算点东西, 再从右子树计算点东西, 然后再计算出一点东西.
                
                即然是后序遍历, 当然也可以使用DFS的非递归形式来计算.
                """,
        relatedQuestions = {}
)
public class Maximum_Depth_of_Binary_Tree {

    static class First {
        public int maxDepth(TreeNode root) {

            int maxDepth = 0;
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
                    maxDepth++;
                }
            }

            return maxDepth;


        }
    }

    static class Second {

        public int maxDepth(TreeNode root) {

            if (root == null) {
                return 0;
            } else {
                int leftMaxDepth = maxDepth(root.left);
                int rightMaxDepth = maxDepth(root.right);
                return Math.max(leftMaxDepth, rightMaxDepth) + 1;
            }
        }
    }
}

package Tree;

import run.elder.Category;
import run.elder.Leetcode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

@Leetcode(
        title = "Find Bottom Left Tree Value",
        link = "https://leetcode.com/problems/find-bottom-left-tree-value/",
        category = Category.TREE,
        how2SolveIt = """
                方法一:
                
                找到最后一行的, 最左边的结点, 这一看就可以使用层序遍历
                
                方法二: 
                
                不能使用DFS, 是因为, 如果你遍历到某个结点, 你并不能判断它是不是最左边的, 而这需要知道其它结点的信息.
                (想想你人工寻找时, 是不是要看其它的结点), 而DFS决定了, 它在遍历过程中, 是无法了解还未遍历到的节点的信息.
                
                所以不能使用DFS
                
                方法三:
                
                依然是层序遍历, 但, 层序即可以从左往右, 又可以从右往左, 如果从右往左的话, 那么, 这最左边的结点, 其实可以看成层序遍历最后
                一个结点了, 这样就不用记录层的信息了.
                
                启示:
                
                层序遍历有两种方向
                
                1. 从左到右
                2. 从右到左
                
                两个方向分别试一试, 看看问题有什么变化.
                
                另外, 把没有层概念的层次遍历, 如果看成一种线性表的迭代, 也是一种直观不容易出错的方式.(这种看法简化了设计不变式的难度)
                
                """,
        relatedQuestions = {}
)
public class Find_Bottom_Left_Tree_Value {

    static class First {
        public int findBottomLeftValue(TreeNode root) {

            Queue<TreeNode> queue = new LinkedList<>();

            // queue保存的是当前将要遍历的层的结点
            queue.add(root);

            // leftMost要处理的有点结点的层的最左边结点
            TreeNode leftMost = root;
            // 每次处理一层的情况
            while (queue.size() != 0) {
                // queue保存的是当前将要遍历的层的结点, count就是该层结点数
                int count = queue.size();
                while (count != 0) {
                    TreeNode node = queue.poll();
                    if (node.left != null) {
                        queue.add(node.left);
                    }

                    if (node.right != null) {
                        queue.add(node.right);
                    }
                    count--;
                }
                // 此时不变式要为真
                // 1. queue保存的是当前将要遍历的层的结点
                // 2. leftMost要处理的层的最左边结点
                if (queue.size() != 0) {
                    leftMost = queue.peek();
                }
            }

            return leftMost.val;

        }
    }

    static class Third {

        public int findBottomLeftValue(TreeNode root) {

            Queue<TreeNode> queue = new LinkedList<>();

            // 注意这里的不变式
            // 把queue看出成是一个线性表, 该线性表的最后一个元素可以看成null
            // next指向下下个元素, pre指向next的前一个
            // 当next为null时, pre正好指向最后一个
            queue.add(root);
            TreeNode pre = null;
            TreeNode next = queue.poll();
            while (next != null) {
                if (next.right != null) {
                    queue.add(next.right);
                }

                if (next.left != null) {
                    queue.add(next.left);
                }
                pre = next;
                next = queue.poll();
            }

            return pre.val;

        }

    }

}

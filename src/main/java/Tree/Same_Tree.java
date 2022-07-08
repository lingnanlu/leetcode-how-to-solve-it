package Tree;

import run.elder.Category;
import run.elder.Leetcode;

import java.util.LinkedList;
import java.util.Queue;

@Leetcode(
        title = "Same Tree",
        link = "https://leetcode.com/problems/same-tree/",
        category = Category.TREE,
        how2SolveIt = """
                各镜像树几乎一样
                
                方法一:
                递归
                
                方法二:
                层序遍历
             
                """,
        relatedQuestions = {}
)
public class Same_Tree {

    static class First {
        public boolean isSameTree(TreeNode p, TreeNode q) {

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

    static class Second {
        public boolean isSameTree(TreeNode p, TreeNode q) {

            Queue<TreeNode> queue1 = new LinkedList<>();
            Queue<TreeNode> queue2 = new LinkedList<>();

            queue1.add(p);
            queue2.add(q);

            while (!queue1.isEmpty() && !queue2.isEmpty()) {
                TreeNode node1 = queue1.poll();
                TreeNode node2 = queue2.poll();

                if (node1 == null && node2 != null) {
                    return false;
                } else if (node1 != null && node2 == null) {
                    return false;
                } else if (node1 != null && node2 != null) {
                    if (node1.val != node2.val) {
                        return false;
                    }
                }

                // 此时两者相同, 要不值一样, 要不就都是null
                if (node1 != null && node2 != null) {
                    queue1.add(node1.left);
                    queue1.add(node1.right);
                    queue2.add(node2.left);
                    queue2.add(node2.right);
                }
            }

            return queue1.isEmpty() && queue2.isEmpty();

        }
    }
}

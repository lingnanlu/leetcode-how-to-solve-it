package Tree;

import run.elder.Category;
import run.elder.Leetcode;

@Leetcode(
        title = "Minimum Absolute Difference in BST",
        link = "https://leetcode.com/problems/minimum-absolute-difference-in-bst/",
        category = Category.TREE,
        how2SolveIt = """
                
                方法一:
                
                将二叉搜索树转换成数组, 这个简单, 就不写了.
                
                
                方法二:
                
                能不能不转换成数组, 依然知道呢? 
                
                其实思路和数组一样, 就是依次找两个相邻的结点, 然后比较差值.
                
                使用两个指针, 一个cur, pre, cur指向当前要遍历的, pre指向前一个
                
                这里最关键的就是, 如何在递归过程中, 修改两个指针
                
                可以先试着修改一个, 比如, 只有cur
                
                这里最关键的是
                
                1. 何时给p赋值: 可以类比print(node.val)
                2. 整个递归可以当成迭代, p就是迭代的指针, 要明白其迭代的顺序, 可类比print(node.val)
                3. 明白了迭代的顺序, 就知道pre怎么找了.
                4. 学会这种把递归当成迭代的思维
                
                """,
        relatedQuestions = {}
)
public class Minimum_Absolute_Difference_In_BST {

    static class First {
        public int getMinimumDifference(TreeNode root) {
            return 0;
        }
    }

    static class Second {

        TreeNode q;
        TreeNode p;

        int min = Integer.MAX_VALUE;
        public int getMinimumDifference(TreeNode root) {
            walk(root);
            return min;
        }

        public void walk(TreeNode node) {

            if (node == null) return;
            // 如果在这里, p指向的结点的顺序, 和先序遍历的顺序一样
            // p = node;
            walk(node.left);

            // 想想在赋值之前p应该在哪里? 就是中序遍历中当前结点的前一个结点.
            q = p;
            // 如果在这里, p指向的结点的顺序就是和中序一样了, 这就和数组的顺序是一样的.可以把这里想像成print(p.val)
            p = node;
            // 从左边dfs返回后, p已经不指向node, 但指向的是左边dfs的最后一个结点

            if (q != null) {
                min = Math.min(min, Math.abs(q.val - p.val));
            }

            walk(node.right);
        }
    }
}

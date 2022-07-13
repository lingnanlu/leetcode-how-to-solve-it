package Tree;

import run.elder.Category;
import run.elder.Leetcode;

@Leetcode(
        title = "Search in a Binary Search Tree",
        link = "https://leetcode.com/problems/search-in-a-binary-search-tree/",
        category = Category.TREE,
        how2SolveIt = """
                
                方法一:
                递归
                
                方法二:
                注意, 这个树是一个特殊的树, 是一个二叉搜索树, 我们能不能利用它的性质来不使用递归来操作呢?
                当然, 使用一般的非递归模板肯定行, 先让我们忘记这个非递归模板, 看看利用它的性质能做什么.
                
                人工的模拟一下, 使用一个指针, 指向一个结点, 如果相等就返回, 不等就根据大小向左或向右, 直到走到null.
                
                这个方法一看也很直观.
                
                启示:
                
                1. 还是要仔细分析题目, 注意到特殊的性质, 看看能不能利用上这特殊的性质.
               
                """,
        relatedQuestions = {}
)
public class Search_In_A_Binary_Search_Tree {

    static class First {
        public TreeNode searchBST(TreeNode root, int val) {
            if (root == null) return null;

            if (root.val == val) return root;
            else if (root.val > val) {
                return searchBST(root.left, val);
            } else {
                return searchBST(root.right, val);
            }
        }
    }

    static class Second {
        public TreeNode searchBST(TreeNode root, int val) {

            TreeNode p = root;
            while (p != null) {
                if (p.val == val) return p;
                else if (p.val > val) p = p.left;
                else p = p.right;
            }

            return p;
        }
    }

}

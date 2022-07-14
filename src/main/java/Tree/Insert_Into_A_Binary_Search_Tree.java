package Tree;

import run.elder.Category;
import run.elder.Leetcode;

@Leetcode(
        title = "Insert into a Binary Search Tree",
        link = "https://leetcode.com/problems/insert-into-a-binary-search-tree/",
        category = Category.TREE,
        how2SolveIt = """
                将一个结点插入到BST中, 仔细一看, 这个查找其实是一个二分查找, 要明白最后终止的指针的含义
                
                画个图模拟一遍就可以了
                """,
        relatedQuestions = {}
)
public class Insert_Into_A_Binary_Search_Tree {

    public TreeNode insertIntoBST(TreeNode root, int val) {

        // node就是二分查找的指针, parent是node的parent
        TreeNode parent = null;
        TreeNode node = root;
        // 画个图可知, node最后指向的位置就是要插入的位置
        // 而parent是其父结点.
        while (node != null) {
            parent = node;
            if (node.val > val) {
                node = node.left;
            } else {
                node = node.right;
            }
        }

        // 此时node == null指向要插入的位置 ,parent是其父结点
        // 那么再判断一次parent与val的关系即可

        if (parent == null) {
            return new TreeNode(val);
        }

        if (parent.val > val) {
            parent.left = new TreeNode(val);
        } else {
            parent.right = new TreeNode(val);
        }

        return root;
    }
}

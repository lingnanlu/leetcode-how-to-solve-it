package Tree;

import run.elder.Category;
import run.elder.Leetcode;

@Leetcode(
        title = "Construct Binary Tree from Preorder and Inorder Traversal",
        link = "https://leetcode.com/problems/construct-binary-tree-from-preorder-and-inorder-traversal/",
        category = Category.TREE,
        how2SolveIt = """
                和另一题一样, 就是注意区间划分 
                """,
        relatedQuestions = {}
)
public class Construct_Binary_Tree_From_Preorder_And_Inorder_Traversal {
    public TreeNode buildTree(int[] preorder, int[] inorder) {
        return walk(inorder, 0, inorder.length, preorder, 0, preorder.length);
    }

    private TreeNode walk(int[] inorder, int inorderStart, int inorderEnd, int[] preorder, int preorderStart, int preorderEnd) {
        // 生成一个节点

        if ((inorderEnd - inorderStart) <= 0) return null;
        int nodeVal = preorder[preorderStart];
        TreeNode node = new TreeNode(nodeVal);

        // 往左子树右, 从左子树返回的就是左子树的根结点
        int pos = inorderStart;
        while (pos != inorderEnd) {
            if (inorder[pos] == nodeVal) {
                break;
            }
            pos++;
        }

        int leftTreeInOrderStart = inorderStart;
        int leftTreeInOrderEnd = pos;
        int leftTreePreOrderStart = preorderStart + 1;
        int leftTreePreOrderEnd = leftTreePreOrderStart + (leftTreeInOrderEnd - leftTreeInOrderStart);
        node.left = walk(inorder, leftTreeInOrderStart, leftTreeInOrderEnd, preorder, leftTreePreOrderStart, leftTreePreOrderEnd);


        // 往右子树走, 从右子树返回的就是右子树的根结点

        int rightTreeInOrderStart = pos + 1;
        int rightTreeInOrderEnd = inorderEnd;
        int rightTreePreOrderStart = leftTreePreOrderEnd;
        int rightTreePreOrderEnd = rightTreePreOrderStart + (rightTreeInOrderEnd - rightTreeInOrderStart);
        node.right = walk(inorder, rightTreeInOrderStart, rightTreeInOrderEnd, preorder, rightTreePreOrderStart, rightTreePreOrderEnd);

        return node;
    }
}

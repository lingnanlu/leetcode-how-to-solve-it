package Tree;

import run.elder.Category;
import run.elder.Leetcode;

@Leetcode(
        title = "Construct Binary Tree from Inorder and Postorder Traversal",
        link = "https://leetcode.com/problems/construct-binary-tree-from-inorder-and-postorder-traversal/",
        category = Category.TREE,
        how2SolveIt = """
                首先, 先人工模拟一下, 发现, 其实是一个递归的过程.
                而且, 人式模拟时, 发现有如下规律
                
                1. 先生成根节点
                2. 再生成左子树
                3. 再生成右子树
                
                这像什么呢? 像一个树的先序遍历.
                
                但这里, 我们就是要生成树啊, 没有树, 哪来的遍历?
                
                其实, 这里也可以当成的一种树的遍历, 这里的访问操作, 其实就是生成结点, 
                
                只是这里的遍历很隐式, 不是显式那么直观.
                
                我们可以试着用walk visit的思想来做
                """,
        relatedQuestions = {}
)
public class Construct_Binary_Tree_From_Inorder_And_Postorder_Traversal {
    public TreeNode buildTree(int[] inorder, int[] postorder) {
        return walk(inorder, 0, inorder.length, postorder, 0, postorder.length);
    }

    private TreeNode walk(int[] inorder, int inorderStart, int inorderEnd, int[] postorder, int postorderStart, int postorderEnd) {
        // 生成一个节点

        if ((inorderEnd - inorderStart) <= 0) return null;
        int nodeVal = postorder[postorderEnd - 1];
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
        int leftTreePostOrderStart = postorderStart;
        int leftTreePostOrderEnd = postorderStart + (leftTreeInOrderEnd - leftTreeInOrderStart);
        node.left = walk(inorder, leftTreeInOrderStart, leftTreeInOrderEnd, postorder, leftTreePostOrderStart, leftTreePostOrderEnd);


        // 往右子树走, 从右子树返回的就是右子树的根结点

        int rightTreeInOrderStart = pos + 1;
        int rightTreeInOrderEnd = inorderEnd;
        int rightTreePostOrderStart = leftTreePostOrderEnd;
        int rightTreePostOrderEnd = rightTreePostOrderStart + (inorderEnd - (pos + 1));
        node.right = walk(inorder, rightTreeInOrderStart, rightTreeInOrderEnd, postorder, rightTreePostOrderStart, rightTreePostOrderEnd);

        return node;
    }
}

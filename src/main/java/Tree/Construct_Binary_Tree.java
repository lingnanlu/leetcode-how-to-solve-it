package Tree;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class Construct_Binary_Tree {

    public TreeNode buildTree(int[] preorder, int[] inorder) {
        return buildTree0(preorder, 0, preorder.length - 1,
                inorder, 0, inorder.length - 1);
    }

    // 从数组构建完全二叉树
    // 其实就是一个层序遍历,
    public TreeNode buildTree(int[] treeInArr) {

        if (treeInArr.length == 0) return null;

        /**
         * 一层一层构建, 相当于使用treeInArr来填充空白结点
         */

        int totalNodes = treeInArr.length;

        TreeNode root = new TreeNode();
        Queue<TreeNode> queue = new LinkedList<>();

        // queue中保存的是将要填充的层
        queue.add(root);

        // i指向treeInArr中, 下一个要使用的值, 如果根节点编号是0的话, 也表示下一个要填充的节点编号.
        int i = 0;

        // 如果值还没有使用完, 就一直填充
        while (i != treeInArr.length) {
            int count = queue.size();
            while (count != 0) {
                // 填充一个结点.
                TreeNode node = queue.poll();
                node.val = treeInArr[i];

                // i此时指向最后一个填充的结点编号.
                // 即于要不要为该结点生成左右结点, 那么要看其左右孩子是不是在总的结点编号内
                if ((2 * i + 1) < totalNodes) {
                    TreeNode left = new TreeNode();
                    node.left = left;
                    queue.add(left);
                }

                if ((2 * i + 2) < totalNodes) {
                    TreeNode right = new TreeNode();
                    node.right = right;
                    queue.add(right);
                }

                // i满足不变式
                i++;
                count--;
            }
        }

        return root;

    }

    // 从数组构建二叉树
    // treeInArr中的null表示要填充空结点
    public TreeNode buildTree(String[] treeInArr) {

        if (treeInArr.length == 0) return null;

        if (treeInArr[0].equals("null")) return null;

        int totalNodes = treeInArr.length;

        TreeNode root = new TreeNode();
        Queue<TreeNode> queue = new LinkedList<>();

        // queue中保存的是将要填充的层, null不属于这一层结点
        queue.add(root);

        // i指向treeInArr中, 下一个要使用的值, 如果根节点编号是0的话, 也表示下一个要填充的节点编号.
        int i = 0;

        // 如果值还没有使用完, 就一直填充
        while (i != treeInArr.length) {
            int count = queue.size();
            while (count != 0) {
                // 填充一个结点.
                TreeNode node = queue.poll();

                // 跳过所有的null
                while (treeInArr[i].equals("null")) {
                    i++;
                }

                node.val = Integer.parseInt(treeInArr[i]);

                // i此时指向最后一个填充的结点编号.
                // 致于要不要为该结点生成左右结点, 那么要看其左右孩子是不是在总的结点编号内
                if ((2 * i + 1) < totalNodes && !treeInArr[2 * i + 1].equals("null")) {
                    TreeNode left = new TreeNode();
                    node.left = left;
                    queue.add(left);
                }

                if ((2 * i + 2) < totalNodes && !treeInArr[2 * i + 2].equals("null")) {
                    TreeNode right = new TreeNode();
                    node.right = right;
                    queue.add(right);
                }

                // i满足不变式
                i++;
                count--;
            }
        }

        return root;

    }

    private TreeNode buildTree0(int[] preorder, int pStart, int pEnd,
                                int[] inorder, int iStart, int iEnd) {

        if (pStart <= pEnd && iStart <= iEnd) {
            int rootVal = preorder[pStart];
            TreeNode root = new TreeNode(rootVal);

            int p = -1;
            for (int i = iStart; i <= iEnd; i++) {
                if(inorder[i] == rootVal) {
                    p = i;
                    break;
                }
            }

            root.left = buildTree0(preorder, pStart + 1, pStart + (p - iStart),
                    inorder, iStart, p - 1);
            root.right = buildTree0(preorder, pStart + (p - iStart) + 1, pEnd,
                    inorder, p + 1, iEnd);

            return root;
        } else {
            return null;
        }

    }

    public static void main(String[] args) {
        Construct_Binary_Tree test = new Construct_Binary_Tree();
//
//        int[] preorder = {3, 9, 20, 15, 7};
//        int[] inorder = {9, 3, 15, 20, 7};
//
//        test.buildTree(preorder, inorder);

        String[] treeInArr = {"1","2","2","null","3","null","3"};
        TreeNode tree = test.buildTree(treeInArr);

        System.out.println(tree.printInList());
    }

}

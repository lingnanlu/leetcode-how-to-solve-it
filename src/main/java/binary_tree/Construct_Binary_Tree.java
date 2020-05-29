package binary_tree;

public class Construct_Binary_Tree {

    public TreeNode buildTree(int[] preorder, int[] inorder) {
        return buildTree0(preorder, 0, preorder.length - 1,
                inorder, 0, inorder.length - 1);
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

        int[] preorder = {3, 9, 20, 15, 7};
        int[] inorder = {9, 3, 15, 20, 7};

        test.buildTree(preorder, inorder);
    }

}

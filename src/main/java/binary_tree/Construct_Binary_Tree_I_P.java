package binary_tree;

public class Construct_Binary_Tree_I_P {

    public TreeNode buildTree(int[] inorder, int[] postorder) {
        return buildTree0(postorder, 0, postorder.length - 1,
                inorder, 0, inorder.length - 1);
    }

    private TreeNode buildTree0(int[] postorder, int pStart, int pEnd,
                                int[] inorder, int iStart, int iEnd) {

        if (pStart <= pEnd && iStart <= iEnd) {
            int rootVal = postorder[pEnd];
            TreeNode root = new TreeNode(rootVal);

            int p = -1;
            for (int i = iStart; i <= iEnd; i++) {
                if(inorder[i] == rootVal) {
                    p = i;
                    break;
                }
            }

            root.left = buildTree0(postorder, pStart, pStart + (p - iStart) - 1,
                    inorder, iStart, p - 1);
            root.right = buildTree0(postorder, pStart + (p - iStart), pEnd - 1,
                    inorder, p + 1, iEnd);

            return root;
        } else {
            return null;
        }

    }

    public static void main(String[] args) {
        Construct_Binary_Tree_I_P test = new Construct_Binary_Tree_I_P();

        int[] inorder = {9, 3, 15, 20, 7};
        int[] postorder = {9, 15, 7, 20, 3};

        test.buildTree(inorder, postorder);
    }
}

package binary_tree;

// 其实想明白一点
// 1. 两个结点， 要不就都在左子树上
// 2. 要不就都在右子树上
// 3. 要不一个在左， 一个在右。此时根结点就是其祖先
public class Lowest_Common_Ancestor_of_BST {

    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {

        if (root == null) return null;

        if (root.val > Math.max(p.val, q.val)) {
            return lowestCommonAncestor(root.left, p, q);
        } else if (root.val < Math.min(p.val, q.val)) {
            return lowestCommonAncestor(root.right, p, q);
        } else {
            return root;
        }
    }


    public TreeNode lowestCommonAncestor2(TreeNode root, TreeNode p, TreeNode q) {

        TreeNode t = root;
        while (t != null) {
            if(t.val > Math.max(p.val, q.val)) {
                t = t.left;
            } else if (t.val < Math.min(p.val, q.val)) {
                t = t.right;
            } else {
                return t;
            }
        }

        return t;
    }
}

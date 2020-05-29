package easy;


public class Same_Tree {

    public boolean isSameTree(TreeNode p, TreeNode q) {

        if (p != null && q != null) {
            return isSameTree(p.left, q.left) && isSameTree(p.right, q.right) && p.val == q.val;
        } else if(p == null && q == null){
            return true;
        } else {
            return false;
        }

    }
}

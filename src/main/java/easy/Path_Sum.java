package easy;

public class Path_Sum {

    public boolean hasPathSum(TreeNode root, int sum) {

        if(root == null) return false;                  //空节点不存在路径
        if(root.left == null && root.right == null) {   //叶子结点
            return root.val == sum;
        }
        int remain = sum - root.val;
        return hasPathSum(root.left, remain) || hasPathSum(root.right, remain);

    }
}

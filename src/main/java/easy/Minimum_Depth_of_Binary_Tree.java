package easy;

public class Minimum_Depth_of_Binary_Tree {

    public int minDepth(TreeNode root) {

        if(root == null) {                              //空结点
            return 0;
        } else if(root.left == null && root.right == null) {    //叶子
            return 1;
        } else if(root.left == null) {                  //左分支为空
            return minDepth(root.right) + 1;
        } else if(root.right == null){                  //右分支为空
            return minDepth(root.left) + 1;
        } else {                                        //都不为空
            return Math.min(minDepth(root.left), minDepth(root.right)) + 1;
        }

    }


}

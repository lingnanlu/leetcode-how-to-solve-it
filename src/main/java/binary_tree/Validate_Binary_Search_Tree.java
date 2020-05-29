package binary_tree;

public class Validate_Binary_Search_Tree {

    public boolean isValidBST(TreeNode root) {

        if (root == null) return true;

        // 判断左子树是否符合要求
        if (root.left != null) {
            TreeNode leftMax = root.left;
            while(leftMax.right != null) {
                leftMax = leftMax.right;
            }
            if (leftMax.val >= root.val || !isValidBST(root.left)) {
                return false;
            }
        }

        // 判断右子树是否符合要求
        if (root.right != null) {
            TreeNode rightMin = root.right;
            while (rightMin.left != null) {
                rightMin = rightMin.left;
            }

            if (rightMin.val <= root.val || !isValidBST(root.right)) {
                return false;
            }
        }

        return true;
    }



//    public boolean isValidBST2(TreeNode root) {
//        // 假想一个父结点， 该结点可以是父结点的左或右
//        return walk(root, Integer.MAX_VALUE, true);
//    }
//
//    /**
//     * 依然是DFS的思想， 对所遍历的每一个结点， 判断其是否符合要求
//     * 进入结点时可以做两件事
//     * 1. 拿到该结点的值， 之后使用
//     * 2. 判断该结点的值是不是满足以下条件
//     *    1. 如果该结点是左结点，比根结点的值要小
//     *    2. 如果该结点是右结点，比根结点的值要大
//     *
//     */
//    public boolean walk(TreeNode node, int parentVal, boolean left) {
//
//        if (node == null) return true;
//        if (left) {
//            if (node.val >= parentVal) return false;
//        } else {
//            if (node.val <= parentVal) return false;
//        }
//
//        if(!walk(node.left, node.val, true)) return false;
//        if(!walk(node.right, node.val, false)) return false;
//
//        return true;
//    }
}

package Tree;

/**
 * 直观的想法就是从root中找到low和high之间的所有点, 然后加起来.
 * 其实还是一个遍历问题, 对于每一个点, 判断是否满足条件, 然后累加起来.
 */
public class Range_Sum_Of_BST {

    public int rangeSumBST(TreeNode root, int low, int high) {
        if (root == null) return 0;
        else return walk(root, low, high);
    }

    // dfs, 每个节点访问一次, 每次访问其实就是判断其是否在low和high之间, 如果在, 那么就累加
    // 返回值为从该结点返回时的累加值
    // 遍历过程中保证不遍历到null
    private int walk(TreeNode node, int low, int high) {
        if (node.val < low) {
            // 满足条件的只可能在右边
            return node.right == null ? 0 : walk(node.right, low, high);
        } else if (node.val > high) {
            // 满足条件的只可能在左边
            return node.left == null ? 0 : walk(node.left, low, high);
        } else {
            return (node.right == null ? 0 : walk(node.right, low, high)) +
                    (node.left == null ? 0 : walk(node.left, low, high)) + node.val;
        }
    }


}

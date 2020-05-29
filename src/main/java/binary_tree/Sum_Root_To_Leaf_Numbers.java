package binary_tree;

import java.util.ArrayList;
import java.util.List;

public class Sum_Root_To_Leaf_Numbers {

    public int sumNumbers(TreeNode root) {

        if (root == null) return 0;

        List<Integer> pathNums = new ArrayList<>();

        walk(0, root, pathNums);

        int result = 0;
        for (int sum : pathNums) {
            result += sum;
        }

        return result;
    }

    /**
     * @param sum 进入该结点时，路径上所有结点所代表的数字
     * @param node
     * @param pathNums  保存所有路径上的数字
     */
    private void walk(int sum, TreeNode node, List<Integer> pathNums) {

        sum = sum * 10 + node.val;

        if (isLeaf(node)) {
            pathNums.add(sum);
        }

        if (node.left != null ) walk(sum, node.left, pathNums);
        if (node.right != null) walk(sum, node.right, pathNums);


    }

    private boolean isLeaf(TreeNode node) {
        return node.left == null && node.right == null;
    }


}

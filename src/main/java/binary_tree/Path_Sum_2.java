package binary_tree;

import java.util.ArrayList;
import java.util.List;

public class Path_Sum_2 {

    public List<List<Integer>> pathSum(TreeNode root, int sum) {
        if (root == null) return new ArrayList<>();
        List<List<Integer>> paths = new ArrayList<>();
        ArrayList<Integer> path = new ArrayList<>();
        walk(root, path, paths, sum);
        return paths;
    }


    /**
     * @param node 要访问的结点
     * @param path DFS访问到该结点的路径
     * @param paths 所有满足条件的路径
     * @param sum 要满足的条件
     */
    public void walk(TreeNode node, ArrayList<Integer> path, List<List<Integer>> paths, int sum) {

        // 进入时， 先将它加入到路径中
        path.add(node.val);

        // 如果满足条件， 就把当前的一份快照保存下来
        if (isLeaf(node) && node.val == sum) {
            paths.add(new ArrayList<>(path));
        }

        int remain = sum - node.val;
        if (node.left != null) walk(node.left, path, paths, remain);
        if (node.right != null) walk(node.right, path, paths, remain);

        // 离开该结点时， 不要忘记删除， 使得path为DFS访问到该结点的路径一直为真
        path.remove(path.size() - 1);



    }

    private boolean isLeaf(TreeNode node) {
        return node.left == null && node.right == null;
    }

}

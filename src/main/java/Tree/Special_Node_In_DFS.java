package Tree;

import run.elder.Category;
import run.elder.Leetcode;

@Leetcode(
        title = "Special_Node_In_DFS",
        link = "",
        category = Category.TREE,
        how2SolveIt = """
               本题是考察DFS的掌握和理解, 以下都要求使用DFS, 即遍历所有节点. 但要返回
               
               1. 树的最左节点
               2. DFS过程中的, 所有子树的最左结点.
               2. 树的最右节点
               3. DFS的最后一个节点
               4. DFS最后一个节点的前一个节点
          
                """,
        relatedQuestions = {}
)
public class Special_Node_In_DFS {

    public TreeNode left;
    public void mostLeft(TreeNode root) {

        if (root == null) return;

        if (root.left == null && left == null) {
            left = root;
        }

        mostLeft(root.left);
        mostLeft(root.right);

    }

    private Result walk(TreeNode node) {

        if (node == null) return new Result(true, 0, 0);

        Result left = walk(node.left);

        System.out.println(left);
        Result right = walk(node.right);

        System.out.println(right);

        return new Result(true, left.min, right.max);
    }

    /**
     * 保存dfs一个node的结果
     */
    static class Result {
        boolean isBST;      // 是不是bst
        int min;            // 最小值
        int max;            // 最大值

        public Result(boolean isBST, int min, int max) {
            this.isBST = isBST;
            this.min = min;
            this.max = max;
        }

        @Override
        public String toString() {
            return "Result{" +
                    "isBST=" + isBST +
                    ", min=" + min +
                    ", max=" + max +
                    '}';
        }
    }



    public TreeNode mostRight(TreeNode root) {
        return null;
    }

    public TreeNode dfsLast(TreeNode root) {
        return null;
    }

    public TreeNode dfsPreLast(TreeNode root) {
        return null;
    }

    public static void main(String[] args) {
        Construct_Binary_Tree constructor = new Construct_Binary_Tree();

        String[] treeInArr = {"5","1","4","null","null","3","6"};
        TreeNode tree = constructor.buildTree(treeInArr);

        tree.prettyPrint();

        Special_Node_In_DFS test = new Special_Node_In_DFS();
        test.mostLeft(tree);

        System.out.println(test.left.val);

    }

}

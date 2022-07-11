package Tree;

import run.elder.Category;
import run.elder.Leetcode;

import java.util.ArrayList;
import java.util.List;

@Leetcode(
        title = "Sum of Left Leaves",
        link = "https://leetcode.com/problems/sum-of-left-leaves/",
        category = Category.TREE,
        how2SolveIt = """
                看来没有很好的方法找到所有的左叶子, 只好DFS找了, 然后保存起来
                """,
        relatedQuestions = {}
)
public class Sum_Of_Left_Leaves {

    public int sumOfLeftLeaves(TreeNode root) {
        List<Integer> leftNodeVals = new ArrayList<>();

        walk(root, false, leftNodeVals);

        int sum = 0;
        for(Integer val :leftNodeVals) {
            sum += val;
        }

        return sum;
    }

    private void walk(TreeNode node, boolean isLeft, List<Integer> leftNodeVals) {

        visit(node, isLeft, leftNodeVals);

        if (node.left != null) {
            walk(node.left, true, leftNodeVals);
        }

        if (node.right != null) {
            walk(node.right, false, leftNodeVals);
        }
    }

    private void visit(TreeNode node, boolean isLeft, List<Integer> leftNodeVals) {
        if (isLeaf(node) && isLeft) {
            leftNodeVals.add(node.val);
        }
    }
    private boolean isLeaf(TreeNode node) {
        return node.left == null && node.right == null;
    }

}

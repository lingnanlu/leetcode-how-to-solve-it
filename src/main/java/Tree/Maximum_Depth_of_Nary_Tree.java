package Tree;

import run.elder.Category;
import run.elder.Leetcode;

@Leetcode(
        title = "Maximum Depth of N-ary Tree",
        link = "https://leetcode.com/problems/maximum-depth-of-n-ary-tree/",
        category = Category.TREE,
        how2SolveIt = """
                和二叉树的类似, 这里只写出递归的.
                
                想想会遍历到所有节点, 且每个节点遍历一次
                """,
        relatedQuestions = {}
)
public class Maximum_Depth_of_Nary_Tree {

    public int maxDepth(Node root) {
        if (root == null) return 0;

        int subTreeMaxDepth = 0;
        for (Node child : root.children) {
            subTreeMaxDepth = Math.max(subTreeMaxDepth, maxDepth(child));
        }

        return subTreeMaxDepth + 1;
    }
}

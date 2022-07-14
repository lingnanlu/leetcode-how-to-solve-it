package Tree;

import run.elder.Category;
import run.elder.Leetcode;

@Leetcode(
        title = "Convert Sorted Array to Binary Search Tree",
        link = "https://leetcode.com/problems/convert-sorted-array-to-binary-search-tree/",
        category = Category.TREE,
        how2SolveIt = """
                
                方法一: 
                递归吧.
                
                方法二:
                
                迭代怎么做? 
                
                其实利用BST的迭代模板就行, 但这题迭代不如递归直观.
                """,
        relatedQuestions = {}
)
public class Convert_Sorted_Array_To_Binary_Search_Tree {

    public TreeNode sortedArrayToBST(int[] nums) {
        return sortedArrayToBST(nums, 0, nums.length - 1);
    }

    // [start, end]
    public TreeNode sortedArrayToBST(int[] nums, int start, int end) {

        // 没有元素的话, 就返回
        if (start > end) return null;

        int middle = (start + end) / 2;

        TreeNode node = new TreeNode(nums[middle]);

        node.left = sortedArrayToBST(nums, start, middle - 1);
        node.right = sortedArrayToBST(nums, middle + 1, end);

        return node;

    }

}

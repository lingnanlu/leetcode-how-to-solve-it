package easy;


public class Convert_Sorted_Array_to_Binary_Search_Tree {

    public TreeNode sortedArrayToBST(int[] nums) {

        if(nums == null || nums.length == 0) return null;

        return sortedArrayToBST(nums, 0, nums.length - 1);
    }

    public TreeNode sortedArrayToBST(int[] nums, int left, int right) {
        if (left <= right) {
            int middle = (left + right) / 2;
            TreeNode node = new TreeNode(nums[middle]);
            node.left = sortedArrayToBST(nums, left, middle - 1);
            node.right = sortedArrayToBST(nums, middle + 1, right);
            return node;
        } else {
            return null;
        }
    }
}

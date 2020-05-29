package search;

/**
 * 本题依然是利用二分法进行一半一半的排除，只是使用的是
 * mid 和 right
 */
public class Find_Minimum_In_Rotated_Sorted_Array {

    public int findMin(int[] nums) {
        int left = 0, right = nums.length - 1;

        // 中止条件举几个例子就好
        while (left != right) {
            int middle = left + (right - left) / 2;
            if (nums[middle] > nums[right]) {
                left = middle + 1; // 注意这里的+1，排除了中间元素
            } else {
                right = middle; // 注意这里没有排除中间元素
            }
        }

        return nums[left];
    }


}

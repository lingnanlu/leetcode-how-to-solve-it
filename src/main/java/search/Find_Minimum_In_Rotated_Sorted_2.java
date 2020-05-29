package search;

/**
 * 其实不一定每次都要排除一半
 * 有时可以排除一半
 * 有时我们可以排除一个
 */
public class Find_Minimum_In_Rotated_Sorted_2 {


    public int findMin(int[] nums) {
        int left = 0, right = nums.length - 1;

        // 中止条件举几个例子就好
        while (left != right) {
            int middle = left + (right - left) / 2;
            if (nums[middle] > nums[right]) {
                left = middle + 1; // 注意这里的+1，排除了中间元素
            } else if (nums[middle] < nums[right]){
                right = middle; // 注意这里没有排除中间元素
            } else {

                // 注意如果是使用left和middle比较的话，是不能排除left的，因为left和middle可能指向同一个元素，
                // 而该元素正好是最小的那个，right就没这个问题，因为right不可能和middle指向同一个元素
                right--; // 这里我们排除一个
            }
        }

        return nums[left];
    }

}

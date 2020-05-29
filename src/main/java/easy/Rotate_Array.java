package easy;


// 如果是可以使用额外的数组, 那么是简单的, 易于理解的
// 如果要求原地, 就三次reverse, 但不直观, 比较trick
public class Rotate_Array {

    public void rotate(int[] nums, int k) {

        // 注意取一个模
        int r = k % nums.length;
        int length = nums.length;
        reverse(nums, 0, nums.length - r - 1);
        reverse(nums, nums.length - r, nums.length - 1);
        reverse(nums, 0, nums.length - 1);
    }

    private void reverse(int[] nums, int start, int end) {
        int left = start, right = end;
        while(left < right) {
            int temp = nums[left];
            nums[left] = nums[right];
            nums[right] = temp;
            left++;
            right--;
         }
    }

}

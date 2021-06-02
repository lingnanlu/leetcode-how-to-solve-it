package question.array;


// 如果是可以使用额外的数组, 那么是简单的, 易于理解的
// 如果要求原地, 就三次reverse, 但不直观, 比较trick

import elder.Category;
import elder.Leetcode;

@Leetcode(
        title = "Rotate Array",
        category = Category.ARRAY,
        howToSolveIt = """
                思路一：
                
                解决不了k次，就解决移动一次，然后再调用k次
                
                思路二：
                
                可以使用一个辅助数组，这样就没有覆盖的问题，然后再把辅助数组替换回来
                
                思路三：
                
                三次reverse，这个相当于一种非常tricky的方式了，记住就行。
                """
)
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

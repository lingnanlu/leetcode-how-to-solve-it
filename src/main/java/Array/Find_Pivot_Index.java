package Array;

import java.util.Arrays;

/**
 * 没什么可说的
 */
public class Find_Pivot_Index {

    public int pivotIndex(int[] nums) {
        int sum = Arrays.stream(nums).sum();

        // 下一个要检查的数字位置
        int i = 0;
        int leftSum = 0;  // 表示[0, i)之间的和
        int rightSum = sum - leftSum - nums[i]; // 表示[i + 1, length)之间的和
        while (i != nums.length) {
            if (leftSum == rightSum) {
                return i;
            } else {
                leftSum += nums[i];
                i++;
                if (i != nums.length) {
                    rightSum = sum - leftSum - nums[i];
                }
            }
        }

        // 所有的位置检查完了.
        return -1;
    }

}

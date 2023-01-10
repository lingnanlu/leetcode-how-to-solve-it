package Array;

/**
 * 其实就是找最大和次大, 比较最大是不是大于次大两倍
 */
public class Largest_Number_At_Least_Twice_Of_Others {


    public int dominantIndex(int[] nums) {

        // 特殊情况处理, 因为下面的算法假设能找出最大和次大.
        if (nums.length == 1) return 0;

        int largest = Integer.MIN_VALUE;
        int second2Largest = Integer.MIN_VALUE;

        int largestIndex = -1;

        for (int i = 0; i < nums.length; i++) {
            if (nums[i] > largest) {
                second2Largest = largest;
                largest = nums[i];
                largestIndex = i;
            } else if (nums[i] > second2Largest) {
                second2Largest = nums[i];
            }
        }

        if (largest >= second2Largest * 2) {
            return largestIndex;
        } else {
            return -1;
        }


    }
}

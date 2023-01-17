package Array;

import java.util.Arrays;

/**
 * 这题很简单, 其实就是找最大的两个数
 */
public class Maximum_Product_Of_Two_Elements_In_an_Array {
    public int maxProduct(int[] nums) {
        int max = Integer.MIN_VALUE;
        int second2max = Integer.MIN_VALUE;
        for (int n : nums) {
            if (n > max) {
                second2max = max;
                max = n;
            } else if (n > second2max) {
                second2max = n;
            }
        }

        return (max - 1) * (second2max - 1);
    }
}

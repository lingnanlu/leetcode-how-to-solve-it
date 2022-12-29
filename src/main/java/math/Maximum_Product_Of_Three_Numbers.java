package math;

import java.util.Arrays;

/**
 * 很自然的想到找3个最大的数相乘.
 *
 * 但其实这是错误的, 如果有负数呢?
 *
 * 如果全是正数, 或全是负数, 都好说.
 *
 * 麻烦就麻烦在, 有正有负的情况. 如果有正有负, 怎么快速找出三个数呢?
 *
 * 其实有正有负的情况下, 就是找所有3个数组合中, 乘积最大的.
 *
 * 这里不必穷举两个数的所有组合, 只要在以下几种情况下进行比较就好.
 *
 * 要不就是3个正数, 要不就是1正两负.
 *
 * 注意0, 0当成正数来处理.
 *
 * todo 一遍遍历找最小, 第二小, 最大, 第二大, 第三大
 *
 */
public class Maximum_Product_Of_Three_Numbers {

    static class First {
        public int maximumProduct(int[] nums) {
            Arrays.sort(nums);

            // 统计正负数 positiveCount包括0
            int negativeCount = 0;
            int positiveCount = 0;

            for (int n : nums) {
                if (n < 0) {
                    negativeCount++;
                } else {
                    positiveCount++;
                }
            }

            // 全是非负数
            if (negativeCount == 0 || positiveCount == 0) {
                return nums[nums.length - 1] * nums[nums.length - 2] * nums[nums.length - 3];
            }

            // 即有正, 也有负
            if (positiveCount >= 3) {
                // 正数有两个情况下, 只比较两种乘积为正数的情况就好
                return Math.max(nums[nums.length - 1] * nums[nums.length - 2] * nums[nums.length - 3],
                        nums[nums.length - 1] * nums[0] * nums[1]);
            } else {
                // 正数只有1或2个, 那么就是最小的两个负数和它的乘积
                return nums[0] * nums[1] * nums[nums.length - 1];
            }
        }
    }

}

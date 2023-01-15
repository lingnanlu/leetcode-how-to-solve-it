package Array;

/**
 * 感觉这题并不难, 题目中已说出了方法.
 * 一个感觉就是, 在计算平均数时, 可能可以简化一些(因为每次变的只有两个值)
 *
 * 方法二:
 *
 * 方法一的时间执行时间有点长, 怎么优化一下呢?
 * 方法一中有浮点运算, 能不能干掉?
 *
 * 其实是可以的, 中间过程的浮点计算没有用.
 */
public class Maximum_Average_Subarray_I {

    static class First {
        public double findMaxAverage(int[] nums, int k) {
            // 先找到第一个子数组平均做为0, 作为基准.

            int i = 0;
            double sum = 0;
            double average = 0;
            while (i != k) {
                sum += nums[i];
                i++;
            }

            // 此时sum和average是第一个子数组, 作为基准.
            // i为下一个子数组的末尾
            average = sum / k;

            double maxAverage = average;

            while (i != nums.length) {
                // 更新到下一个子数组.
                sum -= nums[i - k];
                sum += nums[i];

                average = sum / k;
                maxAverage = Math.max(maxAverage, average);

                i++;
            }

            return maxAverage;



        }
    }

    static class Second {
        public double findMaxAverage(int[] nums, int k) {
            // 先找到第一个子数组平均做为0, 作为基准.

            int i = 0;
            int sum = 0;
            while (i != k) {
                sum += nums[i];
                i++;
            }

            // i为下一个子数组的末尾
            int maxSum = sum;
            while (i != nums.length) {
                // 更新到下一个子数组.
                sum -= nums[i - k];
                sum += nums[i];

                maxSum = Math.max(maxSum, sum);
                i++;
            }

            return maxSum / (k + 0.0);



        }
    }
}

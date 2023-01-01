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
 * 注意 0 可以归为正数
 *
 * 1. 全是正数 => 三个最大的乘积
 * 2. 全是负数 => 三个最大的乘积
 * 3. 一正两负 => 两个最小的, 一个最大的
 * 4. 一正多负 => 两个最小的, 一个最大的
 * 5. 二正一负 => 三个最大的乘积
 * 6. 二正多负 => 两个最小的, 一个最大的
 *
 * 可见, 其实就是两种情况
 *
 * 三个最大的, 两个最小的, 一个最大的.
 *
 * 所以比较以上两种就行
 *
 * 方法一:
 *
 * 先排序, 然后找到三个最大, 两个最小
 *
 * 方法二:
 *
 * 利用类似找最大的方式来找到三个最大和两个最小
 *
 */
public class Maximum_Product_Of_Three_Numbers {

    static class First {
        public int maximumProduct(int[] nums) {
            Arrays.sort(nums);

            int firstMax = nums[nums.length - 1];
            int secondMax = nums[nums.length - 2];
            int thirdMax = nums[nums.length - 3];

            int firstMin = nums[0];
            int secondMin = nums[1];

            return Math.max(firstMax * secondMax * thirdMax, firstMin * secondMin * firstMax);
        }
    }



    static class Second {

        public int maximumProduct(int[] nums) {

            /**
             * 模仿找最大的方式来找次大, 次次大.
             * 但这里应该初始化为什么值呢?
             * 想想找最大时
             *
             * int max = Integer.MIN;
             *
             * 为什么要初始化为min呢? 因为max的含义是到目前为止, 找到的最大的数.
             * 而在开始遍历之前, 还没遍历过一个真正的数, 那么, 这个找到的最大的数应该是多少呢?
             *
             * 相当与我们已经遍历了一堆不存在的数, 然后从这些不存在的数中找到最大的.
             *
             * 我们可以这样想, 在第一个数前, 我们安排一些数, 而从这些数中找最大.
             *
             * 那么应该是哪些数呢? 可以形象化一些. 我们想象一个数轴, 然后每个数字其实是一种高度
             *
             * 现在, nums的数字在数轴上就是此起彼伏的, 最大的也就是找最高的.
             *
             * 那么, nums之前的数字应该是什么样的? 之前的最高应该是多少呢? 我想之前的最高应该是一个基准, 让nums中的每一个都和它比.
             * 它不应该影响到最后从nums中找一个最高的标准.
             *
             * 同理, 次大和次次大也一样. 都是可以赋值为Integer.MIN
             *
             * 而次小也一样.
             */
            int firstMax = Integer.MIN_VALUE;
            int secondMax = Integer.MIN_VALUE;
            int thirdMax = Integer.MIN_VALUE;

            int firstMin = Integer.MAX_VALUE;
            int secondMin = Integer.MAX_VALUE;

            // 通过一遍循环找到以上五个值
            for (int i = 0; i < nums.length; i++) {
                int current = nums[i];

                if (current >= firstMax) {
                    thirdMax = secondMax;
                    secondMax = firstMax;
                    firstMax = current;
                } else if (current >= secondMax) {
                    thirdMax = secondMax;
                    secondMax = current;
                } else if (current >= thirdMax) {
                    thirdMax = current;
                }

                if (current <= firstMin) {
                    secondMin = firstMin;
                    firstMin = current;
                } else if (current <= secondMin) {
                    secondMin = current;
                }
            }

            return Math.max(firstMax * secondMax * thirdMax, firstMin * secondMin * firstMax);


        }

    }

}

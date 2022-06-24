package Array.SlideWindow;

import run.elder.Category;
import run.elder.Leetcode;

@Leetcode(
        title = "Minimum Size Subarray Sum",
        link = "https://leetcode.com/problems/minimum-size-subarray-sum/",
        category = Category.ARRAY,
        how2SolveIt = """
             
                Q. 已知是什么?
                
                A. 
                1. 正整数数组, 每个元素都 > 0
                2. 一个target, 也是 > 0
                
                Q. 要求的是什么
                
                A. 连续子数组, 要求和 >= target
                
                Q. 有什么限制
                
                A. 该连续子数组必须size是最小的.
                
                
                方法一: 
                
                找出所有满足条件的, 然后, 找出size最小的. 这是暴力解法
                
                方法二:
                
                [2, 3, 1, 2, 4, 3]
                target = 7
                
                在方法一个基础上考虑优化, 就是尽量减少不必要的子区间
                
                比如, 我们先从2开始找
                
                当找到, 2, 3, 1, 2 > 7时, 就是找到一个解, 就不必再找下去了, 因为, 2, 3, 1, 2, 4肯定比 2, 3, 1, 2大
              
                此时, 我们就找到了以2开始的最短是 [2, 3, 1, 2]
                下一步我们找以3开始的.
                [3, x, x, ..., x]
                那么, 要不要从[3, 1]开始计算呢? 
                
                其实不需要的, 因为, 3, 1是[2, 3, 1]的子区间, 肯定不符合要求, 所以, 要从[3, 1, 2]开始计算.
                
                这里其实有一个证明, 即
                
                如果
                
                [x1, x2, x3, x4, ..., xn]是以x1开头的满足最小的话, 那么
                [x2, x3, x4, ..., xn-1]就一定不满足.
                
                所以从3开始找的区间第一个就是[3, 1, 2]
                
                这里似乎就用到了两个指针. 而且两个指针都是从头到尾进行遍历, 两个指针之间的子数组和计算并没有重复计算, 所以, 可知时间复杂度是
                
                O(n)
            
                本题的启示就是, 你可以在不知道什么滑动窗口的情况下, 先想出一个暴力的方法, 然后通过分析暴力方法中的多余的计算进行去除, 就可以得到
                一个优化的解.
                
                本次是自己想出来的, 而且一遍过.
                """,
        relatedQuestions = {}
)
public class Minimum_Size_Subarray_Sum {

    public int minSubArrayLen(int target, int[] nums) {

        int length = nums.length;
        int minLength = length + 1;
        /**
         * 注意这里sum的含义其实有两个
         * 1. 在sum -= nums[i];之后, 是下一次要找的第一个区间的和
         * 2. 在sum -= nums[i];之前, 是本次找到的最短区间的和
         * 循环不变式就是循环结束时为真, 所以sum就是下一次要找的第一个区间的和.
         * 由此可知, sum = nums[0]
         */
        int sum = nums[0];
        /**
         * 由过程可知, 当循环结束时, j应该停留在本轮所找到的最短子区间的末尾.
         * 而这里是循环开始前. j不可能停留在某一轮的最短子区间的末尾, 所以设置一个值
         * 让第一次循环有意义就行.
         * 这里设置成0是有意义的, 因为此时的区间是[0, 0],该区间的和是nums[0], 其也正是我们所应该检查的第一个区间
         * 所以这里的解释也是完美的.
         */
        int j = 0;
        for (int i = 0; i < length; i++) {
            // 每一次找到以i为开头的, 满足条件的最短区间

            /**
             * 假如找到了, i, j的位置确定了.
             *
             * 1. 计算迄今为止的最短
             * 2. 为下一次循环做准备
             *
             * 一直改变j, 直到找到一个>=target的区间.
             *
             */
            while (sum < target) {
                // 将j向后移动一位
                j++;
                if (j != length) {
                    sum += nums[j];
                } else {
                    break;
                }
            }

            /**
             * 以nums[i]开头的, 找不到满足的, 就不用再找nums[i+1]开头的了
             * 由循环不变式可以, minLength就是最终结果了.
             */
            if (j == length) {
                break;
            }

            minLength = Math.min(minLength, j - i + 1);

            // 在此之前, sum是本次找到的最短区间的和
            sum -= nums[i];
            // 在此之后, sum是下一次要找的第一个区间的和
        }

        if (minLength == length + 1) {
            return 0;
        } else {
            return minLength;
        }
    }
}

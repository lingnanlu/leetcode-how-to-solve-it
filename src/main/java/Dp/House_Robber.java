package Dp;

import run.elder.Category;
import run.elder.Leetcode;

@Leetcode(
        title = "House Robber",
        link = "https://leetcode.com/problems/house-robber/",
        category = Category.ARRAY,
        how2SolveIt = """
  
                方法一:
                
                猛一看, 依然是没有什么直观的思路, 但是不妨看一下解的形式, 其实就是从原数组中找一个子数组, 使子数组的元素和最大, 并且子数组
                中两两之间不相邻.
                
                那么, 就是想办法找出所有满足限制的子数组, 然后, 从这些解中找最大的.
                
                对于每一个元素, 可以有选或不选两种, 所以可以使用DFS
                
                方法二:
                
                还是给定一个数组, 要求一个数
                
                嗯, 是不是之前做过很多很类似的, 即
                
                求f(n) = m
                
                于是乎, 我们自然想到递推的方式, 所以, 这里就是要找 f(n) 和 f(n - i)的关系.
                
                首先搞明白 f(n)的含义, 那么是什么含义呢?
                
                f(n) 为 前n个中能偷窃的最多, 那么, f(n)和f(n - 1)是什么关系呢?
                
                仔细想一下, 这个很难推导出来, 因为f(n - 1)虽然是前(n - 1)个最多, 但无法知道第n - 1个选还是没选.
                
                在这里, 知道第i个选还是没选还是很重要的.
                
                我们不如变换一下, 比如说 f(n)为以n结尾的最大, 我们看看能不能行.
                
                f(n) = max(f(n - 2) + a[n] , f(n - 3) + a[n] ,  f(0) + a[n])
                
               
                由此, 我们可以求出数组中, 每一个以n结尾的最大, 
                
                那么, a[n]数组中的最大值, 肯定是以数组中的某一个元素结尾的, 所以, 我们就在所有f(n)中找最大就好了.
                
                方法三:
                
                方法二好像可以优化一下, 由递推公式可知, 计算f(n)时, 要计算f(0) .. f(n - 2)的最大值, 这里好像有重复计算了.
                
                比如 f(5) 要计算f(0 - 3)
                f(6) 要计算f(0 - 4)
                
                这里, f(0 - 3)的是重复的, 在计算f(0 - 4)时, 可以利用f(0 - 3)的, 所以, 想办法缓存下来
                
                
                启示: 
                
                其实方法三就是dp[i] = max(dp[i - 2] + nums[i], dp[i - 1])
                但自己利用另一种自然的逻辑的方式, 也达到了效果, 这说明了, 不必纠结与模板, 就按照自然的逻辑也可以解出来这个题目.
                所以, 依靠自己来想出答案很重要.
                """,
        relatedQuestions = {}
)
public class House_Robber {

    static class Second {
        public int rob(int[] nums) {

            // 处理0, 1的情况

            if (nums.length == 1) {
                return nums[0];
            }

            if (nums.length == 2) {
                return Math.max(nums[0], nums[1]);
            }

            int[] dp = new int[nums.length];

            dp[0] = nums[0];
            dp[1] = nums[1];


            for (int i = 2; i < dp.length; i++) {
                dp[i] = 0;
                for (int j = 0; j <= i - 2; j++) {
                    dp[i] = Math.max(dp[j] + nums[i], dp[i]);
                }
            }

            int max = 0;
            for (int i = 0; i < dp.length; i++) {
                max = Math.max(max, dp[i]);
            }

            return max;

        }
    }

    static class Third {

        public int rob(int[] nums) {

            // 处理0, 1的情况

            if (nums.length == 1) {
                return nums[0];
            }

            if (nums.length == 2) {
                return Math.max(nums[0], nums[1]);
            }

            // dp[i]表示以i为结尾的最大值.
            int[] dp = new int[nums.length];

            dp[0] = nums[0];
            dp[1] = nums[1];

            // max[i] 表示 [0, i]之间的最大值
            int[] max = new int[nums.length];

            max[0] = dp[0];
            max[1] = Math.max(dp[0], dp[1]);

            for (int i = 2; i < dp.length; i++) {
                /**
                 * 每一轮要求 dp[i]
                 * 此时已知 dp[0... i - 1]
                 * 以及 max[0... i - 2]
                 */
                dp[i] = max[i - 2] + nums[i];

                // 更新max[i]
                max[i] = Math.max(max[i - 1], dp[i]);
            }


            return max[nums.length - 1];

        }

    }

}

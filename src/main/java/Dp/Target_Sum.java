package Dp;

import run.elder.Category;
import run.elder.Leetcode;

import java.util.Arrays;

@Leetcode(
        title = "Target Sum",
        link = "https://leetcode.com/problems/target-sum/",
        category = Category.ARRAY,
        how2SolveIt = """
                
                方法一:
                
                看到, 每个元素可以有两种变化, +, -, 这里其实就是穷举出所有的 +, -序列, 这就是解空间.
                然后从这个解空间中, 找到所有满足条件的解.
                
                使用DFS即可
                
                方法二:
                
                没有什么直观的思路, 还是一个n的问题, 我们试着用一般的递推公式来看看
                
                比如 f(n)和f(n - 1)是什么关系
                
                发现
                               // 添加正号                       // 添加负号
                f(n)(target) = f(n - 1)(target - nums(n)) + f(n - 1)(target + nums(n))
                
                又是一个二维的递推公式.
                
                关于递推公式, 最重要的是
                
                1. 确定每一维的维度.
                2. 确定初始值.
                3. 确定如何由初始值递推到最终值
                

                一 确定每一维的维度 
                第一维比较简单, 为了方便起见, 我们使用 n + 1, 那么第二维呢? 
                要想知道第二维的维度, 必须知道其最小和最大值. 
               
                仔细观察递推公式, 发现, 整个递推过程类似一个完全二叉树, 最左叶子和最右叶子是两个边界.
               
                其最小为 target - sum(nums) 
                
                最大为   target + sum(nums) 
                
                那么, 因为数组下标都是正的, 所以要把它映射到数组下标中, 
                
                target - sum(nums) ~ 0
                target + sum(nums) ~ 2 * sum(nums)
                
                不难发现 映射关系就是
                
                真正的target + (-target + sums(nums))
                
                二 确定初始值
                
                现在, 有了二维的每一个维度, 那么, 初始值应该是多少呢? 要初始化哪些值呢?
                
                还是观察递推公式, 初始值应该是第0行或第一行.
                
                那么应该是第0行还是第一行?
                
                看下最终会递推到哪里, 最后应该是
                
                f(1)(m) = f(0)(m - nums(1)) + f(0)(m + nums(1))
                
                所以, 应该是第0行, 那么第0行是什么含义呢? 比如
                
                f(0)(5)是什么含义呢? 其实就是选择0个元素, 添加+, -, 然后组成5, 的数目, 这显然是0
                
                而f(0)(0)显然是1.
                
                而对于一个具体的问题, 比如target为3, sum = 5, 那么, 就是初始化
                
                f(0)(-2) ~ f(0)(8)的值
                
                而这里面, 只有f(0)(0)为一.
                
                所以, 其它都是0
                
                又因为, 我们做了偏移, 这里的偏移量是 2, 所以
                
                要初始化的为
                
                dp[0][0, 10] 
                
                dp[0][2] = 1;
                
                三. 确定如何由初始值递推到最终值
             
                即然有了初始值, 那么, 就要看由初始值怎么得到下一个值.
                
                这里下一个值其实就是f(1)(x)的情况, 这里x = -2 ~ 8
                
                也就是 dp[1][0 ~ 10]
                
                由递推公式可知
                
                f(1)(x) = f(0)(x - nums(1)) + f(0)(x + nums(1))
                
                加上便宜量就是
                
                dp[1][x + shift] = dp[0][x - nums(1) + shift] + dp[0][x + nums(1) + shift]
                
                但这里有个问题, 就是x -nums(1) + shift和 x + nums(1) + shift可能超越二维边界. 那么, 如果超越了, 是什么含义呢?
                
                比如说 x - nums(1) + shift == -3, 即dp[0][-3]是什么含义呢? 其实还是选择0个元素, 然后组成 -3, 这显然也是不可能的
                
                同理, 超出的, 也是不可能的, 所以也是0.
                
                到此可以写出完整代码了.
                
                启示
                1. 一定要搞明白每一个值的含义, 想不明白可以举例子
                2. 由递推公式可以想象一下递推结构, 这里最终其实是从一个完全二叉树的最后一层, 作为初始化的值, 然后推倒数第二层, 再推倒数
                第三层.
                
                本题也是一遍过, 思路和leetcode上的基本一致, 但是在这个过程中, 并没有去考虑是不是背包问题等等. 而是使用最基本的数学归纳法
                和找空间.
                
                """,
        relatedQuestions = {}
)
public class Target_Sum {

    static class First {
        public int findTargetSumWays(int[] nums, int target) {
            return 0;
        }
    }

    static class Second {
        public int findTargetSumWays(int[] nums, int target) {

            int sum = Arrays.stream(nums).sum();

            // 确定每一维, 这里的+1是为了方便
            int[][] dp = new int[nums.length + 1][2 * sum + 1];

            // 偏移量
            int shift = -target + sum;

            // 一 初始化
            /*
            表示全部添加 + 都比target小, 或者全部添加 - 都比target大.
             */
            if (target > sum || target < -sum) {
                return 0;
            }

            for (int i = 0; i <= 2 * sum ; i++) {
                dp[0][i] = 0;
            }

            // 其实就是对应f(0)(0)
            dp[0][sum - target] = 1;

            for (int i = 1; i <= nums.length; i++) {
                for (int j = target - sum; j <= target + sum; j++) {
                    int left = j - nums[i - 1] >= target - sum ? dp[i - 1][j - nums[i - 1] + shift] : 0;
                    int right = j + nums[i - 1] <= target + sum ? dp[i - 1][j + nums[i - 1] + shift] : 0;
                    dp[i][j + shift] = left + right;
                }
            }

            return dp[nums.length][target + shift];
        }
    }



}

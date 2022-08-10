package Dp;

import run.elder.Category;
import run.elder.Leetcode;

import java.util.Arrays;

@Leetcode(
        title = "Last Stone Weight II",
        link = "https://leetcode.com/problems/last-stone-weight-ii/",
        category = Category.ARRAY,
        how2SolveIt = """
                
                先考虑一下容易想到的方案
                
                方法一:
                
                从左往右两两碰撞, 显然不是最优的.
                
                方法二:
                
                从小到大排序之后, 从左往右两两碰撞, 但这依然不能证明这种碰撞方法就是最优的.
                
                方法三:
                
                先不考虑实现, 怎样才能最优呢? 我们选择两个石头应该选择哪一个呢? 其实真不好说, 因为选择哪两个石头还要看剩下的石头
                即考虑局部时, 那还要考虑全局.
                
                所以, 我们能不能把所有的解穷举出来? 在这个解里, 选择最优? 
                
                这里的解是什么呢? 其实就是一个全排列, 我所找到所有全排列, 然后每一个全排列就是一种碰撞顺序, 计算出每一种的结果, 然后找最大.
                
                方法四:
                
                这个思路真想不到, 就是转换一下题目
                
                将石头分成两堆, 使其中一堆尽量接近 half. 其实又是一个背包问题
                
                f(n)(weight) = max(f(n - 1)(weight), f(n - 1)(weight - stones[n]) + stones[n])
                
                
                
                
                """,
        relatedQuestions = {}
)
public class Last_Stone_Weight_II {

    static class Forth {
        public int lastStoneWeightII(int[] stones) {

            int sum = Arrays.stream(stones).sum();
            int target = (int) Math.floor(sum / 2);

            int[][] dp = new int[stones.length][target + 1];

            // 初始化第0行
            for (int i = 0; i <= target; i++) {
                dp[0][i] = stones[0] <= i ? stones[0] : 0;
            }

            // 初始化其它行
            for (int i = 1; i < stones.length; i++) {
                for (int j = 0; j <= target; j++) {
                    if (stones[i] > j) {
                        dp[i][j] = dp[i - 1][j];
                    } else {
                        dp[i][j] = Math.max(dp[i - 1][j], dp[i - 1][j - stones[i]] + stones[i]);
                    }
                }
            }

            // dp[stones.length][target]就是能装到背包的最大值.
            return Math.abs(sum - dp[stones.length - 1][target] - dp[stones.length - 1][target]);
        }
    }

    // 一维数组
    static class Fifth {

        public int lastStoneWeightII(int[] stones) {

            int sum = Arrays.stream(stones).sum();
            int target = (int) Math.floor(sum / 2);

            int[] dp = new int[target + 1];

            // 初始化第0行
            for (int i = 0; i <= target; i++) {
                dp[i] = stones[0] <= i ? stones[0] : 0;
            }

            // 初始化其它行
            for (int i = 1; i < stones.length; i++) {
                for (int j = target; j >= 0; j--) {
                    if (stones[i] <= j) {
                        dp[j] = Math.max(dp[j], dp[j - stones[i]] + stones[i]);
                    }
                }
            }

            // dp[stones.length][target]就是能装到背包的最大值.
            return Math.abs(sum - dp[target] - dp[target]);
        }

    }

}

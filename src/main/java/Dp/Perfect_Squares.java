package Dp;


import run.elder.Category;
import run.elder.Leetcode;

import java.util.Arrays;

@Leetcode(
        title = "Perfect Squares",
        link = "https://leetcode.com/problems/perfect-squares/",
        category = Category.ARRAY,
        how2SolveIt = """
                
                这题初一看, 似乎是没见过, 不过没关系, 我们先试着用自己的话描述一遍, 尽量使用数学的语言
                
                1. x1 + x2 + ... xM = n
                2. x1, x2, ... xM为完全平方数
                3. 使完全平方数的个数最少.
                
                不妨举个例子, 比如n = 12, 那么, 小于等于12的完全平方数为[1, 4, 9]
                
                所以就是从[1, 4, 9]中选尽可能少的数, 组成12.
                
                这样一来, 其实就和之前coin change 一样了. 只是多了一步, 先确定数组而已.
                
                题目中, 1 <= n <= 10000, 所以, 可以把[1, 100]完全平方数全部列举出来
                
                                 
                还是设要求的是f(n)
                
                f(n) = min(f(n - 1), f(n - 4), f(n - 9) ... f(n - m)) + 1, n > m 
                
                f(0)
                
                """,
        relatedQuestions = {}
)
public class Perfect_Squares {

    // [1, 100]的完全平方数.
    private static int[] squares = new int[100];

    static {
        for (int i = 0; i < 100; i++) {
            squares[i] = (i + 1) * (i + 1);
        }
    }

    public int numSquares(int n) {

        int[] dp = new int[n + 1];

        // 为了选择最小
        Arrays.fill(dp, Integer.MAX_VALUE);

        dp[0] = 0;
        for (int i = 1; i <= n ; i++) {
            for (int j = 0; j < squares.length; j++) {
                if (i >= squares[j]) {
                    dp[i] = Math.min(dp[i], dp[i - squares[j]]);
                }
            }

            dp[i] += 1;
        }

        return dp[n];

    }
}

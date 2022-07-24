package Dp;

import run.elder.Category;
import run.elder.Leetcode;

@Leetcode(
        title = "Unique Paths",
        link = "https://leetcode.com/problems/unique-paths/",
        category = Category.ARRAY,
        how2SolveIt = """
              
                方法一
                
                又是走n步, 每一步有多种选择的问题, 一看就是dfs
                
                一共走 m + n - 2步, 也就是树的高度
                
                每一步左右两种走法, 也就是二叉树.
                
                估计就是dfs所有结点, 时间复杂度就是遍历的节点的个数, 其实近似一个满二叉树的结点个数计算.
                
                方法二:
                
                我们要走到最后一个结点, 在些之前要走到它左边或上面的结点
                
                这有没有像走楼梯? 
                
                其实可以写出
                
                dp[m, n] = dp[m, n - 1] + dp[m - 1, n]
                
                现在就是找初始化, 以及如何递推来最终算出dp[m, n]了
                
                其实画一画图就知道, 需要把这个二维的所有点的dp填满, 所以, 我们二维的进行遍历就好
                
                时间复杂度为每个方格计算一次, 所以为O(m * n)
                方法三:
                
                在方法二画图过程中, 某一行被用过之后, 就不再需要了, 所以在空间利用上,可以再优化一些.
                
                
                启示:
                
                1. 动规往往在空间上可以优化, 因为一些历史的值不再需要了, 仔细分析动规过程, 就可以消除一些空间的利用
                
                一维是这样的, 二维也是
                
                2. 动规的时间复杂度可以不考虑代码, 就直观的看每一个状态是如何计算出来的, 计算了多少次, 就可以预估了
                """,
        relatedQuestions = {}
)
public class Unique_Paths {

    static class First {
        public int uniquePaths(int m, int n) {
            return 0;
        }
    }

    static class Second {
        public int uniquePaths(int m, int n) {
            int[][] dp = new int[m][n];

            dp[0][0] = 1;
            for (int i = 0; i < m; i++) {
                for (int j = 0; j < n; j++) {
                    // 填充dp[i][j]
                    // 跳过初始化结点
                    if (i == 0 && j == 0) continue;
                    int left = j == 0 ? 0 : dp[i][j - 1];
                    int up = i == 0 ? 0 : dp[i - 1][j];
                    dp[i][j] = left + up;
                }
            }

            return dp[m - 1][n - 1];
        }
    }
}

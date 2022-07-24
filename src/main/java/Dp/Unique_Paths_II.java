package Dp;

import run.elder.Category;
import run.elder.Leetcode;

@Leetcode(
        title = "Unique Paths",
        link = "https://leetcode.com/problems/unique-paths/",
        category = Category.ARRAY,
        how2SolveIt = """
              
                各一相比加了障碍, 
                
                同样可以使用DFS, 就是多一些剪枝
                
                我们来看看动规怎么做.
                
                动规的递推公式为
                
                dp[m, n] = dp[m, n - 1] + dp[m - 1, n]
                
                但假如左边或上边为障碍, 那么, 这一项设计为0不就好了, 其实就是把有障碍的位置设置为0,
                """,
        relatedQuestions = {}
)
public class Unique_Paths_II {

    public int uniquePathsWithObstacles(int[][] obstacleGrid) {

        int m = obstacleGrid.length;
        int n = obstacleGrid[0].length;
        int[][] dp = new int[m][n];

        if (obstacleGrid[0][0] == 1) return 0;

        dp[0][0] = 1;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                // 填充dp[i][j]
                // 跳过初始化结点
                if (i == 0 && j == 0) continue;

                if (obstacleGrid[i][j] == 1) {
                    dp[i][j] = 0;
                } else {
                    int left = j == 0 ? 0 : dp[i][j - 1];
                    int up = i == 0 ? 0 : dp[i - 1][j];
                    dp[i][j] = left + up;
                }
            }
        }

        return dp[m - 1][n - 1];

    }
}

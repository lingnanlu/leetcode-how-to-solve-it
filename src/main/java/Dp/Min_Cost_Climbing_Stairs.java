package Dp;

import run.elder.Category;
import run.elder.Leetcode;

@Leetcode(
        title = "Min Cost Climbing Stairs",
        link = "https://leetcode.com/problems/min-cost-climbing-stairs/",
        category = Category.ARRAY,
        how2SolveIt = """
              
                cost.length就是top
                
                记mincost[n]为走到n的最小共费
                
                mincost[n] 和 mincost[n - 1] , mincost[n - 2]有关, 因为只能走一步或走两步, 这和爬楼梯很像.
                
                mincost[n] = Math.min(mincost[n-1] + cost[n-1], mincost[n - 2] + cost[n - 2])
                
                注意要把cost加上, 因为要从上一步阶梯走, 要有成本的.
                
                现在就是
                
                mincost[0], mincost[1]
                
                因为可以直接走到第0或第一个阶梯, 所以花费都是0, 这就是初始化.
                """,
        relatedQuestions = {}
)
public class Min_Cost_Climbing_Stairs {
    public int minCostClimbingStairs(int[] cost) {
        int[] mincost = new int[cost.length + 1];

        // 走到第0和第1都是0花费
        mincost[0] = 0;
        mincost[1] = 0;

        for (int i = 2; i <= mincost.length - 1 ; i++) {
            mincost[i] = Math.min(mincost[i - 1] + cost[i - 1], mincost[i - 2] + cost[i - 2]);
        }

        return mincost[mincost.length - 1];
    }
}

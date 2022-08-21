package Dp;

import run.elder.Category;
import run.elder.Leetcode;

@Leetcode(
        title = "Coin Change",
        link = "https://leetcode.com/problems/coin-change/",
        category = Category.ARRAY,
        how2SolveIt = """
                方法一:
                
                一看没什么思路, 一看就是凑数, 一看又是最少, 一看又是无限多个, 还是递推
                
                f(n)(amount)为凑成amount的最少数目
                
                f(n)(amount) 其实有两种可能, 能凑成和不能凑成
                
                如果能凑成, 那么
                f(n)(amount) = f(n - 1)(amount) 不选它
                f(n)(amount) = f(n)(amount - coins[n])  + 1 选它
                
                所以, 能凑成的递推为
                f(n)(amount) = min(f(n - 1)(amount), f(n)(amount - coins[n] + 1)
                
                但, f(n)(amount)还有一种不能凑成的值, 此时为-1, 即么, 怎么递推这个呢? 
                
                还是分两种, 选了它和不选它, 如果都不能凑成, 说明确实不能凑成
                
                f(n - 1)(amount) = -1 && f(n)(amount - coins[n]) = -1 说明都不能凑成, 那么, 就把它设置成 - 1
                
                方法二:
                
                不要陷入思维定式, 这种二维的毕竟不是那么直观, 我们还是回到最初, 假如coins不变, 但amount变化, 看看有什么区别
                
                f(amount) = ?
                
                我们希望, 能让右边的amount变小, 直到amount = 1之类的, 这样, 就可以穷举出来了, 
                
                但右边的amount可以是哪些值呢? 
                
                如何抽象就举个例子, 比如说[1, 2, 5], 11
                
                f(11)要么是-1, 要么是组成11的最少硬币数.
                
                而这些硬币只可能是1, 2, 5
                
                所以, f(11) = min(f(10), f(9), f(6)) + 1 or -1
                
                那么, 什么时候是 -1呢? 
                
                就是f(10), f(9), f(6)都是 -1时, f(11)就是-1了, 表示,10, 9, 6都凑不成, 那么, 再加1, 2, 5也不可能凑成.
                
                 
                启示: 
                
                还是不要陷入思维定式.
                
                网上讨论这个问题, 又是备忘录, 又是最优子问题, 我觉得没必要这么复杂.
                
                就是一个f(n)的问题, 然后如果能由更小的得出f(n)就可以递推了, 初始值我们可以一眼看出来, 这个过程中, 如果有的f(n)要
                重复计算, 那么, 我们可以把它保存下来, 下次直接查就行了.
                
                
                
                """,
        relatedQuestions = {}
)
public class Coin_Change {

    static class First {
        public int coinChange(int[] coins, int amount) {
            int[][] dp = new int[coins.length + 1][amount + 1];

            // 初始化第一行, 表示对于amount = 0 , 可使用0个凑成, 对于amount > 0, 则都不会凑成
            dp[0][0] = 0;
            for (int i = 1; i <= amount; i++) {
                dp[0][i] = -1;
            }

            //
            for (int i = 1; i <= coins.length; i++) {
                for (int j = 0; j <= amount; j++) {
                    if (j >= coins[i - 1]) {
                        int a = dp[i - 1][j];
                        int b = dp[i][j - coins[i - 1]];

                        if (a == -1 && b == -1) {
                            dp[i][j] = -1;
                        } else if (a == -1) { // 即不选它不行
                            dp[i][j] = b + 1;
                        } else if (b == -1) { // 即不能选它
                            dp[i][j] = a;
                        } else {
                            dp[i][j] = Math.min(a, b + 1);
                        }
                    } else {
                        dp[i][j] = dp[i - 1][j];
                    }
                }
            }
            return dp[coins.length][amount];
        }
    }

    static class Second {

        public int coinChange(int[] coins, int amount) {
            int[] dp = new int[amount + 1];

            // dp[0] = 0, 这是初始化
            for (int i = 1; i <= amount; i++) {
                // 求dp[i]
                dp[i] = -1; // 先初始化为不存在能组成i的硬币组合

                // 依次尝试每一个硬币
                for (int coin : coins) {
                    if (i >= coin) {  // 可以选这个硬币
                        if (dp[i - coin] != -1) {
                            if (dp[i] == -1) { // 第一次找到组合, 直接赋值.
                                dp[i] = dp[i - coin] + 1;
                            } else {            // 非第一次, 就进行比较
                                dp[i] = Math.min(dp[i], dp[i - coin] + 1);
                            }
                        }
                    } else {
                        // 不会选这个硬币, 跳过
                    }
                }
            }

            return dp[amount];
        }

    }

}

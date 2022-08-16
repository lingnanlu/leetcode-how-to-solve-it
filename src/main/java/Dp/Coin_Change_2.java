package Dp;

import run.elder.Category;
import run.elder.Leetcode;

@Leetcode(
        title = "Coin Change 2",
        link = "https://leetcode.com/problems/coin-change-2/",
        category = Category.ARRAY,
        how2SolveIt = """
                
                方法一:
                
                还是最直观的, 我们看下解空间的解是什么样子的. 这个解空间中, 有的解符合条件, 有的不符合条件
                
                我们统计符合条件的解的个数就行.
                
                以 [1, 2, 5], 5为例
                
                解空间为(a(1), b(2), c(5)), 其中a(1), b(2), c(5)为各自的个数.
                
                虽然, 第一个的数量是无限的, 看似有无数个, 但因为都是正整数, 其实每一种的数量是有上限的, 所以, 还是可以穷举出来的.
                
                比如 a(1) <= 5, b(2) <= 2, c(5) <= 1
                
                因为选择a(1), b(2), c(5)是分三步走, 每一步又有有限中情况, 所以可以使用DFS来穷举出来所有可能的解, 然后在这里面找到符合条件
                的.
                
                方法二:
                
                除了方法一, 还是没有其它直观的思路, 但这里又是一个n个元素的数组, 以及一个target, 我们试试还是用递推的方式来做.
                
                
               记f(n)(amount) 为前n个coins组成amount的组合数.
               
               它和f(n-1)(x)有什么关系呢? 
                                // 不选           // 选一个                   // 选两个
               f(n)(amount) = f(n - 1)(amount) + f(n-1)(amount - coin(n)) + f(n - 1)(amount - 2 * coin(n)) + f(n - 1)(amount - x * coin(n))
               
               (amoutn - x * coin(n)) >= 0.
               
               
               其实就是在0 - 1的基础上变了变形. 但, 我觉得自己也可以写出来.
               
               
               方法三:
               
               方法二中, 有三层循环, 这个时间复杂度有点高, 我们看一看能不能优化一下.
               
               和0 - 1背包不同的时, 在递推公式中
               
                f(n)(amount) = f(n - 1)(amount) + f(n-1)(amount - coin(n)) + f(n - 1)(amount - 2 * coin(n)) + f(n - 1)(amount - x * coin(n))
               
               (amoutn - x * coin(n)) >= 0.
               
               多了好几个, 而且, 第三层循环正是因为和它有关, 因为它的数量是不定的.
               
               那么, 我们能不能把这个数量固定呢? 比如, 对于每n个, 也是选与不选两种, 比如说
               
               f(n)(amount) = f(n - 1)(amount) + f(n - 1)(amount - coin(n))
               
               那么, 这显然是不行的, 因为 f(n - 1)(amount - coin(n))只表示选了一次, 那么选2, 3, 4, ...次的, 都忽略了
               
               但这给了我们一个启示, 将完全背包转化成 0 - 1 背包, 比如
               
               [1, 2, 5] 5
               
               因为1最多有5个, 2最多有2个, 所以, 我们可以把数组展开一下
               
               [1, 1, 1, 1, 1, 2, 2, 5]
               
               这样原问题就变成了.
               
               从[1, 1, 1, 1, 1, 2, 2, 5]找组合, 使之等于5个组合数.
               
               这个和0 - 1背包还是有不一样的, 因为这里有相同的元素.
               
               方法四:
               
               还是再看递推公式, 
               
               f(n)(amount) = f(n - 1)(amount) + f(n - 1)(amount - coin(n))
               
               如果对于第二项, f(n - 1)(amount - coin(n))
               修改成 f(n)(amount - coin(n)) , 现在成了这样
                                
               f(n)(amount) = f(n - 1)(amount) + f(n)(amount - coin(n))
               
               f(n - 1)(amount) 不选coin(n) 
               f(n)(amount -coin(n)) 选择 coin(n), 但依然是由前n个组成 (amount - coin(n))
               
               方法五: 
               
               方法四中的可见其依赖上一行的同列的元素, 以及本行左边的元素, 所以可以空间优化成一维的.
               
               f(n)(amount) = f(n - 1)(amount) + f(n)(amount - coin(n))

               
                """,
        relatedQuestions = {}
)
public class Coin_Change_2 {

    static class Second {
        public int change(int amount, int[] coins) {

            int[][] dp = new int[coins.length + 1][amount + 1];

            // 初始化 f(1)(0 ~ amount) 表示使用前1个元素来组成 0 ~ amount的组合数

            // 注意, i = 0时, 也是1, 表示任何硬币都不选也是一种组合
            for (int i = 0; i <= amount; i++) {
                if (i % coins[0] == 0) {
                    dp[1][i] = 1;
                }
            }

            for (int i = 2; i <= coins.length ; i++) {
                int coin = coins[i - 1];
                for (int j = 0; j <= amount; j++) {
                    for (int k = 0; j - k >= 0; k += coin) {
                        dp[i][j] += dp[i - 1][j - k];
                    }
                }
            }

            return dp[coins.length][amount];

        }
    }

    static class Fourth {

        public int change(int amount, int[] coins) {

            int[][] dp = new int[coins.length + 1][amount + 1];

            // 初始化 f(1)(0 ~ amount) 表示使用前1个元素来组成 0 ~ amount的组合数

            // 注意, i = 0时, 也是1, 表示任何硬币都不选也是一种组合
            for (int i = 0; i <= amount; i++) {
                if (i % coins[0] == 0) {
                    dp[1][i] = 1;
                }
            }

            for (int i = 2; i <= coins.length ; i++) {
                int coin = coins[i - 1];
                for (int j = 0; j <= amount; j++) {
                    if (j - coin >= 0) {  // 可以选或不选
                        dp[i][j] = dp[i - 1][j] + dp[i][j - coin];
                    } else {       // 只能不选
                        dp[i][j] = dp[i - 1][j];
                    }
                }
            }

            return dp[coins.length][amount];

        }

    }


}

package Dp;

import run.elder.Category;
import run.elder.Leetcode;

@Leetcode(
        title = "Combination Sum IV",
        link = "https://leetcode.com/problems/combination-sum-iv/",
        category = Category.ARRAY,
        how2SolveIt = """
                
                方法一:
                
                依然是在解空间找满足条件的. 
                
                其实类似与全排列, 第一步可以选1, 2, 3, 第二步又可以选一二三, 所以使用DFS搜索统计所有满足条件的就行.
                
                方法二:
                
                这题和Coin Change 2 很相似, 唯一的不同之处, 就是coin change 2和顺序无关, 而这个和顺序有关.
                
                那么, 我们能利用 coin change 2的方法么? 或者我们能利用其结果? 
                
                因为coin change 2的结果只是一个整数, 没有包含其它信息, 所以, 我们试着利用求解coin change 2时的方法.
                  
                coin change 2的递推公式为
                
                f(n)(amount) = f(n - 1)(amount) + f(n)(amount - coin(n))
                 
                那么, 这个是和顺序无关的, 怎么改成和顺序有关呢?
                 
                因为递推公式只保留了值, 没有保留组合, 所以, 没办法和顺序有关, 如果我们把dp[][]的元素设置成当前状态下的所有满足条件的
                组合, 就可以了.
                 
                但显然, 时间复杂度与空间复杂度就上去了.
                 
                方法三:
                
                方法二中, 我们陷入了一种二维的思维定式, 发现如果使用f(n)(target), n和target都变化的话, 是找不到递推公式的.
                
                不妨再看下题目, 跳出思维定式, 比如说, 回到最基本的方法, 比如, 这里有[1, 2, 3], 和 target 4.
                
                我们固定[1,2,3]不变, 只变化4看看情况, 
                
                比如 说, [1, 2, 3] , 1
                        [1, 2, 3] , 2
                        [1, 2, 3] , 3
                        [1, 2, 3] , 4
                        [1, 2, 3] , 5
                        
                我们考虑 f(target)与 f(target - x)的关系.
                
                这里我们把
                
                f(target)定义能组成target的元素的排列数.
                
                那么 f(target - 1)和 f(target)有什么关系呢?
                
                f(target - 1)就是能组成target - 1的排列数, 而每个排列的最后再放个 1, 就是以1为结尾的 f(target)的排列数了.
                
                f(target)的排列可以分成, 1, 2, 3为结尾, 所以
                
                f(target) = f(target - 1) + f(target - 2) + f(target - 3) 
                
                那么, 我们就有了一个递推公式了.
                
                注意一个, 初始的target应该是多少, 比如说 [1, 2, 3] 当计算target为2时, 其实 f(target - 2)是有含义的, 即以2为
                结尾, 其它都不选, 所以f(0)是有含义的.
                
                f(0) = ?
                
                以 [1, 2] 2, 举例
                
                f(1) = 1;
                f(2) = f(1) + f(0) = 1 + 1 = 2;
                
                所以, f(0) = 1; 即没有元素也是一种组合.
                
                启示:
                
                本题的启示就是, 有时, 我们会陷入思维定式, 如一上来就想到, 二维状态转移, 背包问题等等, 如果遇到困难了, 其实可以
                忘记你所知道的, 还是基于最基础的思维, 比如说固定一个变量, 改变另一个, 变换问题等等. 
                
                
                             
                """,
        relatedQuestions = {}
)
public class Combination_Sum_IV {

    public int combinationSum4(int[] nums, int target) {

        int[] dp = new int[target + 1];

        dp[0] = 1;

        // 计算 2 ~ target
        for (int i = 2; i <= target; i++) {
            for (int n : nums) {
                if (i - n >= 0) {
                    dp[i] += dp[i - n];
                }
            }
        }

        return dp[target];
    }

}

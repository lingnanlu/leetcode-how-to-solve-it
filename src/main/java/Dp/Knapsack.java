package Dp;

import run.elder.Category;
import run.elder.Leetcode;

@Leetcode(
        title = "Knapsack(背包问题)",
        link = "",
        category = Category.ARRAY,
        how2SolveIt = """
                
                https://www.wikiwand.com/zh/%E8%83%8C%E5%8C%85%E9%97%AE%E9%A2%98
                
                问题描述如上.
                
                对于背包问题, 其实不必先明白动态规划, 而靠自己的努力也是可以做到的. 这里以0-1背包为例
             
                首先, 最自然的, 是使用贪心策略, 最容易想到的是
                
                1. 优先选择最贵的
                2. 优先选择单位重量价值最高的
                
                但以上两者都很容易举出反例来, 所以贪心失败.
                
                好了, 到目前为止, 最直观的方法失败的, 那么, 还有其它方法么?
                
                另一个最直观的, 就是穷举所有可能情况, 也就是把解域的解都找出来, 然后找最大值, 这个解法的问题在于, 如果穷举所有的情况.
                因为物品的数量不是固定的, 所以不能使用循环.
                
                我们注意到对于每一个物品, 其实可以选或不选两种情况. 而我们的穷举, 就是对于每一个物品进行选择或不选择, 这是一个
                多步的情况, 而且每一步有两种可能, 所以想到DFS
                
                另一种DFS的思路就是, 第一次, 我选择一个物品, 有n种情况, 下一次我再选择剩余的, 有 (n - 1)种情况, 这也是一种dfs.
                
                但显然暴力的方法时间复杂度接受不了.
                
                那么, 你还有什么好的思路呢? 灵光一闪? 一拍大腿?
                
                我们还是回到问题定义上去. 我们发现, 问题中有好几个变量
                
                物品个数, 物品重量, 物品价值, 背包容积.
                
                因为物品个数, 物品重量, 物品价值, 这三者是相关的, 我们只让一个变化, 其它不变, 所以, 我们这里因为, 这三者是确定的.
                那么, 剩下的变量就是背包容积.
                
                现在要求的是在W下的最大价值, 似乎, 我们不能直观的, 一下子做出来, 不过, 我们可以变化W. 
                
                比如W = 0时, 最大价值是多少? 0
                
                这让我们觉得, 最大价值是W的函数, 
                
                f(W)
                
                而且我们知道了W = 0时的值.
                
                那么, 这给了我们启发, 如果我们能找到
                
                f(W)和f(W-1)或f(W-2)之类的关系, 我们就不是可以从W = 0递推到最终结果么? 
                
                所以, 现在的关键就是找一下f(W)和前面已经算出来的值的关系. 这个关系怎么找呢? 可以从两方面来试.
                
                抽象的: 就是搞明白f(W)的含义, 然后假设f(0...W-1)全算出来的, 靠理解来得出公式.
                具体的: 如果从抽象的角度不好得出, 那么,可以从具体的来试, 如f(1)和f(0)的关系, f(2)和f(3)的关系
                
                因为这里还涉及到物品的被使用, 有些复杂, 我们从具体的来
                
                f(1) 的值怎么计算出来? 嗯, 要不是0, 要不就是所有重量为1的中, 价值最大的.
                
                那f(2)呢? 发现到了f(2)就找不出什么规律来了, 所以也就无法用数学语言描述, 进而这个递推公式就得不出来了.
                
                此时, 我们走到了一个死胡同好像. 固定物品, 变化W, 推不出什么公式来了.
                
                那么, 我们不妨换个思路, 固定W,把W当常数, 变化物品.
                
                这里我们对候选物品并不排序, 先取第一个, 记
                
                g(1)(W)为在w容积下, 物品先选前一个的时间的最大价值.
                
                g(1)(W)好说, 如果能放进去, 就是该物品的值, 不能放进去, 就是0
                
                那么好, 下一个, 我们加入第二个物品. 现在有两个了, 那么
                
                g(2)(W)怎么计算呢? 
                
                g(2)(W) 其实有两种情况, 对于物品2, 如果不选, 那么就是g(1)(W)
                如果选的话, 这时有个问题, g(1)(W)时的物品还在呢, 怎么办? 要不要移出来? 如果有多个, 移哪个? 
                
                如果选的话, 那么, 说明至少要有w(2)的容积, 而g(1)(W-w(2))为最大价值.
                
                所以, 得
                
                g(2)(W) = max(g(1)(W), g(1)(W-w(2)) + v(2))
                
                可见这个递推公式有两个变量, 有一个变量时, 我们使用一个一维数组, 有两个变量, 那么, 自然的, 使用二维数组.
                
                剩下的就是初始化与遍历顺序了.
                
                
                启示:
                
                其实背包问题如果想用数学归纳法的话(或递推), 最大的问题在于, 有两个改变的方向
                
                1. 背包的容量
                2. 物品的数量.
                
                所以, 最终递推出来的是一个二维的, 这是最难的一点.
                
                
                PS: 二维变一维
                
                dp[i][j] = max(dp[i - 1][j], dp[i - 1][j - weight[i]] + value[i])
                
                对于以上计算公式, 仔细观察, 发现, 对于i, 是一层一层的计算,
                
                计算i时, 利用的是上一层, 且是前面的数据(历史数据)
                
                所以, 理论上, 可以优化一成一维数组.
               
               
                
                
                """,
        relatedQuestions = {}
)
public class Knapsack {

    public static void main(String[] args) {

    }
}

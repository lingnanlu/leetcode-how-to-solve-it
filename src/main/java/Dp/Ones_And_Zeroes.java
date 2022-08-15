package Dp;

import run.elder.Category;
import run.elder.Leetcode;

import java.util.Arrays;

@Leetcode(
        title = "Ones and Zeroes",
        link = "https://leetcode.com/problems/ones-and-zeroes/",
        category = Category.ARRAY,
        how2SolveIt = """
                
                方法一: 
                
                穷举出所有子集, 对每一个解判断其是否符合条件, 然后找出元素个数最多的.
                
                方法二:
                
                还是没什么思路, 不过我们观察这个题的特点
                
                1. 给定一个数组, 这里是strs数组
                2. 给定目标值m, n, 这里有两个
                3. 求一个最xx问题, 这里是最大子集长度.
                
                这让上想到背包问题, 我们可以利用解背包问题时的方法, 还是数学归纳法.
                
                f(x)(m, n) = ?
                
                这里有m, n两个变量, 有些复杂, 我们可以简化一下, 比如只考虑m, 则
                
                f(x)(m) = ?
                
                这个递推公式怎么确定呢? 举例, 比如说, 就拿题目中的
                
                strs = ["10", "0001", "111001", "1", "00"], m = 5
                
                那么, 
                
                f(5)(5) = ? 
                
                我们知道, 右边最好是 f(4)(?), 那么, 这个?在这里是多少呢? 
                
                f(4)(5) or f(4)(3)
                
                我们知道, 对于最后一个元素, 它只有两种情况, 在或不在. 
                
                如果在, 那么, f(5)(5) = f(4)(3) + 1, 说明前4个元素最多含有3个的最长 + 1就可以得到前5个元素了.
                
                不在的话, 说明子集多在前4个元素, 那么 f(5)(5) = f(4)(5)
                
                 
                因为求最长, 而我们并不知道最后一个元素到底在还是不在(其实可以的, 如果全是1就是不在, 0的个数大于m也不可能在, 但在 0 ~ m)
                之间的, 无法判断, 所以
                
                f(5)(5) = max(f(4)(3) + 1, f(4)(5))
                
                即
                
                f(x)(m) = max(f(x - 1)(m - g(x)) + 1, f(x - 1)(m)) g(x)为元素中0的个数.
                
                现在加上n
                
                f(x)(m, n)
                
                还是类似, 判断最后一个元素在或不在
                
                f(x)(m, n) = max(f(x - 1)(m - g(x), n - h(x)) + 1, f(x - 1)(m, n))
                
                现在, 递推公式有了
                
                确定维度
                
                这里, 其实是三个维度了, 第一维是数组个数
                第二维是m
                第三维是n
                
                            
                然后就是初始化, 这里初始化就是初始一个平面.
                    
                
                
                """,
        relatedQuestions = {}
)
public class Ones_And_Zeroes {

    public int findMaxForm(String[] strs, int m, int n) {

        if (strs.length == 0) return 0;

        int[][][] dp = new int[strs.length + 1][m + 1][n + 1];

        // 统计0的个数与1的个数
        int[] zeroCounts = new int[strs.length + 1];
        int[] oneCounts = new int[strs.length + 1];

        for (int i = 0; i < strs.length; i++) {
            String str = strs[i];
            int zeroCount = 0;
            int oneCount = 0;
            for (int j = 0; j < str.length(); j++) {
                if (str.charAt(j) == '0') {
                    zeroCount++;
                } else {
                    oneCount++;
                }
            }

            zeroCounts[i + 1] = zeroCount;
            oneCounts[i + 1] = oneCount;
        }

        System.out.println(Arrays.toString(zeroCounts));
        System.out.println(Arrays.toString(oneCounts));

        // 初始化, f(1)(m, n)

        for (int i = 1; i <= m; i++) {

            // 说明这个元素的0的个数超过了i
            if(zeroCounts[1] > i) {
                continue;
            } else {
                // 此时这个元素的0的个数不超过i
                for (int j = 1; j <= n ; j++) {
                    if (j - oneCounts[1] >= 0) {
                        dp[1][i][j] = 1;
                    }
                }
            }
        }
        for (int i = 1; i <= strs.length ; i++) {
            System.out.println("---初始化---");
            StringBuilder sb = new StringBuilder();
            for (int j = 1; j <= m ; j++) {
                sb.append(Arrays.toString(dp[i][j])).append(" ");
            }
            System.out.println(sb.toString());
        }

        // 初始化其它行

        for (int i = 2; i <= strs.length ; i++) {

            System.out.println("----");
            StringBuilder sb = new StringBuilder();
            // f(x)(m, n) = max(f(x - 1)(m - g(x), n - h(x)) + 1, f(x - 1)(m, n))
            for (int j = 0; j <= m; j++) {

                // 此时该元素的0的个数超过了要求, 所以肯定不选
                if (zeroCounts[i] > j) {
                    // 将上一层的直接复制过来
                    for (int k = 0; k <= n; k++) {
                        dp[i][j][k] = dp[i - 1][j][k];
                    }
                } else {
                    for (int k = 0; k <= n ; k++) {
                        // 1的个数满足, 所以要选
                        if (k - oneCounts[i] >= 0) {
                            dp[i][j][k] = Math.max(dp[i - 1][j - zeroCounts[i]][k - oneCounts[i]] + 1, dp[i - 1][j][k]);
                        } else {
                            // 0的个数满足, 但1的个数不满足, 还是不选
                            dp[i][j][k] = dp[i - 1][j][k];
                        }
                    }
                }

                sb.append(Arrays.toString(dp[i][j])).append(" ");

            }

            System.out.println(sb.toString());
        }


        return dp[strs.length][m][n];


    }

    public static void main(String[] args) {
        Ones_And_Zeroes test = new Ones_And_Zeroes();

        String[] strs = {"10", "0", "1"};

        test.findMaxForm(strs, 1, 1);
    }
}

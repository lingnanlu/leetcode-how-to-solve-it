package Dp;

import run.elder.Category;
import run.elder.Leetcode;

@Leetcode(
        title = "Unique Binary Search Trees",
        link = "https://leetcode.com/problems/unique-binary-search-trees/",
        category = Category.ARRAY,
        how2SolveIt = """
                又是求n, 即f(n)
                
                比较明显的, 使用数学归纳法, 画一画图, 依次以1...n为根, 发现
                
                f(1) = 1
                f(2) = 2
                f(3) = f(2) + f(1) * (f1) + f(2)
                f(4) = f(3) + f(1) * f(2) + f(2) * f(1) + f(3)
                
                
                找找规律, 为了能迭代, 我们把每一项变成两项的乘积, 如下
                f(3) = f(2) * f(0) + f(1) * f(1) + f(0) * f(2)
                f(4) = f(3) * f(0) + f(2) * f(1) + f(1) * f(2) + f(0) * f(3)
                
                归纳出
                f(n) = f(n - 1) * f(0) + f(n - 2) * f(1) + f(n - 3) * f(2) + .... f(0) * f(n - 1)
                
                所以, 可以迭代了
                
                注:
                
                这题网上有用动态规划思路的
                千万不要直接一来就动态规划, 要直观, 自然的分析, 归纳法就是一种很直观自然的方法
                """,
        relatedQuestions = {}
)
public class Unique_Binary_Search_Trees {

    public int numTrees(int n) {

        if (n == 1) return 1;
        if (n == 2) return 2;

        int[] result = new int[n + 1];
        result[0] = 1;
        result[1] = 1;
        result[2] = 2;

        for (int i = 3; i <= n; i++) {
            int k = 0;
            for (int j = i - 1; j >= 0 ; j--) {
                k += result[j] * result[i - 1 - j];
            }
            result[i] = k;
        }

        return result[n];
    }

}

package Dp;

import run.elder.Category;
import run.elder.Leetcode;

@Leetcode(
        title = "Climbing Stairs",
        link = "https://leetcode.com/problems/climbing-stairs/",
        category = Category.ARRAY,
        how2SolveIt = """
                计f(n)为所要求.
                
                可以感觉到是一个递推公式.
                
                我们先看看f(n)与f(n - 1)的关系, f(n - 1) 为 爬 n -1 的爬法, 举例来说, 如果 f (n - 1) = 3, 那么, f(n)至少有3种
                爬法了(对应f(n-1)的每一种, 跨一步就好)
                
                那么, 到f(n)还有什么办法么? 就是从f(n - 2)进行爬, 最后跨两步.
                
                因为最后一步一个是一步, 另一个是两步, 所以 f(n -1)与f(n -2)之间的爬法不重复(假设你记爬法为 n1, n2, n3, nx.因为最后一个
                不同, 一个为1, 一个为2, 所以前面重复也没关系)
                
                又因为, 到达f(n)只有两种方法, 一种爬1, 一种爬二, 
                
                f(n) = f(n - 1) + f(n - 2)
                
                这做到了不重不漏.
                
                f(1) = 1
                f(2) = 2
                
                
                启示:
                
                本题虽然简单, 但要想明白递推公式的意义, 要证明是不重不漏
                    
                """,
        relatedQuestions = {}
)
public class Climbing_Stairs {

    public int climbStairs(int n) {


        if (n <= 2) return n;

        int first = 1;
        int second = 2;


        for (int i = 3; i <= n ; i++) {
            int result = first + second;
            first = second;
            second = result;
        }

        return second;

    }
}

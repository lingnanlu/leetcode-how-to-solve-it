package Todo;

import run.elder.Category;
import run.elder.Leetcode;

@Leetcode(
        title = "Integer Break",
        link = "https://leetcode.com/problems/integer-break/",
        category = Category.ARRAY,
        how2SolveIt = """
              
               方法一:
               
                先手动的画一画, 发现这个k >= 2可以有多种拆分方法
                
                那么, 我们能不能化简一下, 比如固定 k = 2
                
                现在问题变成, 将一个整数拆成2个, 然后求最大
                
                我们知道, 所拆的数越接近, 那么越大.
               
                我们用f2(n)表示一下拆成2个的最大乘机.
                
                好了, 现在我们解决了拆成两个的最大乘积问题, 那么拆成3个的最大乘积呢?
                
                拆成3个可以看成是
                
                先将一个数拆成2个, 然后再将其中一个拆成两个
                
                f3(n) = max(1 * f2(n - 1), 2 * f2(n - 2), 3 * f2(n - 3), .....)
                
                依次递推到
                
                f4(n) = max(1 * f3(n - 1), 2 * f3(n - 2), 3 * f3(n - 3)....)
                
                
                fk(n) = ....
                
                
                但这种方法感觉计算量好大, 先放弃
                
                方法二:
                
                记f(n)为要求的, 这题一看, 不需要得到如何具体划分的, 那我们试试, 可不可以由前面的推导出后面的
                
                10 = 1 + 9
                10 = 2 + 8
                10 = 3 + 7
                
                ...                
             
             
                
                启示:
                
                动规与DFS的区别
                
                注: 
                
                这一题的递推公式不好找, 先缓一缓
                
                """,
        relatedQuestions = {}
)
public class Integer_Break {
    public int integerBreak(int n) {
        return 0;
    }

    private void integerBreak2(int n) {

    }
}

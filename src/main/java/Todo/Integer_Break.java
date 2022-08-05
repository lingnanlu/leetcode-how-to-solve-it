package Todo;

import run.elder.Category;
import run.elder.Leetcode;

@Leetcode(
        title = "Integer Break",
        link = "https://leetcode.com/problems/integer-break/",
        category = Category.ARRAY,
        how2SolveIt = """
                
                如果要拆成2个, 则其实是 找 f(x) = x * (n - x)的最大值
                所以感觉这题其实是一个数学题
                
                这题其实是一个数学题, 就是要证明如下结论
                
                ① 当所有拆分出的数字相等时，乘积最大。② 最优拆分数字为 3
                
                证明步骤如下
                https://leetcode.cn/problems/integer-break/solution/343-zheng-shu-chai-fen-tan-xin-by-jyd/
                
                这题先记住结论吧, 自已现在没能力证明
                
                所以本题的策略是
                
                1. 尽可能拆3
                2. 尽可能拆相等.
                
                """,
        relatedQuestions = {}
)
public class Integer_Break {
    public int integerBreak(int n) {

        if (n == 2) return 1;
        if (n == 3) return 2;
        if (n == 4) return 4;

        // 大于5之后, 尽量拆出3来, 最后剩余一个不大于4的数
        int result = 1;
        while(n > 4) {
            n -= 3;
            result = 3 * result;
        }

        result = result * n;

        return result;



    }

}

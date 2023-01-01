package Array;

import run.elder.Category;
import run.elder.Leetcode;

@Leetcode(
        title = "Unique Number of Occurrences",
        link = "https://leetcode.cn/problems/unique-number-of-occurrences/",
        category = Category.ARRAY,
        how2SolveIt = """
                方法一:
                
                其实就是从数组中, 选择 n / 2个糖, 最多可以选择的种类数
                
                我们可以人工模拟一下, 从第一个开始选, 如果选过, 就不选, 没选过, 就选, 然后真到选了 n / 2或选完为止.
                这里其实就是要判断选没选过. 这有点集合的味道, 不能同时出现.
                """,
        relatedQuestions = {}
)
public class Unique_Number_Of_Occurrences {

}

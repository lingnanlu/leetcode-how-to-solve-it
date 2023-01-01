package Array;

import run.elder.Category;
import run.elder.Leetcode;

import java.util.HashSet;
import java.util.Set;

@Leetcode(
        title = "Distribute Candies",
        link = "https://leetcode.cn/problems/distribute-candies/",
        category = Category.ARRAY,
        how2SolveIt = """
                方法一:
                
                其实就是从数组中, 选择 n / 2个糖, 最多可以选择的种类数
                
                我们可以人工模拟一下, 从第一个开始选, 如果选过, 就不选, 没选过, 就选, 然后真到选了 n / 2或选完为止.
                这里其实就是要判断选没选过. 这有点集合的味道, 不能同时出现.
                
                方法二:
                
                如果糖果的种类值的值域比较小的话, 可以利用一个计数数组代替set
                """,
        relatedQuestions = {}
)
public class Distribute_Candies {

    static class First {
        public int distributeCandies(int[] candyType) {
            int max = candyType.length / 2;     // 最多选的种类

            // 用来判断选没选过, 存放已经选过的.
            Set<Integer> selected = new HashSet<>();

            for (int i = 0; i < candyType.length && selected.size() != max; i++) {
                if (!selected.contains(candyType[i])) {
                    selected.add(candyType[i]);
                }
            }

            return selected.size();
        }
    }
}

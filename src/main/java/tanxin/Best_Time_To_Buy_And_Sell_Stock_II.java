package tanxin;

import run.elder.Category;
import run.elder.Leetcode;

@Leetcode(
        title = "Best Time to Buy and Sell Stock II",
        link = "https://leetcode.com/problems/best-time-to-buy-and-sell-stock-ii/",
        category = Category.ARRAY,
        how2SolveIt = """
                没思路的话, 我们改变一下数据试试.
                
                如 先来两天, 看看你怎么买卖
                
                然后考虑三天的, 如 1, 2, 3
                
                有6种情况
                
                1, 2, 3
                1, 3, 2
                2, 1, 3
                2, 3, 1
                3, 2, 1
                3, 1, 2
                
                对每种情况都进行买卖交易, 找找规律
                
                1, 2, 3 = [1, 2], [2, 3]
                1, 3, 2 = [1, 3]
                2, 1, 3 = [1, 3]
                2, 3, 1 = [2, 3]
                3, 2, 1 = 
                3, 1, 2 = [1, 2]
                
                你发现了一个规律, 就是虽然是三天, 但也只是只看临近两天的, 只要第二天比第一天大, 就可以交易
                
                那么, 对于n天, 我猜也是这样的.
                
                启示
                
                1. 先从小的数量级举例子, 找规律, 因为元素个数少时, 你可以穷举, 这样找到规律之后, 起码你有一个猜想
                2. 猜想证明不了也没问题, 有时你不好证明, 但它确实也是一种解, 你有感觉就好, 起码接近了.
                
                
                """,
        relatedQuestions = {}
)
public class Best_Time_To_Buy_And_Sell_Stock_II {

    public int maxProfit(int[] prices) {

        int profile = 0;
        for (int i = 0; i <= prices.length - 2; i++) {
            if (prices[i + 1] > prices[i]) {
                profile += prices[i + 1] - prices[i];
            }
        }

        return profile;
    }
}

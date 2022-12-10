package Other;

import run.elder.Category;
import run.elder.Leetcode;

@Leetcode(
        title = "Arranging Coins",
        link = "https://leetcode.com/problems/arranging-coins/",
        category = Category.UNKNOWN,
        how2SolveIt = """
                
                方法一: 模拟人工解题步骤
               
               此方法应该是正确的, 但在leetcode上超时了
               
               方法二:
               
               想想方法一, 当n很大时, 其实是
               1 + 2 + 3 + 4 + .... x的一个累加
               而这个累加比较消耗时间
               
               那么, 能不能把这个累加使用公式来计算呢?
               
               这里的关键就是找一个x, 使其满足某种条件.
               
               这是不是很像二分?
             
                """,
        relatedQuestions = {}
)
public class Arranging_Coins {

    static class First {
        public int arrangeCoins(int n) {

            // 已消耗的总硬币数
            int consumed = 0;

            int i = 1; // 将要搭建的下一层
            while (consumed <= n) {
                // 模拟人工操作, 从第一层开始消耗, i表示将要搭建的层数
                consumed += i;   // 搭建了第i层
                i++;// 下一个要搭建的层
            }

            /**
             * 此时已消耗的 consumed > n i是将要搭建的下一层
             *
             * 即已经搭建了i - 1层, 但这i - 1层所消耗的比n多.
             * 所以可以完整搭建 i - 2层
             */

            return i - 2;
        }
    }

    static class Second {
        public int arrangeCoins(int n) {

            if (n == 1) return 1; // 特殊情况处理一个, 否则不变式不好写

            int i = 1, j = n;

            // 这两个初始值没有意义.
            int mid = 0;            // 上一个尝试的层数
            long accumulate = 0;    // 上一个尝试的层数的累积值

            while (accumulate <= n) {
                // 这里面就要不断尝试, 只到找到一个层数使其刚好 > n

                // != 为了方便说明i, j终止时的含义
                if (i != j) {
                    mid = i + (j - i) / 2;  // 尝试一个值
                    accumulate = (long) (1 + mid) * mid / 2;
                    if (accumulate == n) {
                        return mid;
                    } else if (accumulate > n) {
                        j--;
                    } else {
                        i++;
                    }
                } else {
                    //此时 i == j && accumulate <= n
                    break;
                }

            }

            //此时, i == j
            // mid是上一个尝试的层数, accumulate为累积值
            // 此时 accumulate > n或 accumate < n, wjg
            // 最后一个尝试i



        }
    }
}

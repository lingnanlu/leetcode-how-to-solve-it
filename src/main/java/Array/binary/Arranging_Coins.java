package Array.binary;

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
             
               这里的关键问题就是, 满足哪种条件呢? 这就是比一般二分查找复杂的地方.
               
               我觉得可以试一试是否和目标值相等
               
               
               考察:
               
               这题的关键还是要明白二分查找跳出循环之后的i, j的含义
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
            // 二分查找
            int i = 1, j = n;

            // 注意不能使用 i!=j, 因为可能存在让两者不相等的情况, 如n=4时.
            while (i <= j) {
                int mid = i + (j - i) / 2;
                long accu = (long) (1 + mid) * mid / 2;
                if (accu == n) {
                    return mid;
                } else if (accu > n) {
                    j = mid - 1;
                } else {
                    i = mid + 1;
                }
            }

            /**
             * 此时, i = j + 1, 关键是你得明白i, j的含义是什么, 要想搞清这个问题, 你得知道i, j最后是怎样变成这样的.
             *
             * 其实可以这样想
             *
             * [0, i)都是比目标值小的 (注意半开区间)
             * (j, n]都是比目标值大的 (注意半开区间)
             *
             * 而最后 i = j + 1;
             *
             * 所以最后就是 i所指比目标值大, 而j所指的比目标值小, j就是满足小于目标值的最大值
             */

            return j;
        }
    }

    public static void main(String[] args) {
        Second test = new Second();

        test.arrangeCoins(4);
    }
}

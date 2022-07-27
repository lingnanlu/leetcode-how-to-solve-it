package tanxin;

import run.elder.Category;
import run.elder.Leetcode;

@Leetcode(
        title = "Gas Station",
        link = "https://leetcode.com/problems/gas-station/",
        category = Category.ARRAY,
        how2SolveIt = """
                方法一:
                
                每一个加油站做为起点试一试, 时间复杂度为O(n^2)
                
                方法二:
                
                想想没思路, 我们回到方法一上, 看能不能优化.
                
                方法一是每一个加油站都在尝试, 所以时间复杂度为O(n^2), 假如你从一个点A出发, 尝试了几站之后, 发现到不了B
                那么, 你尝试的东西能不能利用上来做一些简化, 比如说
                
                A --> C1 --> C2...--->CK ---> B
                
                A现在到不了B, 但能到C1, C2, ... CK, 那么选择下一个起点时, 可不可以利用
                
                1. A能到C1, C2, CK
                2. A到不了B
                
                这两个fact?
                
                你感觉能不能跳过C1, C2, Ck? 
                
                即能不能证明从Cx开始到不了B?
                
                其实是可以的. 这个很容易证明.
                
                那么, 现在就可以写代码了.
                
                时间复杂度为O(n), 本题中其实是遍历了两次
                
                启示:
                
                1. 如果没有思路, 还是从暴力开始, 想办法优化, 不要去想什么魔法方法
                2. 写循环时, 可以先不管终止条件, 先写循环体
                3. 如果终止条件不好写, 可以写一个让不变式容易解释的终止条件, 然后让循环终止, 再看看不变式的含义, 这样说不定就有了思路.
                4. 不变式依然很重要, 本题方法二是一遍过.
                5. 注意这个方法二想到的思路, 其实是优化了双指针的遍历, 你其它之前是遇到过好几个这种双指针优化的方法的.
                
                """,
        relatedQuestions = {}
)
public class Gas_Station {

    static class First {
        public int canCompleteCircuit(int[] gas, int[] cost) {
            return 0;
        }
    }

    static class Second {
        public int canCompleteCircuit(int[] gas, int[] cost) {

            // 不变式
            int i = 0; // i表示要尝试的起点
            int p = 0; // p用来跟踪走到哪里了.
            int tank = 0; // 表示邮箱里的还剩余的油

            // 这个终止应该怎么写呢? 其实我也不太清楚
            // 但看循环, p是一直往后加的, 所以就写一个容易解释不变式含义的先
            // 然后看看循环终止时, 不变式的含义是什么
            while (p != gas.length) {
                // 试着出发, 先加油再消耗
                tank += gas[p];  // 加油
                tank -= cost[p]; // 试着走到下一站

                if (tank >= 0) {
                    // 能走到下一站, 把出发点设置到下一站
                    // 注意保证不变式为真
                    p++;
                } else {
                    // 此时, 从p不能走到下一站, 更新起点
                    // 注意保证不变式为真
                    i = p + 1;
                    p = i;
                    tank = 0;
                }
            }

            // 此时
            // i是一个尝试的起点
            // p是从该尝试的起点走, 可以一直走到 gas.length, 如果把gas.length看成第0个位置(因为同样加油和消耗了), 所以可以这样看
            // 从i开始, 可以走到0
            // tank为走到0后, 剩余的汽油.

            // 那么, 我们能不能从0开始, 再走到i呢? 如果可以走到, 说明就是一周了.这次和第一次从0开始不一样了, 因为有了剩余汽油.
            // 那我们试试
            p = 0;
            while (p != i) {
                // 试着走到下一站
                tank += gas[p];
                tank -= cost[p];

                if (tank >= 0) {
                    p++;
                } else {
                    break;
                }
            }
            return p == i ? i : -1;


        }
    }
}

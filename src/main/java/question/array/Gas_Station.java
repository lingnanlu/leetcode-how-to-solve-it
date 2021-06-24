package question.array;

import run.elder.Category;
import run.elder.Leetcode;
import run.elder.Solution;

@Leetcode(
        title = "Gas Station",
        link = "https://leetcode.com/problems/gas-station/",
        category = Category.ARRAY,
        how2SolveIt = """
                
                这题是一个模拟题，题中给出的是数组形式表示的圈，为了更直观一些，画一个圈，标上数组中的数值。
                就是使用代码来模拟操作，关键是表述清晰
                
                """,
        relatedQuestions = {}
)
public class Gas_Station {

    @Solution(detail = "这种方法其实是考察智力的一种方法，其实就是一种数学证明，但感觉这种方法没有参考意义，只能用在这里")
    static class Tricky {
        public int canCompleteCircuit(int[] gas, int[] cost) {

            int tank = 0;       //记录油箱储备

            int total = 0;      //如果最终total>=0 说明肯定有方案

            int start = 0;
            for(int i = 0; i < gas.length; i++) {

                total += gas[i] - cost[i];
                tank += gas[i] - cost[i];

                if(tank < 0) {
                    //假如是从start开始, 此时就是第一次从i到达不了i+1了.
                    //那么从start到i之间的任何一个开始, 也是无法到达i+1的,
                    //举个例子, 比如, A, B, C, D, E, F , 从A开始能到B, C, D, E, 到不了F
                    //那么从B, C, D, E, 开始是都达不了F的, 这是因为A能到B, 说明到B之后, 肯定还有剩余. 从A到不了, 那么, 从B也是到不了的
                    //同理, 从C, D, E也是到不了的. 所以可以把这之间的statio全部排除了.
                    tank = 0;
                    start = i + 1;
                }
            }

            // 这里有一个假设, 如果total>=0 , 说明一定有解,
            return total >= 0 ? start : -1;
        }
    }
    @Solution(detail = "模拟的方法， 一个地，一个地的尝试")
    static class Simulation {
        public int canCompleteCircuit(int[] gas, int[] cost) {

            // 从0位置进行尝试。
            for (int start = 0; start < gas.length; start++) {

                // 剩余要走的步数。
                int stepRemained = gas.length;
                // 当前所在地
                int current = start;

                // 下一个目的地
                int next = (start + 1) % gas.length;

                int gasLeft = 0;

                while(stepRemained != 0) {

                    // 走一步试试
                    int left = canMoveToNext(gas, cost, current, gasLeft);

                    if (left >= 0) {         // 说明能移动到下一地
                        stepRemained--;
                        current = next;
                        next = (next + 1) % gas.length;
                        gasLeft = left;
                    } else {
                        break;
                    }
                }

                if(stepRemained == 0) {
                    // 说明从start开始能走一圈
                    return start;
                }

            }

            return -1;
        }

        /**
         * 能不能开到下一地
         * @param gas
         * @param cost
         * @param location 当前所在地
         * @param gasLeft   当前剩余汽油
         * @return 移动到下一地后的剩余汽油
         */
        private int canMoveToNext(int[] gas, int[] cost, int location, int gasLeft) {
            return gasLeft + gas[location] - cost[location] ;
        }
    }

}

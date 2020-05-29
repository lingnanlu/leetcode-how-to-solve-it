package medium;

public class Gas_Station {

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

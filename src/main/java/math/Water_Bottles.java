package math;

/**
 * 这题的一个直观想法其实就是迭代, 你不好通过公式预先算出所有的值
 * 你只能通过当前值算出后一个值.
 *
 * 这题的关键点就是定义好几个变量, 以及不变式.
 *
 * 这题一次过.
 */
public class Water_Bottles {

    public int numWaterBottles(int numBottles, int numExchange) {

        // 已经喝的瓶数
        int total = 0;

        // 当前剩余的空瓶数
        int empty = 0;

        // 下一次要喝的瓶数
        int next = numBottles;

        while (next != 0) {
            // 喝光到空瓶
            total += next;
            empty += next;

            // 更新下一次可以喝的瓶数, 用空瓶交换
            next = empty / numExchange;

            // 交换后还剩余这么多空的.
            empty -= next * numExchange;
        }

        return total;
    }
}

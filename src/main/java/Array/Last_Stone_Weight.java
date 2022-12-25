package Array;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Queue;

/**
 * 方法一:
 *
 * 其实题目中已经操作步骤, 将步骤翻译成代码就好
 *
 * 因为每次要选出最重的两块, 碰撞之后还要把结果放回.
 *
 * 所以大概要想办法维持一个排序的数组.
 *
 * 方法一别看变量多, 但自己是一遍过, 说明自己的方法是正确的.
 *
 * 方法二:
 *
 * 方法一中不断的使用了插入排序, 这个是很麻烦的, 能不能不自己写插入排序, java自带的什么东西是自动有序的?
 *
 * 优先级队列. 所以可使用优先级队列来表示石头堆.
 *
 * 方法三:
 *
 * 方法一和方法二其实都是直接对操作步骤的模拟, 有没有什么办法不模拟?
 *
 * 必须每次都挑出最重的两个么? 能不能是任意两个?
 *
 * 其实不能是任意两个, 假如&是碰撞操作, 那么它不满足交换率.
 *
 * a & b & c != a & c & b
 *
 * 所以只能是每次选择出最重的两个.
 *
 */
public class Last_Stone_Weight {

    static class First {

        public int lastStoneWeight(int[] stones) {

            // 先将原数组从小到大排序.
            Arrays.sort(stones);

            // [0, j)之间是有效石头数
            int j = stones.length;

            // 将有至少两块石头时, 两两碰撞
            while (j >= 2) {
                // first 是最重的, second是次重的.
                int first = stones[j - 1];
                int second = stones[j - 2];

                if (first == second) {
                    // 两着相同, 完全粉碎
                    j = j - 2;
                } else {
                    int remain = first - second;

                    // 将remain再插入[0, j - 2)数组中, 使其有序.

                    // p指向第一个比remain大的位置.
                    // 这里的p = j - 2就是默认插入位置, 如果在[0, j-2)这间找不到比remain大的, 就插入到j-2位置
                    int p = j - 2;
                    for (int i = 0; i < j - 2; i++) {
                        if (stones[i] >= remain) {
                            p = i;
                            break;
                        }
                    }

                    // 此时p指向插入位置, 要将[p, j - 2)之间的元素都往后移动一位
                    for (int i = j - 3; i >= p ; i--) {
                        stones[i + 1] = stones[i];
                    }

                    stones[p] = remain;

                    // 此时已经有序, 更新j, 这里只少了一个石头
                    j = j - 1;

                }

            }

            // 此时j == 0或j == 1

            if (j == 0) {
                return 0;
            } else {
                return stones[0];
            }
        }

    }

    static class Second {
        public int lastStoneWeight(int[] stones) {

            Queue<Integer> priorityQueue = new PriorityQueue<>((o1, o2) -> o2 - o1);

            for (int stone : stones) {
                priorityQueue.add(stone);
            }

            while (priorityQueue.size() >= 2) {
                // 从队首出队
                int first = priorityQueue.poll();
                int second = priorityQueue.poll();

                if (first == second) {
                    // do nothing
                } else {
                    priorityQueue.add(first - second);
                }
            }

            if (priorityQueue.size() == 0) {
                return 0;
            } else {
                return priorityQueue.poll();
            }
        }
    }
}

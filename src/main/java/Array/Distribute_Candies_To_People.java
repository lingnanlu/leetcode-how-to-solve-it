package Array;

/**
 * 方法一:
 *
 * 题解很清楚, 就是翻译成代码, 还是考察不变式, 能不能一遍通过.
 *
 * 方法二:
 *
 * 方法一的实质其实是找
 * 1 + 2 + 3 + 4 + ... n >= candies. 即找这样一个n
 *
 * 其实, 这就是要查找某个数, 满足某种条件.
 *
 * 这让人想起二分查找的办法. 不过, 相比与2分查找, 这里其实不家一个num_people还没利用上
 * 那么这个num_people条件怎么用上呢?
 *
 * 比如说num_people = 4
 * 那么, 其实第一轮是 1 + 2 + 3 + 4 = 10 + 0
 * 第二轮是 5 + 6 + 7 + 8 = 10 + 16 * 1= 26
 * 第三轮是 9 + 10 + 11 + 12 = 10 + 16 + 16 = 10 + 16 * 2 = 42
 * 第n轮是 4 * (n - 1) + 1 , 4 * (n - 1) + 2, 4 * (n - 1) + 3 , 4 * (n - 1) + 4= 10 + 16 * (n - 1)
 *
 * 第1个小朋友其实是 1 + 5 + 9 + ... + 1 + 4 * (n - 1)
 *
 * 那么, 能不能利用这个公式, 来最快的确定每个小孩子分发的糖果数? 因为方法一其实也是最终要确定每个小孩子分发的糖果数.
 * 只是没有利用数学公式而已.
 *
 * 这里的关键是, 能算出发几轮, 即算出,n, 然后就可以计算出每个小孩子的分发糖果数.
 *
 * 其实方法二的实质就是数据公式计算, 难点就是计算完整分发的轮数和求等差数列.
 */
public class Distribute_Candies_To_People {

    static class First {
        public int[] distributeCandies(int candies, int num_people) {

            // 还剩下的糖果数
            int remainning = candies;

            // 下一个要分发的糖果数
            int next = 1;

            int[] result = new int[num_people];

            // 下一个要分发的小朋友.
            int i = 0;

            // 还有糖果可以分发
            while (remainning != 0) {
                // 进行分发
                result[i] = result[i] + next;
                // 更新剩余
                remainning = remainning - next;

                // 保持不变式为直, i指向下一个要分发的小朋友
                i = (i + 1) % num_people;

                // next为下一次要分发的糖果数.
                next = Math.min(next + 1, remainning);

            }

            return result;
        }
    }

    static class Second {

        public int[] distributeCandies(int candies, int num_people) {

            // 计算出base;
            int base = 0;
            for (int i = 1; i <= num_people ; i++) {
                base += i;
            }

            // 每一轮相比上一轮的增量
            int increment = num_people * num_people;


            // 下一次分发是第几轮
            int loop = 1;
            // 记录总的消耗
            int sum = 0;
            while (sum <= candies) {
                // 模拟分发一轮
                sum += base + increment * (loop - 1);
                loop++;
            }

            /**
             * 此时 sum > candies
             * loop 为将要分发的下一轮 loop - 1为已分发的一最后一轮
             * 此时可以推断
             * 分发完第loop - 2轮之后, sum <= candies
             * 分发完第loop - 1轮之后, sum > candies
             *
             * 所以能完整分发第loop - 2轮, 不完整分发第loop - 1轮
             * 于是可以计算每个小朋友的糖果数了
             */

            int[] result = new int[num_people];

            // 先根据公式进行完整分发
            sum = 0;
            for (int i = 0; i < num_people; i++) {

                int give = (i + 1) * (loop - 2) + num_people * ((loop - 2) * (loop - 3)) / 2;
                sum += give;
                result[i] = give;
            }

            // 此时sum还剩下的就依次分给就行了.
            int remain = candies - sum;
            for (int i = 0; i < num_people; i++) {
                int togive = (i + 1) + num_people * (loop - 2);
                if (remain >= togive) {
                    result[i] += togive;
                    remain -= togive;
                } else {
                    result[i] += remain;
                    break;
                }
            }
            return result;

        }

    }
}

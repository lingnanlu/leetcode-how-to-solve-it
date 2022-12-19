package math;


/**
 * 这题当然可以一个一个数字进行检查, 统计符合要求的.
 * 但这个感觉显然可以通过计算来得出来. 通过两者的差值.
 *
 * 当么两者差值和个数有什么关系呢? 我觉得可以举例子看看.
 *
 * 无非四种情况
 *
 * 偶偶, 偶奇, 奇偶, 奇奇
 *
 * 偶偶  2 - 6 = 3, 4, 5 = 2个
 * 偶奇 2 - 7 = 3, 4, 5, 6 = 2 + 1 = 3个
 * 3 - 8 = 4, 5, 6, 7
 * 3 - 9 = 4, 5, 6, 7, 8
 *
 * 观察规律
 *
 * 其实最后奇数的个数由2者本身是否为奇, 以及两者相减的个数差决定的.
 *
 * 这题解的并不优雅, 但把情况列举完全了.其实也能做对. 关键是能不能把情况列举完全.
 *
 * 我想还有优雅的方式就是从数学角度来描述规律了. 见方法二
 */
public class Count_Odd_Numbers_in_an_Interval_Range {

    static class First {
        public int countOdds(int low, int high) {
            int result = 0;

            // 两者相减的个数差
            int diff = high - low - 1;

            if (diff > 0) {
                if (diff % 2 == 0) {
                    result += diff / 2;
                } else {
                    // 此时有奇数个
                    if ((low + 1) % 2 == 0) {
                        // 偶数开头
                        result += diff / 2;
                    } else {
                        // 奇数开头
                        result += diff / 2 + 1;
                    }
                }
            }

            if (low % 2 == 1) {
                result++;
            }

            if (high != low) {
                if (high % 2 == 1) {
                    result++;
                }
            }

            return result;
        }
    }

    static class Second {

        public int countOdds(int low, int high) {
            /**
             * low 到high之间的个数(包含low, high)要不是奇, 要不是偶
             * 偶数好说, 就是一半
             * 奇数个就判断开头(low)是奇还是偶
             */

            int diff = high - low + 1;

            if (diff % 2 == 0) return diff / 2;
            else {
                if (low % 2 == 0) {
                    // 比如说 2 - 6 = 2, 3, 4, 5, 6
                    return diff / 2;
                } else {
                    return diff / 2 + 1;
                }
            }
        }

    }


}

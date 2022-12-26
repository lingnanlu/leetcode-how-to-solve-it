package Array;

/**
 * 考察原地重排
 *
 * 这里最关键的是, 会覆盖部分元素, 如何防止被覆盖?
 *
 * 画一个图.
 *
 * 这个自己尝试了, 发现原地真的不容易想明白. 还是先使用简单的方法吧.
 */
public class Shuffle_The_Array {

    static class First {
        public int[] shuffle(int[] nums, int n) {

            // 交差的将p, q放入新数组中.
            int p = 0, q = n;

            // 新数组的下一个空位
            int i = 0;

            int[] result = new int[2 * n];

            while (i != result.length) {
                // 先放p, 再放q
                result[i++] = nums[p++];
                result[i++] = nums[q++];
            }

            return result;
        }
    }

    // 原地
    static class Second {
        public int[] shuffle(int[] nums, int n) {
            return null;
        }
    }

}

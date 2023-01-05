package Array;

import java.util.Arrays;

/**
 * 直观一点呢, 其实就是分两步
 * 1. 找到丢失的数 => 就是判断nums中是否有从1到n之间的数
 * 2. 找到重复的数 => 就是找出现两次的数
 *
 * 可以对数组排序, 然后一次遍历就可以找到1, 2了
 *
 * 方法二:
 *
 * 方法一是先期待一个数, 然后在nums中找有没有存在, 为了避免每一次都要遍历一遍, 所以先排了个序.
 *
 * 那么, 能不能不排序? 我们就遍历nums中的数, 出现一个, 就在另一个地方标记出现了, 最后丢失的就是没标记的, 重复的就是标记两次的.
 *
 * 方法三:
 *
 * 这题其实也可以纯数学来解, 1...n想到求和
 */

public class Set_Mismatch {

    static class First {
        public int[] findErrorNums(int[] nums) {
            Arrays.sort(nums);

            // 0只是一个占位
            int missed = 0;
            int duplicate = 0;

            int expected = 1; // 下一个期待出现的数
            for (int i = 0; i < nums.length; i++) {

                // 如果还没找到丢失的数
                if (missed == 0) {
                    if (nums[i] == expected) {
                        expected++;
                    } else if (nums[i] > expected) {
                        // 当前数大于下一个期待的数, 因为排序原因, 后面的数都会比它大, 所以expected就是missed
                        missed = expected;
                    }
                }


                if (duplicate == 0) {
                    if (i > 0 && nums[i] == nums[i - 1]) {
                        duplicate = nums[i];
                    }
                }

                // 说明丢失的和重复的都找到了
                if (duplicate != 0 && missed != 0) {
                    break;
                }
            }

            // 说明遍历完了, 也没找到丢失的数. 1 ~ expected - 1都出现在nums中了.
            // 那么, 唯一的丢失可能就是expected了
            // 这个细节要注意, 一开始忘记了, 其实根本原因还是不变式在循环结束后的意义没搞明白.
            if (missed == 0) {
                missed = expected;
            }

            return new int[]{duplicate, missed};
        }
    }

    static class Second {
        public int[] findErrorNums(int[] nums) {
            int[] mark = new int[10000 + 1];

            for (int n : nums) {
                mark[n]++;
            }

            // 0只是一个占位
            int missed = 0;
            int duplicate = 0;

            for (int i = 1; i < mark.length; i++) {
                if (mark[i] == 0) {
                    missed = i;
                } else if (mark[i] == 2) {
                    duplicate = i;
                }
            }

            return new int[]{duplicate, missed};

        }


    }

}

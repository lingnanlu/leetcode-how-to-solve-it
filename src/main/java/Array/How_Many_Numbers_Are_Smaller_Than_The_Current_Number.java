package Array;

import java.util.Arrays;
import java.util.Comparator;

/**
 * 方法一:
 *
 * 最直观的就是对于每一个数, 都把数组遍历一遍. 但显然这个时间复杂度就是O(n^2)
 *
 * 方法二:
 * 那我们来看看能不能在这个朴素的方法一中, 优化一下.
 *
 * [8,1,2,2,3]
 *
 * 比如对于8来说, 1, 2, 2, 3都比其小
 *
 * 对于1来说, 8比其大, 其实这个比较之前已经比过了, 那么, 能不能当8和1比时, 把8, 1对应的值都更新一下?
 *
 * 其实最后就是两两比较, 然后更新对应的值.
 *
 * 相比与方法一, 省去了很多重复比较.
 *
 * 方法三:
 *
 * 方法二中的方法还是O(n^2), 那么, 能不能O(n)?
 *
 * 能不能快速知道比某个数小的数有几个? 其实只要是有序的就好了.
 */
public class How_Many_Numbers_Are_Smaller_Than_The_Current_Number {

    static class First {
        public int[] smallerNumbersThanCurrent(int[] nums) {

            int[] result = new int[nums.length];

            for (int i = 0; i < nums.length; i++) {

                int count = 0;
                for (int j = 0; j < nums.length; j++) {
                    if (j != i && nums[j] < nums[i]) {
                        count++;
                    }
                }

                result[i] = count;
            }

            return result;
        }
    }

    static class Second {

        public int[] smallerNumbersThanCurrent(int[] nums) {


            int[] result = new int[nums.length];

            // 其实就是两两比较, 然后更新对应的值
            for (int i = 0; i < nums.length; i++) {
                for (int j = i + 1; j < nums.length; j++) {
                    if (nums[i] < nums[j]) {
                        result[j]++;
                    } else if (nums[i] > nums[j]) {
                        result[i]++;
                    } else {
                        // do nothing
                    }
                }

            }

            return result;

        }

    }

    static class Third {

        public int[] smallerNumbersThanCurrent(int[] nums) {
            // 保留原来的位置
            Tuple[] tuples = new Tuple[nums.length];

            for (int i = 0; i < nums.length; i++) {
                tuples[i] = new Tuple(nums[i], i);
            }

            Arrays.sort(tuples, Comparator.comparingInt(o -> o.val));

            int[] result = new int[nums.length];

            for (int i = 0; i < tuples.length; i++) {

                // 最多小于它的就是i个, 因为可能有相同的.
                int mostLess = i;

                // 减去相同的.
                for (int j = i - 1; j >= 0 ; j--) {
                    if (tuples[j].val == tuples[i].val) {
                        mostLess--;
                    }
                }
                result[tuples[i].index] = mostLess;
            }

            return result;


        }

        static class Tuple {
            int val;
            int index;

            public Tuple(int val, int index) {
                this.val = val;
                this.index = index;
            }
        }

    }

}

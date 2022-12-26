package Array;


import java.util.Arrays;

/**
 *
 * 方法一:
 * 这里其实就是按照arr2来对arr1进行排序.
 *
 * 原地进行排序, 按照arr2中的顺序进行排序.
 *
 * 为了保证是arr2中的顺序, 就是依次遍历arr2中的元素, 然后对于arr1中出现的, 就依次放在前面.
 *
 * 这种方法时间复杂度较高, 对于arr2中的每一个元素, 其实都要遍历一遍arr1
 *
 * 方法一一遍过
 *
 * 方法二:
 *
 * 方法一的时间复杂度太高, 能不能把时间复杂度降下来.
 *
 * 我们来看结果, 发现, 最终的排序数组其实是根据arr2中的顺序, 以及在arr1出现的个数进行排序的.
 *
 * 再看条件.0 <= arr1[i], arr2[i] <= 1000, 所以比较容易统计个数.
 *
 * 那么, 利用统计个数的方法试一试.
 *
 */
public class Relative_Sort_Array {

    static class First {

        public int[] relativeSortArray(int[] arr1, int[] arr2) {
            // arr1中下一个要插入的位置
            int p = 0;

            int temp; // 用来存放临时元素.
            // 开始遍历arr1中的位置
            int start = 0;

            for (int i = 0; i < arr2.length; i++) {
                int target = arr2[i]; // 要从arr1中找的值.

                // 从start找到最后
                for (int j = start; j < arr1.length; j++) {

                    // 找到一个, 放到p所指的地方
                    if (arr1[j] == target) {
                        temp = arr1[p];
                        arr1[p] = arr1[j];
                        arr1[j] = temp;
                        p++;
                    }
                }

                // 此时, [0, p)之间的元素就是根据arr2中的已遍历的元素排好序的.
                start = p;
            }

            // 此时, [0, p)之间的元素就是根据arr2中的所有元素排好序的. 将剩余的元素排序

            Arrays.sort(arr1, p, arr1.length);

            return arr1;
        }
    }

    static class Second {

        public int[] relativeSortArray(int[] arr1, int[] arr2) {

            // count[i] 为 i出现的次数
            int[] count = new int[1001];

            for (int n : arr1) {
                count[n]++;
            }

            int p = 0;

            for (int n : arr2) {
                while (count[n] != 0) {
                    arr1[p++] = n;
                    count[n]--;
                }
            }

            // 此时int[] count中的非零的就是不在arr2中的
            for (int i = 0; i < count.length; i++) {
                while (count[i] != 0) {
                    arr1[p++] = i;
                    count[i]--;
                }
            }

            return arr1;
        }

    }

}

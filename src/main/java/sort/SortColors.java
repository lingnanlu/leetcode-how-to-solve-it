package sort;

import java.util.Arrays;

/**
 * 分析条件，要排序的其实只有三个数0，1，2
 * <p>
 * 要求
 * <p>
 * 1. 常量空间
 * 2. 一遍遍历
 * <p>
 * 思路
 * <p>
 * 1. count sort
 * <p>
 * 使用快排，以1作为pivot
 * <p>
 * 一遍排完后， 比1小的都在左边， 比1大的都在右边
 * <p>
 * 快排不行， 因为和1相同的， 不确定和0和相对位置
 *
 *
 * 这个one pass的遍历还是想不明白， 有些奇怪。暂时记住 counting sort和two pass的吧。
 */
public class SortColors {

    // 其实就是先简化问题，去除一部分条件，比如能不能只把0排在前面。
    // 这样可以探索出灵感。
    // two pass 先交换1， 再交换2
    public void sortColors(int[] nums) {

        //

        int red = 0;

        int i = 0;

        /**
         *  [0, red)都是红 ， red是要插入的位置
         * 对每一个元素
         *
         * 如果nums[i]是0
         *  如果red元素不是0， 则交换，
         *  如果red元素是0，则不交换
         *
         *  以上都要移动red
         */
        while (i < nums.length) {

            if (nums[i] == 0) {
                if (nums[red] != 0) {
                    int temp = nums[red];
                    nums[red] = nums[i];
                    nums[i] = temp;
                }
                red++;
            }

            i++;
        }

        // （blue, nums.length - 1]都是蓝
        int blue = nums.length - 1;

        i = nums.length - 1;
        while (i >= 0) {
            if (nums[i] == 2) {

                if (nums[blue] != 2) {
                    int temp = nums[blue];
                    nums[blue] = nums[i];
                    nums[i] = temp;
                }

                blue--;
            }
            i--;
        }
    }

    // one pass 在一趟过程中完成交换
    public void sortColors2(int[] nums) {

        int red = 0, blue = nums.length - 1;

        boolean hasSwitch = false;
        for (int i = 0; i < blue + 1; ) {

            if (nums[i] == 1) {
                i++;
            } else {

                hasSwitch = false;
                if (nums[i] == 0) {

                    if (nums[red] != 0) {
                        swap(nums, i, red);
                        hasSwitch = true;
                    }
                    red++;

                } else if (nums[i] == 2) {
                    if (nums[blue] != 2) {
                        swap(nums, i, blue);
                        hasSwitch = true;
                    }
                    blue--;
                }

                if (!hasSwitch) i++;

            }


        }
    }


    private static void swap(int[] A, int i, int j) {

        int tmp = A[i];

        A[i] = A[j];

        A[j] = tmp;

    }

    public static void main(String[] args) {
        int[] nums = {2, 0, 2, 1, 1, 0};
        SortColors test = new SortColors();

        test.sortColors2(nums);
        System.out.println(Arrays.toString(nums));
    }
}

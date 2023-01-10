package Array;

/**
 * 这题还是有点陷阱的
 *
 * 一开始的思路是通过前两个判断是递增还是递减.
 *
 * 这个的问题是, 通过前两个一定能判断出来是递增还是递减.
 *
 * 但可能通过前两个判断不出来.
 *
 * 如
 *
 * [1, 1, 1, 0]这种
 *
 * 所以一定要找两个不相同的.
 *
 * 方法三:
 *
 * 如果数组中有两组数, 一组递增, 一组递减, 那么也能判断. 但感觉不如自己的方法直观.
 * 因为自己的方法就是模拟眼睛从左到右的遍历.
 */
public class Monotonic_Array {

    // 这个方法是错误的.
    static class First {
        public boolean isMonotonic(int[] nums) {

            if (nums.length <= 1) return true;

            // 通过前两个判断是递增还是递减, 1表示是递增, 0表示是递减
            int upOrDown = nums[1] >= nums[0] ? 1 : 0;

            int i = 2;
            while (i < nums.length) {
                if (upOrDown == 1 && nums[i] >= nums[i - 1]) {
                    i++;
                } else if (upOrDown == 0 && nums[i] <= nums[i - 1]) {
                    i++;
                } else {
                    break;
                }
            }

            return i == nums.length;
        }
    }

    static class Second {
        public boolean isMonotonic(int[] nums) {
            if (nums.length <= 1) return true;

            // 通过前几个判断是递增还是递减. 找出两个不同的才能判断

            int i = 0, j = 1;

            while (j != nums.length && nums[i] == nums[j]) {
                i++;
                j++;
            }

            // 说明全部相同
            if (j == nums.length) {
                return true;
            }

            // 此时i, j指向不同元素.
            int upOrDown = nums[j] > nums[i] ? 1 : 0;

            i = j + 1;
            while (i < nums.length) {
                if (upOrDown == 1 && nums[i] >= nums[i - 1]) {
                    i++;
                } else if (upOrDown == 0 && nums[i] <= nums[i - 1]) {
                    i++;
                } else {
                    break;
                }
            }

            return i == nums.length;
        }
    }

}

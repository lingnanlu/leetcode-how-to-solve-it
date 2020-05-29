package search;

// For example, Given [5, 7, 7, 8, 8, 10] and target value 8, return [3, 4] .

import java.util.Arrays;

/**
 *
 * 学会将这种问题转换为二分查找问题。
 *
 * 这里的关键是
 *
 * 如何使用两分查找来找到上下边界。
 *
 * 其实找的是这样的一位置
 *
 * nums[i] = target && nums[i - 1] < target
 *
 * 和二分查找一样， 只是判断条件变了。
 *
 * 所以这里要学会扩展二分查找。
 *
 * 扩展二分查找的关键有两点
 *
 * 1. 什么样的条件
 * 2. 有三种判断结果，对每一种结果，如何处理
 */
public class Search_For_A_Range {
    public int[] searchRange(int[] nums, int target) {

        int lower = findLowerBound(nums, target);
        int upper = findUpperBound(nums, target);

        return new int[] {lower, upper};
    }

    // 利用二分查找，找下界
    public int findLowerBound(int[] nums, int target) {

        int l = 0, r = nums.length - 1;

        int lower = -1;
        while(l <= r) {
            int middle = l + (r - l) / 2;

            int result = isLowerBound(nums, middle, target);
            if (result == 0) {
                lower = middle;
                break;
            } else if (result == 1){
                l = middle + 1;
            } else {
                r = middle - 1;
            }
        }

        return lower;

    }
    /**
     * 判断position所指的元素是不是下边界
     * @param nums
     * @param position
     * @param target
     * @return
     *  0 是
     *  1 向右区间查找
     *  -1 向左区间查找
     */
    private int isLowerBound(int[] nums, int position, int target) {
        if (nums[position] > target) {
            return -1;
        }

        if (nums[position] < target) {
            return 1;
        }

        int pre = position - 1;

        if (pre < 0) {
            return 0;
        } else {
            if (nums[pre] < target) {
                return 0;
            } else {
                // 此时pre和target相等
                return -1;
            }
        }

    }


    // 利用二分查找，找上界
    public int findUpperBound(int[] nums, int target) {

        int l = 0, r = nums.length - 1;

        int upper = -1;
        while(l <= r) {
            int middle = l + (r - l) / 2;

            int result = isUpperBound(nums, middle, target);
            if (result == 0) {
                upper = middle;
                break;
            } else if (result == 1){
                l = middle + 1;
            } else {
                r = middle - 1;
            }
        }

        return upper;

    }
    /**
     * 判断position所指的元素是不是上边界
     * @param nums
     * @param position
     * @param target
     * @return
     *  0 是
     *  1 向右区间查找
     *  -1 向左区间查找
     */
    private int isUpperBound(int[] nums, int position, int target) {
        if (nums[position] > target) {
            return -1;
        }

        if (nums[position] < target) {
            return 1;
        }

        int after = position + 1;

        if (after > nums.length - 1) {
            return 0;
        } else {
            if (nums[after] > target) {
                return 0;
            } else {
                // 此时pre和target相等
                return 1;
            }
        }

    }


    public static void main(String[] args) {

        int[] test = {5, 7, 7, 8, 8, 10};

        Search_For_A_Range example = new Search_For_A_Range();

        System.out.println(Arrays.toString(example.searchRange(test, 6)));
    }
}

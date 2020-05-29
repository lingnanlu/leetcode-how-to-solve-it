package search;


/**
 *
 * 其实二分查找的精髓不是要求数组一定有序。
 *
 * 而是能每一次都能二分的缩小范围。
 */
public class Search_In_Rotated_Sorted_Array {

    public int search(int[] nums, int target) {

        int l = 0, r = nums.length - 1;

        while (l <= r) {
            int mid = l + (r - l) / 2;

            if (nums[mid] == target) {
                return mid;
            } else if (nums[mid] > target) {
                // 可能两边的元素都比中间小， 那么到底是向左走还是向右走呢？
                // 这里是关键， 决定向左走还是向右走
                // 如果左边有序
                if (nums[l] <= nums[mid]) {
                    if (nums[l] <= target) {
                        r = mid - 1;
                    } else {
                        l = mid + 1;
                    }
                } else {
                    // 右边有序
                    r = mid - 1;
                }
            } else {
                if (nums[l] <= nums[mid]) {
                    l = mid + 1;
                } else {
                    if (nums[r] >= target) {
                        l = mid + 1;
                    } else {
                        r = mid - 1;
                    }
                }
            }
        }

        return -1;
    }
}

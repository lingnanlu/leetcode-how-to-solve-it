package search;

public class Search_In_Rotated_Sorted_Array_2 {

//    public boolean search(int[] nums, int target) {
//
//        int l = 0, r = nums.length - 1;
//
//        while (l <= r) {
//            int mid = l + (r - l) / 2;
//
//            if (nums[mid] == target) {
//                return true;
//            } else if (nums[mid] > target) {
//                // 可能两边的元素都比中间小， 那么到底是向左走还是向右走呢？
//                // 这里是关键， 决定向左走还是向右走
//                // 如果左边有序, 这里，小于等于不能判断左边有序了， 只有小于可以断定，
//                if (nums[l] <= nums[mid]) {
//                    if (nums[l] <= target) {
//                        r = mid - 1;
//                    } else {
//                        l = mid + 1;
//                    }
//                } else {
//                    // 右边有序
//                    r = mid - 1;
//                }
//            } else {
//                if (nums[l] <= nums[mid]) {
//                    l = mid + 1;
//                } else {
//                    if (nums[r] >= target) {
//                        l = mid + 1;
//                    } else {
//                        r = mid - 1;
//                    }
//                }
//            }
//        }
//
//        return false;
//    }


    public boolean search(int[] nums, int target) {

        int l = 0, r = nums.length - 1;

        while (l <= r) {
            int mid = l + (r - l) / 2;

            if (nums[mid] == target) {
                return true;
            } else if (nums[mid] > target) {
                // 可能两边的元素都比中间小， 那么到底是向左走还是向右走呢？
                // 这里是关键， 决定向左走还是向右走

                if (nums[l] < nums[mid]) {       // 左边有序
                    if (nums[l] <= target) {
                        r = mid - 1;
                    } else {
                        l = mid + 1;
                    }
                } else if (nums[l] > nums[mid]){        // 左边无序
                    // 右边有序
                    r = mid - 1;
                } else {
                    // nums[l] == nums[mid] 不知道有没有序， 但没有关系， 缩小搜索范围就行，
                    l++;
                }
            } else {
                if (nums[l] < nums[mid]) {      // 左边有序
                    l = mid + 1;
                } else if (nums[l] > nums[mid]){ // 右边有序
                    if (nums[r] >= target) {
                        l = mid + 1;
                    } else {
                        r = mid - 1;
                    }
                } else {
                    l++;
                }
            }
        }

        return false;
    }
}

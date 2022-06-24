package Array;

import run.elder.Category;
import run.elder.Leetcode;

@Leetcode(
        title = "Search in Rotated Sorted Array",
        link = "https://leetcode.com/problems/search-in-rotated-sorted-array/",
        category = Category.ARRAY,
        how2SolveIt = """
                这题和一般二分查找不同的是, nums是经过rotate了的.
                
                如果想利用二分查找的结果, 可以这样, 一个rotate其实就是两个有序的数组.
                
                如果能找到两个有序数组, 就可以利用二分查找的结果了.
                
                所以, 这里要找到pivot.
                
                本题的关键就是最多执行三次二分搜索
                
                1. 一次找到pivot
                2. 二次就是常规的二分搜索
                
                """,
        relatedQuestions = {
        }
)
public class Search_In_Rotated_Sorted_Array {

    public int search(int[] nums, int target) {
        int pivot = findPivot(nums);

        if (pivot == 0) {
            return binarySearch(nums, 0, nums.length - 1, target);
        } else {
            int targetIndex = -1;
            targetIndex = binarySearch(nums, 0, pivot - 1, target);
            if (targetIndex != -1) {
                return targetIndex;
            } else {
                return binarySearch(nums, pivot, nums.length - 1, target);
            }
        }
    }

    /**
     * 二分查找的本质其实就是每次排除一半元素, 不一定要严格有序, 所以要想想, 如何能排除一半元素呢
     * 画个图
     *
     * 返回0表示数组没有旋转
     */
    private int findPivot(int[] nums) {
        int i = 0, j = nums.length - 1;
        /**
         * 不变式一
         * i左边的都是有序的
         * j右边的都是有序的
         * [i, j]是搜寻的区间
         * middle是搜寻区间的中间元素Index
         *
         * 注意, 这里最关键, 每次要排除一部分, 不能多排除, 也不能少排除
         *
         * 不变式二
         * 区间中至少有两个元素, 因为判断两个元素需要才能知道pivot在哪.
         */
        int middle = (i + j) / 2;
        while(i < j) {
            if (nums[middle] > nums[middle + 1]) {
                return middle + 1;
            } else if (nums[middle] >= nums[i]){   // 说明前半部分有序, 不在前半部分
                i = middle + 1;
            } else {        // 说明前半部分无序, 在后半部分
                j = middle; // 注意这里, 没有 - 1, 举个例子就明白了.
            }

            middle = (i + j) / 2;
        }

        //
        /**
         * 到这里, 只可能是i==j, 因为上面的变化是每次i增加一个步长
         * 而又由不变式一可知
         * i左边(包含i)都是有序的
         * j右边(包含j)都是有序的
         * i == j
         * 所以, 整个数组是有序的.
         * 所以
         */

        return 0;

    }

    /**
     * 在nums的[i, j] 区间进行搜索, 该区间上的元素保证单调递增
     */
    private int binarySearch(int[] nums, int i, int j, int target) {

        int middle; // 这里不需要init, 只是为了不再循环中重复的声明
        // 为什么要 i <= j 呢? 说明有元素
        while (i <= j) {
            middle = (i + j) / 2;
            if (nums[middle] == target) {
                return middle;
            } else if (nums[middle] > target) {
                j = middle - 1;
            } else {
                i = middle + 1;
            }
        }

        return -1;

    }
}

package Array.binary;

import run.elder.Category;
import run.elder.Leetcode;
import run.elder.Solution;

@Leetcode(
        title = "Find First and Last Position of Element in Sorted Array",
        link = "https://leetcode.com/problems/find-first-and-last-position-of-element-in-sorted-array/",
        category = Category.ARRAY,
        how2SolveIt = """
                方法一: 
                
                二分查找, 找到这个元素的index, 然后往前和往后搜索, 最坏是O(n)
                
                
                方法二:
                
                利用了一般二分查找中的方法, 关键点还是有二
                
                1. 利用循环不变式搞明白, i, j的含义
                2. 注意循环过程中不变式为真, 且区间不断缩小
                
                
                该题在leetcode一遍过
              
                """,
        relatedQuestions = {
        }
)
public class Find_First_And_Last_Position_Of_Element_In_Sorted_Array {

    @run.elder.Solution(detail = """
            循环不变式, 彻底搞明白i, j的含义
            """
    )
    static class Solution {

        public int[] searchRange(int[] nums, int target) {

            int firstPosition = searchFirstPosition(nums, target);
            int lastPosition = searchLastPosition(nums, target);
            return new int[]{firstPosition, lastPosition};

        }

        private int searchFirstPosition(int[] nums, int target) {

            int i = 0, j = nums.length - 1;

            int middle; // 这里不需要init, 只是为了不再循环中重复的声明

            /**
             * 不变式一
             * i左边的(不包含i), 都 < target
             * j右边的(不包含j), 都 >= target
             *
             * 不变式二
             *
             * i, j之间有元素
             *
             * 每一次迭代, 都要缩小区间.
             * 每一次迭代, 都要使不变式一为真
             */
            while(i <= j) {
                middle = (i + j) / 2;
                int n = nums[middle];
                if (n > target) {
                    j = middle - 1;
                } else if (n < target) {
                    i = middle + 1;
                } else if (n == target) {
                    /**
                     * 注意这里, 依然要
                     * 1. 不变式为真
                     * 2. 区间要缩小
                     */
                    j = middle - 1;
                }
            }

            /**
             * 此时不变式一依然为真, 因为循环过程保证了真
             * 而由循环过程可以知道, 最后
             *
             * i = j + 1;
             *
             * 由不变式一可知
             *
             * nums[j] < target // j在i左边
             * nums[i] >= target // i在j右边
             *
             * 注意, 由缩减过程可知, i一直往右走, 要判断是否越界
             */
            if(i < nums.length && nums[i] == target) {
                return i;
            } else {
                return -1;
            }
        }

        private int searchLastPosition(int[] nums, int target) {

            int i = 0, j = nums.length - 1;

            int middle; // 这里不需要init, 只是为了不再循环中重复的声明

            /**
             * 不变式一
             * i左边的(不包含i), 都 <= target
             * j右边的(不包含j), 都 > target
             *
             * 不变式二
             *
             * i, j之间有元素
             *
             * 每一次迭代, 都要缩小区间.
             * 每一次迭代, 都要使不变式一为真
             */
            while(i <= j) {
                middle = (i + j) / 2;
                int n = nums[middle];
                if (n > target) {
                    j = middle - 1;
                } else if (n < target) {
                    i = middle + 1;
                } else if (n == target) {
                    /**
                     * 注意这里, 依然要
                     * 1. 不变式为真
                     * 2. 区间要缩小
                     */
                    i = middle + 1;
                }
            }

            /**
             * 此时不变式一依然为真, 因为循环过程保证了真
             * 而由循环过程可以知道, 最后
             *
             * i = j + 1;
             *
             * 由不变式一可知
             *
             * nums[j] <= target // j在i左边
             * nums[i] > target // i在j右边
             *
             * 注意, 由缩减过程可知, j一直往左走, 要判断是否越界
             */
            if(j >= 0 && nums[j] == target) {
                return j;
            } else {
                return -1;
            }
        }
    }
}

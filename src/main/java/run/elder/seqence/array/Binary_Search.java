package run.elder.seqence.array;

import run.elder.Category;
import run.elder.Leetcode;

@Leetcode(
        title = "Binary Search",
        link = "https://leetcode.com/problems/binary-search/",
        category = Category.ARRAY,
        how2SolveIt = """
                经典的二分查找问题
                
                这里, 最难的其实是
                1. i, j, middle所指向元素的含义
                2. 搞明白查询范围是如何缩小的.
                
                
                
                如果一个区间有元素, 那么
                
                i <= middle <= j
                
                而此时, 如果middle不是要查找的元素, 有两种情况
                
                1. 比target大
                2. 比target小
                
                这两种情况都会导致区间近一步缩小.
                
                而缩小的后果可能是
                1. 区间还有元素
                2. 区间没有元素了 (i > j)
                
                对于1, 继续迭代
                对于2, 返回-1
                """,
        relatedQuestions = {
                "Search Insert Position"
        }
)
public class Binary_Search {

    static class Solution {

        public int search(int[] nums, int target) {

            int i = 0, j = nums.length - 1;

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
}

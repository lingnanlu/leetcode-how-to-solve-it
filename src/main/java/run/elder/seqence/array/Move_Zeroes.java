package run.elder.seqence.array;

import run.elder.Category;
import run.elder.Leetcode;
import run.elder.Solution;

@Leetcode(
        title = "Move Zeroes",
        link = "https://leetcode.com/problems/move-zeroes/",
        category = Category.ARRAY,
        how2SolveIt = """
                其实这种题和Remove Element, Remove Duplicates From Sorted Array一样，都是对数组的元素做移动, 这种的套路都可以统一成
                1. 使用两个数组, 进行复制
                2. 将两个数组合成一个.
                
                如果你原地不好解决，可以先使用一个新数组，看如何操作的，然后再原地就行了。
                
                一般这种题就是O(n)的复杂度
                """,
        relatedQuestions = {}
)
public class Move_Zeroes {

    @Solution
    static class First {
        public void moveZeroes(int[] nums) {

            int[] newNums = nums;

            int i = 0;
            int j = 0;

            while (i != nums.length) {
                if(nums[i] != 0) {
                    newNums[j] = nums[i];
                    j++;
                }
                i++;
            }


            while(j != nums.length) {
                newNums[j] = 0;
            }
        }
    }

}

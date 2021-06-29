package question.array;

import run.elder.Category;
import run.elder.Leetcode;

@Leetcode(
        title = "Move Zeroes",
        link = "https://leetcode.com/problems/move-zeroes/",
        category = Category.ARRAY,
        how2SolveIt = """
                其实这种题和Remove Element, Remove Duplicates From Sorted Array一样，就是要求原地对数组进行一些操作。
                
                如果你原地不好解决，可以先使用一个新数组，看如何操作的，然后再原地就行了。
                
                一般这种题就是O(n)的复杂度
                """,
        relatedQuestions = {}
)
public class Move_Zeroes {

    public void moveZeroes(int[] nums) {
        int begin = 0;
        for (int i = 0; i < nums.length; i++) {
            if(nums[i] != 0) {
                nums[begin] = nums[i];
                begin++;
            }
        }

        for (int i = begin; i < nums.length; i++) {
            nums[i] = 0;
        }
    }
}

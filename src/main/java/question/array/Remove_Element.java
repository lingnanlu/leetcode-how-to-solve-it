package question.array;

import run.elder.Category;
import run.elder.Leetcode;

@Leetcode(
        title = "Remove Element",
        link = "https://leetcode.com/problems/remove-element/",
        category = Category.ARRAY,
        how2SolveIt = """
                这题很简单，使用一个指针遍历原数组，另一个指针指向结果数组就行。
                """,
        relatedQuestions = {}
)
public class Remove_Element {

    public int removeElement(int[] nums, int val) {

        int begin = 0;

        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != val) {
                nums[begin] = nums[i];
                begin++;
            }
        }

        return begin;
    }
}

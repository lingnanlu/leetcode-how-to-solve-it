package question.array;

import elder.Category;
import elder.Leetcode;

@Leetcode(
        title = "Remove Element",
        category = Category.ARRAY,
        howToSolveIt = """
                这题很简单，使用一个指针遍历原数组，另一个指针指向结果数组就行。
                """
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

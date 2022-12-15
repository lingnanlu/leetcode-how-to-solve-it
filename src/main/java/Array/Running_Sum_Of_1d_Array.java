package Array;


import run.elder.Category;
import run.elder.Leetcode;

@Leetcode(
        title = "Running Sum of 1d Array",
        link = "https://leetcode.com/problems/running-sum-of-1d-array/",
        category = Category.ARRAY,
        how2SolveIt = """
                很简单的题, 没什么可说的, 要求一遍过
                """,
        relatedQuestions = {}
)
public class Running_Sum_Of_1d_Array {

    public int[] runningSum(int[] nums) {

        int[] runningSums = new int[nums.length];

        // 依次产生每一个元素
        for (int i = 0; i < runningSums.length; i++) {
            // 不确定优先级时, 要加()
            runningSums[i] = (i == 0 ? 0 : runningSums[i - 1]) + nums[i];
        }

        return runningSums;
    }
}

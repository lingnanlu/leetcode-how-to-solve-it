package Array;

import run.elder.Category;
import run.elder.Leetcode;

@Leetcode(
        title = "Max Consecutive Ones",
        link = "https://leetcode.com/problems/max-consecutive-ones/",
        category = Category.ARRAY,
        how2SolveIt = """
                考察一次性通过
                """,
        relatedQuestions = {}
)
public class Max_Consecutive_Ones {
    public int findMaxConsecutiveOnes(int[] nums) {

        // 已找到的最长
        int max = 0;
        int i = 0; // 指向下一个要测试的位置, 这里以最开始为测试位置
        while(i != nums.length) {

            // 先找到开始的1
            while (i != nums.length && nums[i] == 0) {
                i++;
            }

            if (i == nums.length) {
                return max;
            }

            //此时i指向的是1, 说明可能要更新max

            int count = 0;  // 统计1的个数
            while(i != nums.length && nums[i] == 1) {
                count++; // i指向一个1, 就统计一次
                i++;
            }

            // 更新max
            max = Math.max(max, count);

            // 之后, 进入下一轮测试
        }

        return max;
    }
}

package easy;

import elder.Question;

@Question(
        title = "Single Number",
        url = "https://leetcode.com/problems/single-number/",
        description = """
        Given a **non-empty** array of integers, every element appears twice except for one. Find that single one.

        Note:

        Your algorithm should have a linear runtime complexity. Could you implement it without using extra memory?

        Example 1:
        ```
        Input: [2,2,1]
        Output: 1
        ```

        Example 2:

        ```
        Input: [4,1,2,1,2]
        Output: 4
        ```

        """,
        solution = "this is the soluction",
        category = "other"
)
public class Single_Number {


    public int singleNumber(int[] nums) {

        int result = 0;

        for (int n : nums) {
            result = result ^ n;
        }

        return result;
    }
}
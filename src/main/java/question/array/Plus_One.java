package question.array;


import run.elder.Category;
import run.elder.Leetcode;

@Leetcode(
        title = "Plus One",
        link = "https://leetcode.com/problems/plus-one/",
        category = Category.ARRAY,
        how2SolveIt = """
                没什么可说的，纯粹模拟加法操作
                """,
        relatedQuestions = {}
)
public class Plus_One {

    public int[] plusOne(int[] digits) {
        int carry = 1;

        for (int i = digits.length - 1; i >= 0; i--) {
            digits[i] = digits[i] + carry;      //对于最低位来说, 相当于后一位有一个进位
            if (digits[i] == 10) {
                digits[i] = 0;
                carry = 1;
            } else {
                carry = 0;
            }
        }

        if(carry == 1) {
            int[] result = new int[digits.length + 1];
            result[0] = 1;
            for (int i = 1; i < result.length; i++) {
                result[i] = 0;
            }
            return result;
        } else {
            return digits;
        }
    }

}

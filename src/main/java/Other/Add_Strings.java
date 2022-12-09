package Other;

import run.elder.Category;
import run.elder.Leetcode;

@Leetcode(
        title = "Add Strings",
        link = "https://leetcode.com/problems/add-strings/",
        category = Category.UNKNOWN,
        how2SolveIt = """
                模型题, 要求一遍过
                """,
        relatedQuestions = {}
)
public class Add_Strings {

    static class First {
        public String addStrings(String num1, String num2) {

            // p, q指向要进行加法的最低位
            int p = num1.length() - 1;
            int q = num2.length() - 1;

            // 上一次计算的进位
            int carry = 0;

            StringBuilder sb = new StringBuilder();

            while (p != -1 && q != -1) {

                // 最低位进行计算
                int sum = (num1.charAt(p) - '0') + (num2.charAt(q) - '0') + carry;

                p--;
                q--;

                if (sum >= 10) {
                    carry = 1;
                    sb.append(sum - 10);
                } else {
                    carry = 0;
                    sb.append(sum);
                }
            }

            while (p != -1) {
                int sum = (num1.charAt(p) - '0') + carry;

                p--;
                if (sum >= 10) {
                    carry = 1;
                    sb.append(sum - 10);
                } else {
                    carry = 0;
                    sb.append(sum);
                }
            }

            while (q != -1) {
                int sum = (num2.charAt(q) - '0') + carry;

                q--;
                if (sum >= 10) {
                    carry = 1;
                    sb.append(sum - 10);
                } else {
                    carry = 0;
                    sb.append(sum);
                }
            }

            if (carry == 1) {
                sb.append(1);
            }

            return sb.reverse().toString();


        }
    }

    // 其实一的三个for可以合并成一个, 时间复杂度是一样的, 只是看起来更简洁一些
    // 相当于缺少的位补成了0
    static class Second {
        public String addStrings(String num1, String num2) {
            StringBuilder sb = new StringBuilder();
            int carry = 0;
            for(int i = num1.length() - 1, j = num2.length() - 1; i >= 0 || j >= 0 || carry == 1; i--, j--){
                int x = i < 0 ? 0 : num1.charAt(i) - '0';
                int y = j < 0 ? 0 : num2.charAt(j) - '0';
                sb.append((x + y + carry) % 10);
                carry = (x + y + carry) / 10;
            }
            return sb.reverse().toString();
        }
    }

}

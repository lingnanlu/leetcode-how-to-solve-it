package question.string;


import run.elder.Category;
import run.elder.Leetcode;

@Leetcode(
        title = "Add Binary",
        link = "https://leetcode.com/problems/add-binary/",
        category = Category.STRING,
        how2SolveIt = """
                关于字符串和整数的题，注意一点，就是溢出问题
                
                这题不难，模拟操作就行, 如果不转化成整数，就可以避免溢出问题了
                """,
        relatedQuestions = {}
)
public class Add_Binary {

    public String addBinary(String a, String b) {

        int i = a.length() - 1;
        int j = b.length() - 1;

        StringBuilder result = new StringBuilder();
        int carry = 0;
        while(i >= 0 || j >= 0) {

            int sum = 0;

            if(i >= 0) {
                sum += a.charAt(i) - '0';
                i--;
            }
            if(j >= 0) {
                sum += b.charAt(j) - '0';
                j--;
            }

            sum += carry;
            carry = sum / 2;
            result.insert(0, (char) (sum % 2 + '0'));

        }

        if(carry == 1) {
            result.insert(0, '1');
        }
        return result.toString();
    }
}

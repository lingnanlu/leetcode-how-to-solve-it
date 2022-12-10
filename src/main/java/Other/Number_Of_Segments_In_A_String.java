package Other;

import run.elder.Category;
import run.elder.Leetcode;

@Leetcode(
        title = "Number of Segments in a String",
        link = "https://leetcode.com/problems/number-of-segments-in-a-string/",
        category = Category.UNKNOWN,
        how2SolveIt = """
                模型题, 要求一遍过
                
                这题有问题
                
                "     " 这种的结果是1, 我不理解
                """,
        relatedQuestions = {}
)
public class Number_Of_Segments_In_A_String {

    public int countSegments(String s) {
        int count = 0; // 已找到的
        int p = 0; // 指向上一个segment的结尾

        /**
         * 总的来说, 就是p每找到一个segment的结尾, count就加1
         */

        while (p != s.length()) {

            // 先寻找下一个segment的开头
            while (p != s.length() && s.charAt(p) == ' ') {
                p++;
            }

            // 此时要不指向末尾, 要不指向第一个非空格. 这里不重要. p都指向上一个seg的开头.
            // 试着让其不变式为真
            while(p != s.length() && s.charAt(p) != ' ') {
                p++;
            }

            // 此时p找向上一个segment的结尾
            count++;

        }

        return count;

    }
}

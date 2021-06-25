package question.string;


import run.elder.Category;
import run.elder.Leetcode;

@Leetcode(
        title = "Implement StrStr",
        link = "https://leetcode.com/problems/implement-strstr/",
        category = Category.STRING,
        how2SolveIt = """
                这里面试的话，使用暴力方法就行，也就是最直观的那个。注意思路一定要清晰。
                """,
        relatedQuestions = {}
)
public class StrStr {

    public int strStr(String haystack, String needle) {

        //先处理特殊情况
        if (haystack.length() < needle.length()) {
            return -1;
        }

        if (needle.length() == 0) {
            return 0;
        }

        for (int i = 0; i < haystack.length(); i++) {
            char c = haystack.charAt(i);
            if (c == needle.charAt(0)) {
                //开始匹配
                int j = i, k = 0;
                while (j != haystack.length() && k != needle.length()) {
                    if (haystack.charAt(j) == needle.charAt(k)) {
                        j++;
                        k++;
                    } else {
                        break;
                    }
                }

                if (k == needle.length()) {
                    //说明匹配完了
                    return i;
                } else if (j == haystack.length()) {
                    return -1;
                }


            }
        }

        return -1;
    }

}

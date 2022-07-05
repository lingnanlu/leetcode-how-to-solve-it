package String;

import run.elder.Category;
import run.elder.Leetcode;

@Leetcode(
        title = "Reverse String",
        link = "https://leetcode.com/problems/reverse-string/",
        category = Category.STRING,
        how2SolveIt = """
                基础题, 重点是举几个例子, 了解一下翻转具体指的是什么操作
                """,
        relatedQuestions = {}
)
public class Reverse_String {

    public void reverseString(char[] s) {
        int i = 0, j = s.length - 1;
        while (i < j) {
            char temp = s[i];
            s[i] = s[j];
            s[j] = temp;
            i++;
            j--;
        }
    }
}

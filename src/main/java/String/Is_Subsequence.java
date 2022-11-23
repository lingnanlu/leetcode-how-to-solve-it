package String;

import run.elder.Category;
import run.elder.Leetcode;

@Leetcode(
        title = "Is Subsequence",
        link = "https://leetcode.com/problems/is-subsequence/",
        category = Category.STRING,
        how2SolveIt = """
                 方法一:
                 
                 因为和顺序有关, 所以就不能排序了哈. 如何判断s在t里面呢? 
                 
                 最直观的就是模拟法, 依次判断s中的字符是不是出现在t中.
                          
                 复杂度就是O(length(t))
                   
                 """,
        relatedQuestions = {}
)
public class Is_Subsequence {

    static class First {

        public boolean isSubsequence(String s, String t) {

            // p用来在t中进行搜索, 指向将要搜索的下一个位置
            int p = 0;
            for (char c : s.toCharArray()) {

                while (p != t.length() && t.charAt(p) != c) {
                    p++;
                }

                /**
                 * 这里p == t.length()或 t.charAt(p) == c
                 */

                if (p == t.length()) {
                    return false;
                } else {
                    p++;
                }

            }

            return true;
        }

    }

}

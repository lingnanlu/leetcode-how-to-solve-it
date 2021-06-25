package question.string;


import run.elder.Category;
import run.elder.Leetcode;

@Leetcode(
        title = "Longest Common Prefix",
        link = "https://leetcode.com/problems/longest-common-prefix/",
        category = Category.STRING,
        how2SolveIt = """
                q 你能想出一种直观的方法么？
                
                a 直观一点就是，拿第一个字符串的字符去和其它的比，当比到没有的时候，就是结束了。
                
                q 时间复杂度是多少
                
                a len(first string) * n, n是数组的字符串的个数。
                
                q 试着写出来
                
                总结：
                
                这个方法叫纵向扫描，也是比较直观的，还有一种方法是横向扫描，不如这个直观。
                
                两者都是同样的时间复杂度
                
                """,
        relatedQuestions = {}
)
public class Longest_Common_Prefix {

    static class Simple {

        public String longestCommonPrefix(String[] strs) {
            StringBuilder commonPrefix = new StringBuilder();

            if (strs.length > 0){
                String firstString = strs[0];
                for (int i = 0; i < firstString.length(); i++) {
                    char c = firstString.charAt(i);

                    int j = 1;
                    for (; j < strs.length; j++) {
                        if(i < strs[j].length() && strs[j].charAt(i) == c) {
                            continue;
                        } else {
                            break;
                        }
                    }

                    // 说明c不是某一个字符串的前缀
                    if(j != strs.length) {
                        return commonPrefix.toString();
                    } else {
                        commonPrefix.append(c);
                    }
                }

                return commonPrefix.toString();
            }

            return "";
        }
    }



}

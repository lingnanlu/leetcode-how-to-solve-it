package Array;

import run.elder.Category;
import run.elder.Leetcode;

@Leetcode(
        title = "License Key Formatting",
        link = "https://leetcode.com/problems/license-key-formatting/",
        category = Category.ARRAY,
        how2SolveIt = """
                这题应该是一个简单的遍历题, 目标考察如何用代码清晰表达.
                
                大概算法描述如下
                
                从后往前遍历s
                每找到一个有效字符, 插入到新字符串中
                每插入k个, 就要添加一个'-'
                直到s中的遍历完
                
                另一个考察的就是几种极端情况.
                
                注: 这里可能有一个陷阱, 就是sb.insert, 它的时间复杂度是O(n), 可能导致时间比较长, 方法二改善了这一点
                """,
        relatedQuestions = {}
)
public class License_Key_Formatting {

    static class First {
        public String licenseKeyFormatting(String s, int k) {

            StringBuilder sb = new StringBuilder();

            int count = 0;
            for (int i = s.length() - 1; i >= 0 ; i--) {
                if(s.charAt(i) != '-') {
                    sb.insert(0, Character.toUpperCase(s.charAt(i)));
                    count++;
                    if (count == k) {
                        sb.insert(0, '-');
                        count = 0;
                    }
                }
            }

            if (sb.length() != 0 && sb.charAt(0) == '-') {
                sb.deleteCharAt(0);
            }
            return sb.toString();
        }
    }

    static class Second {

        public String licenseKeyFormatting(String s, int k) {

            StringBuilder sb = new StringBuilder();

            int count = 0;
            for (int i = s.length() - 1; i >= 0 ; i--) {
                if(s.charAt(i) != '-') {
                    sb.append(s.charAt(i));
                    count++;
                    if (count == k) {
                        sb.append('-');
                        count = 0;
                    }
                }
            }

            sb.reverse();

            if (sb.length() != 0 && sb.charAt(0) == '-') {
                sb.deleteCharAt(0);
            }

            return sb.toString().toUpperCase();
        }

    }

}

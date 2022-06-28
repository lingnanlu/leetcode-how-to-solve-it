package String;

import run.elder.Category;
import run.elder.Leetcode;

@Leetcode(
        title = "Valid Anagram",
        link = "https://leetcode.com/problems/valid-anagram/",
        category = Category.STRING,
        how2SolveIt = """
                方法一:
                
                排个序, 然后依次比较
                
                方法二:
                
                统计每个字符出现的次数.
                注意, 这里字符只有26个, 所以, 不一定要使用hash表, 可考虑使用数组的方式
                
                a = b => a - b = 0
                即不需要使用两个数组分别统计, 先加后减也可以
                
                启示
                1. 不一定要使用hash表, 有时如果数据集的元素种类不多且确定, 可以使用数组, 来节省空间
                2. 对于count型的, 可以考虑同时加减
                """,
        relatedQuestions = {}
)
public class Valid_Anagram {

    // 两遍遍历, 先统计s的, 然后再减去t的.
    static class First {
        public boolean isAnagram(String s, String t) {

            if (s.length() != t.length()) {
                return false;
            }

            int[] charCounts = new int[26];

            // 统计s中的字符出现的次数
            for (int i = 0; i < s.length(); i++) {
                charCounts[s.charAt(i) - 'a']++;
            }

            for (int i = 0; i < t.length(); i++) {
                charCounts[s.charAt(i) - 'a']--;
            }

            for (int i = 0; i < 26; i++) {
                if (charCounts[i] != 0) {
                    return false;
                }
            }

            return true;

        }
    }

    // 一遍遍历也行
    static class Second {

        public boolean isAnagram(String s, String t) {

            if (s.length() != t.length()) {
                return false;
            }

            int[] charCounts = new int[26];

            // 统计s中的字符出现的次数
            for (int i = 0; i < s.length(); i++) {
                charCounts[s.charAt(i) - 'a']++;
                charCounts[t.charAt(i) - 'a']--;
            }


            for (int i = 0; i < 26; i++) {
                if (charCounts[i] != 0) {
                    return false;
                }
            }

            return true;

        }

    }

}

package Array;

import run.elder.Category;
import run.elder.Leetcode;

@Leetcode(
        title = "Backspace String Compare",
        link = "https://leetcode.com/problems/backspace-string-compare/",
        category = Category.ARRAY,
        how2SolveIt = """
                方法一:
                
                最直观的是先分别得出两个字符串的最终形式, 然后再进行按照字符的对比.
                
                假设长度分别为m,n
                
                则时间复杂度为: O(m) + O(n) + O(min(m, n))
                
                而且要保存处理后的两个字符串, 所以有额外的空间.
                
                方法二:
                
                看方法一中, 有一个复制字符串的操作, 这让人想到数组的复制, 而数组复制可以原地复制, 所以试试原地复制
              
                这题其实出的不好. 引入了额外的复杂度, 该复杂度和解题的本质无关, 即String
                如果是
                
                public boolean backsapceCompare(char[] s, char[] t) 就好了
                
                方法三
                
                如果java无法原地修改string, 但又要求不能有额外的复杂度, 只能直接比较了. 这个比较trick
                """,
        relatedQuestions = {}
)
public class Backspace_String_Compare {

    static class First {

        public boolean backspaceCompare(String s, String t) {
            /**
             * 1. 处理s
             * 2. 处理t
             * 3. 比较新的s和t
             */
            String s2 = handle(s);
            String t2 = handle(t);

            int i = 0, j = 0;
            while (i < s2.length() && j < t2.length()) {
                if (s2.charAt(i) != t2.charAt(j)) {
                    return false;
                } else {
                    i++;
                    j++;
                }
            }

            // 有一方还有剩余
            if (i < s2.length() || j < t2.length()) {
                return false;
            } else {
                return true;
            }

        }

        private String handle(String s) {

            char[] s2 = new char[s.length()];

            // j指向新的字符数组中下一个空位.
            int j = 0;
            for (int i = 0; i < s.length(); i++) {
                if (s.charAt(i) != '#') {
                    s2[j] = s.charAt(i);
                    j++;
                } else {
                    if (j > 0) {
                        j--;
                    }

                }
            }

            // 由数组创建一个string.
            return new String(s2, 0, j);
        }



    }

    static class Second {

    }

    static class Third {

    }
}

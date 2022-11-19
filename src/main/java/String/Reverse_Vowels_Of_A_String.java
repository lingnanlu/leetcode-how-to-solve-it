package String;

import run.elder.Category;
import run.elder.Leetcode;

@Leetcode(
        title = "Reverse Vowels of a String",
        link = "https://leetcode.com/problems/reverse-vowels-of-a-string/",
        category = Category.STRING,
        how2SolveIt = """
                倒转元音, 这让我想起一个更简单的题目, 就是倒转字符串.
                借用之前的方法, 利用两个指针两两交换, 只是这里在交换之前要判断是否是元音, 是就交换, 不是就保持不动
                """,
        relatedQuestions = {}
)
public class Reverse_Vowels_Of_A_String {

    static class First {
        public String reverseVowels(String s) {
            char[] result = new char[s.length()];

            int i = 0, j = s.length() - 1;

            /**
             * 不变式, [i, j]之间还有元素可以处理, 当跳出循环时, i < j, j - i + 1 <= 0即没有元素要处理了
             */
            while (i <= j) {
                // 其实一共四种情况 00 - 01 - 10 - 11
                if (!isVowel(s.charAt(i)) && !isVowel(s.charAt(j))) {
                    result[i] = s.charAt(i);
                    i++;

                    result[j] = s.charAt(j);
                    j--;
                } else if (!isVowel(s.charAt(i)) && isVowel(s.charAt(j))) {
                    result[i] = s.charAt(i);
                    i++;
                } else if (isVowel(s.charAt(i)) && !isVowel(s.charAt(j))) {
                    result[j] = s.charAt(j);
                    j--;
                } else {
                    result[i] = s.charAt(j);
                    result[j] = s.charAt(i);
                    i++;
                    j--;
                }
            }

            return new String(result);
        }

        private boolean isVowel(char test) {
            char c = Character.toLowerCase(test);
            return c == 'a' || c == 'e' || c == 'i' || c == 'o' || c == 'u';
        }
    }
}

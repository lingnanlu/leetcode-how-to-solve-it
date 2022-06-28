package String;

import run.elder.Category;
import run.elder.Leetcode;

@Leetcode(
        title = "Ransom Note",
        link = "https://leetcode.com/problems/ransom-note/",
        category = Category.STRING,
        how2SolveIt = """
                先抽象成一个数学问题
                
                给定一个s和t, 问, s中的字符是不是t中的子集.
                
                其实就是s中每类字符出现的次数是不是<=t中每类字符出现的次数
                
                和Valid_Anagram非常相似
                
                启示, 方法二是在方法一解题过程中想到的, 所以, 无论一个题多简单, 多想想还是有收获的.
                """,
        relatedQuestions = {}
)
public class Ransom_Note {

    static class First {
        public boolean canConstruct(String ransomNote, String magazine) {

            if (ransomNote.length() > magazine.length()) {
                return false;
            }

            int[] charCounts = new int[26];

            // 统计magazine中的字符出现的次数
            for (int i = 0; i < magazine.length(); i++) {
                charCounts[magazine.charAt(i) - 'a']++;
            }

            // 消耗magazine中的字符来组成ransomNote
            for (int i = 0; i < ransomNote.length(); i++) {
                charCounts[ransomNote.charAt(i) - 'a']--;
            }

            //看还剩余不剩余字符
            for (int i = 0; i < 26; i++) {
                if (charCounts[i] < 0) {
                    return false;
                }
            }

            return true;


        }
    }

    // 在方法一种, 有一种消耗的思想, 想到, 如果在消耗过程中, 发现某一字符用完了, 就可以提前终止了, 不必再进行下去
    static class Second {

        public boolean canConstruct(String ransomNote, String magazine) {

            if (ransomNote.length() > magazine.length()) {
                return false;
            }

            int[] charCounts = new int[26];

            // 统计magazine中的字符出现的次数
            for (int i = 0; i < magazine.length(); i++) {
                charCounts[magazine.charAt(i) - 'a']++;
            }

            // 消耗magazine中的字符来组成ransomNote
            for (int i = 0; i < ransomNote.length(); i++) {
                // 将要消耗的字符
                char charToConsume = ransomNote.charAt(i);

                // 储备的个数已经用完了
                if (charCounts[charToConsume - 'a'] == 0) {
                    return false;
                } else {
                    // 还没用完, 用一个
                    charCounts[charToConsume - 'a']--;
                }
            }

            return true;

        }

    }

}

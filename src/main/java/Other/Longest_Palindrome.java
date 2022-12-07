package Other;

import run.elder.Category;
import run.elder.Leetcode;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

@Leetcode(
        title = "Longest Palindrome",
        link = "https://leetcode.com/problems/longest-palindrome/",
        category = Category.UNKNOWN,
        how2SolveIt = """
                
                方法一:
                
                
                这题猛一看, 没什么思路
                
                最简单的想法就是穷举所有可能, 然后找最长的.
                
                你想啊, 这个字符, 任意选择, 任意排列, 有多少种? 你怎么穷举? 是不是很复杂.
                
                这时, 肯定不能这样做了, 需要再仔细看下题目, 你看看求的是什么? 一个数字, 一个长度, 是一个回文的长度. 
                
                并没有要求你给出这个字符串本身.
                
                而原字符串的字符能不能出现在回文串里, 其实和它是偶数还是奇数个有关系. 
                
                所以, 这给我们的启示就是, 关注原字符串中某个字符的个数.
                
                如果原字符数量是偶数, 全用
                如果原字符数量是奇数, 减一再用
                
                如果有奇数字符, 再+1, 全是偶数就不加一了.
                
                方法二:
                
                其实遇到这种回文, 两两相同的, 可以考虑两两相消
                不记录每个字符的个数, 而是记录相消的次数
                
                这不得不说是一种思维的跳跃, 由
                
                回文 -> 两两相消 -> 相消的次数 -> 由相消的次数计算出最终结果.
                
                其实就里并没有直接使用直观的记录字符数, 而是求一个相关值, 由这个相关值计算出最终结果.
                
                这就是联想的能力 
                
                """,
        relatedQuestions = {}
)
public class Longest_Palindrome {

    static class First {
        public int longestPalindrome(String s) {
            Map<Character, Integer> charCount = new HashMap<>();

            for (char c : s.toCharArray()) {
                charCount.put(c, charCount.getOrDefault(c, 0) + 1);
            }

            boolean hasEven = false;
            int result = 0;
            for (Character c : charCount.keySet()) {
                int count = charCount.get(c);
                if (count % 2 == 0) {
                    result += count;
                } else {
                    result += count - 1;
                    hasEven = true;
                }
            }

            if (hasEven) result++;
            return result;
        }

    }

    static class Second {

        public int longestPalindrome(String s) {

            // 用于两两相消
            HashSet<Character> set = new HashSet<>();

            // 用于记录抵消的次数
            int count = 0;

            for (int i = 0; i < s.length(); i++) {
                if (set.contains(s.charAt(i))) {
                    count++; // 进行一次相抵消
                    set.remove(s.charAt(i));
                } else {
                    set.add(s.charAt(i));
                }
            }

            // 如果还有没有消除的, 说明有奇数
            if (!set.isEmpty()) return count * 2 + 1;
            else return count * 2;
        }


    }

}

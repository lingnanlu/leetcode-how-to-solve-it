package question.string;

import elder.Category;
import elder.Leetcode;
import elder.Solution;

@Leetcode(
        title = "Longest Palindromic Substring",
        category = Category.TODO,
        howToSolveIt = """
                
                (使用到了动态规划还有一个非常tricky的方法，等学了动规，再返回来做）
                
                q 问题是什么
                
                a 找到一个字符串中最长的回文子串
                
                q 你能分解一下问题么？分成几个部分列出来
                
                a
                
                给定一个字符串，找这样一个子串
                
                1. 满足回文
                2. 最长
                
               
                q 仔细看看问题，你有没有遇到过类似的。
                
                a 嗯，判断一个字符串是不是回文，这个之前解决过。
                
                q 好，那么，既然你能判断一个字符串是不是回文的，那么，你也能判断子串是不是回文的，是吧
                
                a 这个没问题
                
                我可以暴力穷举出所有的子串，然后判断其是不是回文的，当然，穷举的时候可以从长到短的顺序，遇到是回文的就结束，这样就可以了。
                
                q 你可以说出这种方法的时空复杂度么。
                
                a 其实就是穷举多少种子串。
                
                举个例子，比如是10个元素，就是 10 * 1 + 9 * 2 + 8 * 3 + ... 2 * 9 + 1 * 10
                
                方案一：暴力枚举
                
                方案二：由中间向两头扩展，这个也比较tricky啊！注意奇偶情况！
                
               
                
                """
)

public class Longest_Palindromic_Substring {

    @Solution("从中间到两边, 暴力方法")
    static class FromMidToSide {
        /**
         * 从头到尾，对于每个元素，把它当成中心（奇），或测试它和它后面的为中心（偶）
         */
        public String longestPalindrome(String s) {

            if(s == null) return null;
            if(s.length() == 0) return "";

            int length = s.length();
            int max = 0;            // 记录最长回文子串的长度

            // 这里其实是有点瑕疵的，因为，这里的值不能让这两个的含义断言为直，所以才在前面出现if(s.length() == 0) return "";
            int start = 0, end = 0; //保存最长回文子串的开头和结尾位置

            int left = 0, right = 0; // 用来定位回文串
            for (int i = 0; i < length; i++) {

                //奇
                left = i - 1;
                right = i + 1;

                while(left >= 0 && right <= length - 1 && s.charAt(left) == s.charAt(right)) {
                    left--;
                    right++;
                }

                // 此时，[left, right]之间的就是回文串
                if((right - left - 1) > max) {
                    max = right - left - 1;
                    start = left + 1;
                    end = right - 1;
                }

                // 偶，先判断其是否和后一位相同
                if((i + 1) < length && s.charAt(i + 1) == s.charAt(i)) {
                    left = i - 1;
                    right = i + 2;

                    while(left >= 0 && right <= length - 1 && s.charAt(left) == s.charAt(right)) {
                        left--;
                        right++;
                    }

                    // 此时，[left, right]之间的就是回文串
                    if((right - left - 1) > max) {
                        max = right - left - 1;
                        start = left + 1;
                        end = right - 1;
                    }
                }

            }

            return s.substring(start, end + 1);
        }
    }
}

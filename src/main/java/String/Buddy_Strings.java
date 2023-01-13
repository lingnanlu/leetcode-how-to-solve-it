package String;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 这题看起来不难
 * 俺看了几个输入, 感觉似乎要注意一些情况, 还是小心为妙.
 * 仔细审查自己的算法, 看是不是有不当的假设.
 * <p>
 * 方法一:
 * <p>
 * 既然题目说, 交换两个字符就能得到goal, 那么, 交换的是哪两个字符呢?
 * <p>
 * 初一感觉就是s中有两个字符的位置错位了, 它们的正确位置就是在goal中.
 * <p>
 * 那么, 一个最直观的想法, 就是找到s中错位的两个字母, 以goal为参照.
 * <p>
 * 所谓错位的字母就是位置不在其应该有的位置上, 就是在同样的位置, 字符不同. 所以我们使用两个指针同时遍历s, goal, 找到两个不同的字符.
 * <p>
 * 这一想法其实有一个假设, 错位的字符一定是不同的, 但会不会错位的也相同呢? 这样其实s和goal是一样的.
 * <p>
 * 那么, 此时能不能交换两个字符呢? 能, 就是在s中随便找两个相同的字符交换就行了.
 * <p>
 * 方法二:
 * <p>
 * 方法一的执行时间, 有点长, 有没有可以优化的地方?
 * <p>
 * 方法一中其实有两遍遍历, 能不能变成一遍遍历?
 * <p>
 * 方法三:
 * <p>
 * 方法二其实也没省多少时间, 它这个最后时间是不是对所有测试用例执行时间的平均?
 * 那么, 我现在的方法是通用的, 能不能对一些特殊的用例检测一下, 然后快速计算出结果?
 * 方法三的性能打败了50%, 我看和官方的解没区别啊, 为什么只是打败了50%?
 */
public class Buddy_Strings {

    static class First {
        public boolean buddyStrings(String s, String goal) {

            // 用于保存两个不同字符的下标
            List<Integer> wrongPos = new ArrayList<>();
            int p = 0;

            while (p != s.length() && p != goal.length()) {
                // p位置处的字符不相等, 记录下来
                if (s.charAt(p) != goal.charAt(p)) {
                    wrongPos.add(p);
                }
                p++;
            }

            // 此时就是找到的在min(s.length(), goal.length())长度下的不相等的字符数.
            if (p != s.length() || p != goal.length()) {
                //还有一个字符串没遍历完
                return false;
            } else {
                // 说明遍历完了
                if (wrongPos.size() == 2) {
                    return s.charAt(wrongPos.get(0)) == goal.charAt(wrongPos.get(1)) &&
                            s.charAt(wrongPos.get(1)) == goal.charAt(wrongPos.get(0));
                } else if (wrongPos.size() == 0) {
                    // 此时所有字符都相同. 看有没有出现两次的字符, 有就可以
                    int[] count = new int[26];
                    for (char c : s.toCharArray()) {
                        count[c - 'a']++;
                        if (count[c - 'a'] == 2) {
                            return true;
                        }
                    }
                    return false;
                } else {
                    // 此时无论如何, 也不能通过一次交换
                    return false;
                }
            }
        }


    }

    static class Second {

        public boolean buddyStrings(String s, String goal) {
            // 用于保存两个不同字符的下标
            List<Integer> wrongPos = new ArrayList<>();
            int p = 0;

            int[] count = new int[26];
            while (p != s.length() && p != goal.length()) {
                // p位置处的字符不相等, 记录下来
                if (s.charAt(p) != goal.charAt(p)) {
                    wrongPos.add(p);
                }
                count[s.charAt(p) - 'a']++;
                p++;
            }

            // 此时就是找到的在min(s.length(), goal.length())长度下的不相等的字符数.
            if (p != s.length() || p != goal.length()) {
                //还有一个字符串没遍历完
                return false;
            } else {
                // 说明遍历完了
                if (wrongPos.size() == 2) {
                    return s.charAt(wrongPos.get(0)) == goal.charAt(wrongPos.get(1)) &&
                            s.charAt(wrongPos.get(1)) == goal.charAt(wrongPos.get(0));
                } else if (wrongPos.size() == 0) {
                    // 此时所有字符都相同. 看有没有出现两次的字符, 有就可以
                    for (int n : count) {
                        if (n >= 2) {
                            return true;
                        }
                    }
                    return false;
                } else {
                    // 此时无论如何, 也不能通过一次交换
                    return false;
                }
            }

        }
    }

    static class Third {

        public boolean buddyStrings(String s, String goal) {

            // 对一些特殊用例, 简化执行.

            if (s.length() != goal.length()) return false;
            // 用于保存两个不同字符的下标
            int[] wrongPos = new int[2];
            int top = 0; // 指向wrongPos中的空位

            int p = 0;

            int[] count = new int[26];
            // 此时两者长度已经相等
            while (p != s.length()) {
                // p位置处的字符不相等, 记录下来
                if (s.charAt(p) != goal.charAt(p)) {
                    if (top == 2) {
                        return false; // 已经有两个不同的了
                    } else {
                        wrongPos[top++] = p;
                    }
                }
                count[s.charAt(p) - 'a']++;
                p++;
            }

            // 说明遍历完了
            if (top == 2) {
                return s.charAt(wrongPos[0]) == goal.charAt(wrongPos[1]) &&
                        s.charAt(wrongPos[1]) == goal.charAt(wrongPos[0]);
            } else if (top == 0) {
                // 此时所有字符都相同. 看有没有出现两次的字符, 有就可以
                for (int n : count) {
                    if (n >= 2) {
                        return true;
                    }
                }
                return false;
            } else {
                // 此时, 只有一个字符不同
                return false;
            }

        }
    }
}

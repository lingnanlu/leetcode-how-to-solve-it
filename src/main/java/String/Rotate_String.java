package String;

import java.util.ArrayList;
import java.util.List;

/**
 * 方法一:
 * 最直观的想法就是将模拟, 将s每次转一位, 然后比较两个字符串.
 * 显然, 这个方法看起来性能比较差.
 *
 * 方法二:
 * 对于这种一维线性结构, 我们总希望遍历一次就能得到答案, 能不能遍历一次呢?
 *
 * 当然, 如果没有思路, 想想在方法一上进行改进.
 *
 * 方法一的问题时, 每次转一位. 但如果我们能知道转了几位的话, 那不就是少了很多比较的次数了?
 *
 * 而可能转了几位呢? 其实就是找和一个字符相同的数在另一个字符串中的位置, 那几个位置就是可能转到的位置.
 *
 * 方法三:
 *
 * 基于以下一个结论.
 * 如果旋转s可以得到goal, 那么和 goal一定是s + s的字串是等价的..
 *
 * 那么a问题就可以完全转化成一个b问题
 * 而b问题容易解(使用KMP方法)
 *
 * 那么, 这里的关键就是, 这个b问题是怎么想出来的呢? 其实我感觉我想不出来, 暂时记住好了.
 *
 * 启示:
 * 方法三真不好想, 方法二其实不错
 */
public class Rotate_String {

    static class First {
        public boolean rotateString(String s, String goal) {

            if (s.length() != goal.length()) return false;

            // 可能转到的位置
            List<Integer> possiblePostions = new ArrayList<>();

            // 通过s的第一个字符, 判断可能转到的位置.

            char c = s.charAt(0);
            for (int i = 0; i < goal.length(); i++) {
                if (goal.charAt(i) == c) {
                    possiblePostions.add(i);
                }
            }

            // 此时possiblePostions中就是c可能转到的位置, 那么, 剩下的就是对每个位置进行测试了.
            for (int position : possiblePostions) {
                // 对于每一个位置, 将goal分成两段, 然后在s中看有没有这两段.
                String firstPart = goal.substring(0, position);
                String secondPart = goal.substring(position, goal.length());

                if (s.startsWith(secondPart) && s.substring(secondPart.length()).equals(firstPart)) {
                    return true;
                }
            }

            // 此时所有的位置都不匹配
            return false;

        }
    }

    static class Second {

        public boolean rotateString(String s, String goal) {

           if (s.length() != goal.length()) return false;

           return (s + s).contains(goal);

        }

    }


}

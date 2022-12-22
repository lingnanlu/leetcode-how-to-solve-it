package String;

import java.util.Stack;

/**
 * 方法一:
 * 因为是要分割出尽可能多的.
 * 所以直观的一种想法其实是, 从左向右遍历, 当刚好满足条件时, 进行分割, 这样的方法分割出来的就是最多的.
 *
 * 但有个限制, 就是每个分割出来的必须是即有L又有R
 * 因为S并身是平衡的, 如果分割出来RR, LL, 即么必定剩余的就不平衡,而要求所有分割出来的都是平衡的.
 * 所以每个分割出来的, 都是有R, L的最短的.
 *
 * 方法一主要是练习自己的不变式能力.
 *
 * 方法二:
 *
 * 两两相等其实可以想到覆灭, 使用一个栈来代替计数.
 * 这个方法其实不如方法一使用不变式来得有保障, 这里没有不变式, 所以还是有点虚
 */
public class Split_A_String_In_Balanced_Strings {

    static class First {
        public int balancedStringSplit(String s) {

            int count = 0;
            int i = 0, j = 0; // [i, j)之间的就是满足条件的最短的.
            while (i != s.length()) {
                /**
                 * 想办法移动j, 让[i, j)之间的R与L个数相同.
                 */

                // 初始化j, r, l
                // r 表示[i, j)之间的R的数量
                // l 表示[i, j)之间的L的数量
                // 当 r == l 时, 表示[i, j)就是一个满足条件的.
                j = i + 1;
                int r = 0, l = 0;
                if (s.charAt(i) == 'R') {
                    r++;
                } else {
                    l++;
                }

                // 以下让不变式为真
                while (r != l) {
                    if (j != s.length()) {
                        if (s.charAt(j) == 'R') {
                            r++;
                        } else {
                            l++;
                        }
                        j++;
                    } else {
                        // 这里其实走不到的.
                        break;
                    }
                }

                // 按照题意, r肯定与l相等, [i, j)
                count++;
                i = j;
            }
            return count;
        }


    }

    static class Second {

        public int balancedStringSplit(String s) {

            int count = 0;

            Stack<Character> stack = new Stack<>();

            for (char c : s.toCharArray()) {
                if (!stack.isEmpty()) {
                    if((stack.peek() == 'R' && c == 'L') || (stack.peek() == 'L' && c == 'R')) {
                        stack.pop(); // R和L抵消.
                        if (stack.isEmpty()) {
                            // 栈为空, 说明之前的正好组队了.
                            count++;
                        }
                    } else {
                        stack.push(c);
                    }
                } else {
                    stack.push(c);
                }
            }

            return count;
        }



    }

}

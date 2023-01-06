package String;

/**
 * 就是找s中的每个字符与c的最近距离, 看起来不难
 *
 * 先试着人工找找, 看能不能找到规律, 比如
 *
 * s = "loveleetcode", c = "e"
 *
 * 对于每一个字母, 它离哪个e更近呢? 其实就是以它为中心, 向左右开始找, 第一个找到的就是.
 * 这是一种最朴素的想法.
 *
 * 可以做为方法一
 *
 * 方法一的性能不好, 因为对于每一个字符, 总要向左和向右找最近的. 而这要再把数组遍历一遍, 这好像损失性能.
 *
 * 能不能在这个地方优化一下?
 *
 * 优化就是看方法一中有没有重复计算的地方, 我感觉找e的位置似乎重复计算了, 其实可以先把e的位置找出来
 * 对于以上例子, 可以得到e的位置
 *
 * [3, 5, 6, 11]
 *
 * 那么, 有了以上信息, 对于任意字符怎么找到最近的呢. 这个举例子, 比如l的位置为0
 *
 * 因为是0, 左边没有, 所以只能往右边找, 就是在[3, 5, 6, 11]中找第一个比0大的.
 *
 * 再来看c, 它的位置为8
 *
 * 它的左边和右边都有, 其实就是要找其在[3, 5, 6, 11]中的插入位置, 而这个位置又可以使用2分法找. 找到之后, 再比较插入位置前面的数
 * 和插入位置的数与它的距离.
 *
 * 其实, 以上就是把方法一的O(n)利用二分法简化了.
 *
 * 方法三:
 *
 * 我们其实可以换一个方法. 题目是找左右离的最近的, 这是两个方向. 我们其实习惯一个方向, 比如说找左边最近的.
 *
 * s = "loveleetcode", c = "e"
 *
 * 我们人工试着找左边最近的, 其实这个可以通过一遍遍历就能得到. 那么怎么知道一遍遍历就可以找到了呢?
 * 比如如果知道了t的左边最近的, 那么其实也就知道了c的, 所以由归纳法就可以得出这个结论.
 *
 * 同理, 我们可以得出右边最近的.
 *
 * 然后我们就可以比较左边, 右边最近的了.
 *
 * 最后, 我们得出以下两个数组
 *
 * [-1, -1, -1, 3, 3, 5, 6,  6, 6,  6,   6, 11]
 * [ 3,  3,  3, 3, 5, 5, 6, 11, 11, 11, 11, 11]
 *
 * 启示:
 *
 * 看到这种两个方向的, 先简化一下, 先解决一个方向的问题.
 */
public class Shortest_Distance_To_A_Character {

    static class First {
        public int[] shortestToChar(String s, char c) {

            int[] result = new int[s.length()];

            // 依次确定i位置上的离c最近的
            for (int i = 0; i < result.length; i++) {

                if (s.charAt(i) == c) {
                    result[i] = 0;
                } else {
                    // 向左向右找

                    // left用来寻找左边c出现的位置
                    int left = i - 1;

                    while (left >= 0 && s.charAt(left) != c) {
                        left--;
                    }

                    // 用来寻找右边c出现的位置
                    int right = i + 1;

                    while (right < s.length() && s.charAt(right) != c) {
                        right++;
                    }

                    if (left < 0) {
                        // 左边没有, 就是右边
                        result[i] = right - i;
                    } else if (right == s.length()) {
                        // 右边没有, 就是左边
                        result[i] = i - left;
                    } else {
                        result[i] = Math.min(right - i, i - left);
                    }

                }
            }

            return result;
        }
    }

    static class Third {

        public int[] shortestToChar(String s, char c) {

            int[] result = new int[s.length()];

            // leftNearest[i]记录的是i位置的字符, 左边(包括它本身)最近的c的位置
            int[] leftNearest = new int[s.length()];

            // 因为后一个最近要依赖前一个, 对于第一个要人工的初始
            if (s.charAt(0) == c) {
                leftNearest[0] = 0;
            } else {
                // -1表示左边最近的c在无穷远处
                leftNearest[0] = -1;
            }

            for (int i = 1; i < leftNearest.length; i++) {
                if (s.charAt(i) == c) {
                    leftNearest[i] = i;
                } else {
                    leftNearest[i] = leftNearest[i - 1];
                }
            }

            int[] rightNearest = new int[s.length()];

            if (s.charAt(s.length() - 1) == c) {
                rightNearest[s.length() - 1] = s.length() - 1;
            } else {
                rightNearest[s.length() - 1] = -1;
            }

            for (int i = rightNearest.length - 2; i >= 0; i--) {
                if (s.charAt(i) == c) {
                    rightNearest[i] = i;
                } else {
                    rightNearest[i] = rightNearest[i + 1];
                }
            }


            for (int i = 0; i < result.length; i++) {

                if (leftNearest[i] == -1) {
                    result[i] = rightNearest[i] - i;
                } else if (rightNearest[i] == -1) {
                    result[i] = i - leftNearest[i];
                } else {
                    result[i] = Math.min(i - leftNearest[i], rightNearest[i] - i);
                }
            }

            return result;
        }

    }


}

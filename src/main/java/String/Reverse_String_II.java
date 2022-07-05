package String;

import run.elder.Category;
import run.elder.Leetcode;

@Leetcode(
        title = "Reverse String II",
        link = "https://leetcode.com/problems/reverse-string-ii/",
        category = Category.STRING,
        how2SolveIt = """
                依然是一个基础题, 要求就是准确的将题目翻译成代码. 
                模拟题, 信心, 画画图, 举个例子
                """,
        relatedQuestions = {}
)
public class Reverse_String_II {
    public String reverseStr(String s, int k) {

        // 字符数组方便, 先转成数组, 然后原地reverse
        char[] toBeReverseStr = s.toCharArray();

        // 观察题目要求, reverse的区间是
        // [0, k), [2k, 3k), [4k - 5k).....
        // [i, j)表示要reverse的区间, 这里初始化为第一个区间
        int i = 0, j = Math.min(k, s.length());

        // j - i为区间内的元素个数, 当区间内元素个数多于1个时, 才需要reverse.
        while ((j - i) > 1) {
            // 1. 将[i, j)区间的进行reverse
            reverse(toBeReverseStr, i, j);
            // 2. 调整i, 使之指向下一个要reverse的区间的开头
            i = i + 2 * k;
            if (i >= s.length()) {
                // 下一个reverse区间不存在, 不需要再进行下去
                break;
            } else {
                j = Math.min(i + k, s.length());
            }

        }

        return new String(toBeReverseStr);

    }

    private void reverse(char[] str, int i, int j) {
        int p = i, q = j - 1;
        while (p < q) {
            char temp = str[p];
            str[p] = str[q];
            str[q] = temp;
            p++;
            q--;
        }
    }
}

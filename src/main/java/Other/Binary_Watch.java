package Other;

import run.elder.Category;
import run.elder.Leetcode;

import java.util.ArrayList;
import java.util.List;

@Leetcode(
        title = "Binary Watch",
        link = "https://leetcode.com/problems/binary-watch/",
        category = Category.UNKNOWN,
        how2SolveIt = """
                唉,原来是一个智力题,想不到,想不到啊!
                又是一个模拟题,看样子不算智力题.
                考察的就是你的表述能力,能不能把你的思路表达出来.
                
                另一个考察点是位运算
                
                如果你能人工做出来,写成代码应该也没问题.
                
                先看一下人工模拟
                
                假如两个灯亮,那么,其实就是一个组合,这个穷举出组合有点麻烦啊,一个简单题不应该.
                而且如果3个灯亮,就是找3个组合,更麻烦.
                
                所以看起来穷举组合不太可能了.
                
                我觉得,这个题吧,反正组合数就那么多,不如全部列举出来,查表怎么样?
                
                方法一: 查表
                
                方法二: 逆向思维
                
                不是先通过亮的灯来确定能组成的时间.
                而是穷举时间,看能不能用几个灯的组成
                
                以上思路是自己看答案得出来的. 这种思维方式自己也是第一次见.
                
                这题可以说是智力题了,有点trick
                """,
        relatedQuestions = {}
)
public class Binary_Watch {

    public List<String> readBinaryWatch(int turnedOn) {

        List<String> result = new ArrayList<>();
        // 将i转化成2进制看多少位
        // 将j转化成2进制看多少位
        // 两者相加和turedOn进行判断
        for (int i = 0; i <= 11; i++) {
            int m = bitCount(i);
            for (int j = 0; j <= 59; j++) {
                int n = bitCount(j);
                if ((m + n) == turnedOn) {
                    result.add((i + ":") + (j >= 10 ? j : ("0" + j)));
                }
            }
        }

        return result;
    }

    // 这里只统计正整数中1的个数
    private int bitCount(int n) {
        int result = 0;
        while (n != 0) {
            // 判断最后一位
            if ((n & 1) == 1) {
                result++;
            }
            n = n >> 1;
        }

        return result;
    }

    public static void main(String[] args) {
        Binary_Watch test = new Binary_Watch();

        test.readBinaryWatch(1);
    }
}

package Binary;

import run.elder.Category;
import run.elder.Leetcode;

@Leetcode(
        title = "Number Complement",
        link = "https://leetcode.com/problems/number-complement/",
        category = Category.UNKNOWN,
        how2SolveIt = """
                一看就是位运算, 想想, 也就是异或. 直接使用取反运算符不就行了?
                
                其实不行, 取反运算符会把前导0也求反, 这里求反的时候, 是忽略前导0的.
                
                比如 5来说, 假如是8位存储, 那么就是 00000101
                而我们只能使用 00000111来对其运算
                
                再比如
                
                00000001, 那么就只能使用 00000001来运算
                
                再举一个例子
                
                00010010 => 00011111
                
                所以可以看出, 这个用来运算的数字要原来的有效位的个数有关, 所以关键是找出有效位, 以及求出运算数字.
                
                """,
        relatedQuestions = {}
)
public class Number_Complement {

    public int findComplement(int num) {
        int copy = num;

        // 统计copy中已找到的有效位个数, 有多少个1就是有多少个有效位
        int mask = 0;

        while (copy != 0) {
            // 说明还有有效位, 统计数 + 1
            mask = (mask << 1) + 1;
            copy = copy >>> 1;      // 去除一个最低位
        }

        return num ^ mask;
    }
}

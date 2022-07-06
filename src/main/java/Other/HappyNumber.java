package Other;

import run.elder.Category;
import run.elder.Leetcode;

import java.util.HashSet;
import java.util.Set;

@Leetcode(
        title = "Happy Number",
        link = "https://leetcode.com/problems/happy-number/",
        category = Category.UNKNOWN,
        how2SolveIt = """
                这个题的关键就是, 如果不是happy number, 在计算过程中, 有的数会再次出现.
                另一个就是取一个整数的每个位的元素了.
                """,
        relatedQuestions = {}
)
public class HappyNumber {

    public boolean isHappy(int n) {

        Set<Integer> history = new HashSet<>();

        int next = n;
        while (true) {
            // 1. 计算next的各位平方和
            // 2. 如果平方和为1, 则是快乐数
            // 3. 如果不是1, 看是否出现过, 出现过就不是快乐数, 没出现过, 就继续计算
            int squareSum = sum(next);
            if (squareSum == 1) {
                return true;
            } else {
                if (history.contains(squareSum)) {
                    return false;
                } else {
                    history.add(squareSum);
                    next = squareSum;
                }
            }
        }
    }

    /**
     * 取n的各个位的平方和
     * todo 这里的循环不变式可以抽时间想想,还是比较重要的.
     */
    public int sum(int n) {
       int res = 0;
       // 这里不好写循环不变式
        // 把这里看成依次取n的最低位, 边界条件可以举个例子
        while (n > 0) {
            int i = n % 10;
            res += i * i;
            n /= 10;
        }

        return res;
    }

    public static void main(String[] args) {
        HappyNumber test  = new HappyNumber();
        boolean result = test.isHappy(19);
        System.out.println(result);
    }

}

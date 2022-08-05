package tanxin;

import run.elder.Category;
import run.elder.Leetcode;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

@Leetcode(
        title = "Monotone Increasing Digits",
        link = "https://leetcode.com/problems/monotone-increasing-digits/",
        category = Category.ARRAY,
        how2SolveIt = """
                方法一:
                
                最直观的做法, 对n依次减少, 然后判断一下是不是满足条件.
                
                但这个显然时间复杂度有些高
                
                方法二:
                
                方法一好像没什么可改进的, 我们只好重新看待这个问题, 举几个特殊例子, 比如
                
                
                8638, 这种最高的前两位不满足的.
                
                那么下一个最大的是多少呢?
                
                8, 6不满足, 要想满足, 不能提升6, 只能降低8, 所以, 8 -> 7
                
                即然8 -> 7之后, 形成的数都比原来小, 那么, 后面的数全部设置为9就好, 所以是 7999
                
                假如这种不满足在中间会怎么样? 比如
                
                5638
                
                要想让63满足, 不能把3-7, 而是忽略5, 当638看待, 这又是不满足在开头的情况, 所以变成599. 此时为5599
                
                那如果 6 -> 5导致前面也不满足的怎么办? 如 
                
                6638 -> 6599? 
                
                那么又符合了第一种情况 -> 5999
                
                再举一个例子
                
                66638 -> 66599 -> 65999 -> 59999
                
                可见要一直往前看. 
                
                这样基本确定思路了.
                
                启示:
                
                还是举例子, 找规律
                
                """,
        relatedQuestions = {}
)
public class Monotone_Increasing_Digits {

    static class First {
        public int monotoneIncreasingDigits(int n) {
            // 将n转换成int[]表示
            // 然后依次自减一
            // 判断是不是满足要求
            Digits digits = new Digits(n);

            while(!digits.isMonotone()) {
                boolean canDecrease = digits.decrease();

                // 为0了
                if (!canDecrease) {
                    return 0;
                }
            }

            return digits.toInt();

        }


        static class Digits {

            private final List<Integer> digit = new ArrayList<>(); // n 表数组化表示, 是倒序排列的

            public Digits(int n) {
                while (n != 0) {
                    digit.add(n % 10);
                    n = n / 10;
                }
            }

            public int toInt() {
                int result = 0;
                int i = 1;
                for (int n : digit) {
                    result += n * i;
                    i *= 10;
                }

                return result;
            }

            public boolean isMonotone() {
                for (int i = 0; i < digit.size() - 1; i++) {
                    if (digit.get(i) < digit.get(i + 1)) {
                        return false;
                    }
                }
                return true;
            }

            // 自减一, 最多到0 false 表示已经是0了, 不能再减了
            public boolean decrease() {

                // 如果该位置够减
                if (digit.get(0) > 0) {
                    digit.set(0, digit.get(0) - 1);
                } else {
                    // 该位置为0, 不够减, 向前一个非0位借一
                    int j = 1;
                    while (j != digit.size() && digit.get(j) == 0) {
                        j++;
                    }

                    if (j == digit.size()) {
                        // 说明整个数字为0了, 没有可借的位了. 直接返回, 什么也不做.
                        return false;
                    } else {
                        // j是可借的位
                        // 借一位
                        digit.set(j, digit.get(j) - 1);

                        for (int i = 0; i < j; i++) {
                            digit.set(i, 9);
                        }
                    }
                }

                return true;
            }



        }
    }

    static class Second {
        public int monotoneIncreasingDigits(int n) {



            Stack<Integer> stack = new Stack<>();

            int m = n;
            while (m != 0) {
                stack.push(m % 10);
                m = m / 10;
            }

            List<Integer> digit = new ArrayList<>();
            while (!stack.isEmpty()) {
                digit.add(stack.pop());
            }

            if (digit.size() <= 1) return n;

            // 注意这里假设至少有两个数字
            // i找到逆序的位置
            int i = 0;
            while(i != digit.size() - 1 && digit.get(i) <= digit.get(i + 1)) {
                i++;
            }

            // 找不到逆序, 本身满足要求
            if (i == digit.size() - 1) {
                return n;
            }

            // 本身 -1, 后面设置成9
            digit.set(i, digit.get(i) - 1);
            for (int j = i + 1; j < digit.size(); j++) {
                digit.set(j, 9);
            }

            // 从i往前看
            while (i != 0) {
                if (digit.get(i) < digit.get(i - 1)) {
                    digit.set(i, 9);
                    digit.set(i - 1, digit.get(i - 1) - 1);
                    i--;
                }  else {
                    // 找到顺序的就不用往前看了
                    break;
                }
            }


            // 再转化回数字
            int result = 0;
            int k = 1;
            for (int j = digit.size() - 1; j >= 0 ; j--) {
                result += digit.get(j) * k;
                k *= 10;
            }

            return result;


        }
    }

}

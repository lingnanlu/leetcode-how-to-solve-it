package Other;

import run.elder.Category;
import run.elder.Leetcode;

@Leetcode(
        title = "Convert a Number to Hexadecimal",
        link = "https://leetcode.com/problems/convert-a-number-to-hexadecimal/",
        category = Category.UNKNOWN,
        how2SolveIt = """
                方法一:
                
                将一个十进制转化成十六进制表示.
                
                算是一个考察计算机基础知识的题, 看你能不能用代码表述出来.
                
                一开始可以先不考虑负数, 先想明白正数的转换.
                
                正数的转换.
                
                其实就是不断mod 16, 然后找到结果对应的16进制数字.
                
                看来正数, 没问题了, 那么, 负数怎么办?
                
                这里有一个前提, 就是你要知道, 负数其实可以在之前加了负号就行, 但这里要求补码.
                
                所以, 需要了解原码到补码的转化.
                
                https://www.cnblogs.com/wxf0701/archive/2008/08/14/1267639.html
                
                但写着写着,太麻烦了
                
                方法二:
                
                其实还是对位操作不熟悉, 这方面接触不多.
                
                """,
        relatedQuestions = {}
)
public class Convert_A_Number_To_Hexadecimal {


    static class First {
        public String toHex(int num) {

            StringBuilder sb = new StringBuilder();
            if (num > 0) {
                /**
                 * 正数转换, mod 16取余就行.
                 */
                while (num != 0) {
                    int n = num % 16;
                    switch (n) {
                        case 10 -> sb.insert(0, 'a');
                        case 11 -> sb.insert(0, 'b');
                        case 12 -> sb.insert(0, 'c');
                        case 13 -> sb.insert(0, 'd');
                        case 14 -> sb.insert(0, 'e');
                        case 15 -> sb.insert(0, 'f');
                        default -> sb.insert(0, n);
                    }
                    num /= 16;
                }
            } else if (num == 0) {
                sb.append('0');
            } else {
                /**
                 * 负数转换
                 * 因为涉及到原码到补码, 而原码到补码是二进制形式.
                 * 所以先利用二进制形式. 然后再从二进制转化成十六进制.
                 * 看题要求是需要32位, 使用4个字节表示.
                 */
                byte[] bytes = new byte[4];
            }

            return null;
        }
    }

    static class Second {

        char[] dict = new char[]{'0','1','2','3','4','5','6','7','8','9','a','b','c','d','e','f'};
        public String toHex(int num) {
            /**
             * 整数在内存中,其实就是已经使用补码表示了, 所以不用考虑原码与补码问题.
             * 现在的关键问题是, 怎样把这种二进行补码表示成16进制字面形式?
             * 看题目要求是8个十六进制, 其实需要32个二进制.
             * 这里不必关心int在内存中是16位还是32位, 使用有符号的右移就行了.
             *
             * 如果确定int是32位, 就没必要使用两个分支了.
             * 整个算法基本就是, 4位4位的取, 最多取8次(负数), 就行了.
             */

            StringBuilder sb = new StringBuilder();
            int mask = 0b1111;  // 用于取最低4位

            if (num > 0) {
                while (num != 0) {
                    int low4 = num & mask;
                    sb.insert(0, dict[low4]);
                    num = num >> 4;
                }
            } else if (num == 0) {
                return "0";
            } else {
                // 负数就是取8次
                // count 剩余要取的次数
                int count = 8;

                while (count != 0) {
                    int low4 = num & mask;
                    sb.insert(0, dict[low4]);
                    count--;
                    num = num >> 4;
                }
            }

            return sb.toString();
        }

    }

}

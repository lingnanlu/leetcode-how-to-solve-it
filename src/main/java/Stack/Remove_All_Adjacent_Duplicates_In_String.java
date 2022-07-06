package Stack;

import run.elder.Category;
import run.elder.Leetcode;

@Leetcode(
        title = "Remove All Adjacent Duplicates In String",
        link = "https://leetcode.com/problems/remove-all-adjacent-duplicates-in-string/",
        category = Category.STACK,
        how2SolveIt = """
                心里模拟一遍, 就是使用栈来做
                但最后要输出的还是去除重复后的元素, 顺序和读入顺序一样
                所以, 使用一个数组是最方便的.
                """,
        relatedQuestions = {}
)
public class Remove_All_Adjacent_Duplicates_In_String {
    public String removeDuplicates(String s) {

        char[] result = new char[s.length()];

        // i指向栈顶的下一个元素.
        int i = 0;
        /**
         * 将s的字符依次读入
         * 如果和栈顶相同, 就消除
         * 如果和栈顶不同, 就读入
         */
        for (char c : s.toCharArray()) {
            if (i == 0) {
                result[i++] = c;
            } else {
                // 取栈顶元素
                char last = result[i - 1];

                if (last == c) {
                    // 相同就出栈
                    i--;
                } else {
                    result[i++] = c;
                }
            }
        }

        // 此时, 栈中的就是最后的结果
        return new String(result, 0, i);


    }
}

package List;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * 方法一:
 * 不难的题, 就是要从低到高转换, 顺序不同, 想到使用栈
 *
 * 方法二:
 * 方法一使用了栈, 使用栈的原因, 要从最低位开始, 能不能不使用栈, 从最高位开始呢?
 * 其实也行, 二进制我们不熟悉, 其实可以使用10进制的一个例子.
 * 注意, 它其实不如方法一来的直观.
 *
 * 比如说对于10进制 526
 * 先读一个5, 然后 5 * 10 + 2, 然后 (5 * 10 + 2) * 10 + 6
 *
 * 其实就是三步
 *
 * (0 * 10) + 5
 * (((0 * 10) + 5) * 10) + 2
 * ((((0 * 10) + 5) * 10) + 2) * 10 + 6
 *
 * 启示:
 *
 * 这个题方法二没思路的话, 就想一个类似的题目, 10进制的, 找找规律
 */
public class Convert_Binary_Number_In_A_Linked_List_To_Integer {

    static class First {
        public int getDecimalValue(ListNode head) {
            Deque<Integer> stack = new ArrayDeque<>();

            ListNode p = head;

            while (p != null) {
                stack.push(p.val);
                p = p.next;
            }

            int result = 0;

            // 下一个要处理的位的权
            // 栈顶就是下一个要处理的值
            int n = 1;
            while(!stack.isEmpty()) {
                int low = stack.pop();
                if (low == 1) {
                    result += n;
                }
                n = n << 1;
            }

            return result;
        }
    }

    static class Second {
        public int getDecimalValue(ListNode head) {

            ListNode p = head;
            int result = 0;

            while (p != null) {
                result = result * 10 + p.val;
                p = p.next;
            }

            return result;

        }
    }


}

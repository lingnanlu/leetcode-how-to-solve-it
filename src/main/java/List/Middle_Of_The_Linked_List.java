package List;

import run.elder.Category;
import run.elder.Leetcode;

@Leetcode(
        title = "Middle of the Linked List",
        link = "https://leetcode.cn/problems/middle-of-the-linked-list/",
        category = Category.LIST,
        how2SolveIt = """
                方法一: 
                最直观的是, 先进行一遍遍历, 统计个数, 再进行第二遍遍历, 关键点是不变式.
                
                方法二:
                方法一是两遍遍历, 能不能一遍遍历? 其实也是可以的, 就是在list上常用的走一步和走两步.
                但这个不太直观, 最好是先举例子试验一下
                
                如果是偶数个, 一个每次跳过2个结点, 一个每次跳过1个结点, 那么, 当快的到达结尾时, 慢的刚好跳过1/2, 慢的所指的正好就是要求的
                如果是奇数个呢? 一个每次跳过2个结点, 但在最后一次只能跳过1个结点, 此时, 慢的应该少跳一次
                
                总结一下
                偶数个情况下, 快慢跳的次数相同, 但结点数差2倍
                奇数情况下, 快慢跳的次数不同, 慢的少跳一次
                """,
        relatedQuestions = {}
)
public class Middle_Of_The_Linked_List {
    static class First {
        public ListNode middleNode(ListNode head) {

            /**
             * count 统计已遍历过的节点数
             * p 指向将要遍历的节点
             */
            int count = 0;
            ListNode p = head;

            while (p != null) {
                // 这里的遍历指的就是count + 1
                count++;
                p = p.next;
            }

            if (count == 0) {
                return null;
            }

            // 通过数学计算, 目标node的位置
            int goal = count / 2 + 1;

            // 想办法让p到达那个位置
            // pos 为当前p所指的结点位置
            int pos = 1;
            p = head;
            while (pos != goal) {
                p = p.next;
                pos++;
            }

            // 此时pos == goal, 有不变式可知, p所指的位置就是要求的位置
            return p;
        }
    }

    static class Second {

        public ListNode middleNode(ListNode head) {
            ListNode slow = head;
            ListNode fast = head;

            while (fast != null) {

                // 快结点最多一次跳两个.
                fast = fast.next;

                if (fast != null) {
                    fast = fast.next;

                    // 快的一次跳两个, 慢的一次跳一个
                    slow = slow.next;
                } else {
                    /**
                     * 偶数个的情况走不到这里来
                     * 只有奇数个才到这里, 此时, 慢的不再跳, 最终, 慢的少跳一次.
                     */
                }
            }

            return slow;
        }

    }
}

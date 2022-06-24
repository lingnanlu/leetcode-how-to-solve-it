package List;

import run.elder.Category;
import run.elder.Leetcode;

@Leetcode(
        title = "Intersection of Two Linked Lists",
        link = "https://leetcode.com/problems/intersection-of-two-linked-lists/",
        category = Category.LIST,
        how2SolveIt = """
                方法一:
                
                最直观的做法, 依次从headA中取出一个结点, 判断是不是在B中, 时间复杂度是 O(m * n)
                
                方法二:
                
                已知是什么
                1. 两个链表
                2. 两个链表的长度(隐含条件)
                
                那么, 我们能不能利用这个隐含条件呢?
                
                假设, 一个为m, 一个为n, 那么, 它们相交的部分结点个数, 一定是 <= min(m, n)的
                
                画个图, 容易看出, 长的那个先走几步, 然后同时走, 就能相遇了.
                
                
              
                """,
        relatedQuestions = {}
)
public class Intersection_Of_Two_Linked_Lists {

    static class First {
        public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
            return null;
        }
    }

    static class Second {
        public ListNode getIntersectionNode(ListNode headA, ListNode headB) {

            /**
             * lengthA表示[0, p)之间元素个数
             */
            int lengthA = 0;
            for (ListNode p = headA; p != null; p = p.next) {
                lengthA++;
            }

            int lengthB = 0;
            for (ListNode p = headB; p != null; p = p.next) {
                lengthB++;
            }

            // 长的先走 step 步
            int step = Math.abs(lengthA - lengthB);
            ListNode p = headA, q = headB;
            // count 已走的步数
            int count = 0;
            if (lengthA >= lengthB) {
                while (count != step) {
                    p = p.next;
                    count++;
                }
            } else {
                while (count != step) {
                    q = q.next;
                    count++;
                }
            }

            // 此时同步走
            while (p != null && p != q) {
                p = p.next;
                q = q.next;
            }

            return p;
        }
    }
}

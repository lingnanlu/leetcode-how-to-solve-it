package List;

import run.elder.Category;
import run.elder.Leetcode;

@Leetcode(
        title = "Swap Nodes in Pairs",
        link = "https://leetcode.com/problems/swap-nodes-in-pairs/",
        category = Category.LIST,
        how2SolveIt = """
                
                又是考察基本的操作, 直观的来看就是两两交换
                
                其实这题也是递归的方法, 不过, 对于链表来说, 好像直接处理指针更加直观
                """,
        relatedQuestions = {}
)
public class Swap_Nodes_In_Pairs {

    // 交换值, 但是题目限制不允许
    static class First {
        public ListNode swapPairs(ListNode head) {
            return null;
        }
    }

    // 改变指针
    static class Second {
        public ListNode swapPairs(ListNode head) {

            // 特殊输入要特殊处理
            if (head == null || head.next == null) {
                return head;
            }

            // p, q是要交换的元素, tail是到目前为止, 已处理完的list的末尾
            ListNode p = head;
            ListNode q = p.next;
            ListNode tail = new ListNode();

            ListNode dummy = tail;

            // 注意循环不变式要为直
            while (p != null && q != null) {
                ListNode tmp = q.next;
                q.next = p;
                p.next = null;
                tail.next = q;
                tail = p;

                p = tmp;
                if (p != null) {
                    q = p.next;
                }
            }

            // 注意以上循环是两两处理, 如果最后剩余一个p
            if (p != null) {
                tail.next = p;
            }

            return dummy.next;
        }
    }

}

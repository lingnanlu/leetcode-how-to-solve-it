package question.list;

import run.elder.Category;
import run.elder.Leetcode;

@Leetcode(
        title = "Reorder List",
        link = "https://leetcode.com/problems/reorder-list/",
        category = Category.LIST,
        how2SolveIt = """
                又是一下细节模拟题，按照直观的分三步做就行
                
                其实就是三个小问题合成了一个大问题而已。
                
                注意这里的一个技巧，使用两步走的方式找中间结点。
                """,
        relatedQuestions = {}
)
public class Reorder_List {

    public void reorderList(ListNode head) {

        if(head == null || head.next == null || head.next.next == null) return;

        // 1. 找到中间结点
        // 这里不好证明，需要你举奇数和偶数个的例子来说明一下正确性
        // dummy结点是为了简化操作，举例可以知道，p1.next就是后半部分的开始
        // 如果不用dummy，可能要麻烦一些，所以对于list的题，可以试试使用dummy来简化一些操作。
        // 这里后半部分的结点个数总是<= 前半部分
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode p1 = dummy;
        ListNode p2 = dummy;
        while(p2 != null && p2.next != null) {
            p1 = p1.next;
            p2 = p2.next.next;
        }

        // 断开后半部分，p所指的就是后半部分
        ListNode p = p1.next;
        p1.next = null;

        // reverse后半部分
        dummy.next = null;
        ListNode i = p;
        while(i != null) {
            ListNode temp = i;
            i = i.next;
            temp.next = null;

            temp.next = dummy.next;
            dummy.next = temp;
        }

        // 此时p所指的就是reverse之后的。
        p = dummy.next;

        i = p;
        ListNode q = head;
        while(i != null) {
           ListNode temp = i;
           i = i.next;
           temp.next = null;

           temp.next = q.next;
           q.next = temp;
           q = q.next.next;
        }

    }
}

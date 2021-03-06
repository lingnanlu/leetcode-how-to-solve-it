package question.list;

import run.elder.Category;
import run.elder.Leetcode;

@Leetcode(
        title = "Partition List",
        link = "https://leetcode.com/problems/partition-list/",
        category = Category.LIST,
        how2SolveIt = """
                思路比较直观，使用两个新的链表，一个保存小的，一个保存大的，然后拼接起来
                """,
        relatedQuestions = {}
)
public class Partition_List {
    public ListNode partition(ListNode head, int x) {

        ListNode less = new ListNode(-1);
        ListNode lessTail = less;
        ListNode more = new ListNode(-1);
        ListNode moreTail = more;

        ListNode p = head;

        while(p != null) {

            // 先摘下来一个
            ListNode node = p;
            p = p.next;
            node.next = null;

            if(node.val < x) {
                lessTail.next = node;
                lessTail = node;
            } else {
                moreTail.next = node;
                moreTail = node;
            }
        }

        // 拼接两个list
        lessTail.next = more.next;
        return less.next;
    }
}

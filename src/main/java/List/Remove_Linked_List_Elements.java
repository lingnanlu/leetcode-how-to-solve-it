package List;

import run.elder.Category;
import run.elder.Leetcode;

@Leetcode(
        title = "Remove Linked List Elements",
        link = "https://leetcode.com/problems/remove-linked-list-elements/",
        category = Category.LIST,
        how2SolveIt = """
                链表的基本操作, 使用两个指针遍历
                """,
        relatedQuestions = {}
)
public class Remove_Linked_List_Elements {

    public ListNode removeElements(ListNode head, int val) {

        ListNode dummy = new ListNode(0, head);

        // p是q的前一个元素
        // q用来遍历整个链表
        // 另外要注意的是, 在整个遍历过程中, dummy的含义是什么
        // 画个图, 确定在p, q变化过程中, dummy始终指向的是链表头部
        ListNode p = dummy, q = dummy.next;
        while (q != null) {
            if (q.val == val) {
                p.next = q.next;
                q = p.next;
            } else {
                p = q;
                q = q.next;
            }
        }

        return dummy.next;

    }
}

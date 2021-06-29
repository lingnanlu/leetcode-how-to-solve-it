package question.list;

import run.elder.Category;
import run.elder.Leetcode;

@Leetcode(
        title = "Rotate List",
        link = "https://leetcode.com/problems/rotate-list/",
        category = Category.LIST,
        how2SolveIt = """
                又是一个模拟题，比较简单，意义不大.
                
                先遍历一遍，得出链表长度 len ，注意 k 可能大于 len ，因此令 k %= len 。将尾节点next指针指向首 节点，形成一个环，
                接着往后跑 len-k 步，从这里断开，就是要求的结果了。
                """,
        relatedQuestions = {}
)
public class Rotate_List {

    public ListNode rotateRight(ListNode head, int k) { if (head == null || k == 0) return head;

        int len = 1;

        ListNode p = head; while (p.next != null) { // 求长度

            len++;

            p = p.next; }

        k = len - k % len;

        p.next = head; // 首尾相连

        for(int step = 0; step < k; step++) {

            p = p.next; //接着往后跑

        }
        head = p.next; // 新的首节点
        p.next = null; // 断开环
        return head;

    }
}

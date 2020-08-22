package list;

import elder.Category;
import elder.Leetcode;

@Leetcode(
        title = "Remove Duplicates from Sorted List II",
        category = Category.LIST,
        howToSolveIt = """
                
                这题依然不难，因为你能在纸上很自然的写写画画就写出来了。
                
                关键是清晰的把你的过程用程序来表达出来。这就需要清楚的明白，每一个变量是什么含义。
                
                注意不变式的作用，或者说断言的作用，你的代码就是要让断言为真。
                
                再结合你的模拟的思路，就能一遍写对了。
                
                
                """
)
public class Remove_Duplicates_From_Sorted_List_2 {

    public ListNode deleteDuplicates(ListNode head) {

        if (head == null || head.next == null) return head;

        ListNode dummy = new ListNode(0);         // 结果list的尾结点
        ListNode tail = dummy;
        ListNode p = head;
        ListNode q = head.next;

        // 不变式，一直保持q是p的下一个元素，如果q和p不等，就说明p是单一元素。
        while(p != null) {
            // 此时，由不变式可知、q是p的下一个元素。
            if(q == null || p.val != q.val) {
                //说明p所指元素不重复
                tail.next = p;
                tail = p;
                p = q;
                if(q != null) {
                    q = q.next;
                }
            } else {
                // 此时，p，q相同，需要跳过p,q所指元素，想办法回归到不变式
                while(q != null && p.val == q.val) {
                    q = q.next;
                }

                // 此时，跳过p，q所指的那段重复元素，又回到了不变式
                p = q;
                if(q != null) {
                    q = q.next;
                }
            }
        }

        tail.next = null;
        return dummy.next;
    }
}

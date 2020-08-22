package list;

import elder.Category;
import elder.Leetcode;

@Leetcode(
        title = "Remove Duplicates From Sorted List",
        category = Category.LIST,
        howToSolveIt = """
                这个题很简单，其实先手动模拟一遍就行。
                
                然后，关键是能清楚的说明每个变量的含义
                """
)
public class Remove_Duplicates_From_Sorted_List {

    public ListNode deleteDuplicates(ListNode head) {

        if (head == null) return null;

        ListNode p = head;          // 去重后的list的尾部
        ListNode q = head.next;     // q的下一个

        // 整个过程就是在不断在在p后面，找第一个与它不同的，然后接在它后面。直到遍历完整个list.

        while(q != null) {
            if(q.val != p.val) {
                p.next = q;
                p = q;
                q = q.next;
            } else {
                q = q.next;
            }
        }

        p.next = q;

        return head;

    }
}


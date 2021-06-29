package question.list;


import run.elder.Category;
import run.elder.Leetcode;

@Leetcode(
        title = "Linked List Cycle II",
        link = "https://leetcode.com/problems/linked-list-cycle-ii/",
        category = Category.LIST,
        how2SolveIt = """
                
                见https://www.cnblogs.com/hiddenfox/p/3408931.html中的解释。
                
                """,
        relatedQuestions = {}
)
public class Linked_List_Cycle_2 {

    public ListNode detectCycle(ListNode head) {

        ListNode slow = head;
        ListNode fast = head;

        while(slow != null & fast != null && fast.next != null) {

            slow = slow.next;
            fast = fast.next.next;

            if(fast == slow) {
                ListNode slow2 = head;
                //让两个慢指针同时走
                while(slow2 != slow) {
                    slow = slow.next;
                    slow2 = slow2.next;
                }

                return slow;
            }
        }

        return null;
    }

}

package question.list;

import elder.Category;
import elder.Leetcode;

@Leetcode(
        title = "Linked List Cycle II",
        category = Category.LIST,
        howToSolveIt = """
                
                见https://www.cnblogs.com/hiddenfox/p/3408931.html中的解释。
                
                
               
                """
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

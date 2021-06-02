package question.list;

import elder.Category;
import elder.Leetcode;

import java.util.Stack;

@Leetcode(
        title = "Palindrome Linked List",
        category = Category.LIST,
        howToSolveIt = """
                其实这个题也不需要什么技巧，分以下几步走
                
                1. 找到后半部分
                2. 将后半部分的开头和前半部分的尾部开始对比，这里需要一个reverse，或者stack.
                
                """
)
public class Palindrome_Linked_List {

    public boolean isPalindrome(ListNode head) {

        if (head == null || head.next == null) return true;

        ListNode dummy = new ListNode(0);
        dummy.next = head;

        ListNode p1 = dummy, p2 = dummy;

        while(p2 != null && p2.next != null) {
            p1 = p1.next;
            p2 = p2.next.next;
        }


        ListNode p = p1.next;
        Stack<ListNode> stack = new Stack<>();

        while (p != null) {
            stack.push(p);
            p = p.next;
        }

        ListNode q = head;
        while(!stack.empty()) {
            ListNode a = stack.pop();
            ListNode b = q;

            if(a.val != b.val) {
                return false;
            } else {
                q = q.next;
            }
        }

        return true;

    }
}

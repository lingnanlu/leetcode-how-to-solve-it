package question.list;

import elder.Category;
import elder.Leetcode;
import elder.Solution;

import java.util.Stack;

@Leetcode(
        title = "Add Two Numbers II",
        category = Category.LIST,
        howToSolveIt = """
                这个其实和add two numbers 类似 ，只是位序倒了过来。可以先reverse，然后使用add two numbers的方法，最后再将结果reverse
                
                但这里加了一个条件，不能reverse list。
                
                不能reverse list，但想了想，依旧要使用相反的顺序来计算才行！那么，使用相反的顺序，你能想到什么呢？
                
                stack.
                
                对，使用stack,把顺序调转过来，再进行计算
                
                
                """
)
public class Add_Two_Numbers_2 {

    @Solution("使用stack")
    static class UsingStack {

        public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
            Stack<Integer> s1 = new Stack<>();
            Stack<Integer> s2 = new Stack<>();

            while (l1 != null) {
                s1.push(l1.val);
                l1 = l1.next;
            }

            while (l2 != null) {
                s2.push(l2.val);
                l2 = l2.next;
            }

            int carry = 0;
            ListNode dummy = new ListNode(0);
            int sum = 0;
            while(!s1.empty() || !s2.empty()) {

                if(!s1.empty()) sum += s1.pop();
                if(!s2.empty()) sum += s2.pop();

                sum += carry;

                ListNode node = new ListNode(sum % 10);
                node.next = dummy.next;
                dummy.next = node;

                carry = sum / 10;
                sum = 0;
            }

            if(carry == 1) {
               ListNode node = new ListNode(1);
               node.next = dummy.next;
               dummy.next = node;
            }

            return dummy.next;
        }

    }
}

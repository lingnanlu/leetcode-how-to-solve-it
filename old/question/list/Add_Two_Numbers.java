package question.list;


import run.elder.Category;
import run.elder.Leetcode;

@Leetcode(
        title = "Add Two Numbers",
        link = "https://leetcode.com/problems/add-two-numbers/",
        category = Category.LIST,
        how2SolveIt = """
                一道模拟操作题，没什么意思，细心，关注一下边界条件就行。
                """,
        relatedQuestions = {}
)
public class Add_Two_Numbers {

    // 将l2加到l1, 和将l1加到l2一样, 无区别
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {

        ListNode result = new ListNode(-1);
        ListNode tail = result;
        ListNode p1 = l1;
        ListNode p2 = l2;
        int carry = 0;

        while(p1 != null && p2 != null) {

            int sum = p1.val + p2.val + carry;
            ListNode node = new ListNode(0);
            node.val = sum % 10;
            carry = sum / 10;

            tail.next = node;
            tail = node;

            p1 = p1.next;
            p2 = p2.next;

        }

        while(p1 != null) {

            int sum = p1.val + carry;
            ListNode node = new ListNode(0);
            node.val = sum % 10;
            carry = sum / 10;

            tail.next = node;
            tail = node;

            p1 = p1.next;
        }

        while(p2 != null) {

            int sum = p1.val + carry;
            ListNode node = new ListNode(0);
            node.val = sum % 10;
            carry = sum / 10;

            tail.next = node;
            tail = node;

            p2 = p2.next;
        }

        if (carry == 1) {
            tail.next = new ListNode(1);
        }

        if(result.next != null) {
            return result.next;
        } else {
            return null;
        }
    }
}

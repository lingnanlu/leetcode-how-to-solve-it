package common;

import list.ListNode;

public class ListTool {

    public static ListNode build(int... vals) {
        ListNode result = new ListNode(-1);
        ListNode tail = result;
        for(int n : vals) {
            ListNode node = new ListNode(n);
            tail.next = node;
            tail = node;
        }

        return result.next;
    }

    public static String print(ListNode head) {
        StringBuilder sb = new StringBuilder();
        if(head == null) {
            return "";
        } else {
            sb.append("[");
            ListNode p = head;
            while(p != null) {
                sb.append(p.val).append(",");
                p = p.next;
            }

            sb.deleteCharAt(sb.length() - 1);
            sb.append("]");
            return sb.toString();
        }
    }
}

package list;

public class Partition_List {
    public ListNode partition(ListNode head, int x) {

        ListNode less = new ListNode(-1);
        ListNode lessTail = less;
        ListNode more = new ListNode(-1);
        ListNode moreTail = more;

        ListNode p = head;

        while(p != null) {

            // 先摘下来一个
            ListNode node = p;
            p = p.next;
            node.next = null;

            if(node.val < x) {
                lessTail.next = node;
                lessTail = node;
            } else {
                moreTail.next = node;
                moreTail = node;
            }
        }

        // 拼接两个list
        lessTail.next = more.next;
        return less.next;
    }
}

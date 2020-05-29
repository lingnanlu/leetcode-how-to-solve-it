package list;


public class Remove_Duplicates_From_Sorted_List {

    //原List不变
    //todo 如果是修改原列表呢?
    public ListNode deleteDuplicates(ListNode head) {

        if (head == null) return null;

        ListNode resultHead = new ListNode(head.val);


        ListNode last = resultHead;
        ListNode p = head.next;

        while (p != null) {
            if(p.val != last.val) {
                last.next = new ListNode(p.val);
                last = last.next;
                p = p.next;
            } else {
                p = p.next;
            }
        }

        return resultHead;

    }
}


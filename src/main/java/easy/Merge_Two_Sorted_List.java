package easy;

public class Merge_Two_Sorted_List {


    public static void main(String[] args) {

        ListNode l1 = new ListNode(1);
        l1.next = new ListNode(2);
        l1.next.next = new ListNode(4);

        ListNode l2 = new ListNode(1);
        l2.next = new ListNode(3);
        l2.next.next = new ListNode(4);


        Merge_Two_Sorted_List a = new Merge_Two_Sorted_List();

        a.mergeTwoLists(l1, l2);

    }
    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {



        ListNode dummy = new ListNode(0);
        ListNode cur = dummy;       //指向结果的最尾

        while (l1 != null && l2 != null) {
            if (l1.val <= l2.val) {
                cur.next = l1;
                l1 = l1.next;
            } else {
                cur.next = l2;
                l2 = l2.next;
            }
            cur = cur.next;
        }

        if (l1 != null) {
            cur.next = l1;
        }

        if (l2 != null) {
            cur.next = l2;
        }


        return dummy.next;
    }

}


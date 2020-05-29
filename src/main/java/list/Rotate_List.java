package list;

public class Rotate_List {

    // 其实就是将原来的list分成两个部分， 然后重新链接起来。
    public ListNode rotateRight(ListNode head, int k) {

        if(head == null) return null;

        int length = 0;
        // length表示 的是[0, p)区间的list的长度
        for(ListNode p = head; p != null; p = p.next) {
            length++;
        }

        int moveStep = k % length;

        if(moveStep == 0) return head;

        // p指向的是第i个结点
        ListNode p = head;
        int i = 1;

        while(i != length - moveStep) {
            i++;
            p = p.next;
        }

        //此时p指向的是第length - moveStep个结点, 将链表分成两部分
        ListNode newhead = p.next;
        p.next = null;

        p = newhead;
        while(p.next != null) {
            p = p.next;
        }

        p.next = head;

        return newhead;


    }
}

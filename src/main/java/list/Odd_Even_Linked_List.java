package list;

public class Odd_Even_Linked_List {

    // 其实就是把一个list拆分成两个list, 然后再联起来
    public ListNode oddEvenList(ListNode head) {

        if(head == null || head.next == null) return head;

        ListNode oddHead = head, oddTail = oddHead;
        ListNode evenHead = head.next, evenTail = evenHead;
        ListNode pNode = evenTail.next;

        // 注意断开, 这样可以使得算法更清晰
        oddTail.next = null;
        evenTail.next = null;

        boolean odd = true;

        ListNode node;
        while(pNode != null) {
            node = pNode;
            pNode = pNode.next;
            node.next = null;
            if(odd) {
                oddTail.next = node;
                oddTail = node;
                odd = false;
            } else {
                evenTail.next = node;
                evenTail = node;
                odd = true;
            }
        }

        oddTail.next = evenHead;
        return head;
    }

}

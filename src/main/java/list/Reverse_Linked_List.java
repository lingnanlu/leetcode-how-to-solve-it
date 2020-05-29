package list;

public class Reverse_Linked_List {

    // 1. 新建一个链表, 不断的从原链表拿一个结点过来, 往新链表表头插
    // 传入的head最后变成了新链表的最后一个元素.
    public ListNode reverseList(ListNode head) {

        ListNode newP = null;

        //取第一个结点
        ListNode p = head;
        if(head != null) head = head.next;

        //p表示所取的结点
        while(p != null) {

            //查入到新链表中.
            p.next = newP;
            newP = p;

            //再取一个
            p = head;
            if(head != null) head = head.next;
        }

        return newP;
    }

    //
    //

    /**
     * 递归的方式
     * 其实理解递归的关键是, 返回的是reverse的, 但原来做为头部的结点现在变成了尾结点
     */
    public ListNode reverseList2(ListNode head) {

        if (head == null || head.next == null) return head;
        ListNode reversed = reverseList2(head.next);
        head.next.next = head;
        head.next = null;
        return reversed;

    }



    //从list中取走头结点, 如果头部有一个空结点, 还好说, 但没有空结点, 怎么办?
    // javd sdk中的LinkedList不单单是一个Node对象, 外面还有一层LinkedList
//    private static void takeNode(ListNode head) {
//        head = head.next;
//    }

//    public static void main(String[] args) {
//
//        ListNode a = new ListNode(1);
//        ListNode b = new ListNode(2);
//        a.next = b;
//        takeNode(a);
//
//        System.out.println(a);
//    }
}

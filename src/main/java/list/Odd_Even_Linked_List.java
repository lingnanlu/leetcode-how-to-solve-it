package list;

import elder.Category;
import elder.Leetcode;

@Leetcode(
        title = "Odd Even Linked List",
        category = Category.LIST,
        howToSolveIt = """
                很自然的想法
                
                创建两个链表，一个是odd链表，一个是even链表
                
                遍历一遍，然后将odd添加到odd链表，even添加到even链表。然后再合并两个链表。
                
                """
)
public class Odd_Even_Linked_List {
    public ListNode oddEvenList(ListNode head) {

        if(head == null || head.next == null) return head;

        ListNode oddDummy = new ListNode(0), oddTail = oddDummy;
        ListNode evenDummy = new ListNode(0), evenTail = evenDummy;

        ListNode p = head;
        boolean atOdd = true;
        ListNode temp;
        while (p != null) {

            // 先把p摘下来
           temp = p;
           p = p.next;
           temp.next = null;

           //判断temp结点是奇还是偶，然后加入到不同的列表。
            if(atOdd) {
                oddTail.next = temp;
                oddTail = temp;
            } else {
                evenTail.next = temp;
                evenTail = temp;
            }

            atOdd = !atOdd;
        }

        oddTail.next = evenDummy.next;

        return oddDummy.next;
    }

//    public ListNode oddEvenList(ListNode head) {
//
//        if(head == null || head.next == null) return head;
//
//        ListNode oddHead = head, oddTail = oddHead;
//        ListNode evenHead = head.next, evenTail = evenHead;
//        ListNode pNode = evenTail.next;
//
//        // 注意断开, 这样可以使得算法更清晰
//        oddTail.next = null;
//        evenTail.next = null;
//
//        boolean odd = true;
//
//        ListNode node;
//        while(pNode != null) {
//            node = pNode;
//            pNode = pNode.next;
//            node.next = null;
//            if(odd) {
//                oddTail.next = node;
//                oddTail = node;
//                odd = false;
//            } else {
//                evenTail.next = node;
//                evenTail = node;
//                odd = true;
//            }
//        }
//
//        oddTail.next = evenHead;
//        return head;
//    }

}

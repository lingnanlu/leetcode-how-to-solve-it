package sort;

import common.ListTool;
import list.ListNode;

public class Insertion_Sort_List {

    public ListNode insertionSortList(ListNode head) {

        // 使用一个dummy node 有助于简化代码。
        ListNode dummy = new ListNode(0);

        // p 表示要插入的结点
        ListNode p = head;
        while (p != null) {

            // 摘下一个元素
            ListNode n = p;
            p = p.next;
            n.next = null;


            // 找到第一个比要插入结点大的node
            ListNode q = dummy.next;
            ListNode pre = dummy;

            while (q != null && q.val < n.val) {
                pre = q;
                q = q.next;
            }

            n.next = q;
            pre.next = n;

        }

        return dummy.next;
    }


    public ListNode insertionSortList2(ListNode head) {

        ListNode sortedHead = null;

        // p 表示要插入的结点
        ListNode p = head;
        while (p != null) {

            // 摘下一个元素
            ListNode n = p;
            p = p.next;
            n.next = null;

            if (sortedHead == null) {
                sortedHead = n;
            } else {

                // 找到第一个比要插入结点大的node
                ListNode q = sortedHead;
                ListNode preP = null;

                while (p != null && p.val < n.val) {
                    preP = p;
                    p = p.next;
                }

                n.next = p;
                if (preP == null) {
                    sortedHead = n;
                } else {
                    preP.next = n;
                }

            }
        }

        return sortedHead;
    }

    public static void main(String[] args) {
        ListNode test = ListTool.build(4, 2, 1, 3);
        Insertion_Sort_List example = new Insertion_Sort_List();

        ListNode result = example.insertionSortList(test);

        ListTool.print(result);
    }
}

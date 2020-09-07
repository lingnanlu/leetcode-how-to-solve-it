package sort;

import common.ListTool;
import list.ListNode;

/**
 * 常量空间以及nlogn只有归并了。
 *
 * 快排对单链表不行，因为快排要两个方向走
 */
public class SortList {

    public ListNode sortList(ListNode head) {

        if (head == null || head.next == null) return head;

        // 将链表断开成两个
        // 对两个进行排序
        // 合并两个

        ListNode middle = findMiddle(head);
        ListNode list1 = head;

        ListNode list2 = middle.next;
        middle.next = null;

        ListNode list1Sorted = sortList(list1);
        ListNode list2Sorted = sortList(list2);

        return merge(list1Sorted, list2Sorted);
    }

    private ListNode merge(ListNode list1, ListNode list2) {
        ListNode dummy = new ListNode(0);
        ListNode tail = dummy;
        ListNode p1 = list1, p2 = list2;

        while (p1 != null && p2 != null) {
            if(p1.val <= p2.val) {
                tail.next = p1;
                p1 = p1.next;
            } else {
                tail.next = p2;
                p2 = p2.next;
            }
            tail = tail.next;
        }

        if (p1 != null) {
            tail.next = p1;
        }

        if (p2 != null) {
            tail.next = p2;
        }

        return dummy.next;

    }

    /**
     * 其实这个不好证明，举个单，双数的例子， 模拟一下就明白了
     * @param head
     * @return
     */
    private ListNode findMiddle(ListNode head) {
       ListNode fast = head;
       ListNode slow = head;

       while(fast != null && fast.next != null && fast.next.next != null) {
           fast = fast.next.next;
           slow = slow.next;
       }

       return slow;
    }


    public static void main(String[] args) {


        ListNode example = ListTool.build(4, 2, 1, 3);

        SortList test = new SortList();

        test.sortList(example);
    }
}

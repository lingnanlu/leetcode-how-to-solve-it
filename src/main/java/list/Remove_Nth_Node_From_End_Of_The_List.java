package list;

import common.ListTool;

public class Remove_Nth_Node_From_End_Of_The_List {

    // 这里要求只遍历一遍， 那么就不能通过得到list的长度去找到要删除的元素。
    // 一个trick就是， 使用两个指针， 两个指针之间相隔n个元素
    public static ListNode removeNthFromEnd(ListNode head, int n) {
        ListNode p = head;

        int i = 1;
        while (i != n) {
            p = p.next;
            i++;
        }

        //此时p指向的是第n个元素
        //然后p，q同时移动， 当p指向最后一个元素时，q指向的是倒数第n个， pre指向的是倒数第n个的前一个
        ListNode q = head, pre = null;
        while(p.next != null) {
            p = p.next;
            pre = q;
            q = q.next;
        }

        if(pre == null) {
            return q.next;
        }
        else {
            pre.next = p.next;
            p.next = null;
            return head;
        }
    }

    public static void main(String[] args) {
        ListNode test = ListTool.build(1, 2, 3, 4, 5);
        removeNthFromEnd(test, 2);
    }
}

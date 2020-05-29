package list;

public class Reverse_Node_In_K_Group {

    /**
     * 其实就是swap_nodes的一般化
     * @param head
     * @param k
     * @return
     */
    public ListNode reverseKGroup(ListNode head, int k) {

        int length = 0;
        for(ListNode p = head; p != null; p = p.next) {
            length ++;
        }

        int group = length / k;

        ListNode dummy = new ListNode(-1);
        ListNode dummyTail = null; //插入完每一对pair后， dummyHead就重置成dummyTail
        ListNode dummyHead = dummy; //每一对pair要从head插入
        int count = k;
        ListNode p = head;

        while(p != null && group != 0) {
            if(count != 0) {
                // 摘下来
                ListNode node = p;
                p = p.next;
                node.next = null;

                // 插入头部
                node.next = dummyHead.next;
                dummyHead.next = node;

                // 说明是插的这个pair的第一个， 也就是最后一个
                if (count == k) dummyTail = node;

                count--;
            } else {

                //处理完了一组， 重置count和dummyhead
                count = k;
                dummyHead = dummyTail;
                group--;
            }
        }

        // 说明还有剩余
        if(p != null) {
            dummyHead.next = p;
        }

        return dummy.next;
    }
}

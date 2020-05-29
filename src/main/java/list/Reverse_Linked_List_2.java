package list;

public class Reverse_Linked_List_2 {

    // 虽然罗嗦, 但没有什么技巧, 清晰, 你可以边叙述边写, 然后把它写出来.
    public static ListNode reverseBetween(ListNode head, int m, int n) {

        // 先找到各个边界

        ListNode mBeforeNode = null;
        ListNode mNode = head;
        int i = 1;
        //mNode指向第i个元素, mPreNode指向mNode这前的元素
        while(i != m) {
            i++;
            mBeforeNode = mNode;
            mNode = mNode.next;
        }

        ListNode nNode = head;
        ListNode nAfterNode = head.next;
        i = 1;
        while(i != n) {
            i++;
            nNode = nNode.next;
            nAfterNode = nNode.next;
        }

        // 断开
        if (mBeforeNode != null) {
            mBeforeNode.next = null;
        } else {
            // 人工添加一个结点
            mBeforeNode = new ListNode(-1);
            mBeforeNode.next = null;
        }

        if(nNode.next != null) nNode.next = null;

        // 将[m, n]之间的node倒序插入到mBeforeNode之间

        // p指向要插入的结点
        ListNode pNode = mNode;

        while (pNode != null) {
            ListNode node = pNode;
            pNode = pNode.next;

            //插入
            node.next = mBeforeNode.next;
            mBeforeNode.next = node;
        }

        // 此时mNode的位置应该在nNode处
        mNode.next = nAfterNode;

        if (m == 1) return mBeforeNode.next;
        else return head;

    }

    public static void main(String[] args) {

    }
}

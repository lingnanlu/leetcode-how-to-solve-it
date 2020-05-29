package easy;

public class Linked_List_Cycle {

    /**
     * 一 一种很直观的想法是, 染色法, 但需要O(n)的空间
     *
     * 二 另一种是追逐法, 怎么给一个证明, 或直观的解释呢?
     *
     * 可以这样想, 一个每次走一个结点, 一个每次走两个结点.
     *
     * 总有那么一个时机, 走两个结点的会落后走一个结点的要不一个, 要不两个.
     *
     * 无论是落后一个还是两个, 总能经过若干步追上.
     *
     */
    public boolean hasCycle(ListNode head) {
        ListNode slow = head;
        ListNode fast = head;

        while(slow != null & fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;

            if(fast == slow) return true;
        }

        // 跳出循环说明一直没有找到
        return false;
    }
}

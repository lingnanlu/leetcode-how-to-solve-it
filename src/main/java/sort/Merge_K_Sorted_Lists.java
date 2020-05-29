package sort;

import list.ListNode;

import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * 思路一：
 *
 * 最直观的一种方式是两两合并，假如有4个，长度分别是L1, L2, L3, L4
 *
 * 则时间复杂度为
 *
 * (L1 + L2) + ((L1 + L2) + L3) + (((L1 + L2) + L3) + L4)
 * = 3L1 + 3L2 + 2L3 + L4
 *
 * 这种方式是利用的两两合并的结果
 *
 * 思路二：
 *
 * 利用两两合并的方法。
 *
 * 假设有4个， 分别找四个指针指向当前元素， 然后对这四个指针所指向的元素进行排序。
 * 每次将最小的插入到结果中。
 *
 * 思路三：
 *
 * 利用一个堆来进行排序。
 *
 * 假设有4个， 则先拿4个list的第一个元素来构成初始小顶堆，然后取出最小的。
 *
 * 再将最小的放小的插入堆中。
 *
 * 每次插入的时间复杂度为LogK
 *
 * 则最后的时间复杂度为
 *
 * (L1 + L2 + ... LN) * LogK
 *
 * 与思路二相比， 就是每次找最小元素时， 时间复杂度降到了logK
 *
 *
 */
public class Merge_K_Sorted_Lists {

    private Comparator<ListNode> comparator = new Comparator<ListNode>() {
        @Override
        public int compare(ListNode o1, ListNode o2) {
            if (o1.val < o2.val) {
                return -1;
            } else if (o1.val == o2.val) {
                return 0;
            } else {
                return 1;
            }
        }
    };

    public ListNode mergeKLists(ListNode[] lists) {

        if (lists == null || lists.length == 0) return null;

        ListNode dummy = new ListNode(0);
        ListNode tail = dummy;

        PriorityQueue<ListNode> heap = new PriorityQueue<>(lists.length, comparator);

        // 初始化堆
        for (ListNode node: lists) {
            if (node != null) heap.add(node);
        }

        while (heap.size() != 0) {

            ListNode min = heap.poll();

            tail.next = min;
            tail = min;

            if (min.next != null) {
                heap.add(min.next);
            }
        }

        return dummy.next;

    }


}

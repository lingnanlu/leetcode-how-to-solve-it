package List;

import run.elder.Category;
import run.elder.Leetcode;

import java.util.HashSet;
import java.util.Set;

@Leetcode(
        title = "Linked List Cycle II",
        link = "https://leetcode.com/problems/linked-list-cycle-ii/",
        category = Category.LIST,
        how2SolveIt = """
               
               这个环可不可以出现在中间呢, 不可以, 画个图你就知道了, 因为是单链表, 所以环一定包含最后一个元素
               
               方法一:
               
               这题要找的是环的入口元素, 那么, 它有什么特征呢? 如果你遍历列表, 一直遍历下去的话.
               入口元素是第一个, 重复遍历到的元素. 所以可以利用这种方式来确定.
               
               这种方法需要链表长度个的格外空间.
               
               方法二:
               
               不使用格外空间. 其实这个很trick, 就是使用快慢指针. 一个走两步, 一个走一步
               虽然想不出来, 但你能证明吗, 这个也很难证明
               
               在证明之前先了解如下
               
               1. 快的一定不晚于慢的进入环(假设进入环的定义为走到环内结点, 包含初始节点)
               2. 快慢指针是同步向前的
               3. 这里的节点是离散的
               
               现在要证明的是, 快慢一定能在环内某个节点相遇
               
               假设, 某一步之后, 快结点在i, 慢结点j, i > j (可看成慢的领先快的, 因为是环, 所以任何情况都可以这样看)
               所以, 两者之间其实相差j - i个结点
               而每走一步, 快的都会接近一个, 所以经过j - i步之后, 两者就相遇了.
               
               注意, 这是在一个走两步, 一个走一步下的证明, 如果一个走3步, 一个走一步, 就更不直观了.
               
               以上只是说明了有环, 对于环的长度以及入口结点等, 还要有进一步数学证明, 如
               
               https://www.cnblogs.com/hiddenfox/p/3408931.html
                """,
        relatedQuestions = {}
)
public class Linked_List_Cycle_II {

    static class First {
        public ListNode detectCycle(ListNode head) {

            // p是将要遍历的元素
            ListNode p = head;

            // 保存已经遍历过的元素
            Set<ListNode> visited = new HashSet<>();
            while (p != null && !visited.contains(p)) {
                visited.add(p);
                p = p.next;
            }

            if (p == null) {
                // 说明无环
                return p;
            } else {
                // 此时visited 包含p, 而p指向的就是入口
                return p;
            }
        }
    }

    static class Second {
        // 该版本虽然长一些, 但比网上的直观
        public ListNode detectCycle(ListNode head) {

            ListNode fast = head, slow = head;

            // 让两者开始走, 如果相遇一定是在环内, do while就是让两者开始走
            do {

                // fast 走一步
                if (fast != null) {
                    fast = fast.next;
                } else {
                    break;
                }

                // fast 再走一步
                if (fast != null) {
                    fast = fast.next;
                } else {
                    break;
                }

                // slow走一步
                if (slow != null) {
                    slow = slow.next;
                } else {
                    break;
                }

            } while (fast != slow);

            // 说明走到了末尾, 则无环
            if (fast == null) {
                return null;
            }

            // 由数学证明, 相遇节点到入口节点的距离, 等与head到入口结点的距离
            ListNode p = slow, q = head;
            while (p != q) {
                p = p.next;
                q = q.next;
            }

            return p;
        }

    }
}

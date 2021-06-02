package question.list;

import elder.Category;
import elder.Leetcode;

@Leetcode(
        title = "Swap Nodes in Pairs",
        category = Category.LIST,
        howToSolveIt = """
                又这是一个模拟操作的题，在纸上理清各个指针的操作即可，要细心一些。
                
                这题还有另外一种思路，就是交换相邻节点的值，这是又一种比较trick的方式。
                
                更一般化是交换相接的n个，此时可以写出通用的方式，这就是解决一个通用的问题和解决一个特殊问题不同的地方。
                
                见Reverse Nodes in k-Group
                """,
        relatedTopics = "Reverse Nodes in k-Group"
)
public class Swap_Nodes_In_Pairs {

    /**
     * 这里只是要求交换相接的两个， 其实可以扩展一个交换相接的n个， 当n为list的长度是， 其实就是reverse该list
     * 看到reverse就想到从头部插入
     * @param head
     * @return
     */
    public ListNode swapPairs(ListNode head) {
        ListNode dummy = new ListNode(-1);
        ListNode dummyTail = null; //插入完每一对pair后， dummyHead就重置成dummyTail
        ListNode dummyHead = dummy; //每一对pair要从head插入
        int count = 2;
        ListNode p = head;

        while(p != null) {
            if(count != 0) {

                // 摘下来
                ListNode node = p;
                p = p.next;
                node.next = null;

                // 插入头部
                node.next = dummyHead.next;
                dummyHead.next = node;

                // 说明是插的这个pair的第一个， 也就是最后一个
                if (count == 2) dummyTail = node;

                count--;
            } else {

                //重置count和dummyhead
                count = 2;
                dummyHead = dummyTail;
            }
        }

        return dummy.next;
    }
}

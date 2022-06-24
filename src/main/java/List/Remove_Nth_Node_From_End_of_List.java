package List;

import run.elder.Category;
import run.elder.Leetcode;

@Leetcode(
        title = "Remove Nth Node From End of List",
        link = "https://leetcode.com/problems/remove-nth-node-from-end-of-list/",
        category = Category.LIST,
        how2SolveIt = """
                方法一:
                
                因为是从最后往前到数, 不知道其正数是第几个, 所以, 先遍历一遍, 得到总共有多少个, 然后转化成正数删除多少个
                
                方法二:
                
                要求一遍遍历搞定. 那么, 怎么办呢? 
                
                回到题目, 已知的是什么
                
                1. 链表
                2. 到数N
                
                我们怎么能直接利用这个到数N呢? 或者把它换一种说法
                
                到数N => 到数第N个与到数第1个相差N-1
                
                这个N-1是不变的, 那么, 我们利用两个指针, 让它们之间所指元素的序号差N-1, 然后, 两者同步走, 当一个到达最后时, 
                另一个所指的正好是第N个
                
                启示就是, 好好的思考条件, 思考已知, 挖掘已知是蕴含的信息.
                
                这一题也可以不使用dummy, 或者不使用pre, 但pre和dummy显然简化了操作
              
                """,
        relatedQuestions = {}
)
public class Remove_Nth_Node_From_End_of_List {

    static class First {
        public ListNode removeNthFromEnd(ListNode head, int n) {
            return null;
        }
    }

    static class Second {
        public ListNode removeNthFromEnd(ListNode head, int n) {

            // dummy结点方便操作
            // pre是p之前的结点
            // dummy是新链表的头结点
            ListNode dummy = new ListNode();
            dummy.next = head;
            ListNode pre = dummy;

            // p, q之间的序号差就是N - 1
            ListNode p = head, q = head;
            int distance = 0; // p, q之间的序号差
            while (distance != n - 1) {
                q = q.next;
                distance++;
            }

            // 此时, p, q之间相差N - 1, 两者同步往后移动, 到达最后一个就停止
            while (q.next != null) {
                pre = p;
                p = p.next;
                q = q.next;
            }

            // 此时p指向的是要删除元素, q是最后一个元素, pre是p的前一个元素
            pre.next = p.next;

            return dummy.next;

        }
    }
}

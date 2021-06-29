package question.list;

import run.elder.Category;
import run.elder.Leetcode;
import run.elder.Solution;

@Leetcode(
        title = "Reverse Linked List II",
        link = "https://leetcode.com/problems/reverse-linked-list-ii/",
        category = Category.LIST,
        how2SolveIt = """
                q 这里只是reverse部分，其实可以利用之前的方法. 其实这是把原链表分成了三份。
                
                我们需要将中间那一份reverse，这个可以迭代，也可以递归。
                
                然后再将三者连起来。
                
                为了将三者连起来，所以要记住第一个链表的尾，第三个链表的头。
                
                """,
        relatedQuestions = {}
)
public class Reverse_Linked_List_2 {

    @Solution(detail = "两遍遍历，第一遍找边界，第二遍reverse，然后链接, 这种方法其它也不算严格意义上的两遍，但比onepass清晰")
    static class TwoPass {
        public static question.list.ListNode reverseBetween(question.list.ListNode head, int m, int n) {

            if (head == null || head.next == null) return head;
            if (m == n) return head;

            question.list.ListNode mNode , mNodeBefore , nNode , nNodeAfter;
            mNode = mNodeBefore = nNode = nNodeAfter = null;

            question.list.ListNode p = head;
            int count = 1;

            // 第一步：遍历一遍，找到三个链表边界
            // 注意检查是否适用与边界，如m为1，n为最后一个,mNode,mNodeBefore, nNode, nNodeAfter的含义是否还是正确的。
            // 这里没有问题
            while(p != null) {

                if(count == m - 1) {
                    mNodeBefore = p;
                } else if(count == m) {
                    mNode = p;
                } else if(count == n) {
                    nNode = p;
                } else if(count == n + 1) {
                    nNodeAfter = p;
                }

                p = p.next;
                count++;

            }


            // 第二步：reverse mNode和nNode
            question.list.ListNode q = null;
            question.list.ListNode tmp = null;

            //使用p来迭代
            for(p = mNode; p != nNodeAfter;) {

                tmp = p;
                p = p.next;
                tmp.next = null;

                tmp.next = q;
                q = tmp;
            }

            // 此时，mNode就是reverse的尾，nNode就是头
            if(mNodeBefore != null) mNodeBefore.next = nNode;
            else {
                //说明mNode是第一个元素。
                head = nNode;
            }

            mNode.next = nNodeAfter;

            return head;
        }
    }

    @Solution(name = "one pass todo")
    static class OnePass {

        public static question.list.ListNode reverseBetween(ListNode head, int m, int n) {
            // 其实onepass和之前的类似，只是先找到m边界，然后再reverse，然后现链接第三部分，感觉不如上面的清晰一些
            return null;
        }
    }


}

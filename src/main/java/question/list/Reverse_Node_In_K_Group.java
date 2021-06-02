package question.list;

import elder.Category;
import elder.Leetcode;
import elder.Solution;

@Leetcode(
        title = "Reverse Nodes in k-Group",
        category = Category.LIST,
        howToSolveIt = """
                swap nodes in pairs 的一般化问题，也是一个模拟操作的题，注意指针的含义以及细节。
                """
)
public class Reverse_Node_In_K_Group {

    @Solution("一遍遍历")
    static class Onepass {
        public ListNode reverseKGroup(ListNode head, int k) {

            ListNode dummy = new ListNode(0);
            ListNode tail = dummy;  //新list的尾结点
            ListNode p = head, q = p;   //p,q要来定位含有k个结点的list

            while(true) {
                //1. 先定位k个结点
                int count = k;
                while(count != 0) {
                    if(q != null) {
                        q = q.next;
                        count--;
                    } else {
                        break;
                    }
                }

                //此时，p，q之间就是有k个结点
                if(count != 0) {
                    // 说明不满k个了
                    tail.next = p;
                    break;
                } else {
                    // reverse
                    ListNode newTail = p; //将第一个结点当作是新的tail
                    while(p != q) {

                        //摘下p
                        ListNode temp = p;
                        p = p.next;
                        temp.next = null;

                        //插入
                        temp.next = tail.next;
                        tail.next = temp;

                    }

                    //此时已经reverse完毕
                    tail = newTail;
                }
            }
            return dummy.next;
        }
    }

    @Solution("两遍遍历")
    static class TwoPass {
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

}

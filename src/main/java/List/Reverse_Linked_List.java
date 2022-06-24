package List;

import run.elder.Category;
import run.elder.Leetcode;

@Leetcode(
        title = "Reverse Linked List",
        link = "https://leetcode.com/problems/reverse-linked-list/",
        category = Category.LIST,
        how2SolveIt = """
                基础题, 考虑对指针操作
                
                先直观的看怎么翻转, 比如方法三, 然后通过操作指针以及利用不变式来达到目的.
                
                注意不要去套什么双不双指针, 那就限制了你的思路, 你就是思考如何修改链表, 并画图把链表修改正确就行.
                
                注意递归的操作, 小心各个指针的含义, 画个图
                """,
        relatedQuestions = {}
)
public class Reverse_Linked_List {

    /**
     * 1 -> 2 -> 3 -> 4
     *
     * 2 -> 1 -> 3 -> 4
     *
     * 3 -> 2 -> 1 -> 4
     *
     * 4 -> 3 -> 2 -> 1
     *
     * 每次取头元素的下一个插入到链表头部, 直到没有
     *
     */
    static class First {

        public ListNode reverseList(ListNode head) {

            if (head == null) {
                return null;
            }

            // p指向原头元素的下一个
            ListNode p = head.next;

            // 新的头部, 注意这个新的头部的初始化, 画个图理解一下.
            // 因为还没有变化过, 所以新的头部和原来是一样的
            ListNode head2 = head;
            while (p != null) {
                // 1. 摘下来
                head.next = p.next;
                p.next = null;

                // 2. 插入到新的链表头部
                p.next = head2;
                head2 = p;

                // 3. p指向原头元素的下一个
                p = head.next;
            }

            return head2;

        }
    }


    /**
     * 1 -> 2 -> 3 -> 4
     *
     * 4 -> 3 -> 2 -> 1
     *
     * 4 -> 3 -> 2
     *
     * 4 -> 3
     *
     * 4
     *
     * 递归的方式
     *
     *
     */
    static class Second {

        /**
         *
         * @param head 调用之后, head指向尾结点
         * @return 翻转后的结点的头结点.
         */
        public ListNode reverseList(ListNode head) {

            // 注意递归中止的条件, 画个图
            if (head == null || head.next == null) {
                return head;
            }
            // 先摘下来head
            ListNode p = head.next;
            head.next = null;

            // 将子list翻转
            ListNode node = reverseList(p);

            // 将摘下来的node拼接到最后
            p.next = head;
            return node;


        }


    }

    /**
     * 1 -> 2 -> 3 -> 4
     *
     * 1 <- 2 -> 3 -> 4
     *
     * 1 <- 2 <- 3 -> 4
     *
     * 1 <- 2 <- 3 <- 4
     *
     * 另一种方式, 改变每个箭头的方向
     *
     *
     */
    static class Third {

        public ListNode reverseList(ListNode head) {

            if (head == null) {
                return null;
            }
            /**
             * p是要依次遍历的结点
             * q是p的下一个结点
             * pre是p的前一个结点
             */
            ListNode p = head, q = head.next, pre = null;
            // 依次调整p的指针方向
            while (p != null) {
                p.next = pre;
                pre = p;
                p = q;

                if (p != null) {
                    q = p.next;
                }
            }

            return pre;

        }
    }

}

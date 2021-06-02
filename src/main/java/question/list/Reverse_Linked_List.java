package question.list;

import elder.Category;
import elder.Leetcode;
import elder.Solution;

@Leetcode(
        title = "Reverse Linked List",
        category = Category.LIST,
        howToSolveIt = """
                方法一：新建一个list，不断头部插
                
                关键是搞清楚使用几个指针，最好用图来模拟一遍就行。
                
                方法二：递归的方法，要注意，递归之后返回的是新链表的头head,但原来的head呢？
                
                这个问题的回答，可以自己来模拟递归，然后递归到最后一层，其实你可以控制将原来的head指向tail.
                
                """
)
public class Reverse_Linked_List {

    @Solution("迭代的方式")
    static class InsertIntoTheHead {

        public question.list.ListNode reverseList(question.list.ListNode head) {

            if(head == null) return null;

            question.list.ListNode q = null;

            // p是迭代指针，从第一下，迭代到null
            for(question.list.ListNode p = head, tmp = null; p != null;) {

                // 摘下p，tmp指向摘下的node
                tmp = p;
                p = p.next;
                tmp.next = null;

                // 将tmp插入新list中
                tmp.next = q;
                q = tmp;
            }

            return q;
        }
    }

    @Solution("递归的方式")
    static class Reverse {
        public question.list.ListNode reverseList(question.list.ListNode head) {
            if (head == null || head.next == null) return head;
            ListNode reversed = reverseList(head.next);

            //head.next现在成了子list的tail.
            head.next.next = head;
            head.next = null;
            return reversed;
        }
    }

}

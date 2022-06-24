package List;

import run.elder.Category;
import run.elder.Leetcode;

@Leetcode(
        title = "Design Linked List",
        link = "https://leetcode.com/problems/design-linked-list/",
        category = Category.LIST,
        how2SolveIt = """
                本题主要是涉及到一个全局的不变式, head, tail, length每个的方法都不是太难, 但要注意任何方法都要保证不变式为真.
                """,
        relatedQuestions = {}
)
public class Design_Linked_List {

    static class MyLinkedList {

        // 注意这三个可以看成是全局不变式, 在所有的操作中, 都要保持其含义不变
        int length;
        ListNode head;  // 链表头
        ListNode tail;  // 链表尾

        public MyLinkedList() {

        }

        private boolean empty() {
            return length == 0;
        }
        // 获取链表中第 index 个节点的值。如果索引无效，则返回-1
        public int get(int index) {

            if (empty()) {
                return -1;
            }

            if (index < 0 || index > length - 1) {
                return -1;
            }

            // 定位到第index个节点, p指向第n个节点
            ListNode p = head;
            int n = 0;
            while (n != index) {
                p = p.next;
                n++;
            }

            // 此时n == index, p指向第n个节点
            return p.val;

        }

        public void addAtHead(int val) {
            ListNode node = new ListNode(val);
            if (empty()) {
                head = node;
                tail = node;
            } else {
                node.next = head;
                head = node;
            }
            length++;
        }

        public void addAtTail(int val) {
            ListNode node = new ListNode(val);
            if (empty()) {
                head = node;
                tail = node;
            } else {
                tail.next = node;
                tail = node;
            }
            length++;
        }

        public void addAtIndex(int index, int val) {
            if (index <= 0) {
                addAtHead(val);
            } else if (index == length) {
                addAtTail(val);
            } else if (index > length) {
                return;
                // do nothing
            } else {
                // 找到第index - 1的结点
                // p指向第i个结点
                ListNode p = head;
                int i = 0;
                while (i != index - 1) {
                    i++;
                    p = p.next;
                }

                // 由不变式, 此时p指向第index - 1的结点
                ListNode node = new ListNode(val, p.next);
                p.next = node;
                length++;
            }

        }

        public void deleteAtIndex(int index) {
            if (index < 0 || index > length - 1) {
                return;
            } else {
                ListNode dummy = new ListNode(0, head);

                // 找到第index - 1的结点
                // p指向第i个结点
                ListNode p = dummy;
                int i = -1; // dummy结点可以看成是第-1个结点
                while (i != index - 1) {
                    i++;
                    p = p.next;
                }

                // 由不变式, 此时p指向第index - 1的结点
                p.next = p.next.next;

                /**
                 * 注意保持length, head, tail三者的含义不变
                 */
                // 还是有不变式可知, dummy永远是head的前一个结点
                head = dummy.next;

                // 注意如何删除的是尾元素的情况
                if (index == length - 1) {
                    tail = p;
                }

                length--;
            }
        }
    }
}

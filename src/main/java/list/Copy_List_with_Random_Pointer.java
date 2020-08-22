package list;

import elder.Category;
import elder.Leetcode;

import java.util.HashMap;
import java.util.Map;

@Leetcode(
        title = "Copy List with Random Pointer",
        category = Category.LIST,
        howToSolveIt = """
                q: 可不可以，先简化一个问题？比如说，如果没有random，你会做么？
                
                a：没问题，很简单
                
                q：好，现在你可以拷贝所有结点了，但是你忽略了random.你如何根据原来的对应关系，来在新的结点中建立对应关系 ？
                
                用符号表示就是，你知道 A->B, A->A1, 那么，如何建立A1-B1？
                
                a：如果能知道B->B1,那么，因为A->B->B1, A->A1, 所以，就可以得到A1->B1了
                
                q：也就是说，你要建立，新旧两点之前的映射关系？
                
                a：对的，可以用hashmap, 试一试
                
                q: 还有一种比较trick的方式，欣赏就好，你不容易想得到
                
                """
)
public class Copy_List_with_Random_Pointer {

    static class Node {
        int val;
        Node next;
        Node random;

        public Node(int val) {
            this.val = val;
            this.next = null;
            this.random = null;
        }
    }

    static class UsingHashMap {

        public Node copyRandomList(Node head) {

            //1. 先依次拷贝结点，并在拷贝过程中，建立新旧对应关系

            Node dummy = new Node(0);
            Map<Node, Node> old2new = new HashMap<>();
            Node tail = dummy, p = head;

            while(p != null) {
                Node temp = new Node(p.val);
                old2new.put(p, temp);
                tail.next = temp;
                tail = temp;
                p = p.next;
            }

            //2. 两遍历一遍，构建random之间的联系
            // p->q p->p1 q->q1
            p = head;
            Node p1 = dummy.next;
            Node q, q1;
            while(p != null) {
                q = p.random;
                q1 = old2new.get(q);
                p1.random = q1;

                p = p.next;
                p1 = p1.next;
            }

            return dummy.next;
        }

    }

    static class Trick {
        /*
        当然，如果使用 HashMap 占用额外的空间，如果这道题限制了空间的话，就要考虑别的方法。下面这个方法很巧妙，可以分为以下三个步骤：

        1. 在原链表的每个节点后面拷贝出一个新的节点。

        2. 依次给新的节点的随机指针赋值，而且这个赋值非常容易 cur->next->random = cur->random->next。

        3. 断开链表可得到深度拷贝后的新链表。

        举个例子来说吧，比如原链表是 1(2) -> 2(3) -> 3(1)，括号中是其 random 指针指向的结点，那么这个解法是首先比遍历一遍原链表，在每个结点后拷贝一个同样的结点，但是拷贝结点的 random 指针仍为空，则原链表变为 1(2) -> 1(null) -> 2(3) -> 2(null) -> 3(1) -> 3(null)。然后第二次遍历，是将拷贝结点的 random 指针赋上正确的值，则原链表变为 1(2) -> 1(2) -> 2(3) -> 2(3) -> 3(1) -> 3(1)，注意赋值语句为：

        cur->next->random = cur->random->next;

        这里的 cur 是原链表中结点，cur->next 则为拷贝链表的结点，cur->next->random 则为拷贝链表的 random 指针。cur->random 为原链表结点的 random 指针指向的结点，因为其指向的还是原链表的结点，所以我们要再加个 next，才能指向拷贝链表的结点。最后再遍历一次，就是要把原链表和拷贝链表断开即可，参见代码如下：
         */
    }
}

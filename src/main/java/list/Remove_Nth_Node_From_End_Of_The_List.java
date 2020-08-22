package list;


import elder.Category;
import elder.Leetcode;

@Leetcode(
        title = "Remove Nth Node From End of List",
        category = Category.LIST,
        howToSolveIt = """
                
                方法一：
                
                这里，要删除的是倒数第N个，另一个类似的问题是删除正数的第N个，正数就比较简单。所以，可以先遍历一遍统计个数，然后将倒数转为正数
                
                方法二：
                
                由一的启发来看，这种倒数转正数，似乎有那么一点stack的味道，所以可以使用一个stack, 先全部压栈，然后弹出第N个
                
                方法三：
                
                使用两个指针，这种就是比较trick的方法了，可以一遍遍历，当要求一遍遍历时，记住这种比较trick一点的方式。
                
                至于两个指针分别指向哪个，如果想不明白，就举个例子就可以了。
                
                """
)
public class Remove_Nth_Node_From_End_Of_The_List {

    public static ListNode removeNthFromEnd(ListNode head, int n) {

        ListNode p = head, q = head;

        int count = n;
        while(count != 0) {
            q = q.next;
            count--;
        }

        if ( q == null ) {         //特殊情况，说明删除第一个
            return head.next;
        } else {
            while(q.next != null) {
                p = p.next;
                q = q.next;
            }

            p.next = p.next.next;

            return head;
        }
    }
//    public static ListNode removeNthFromEnd(ListNode head, int n) {
//        ListNode p = head;
//
//        int i = 1;
//        while (i != n) {
//            p = p.next;
//            i++;
//        }
//
//        //此时p指向的是第n个元素
//        //然后p，q同时移动， 当p指向最后一个元素时，q指向的是倒数第n个， pre指向的是倒数第n个的前一个
//        //如果不好想明白，举个例子就行！
//        ListNode q = head, pre = null;
//        while(p.next != null) {
//            p = p.next;
//            pre = q;
//            q = q.next;
//        }
//
//        if(pre == null) {
//            return q.next;
//        }
//        else {
//            pre.next = p.next;
//            p.next = null;
//            return head;
//        }
//    }

}

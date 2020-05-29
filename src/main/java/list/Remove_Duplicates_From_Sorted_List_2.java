package list;

import common.ListTool;

public class Remove_Duplicates_From_Sorted_List_2 {

    /**
     * 1. 一种简单的方式是， 先遍历一遍， 找到重复元素， 第二遍遍历时， 跳过重复元素。
     * 2。 另一种方式是， 使用一个p来遍历这个list, 如果p所指的元素是单例元素， 则插入到新list中， 这里的关键点是判断p所指的是不是单例元素
     * @param head
     * @return
     */
    public static ListNode deleteDuplicates2(ListNode head) {

        ListNode result = new ListNode(-1);
        ListNode tail = result;
        ListNode pre = null;
        ListNode p = head;

        while(p != null) {

            if ((pre == null && p.next == null) ||              // 如果只有一个元素
                    (pre == null && p.val != p.next.val) ||     // 如果有多个元素， 在开头
                    (p.next == null && pre.val != p.val) ||     // 如果有多个元素， 在结尾
                    (pre.val != p.val && p.val != p.next.val)   // 如果有多个元素， 在中间
            ) {
                // 不能使用这种方式，因为摘下来之后， 相当于这个元素没了。
                // 摘下来
                ListNode node = p;
                p = p.next;
                node.next = null;

                tail.next = p;
                tail = p;
            } else {
                pre = p;
                p = p.next;
            }

        }

        return result.next;
    }

    // 因为是单链表， 不能往前看， 所发要有一个地方记录其是否曾经重复过。
    public static ListNode deleteDuplicates(ListNode head) {

        ListNode result = new ListNode(-1);
        ListNode tail = result;
        ListNode p = head;
        boolean duplicate = false;      //代表p所指的元素之前有没有出现过。

        while(p != null) {
            if(p.next != null && p.val == p.next.val) {
                duplicate = true;       //说明p所指的元素之前出现过
                p = p.next;
            } else {                //这里说明p和它后面的元素不同， 如果后面没有元素， 也是认为不同的。
                if(duplicate) {     //p和后一个元素不同， 但出现过， 所以跳过
                    p = p.next;
                } else {            //这里说明p所指元素没有出现过， 且和后一个不同
                    ListNode node = p;
                    p = p.next;
                    node.next = null;

                    tail.next = node;
                    tail = node;
                }

                duplicate = false;      // 因为p已经移到了后一个不同的元素， 该元素还没出现过， 所以设置成false.
            }
        }

        return result.next;
    }

    public static void main(String[] args) {
        ListNode test = ListTool.build(1, 2, 3, 3, 4, 4, 5);
        System.out.println(ListTool.print(test));

        deleteDuplicates(test);
    }
}

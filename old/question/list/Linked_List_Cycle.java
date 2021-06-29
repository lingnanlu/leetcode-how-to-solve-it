package question.list;

import run.elder.Category;
import run.elder.Leetcode;

import java.util.HashSet;
import java.util.Set;

@Leetcode(
        title = "Linked List Cycle",
        link = "https://leetcode.com/problems/linked-list-cycle/",
        category = Category.LIST,
        how2SolveIt = """
                
                一种直观的想法是，使用一个set来保存访问过的结点。
                
                另一种是追逐法, 怎么给一个证明, 或直观的解释呢?
                     
                可以这样想，有两个人，在操场跑，一个每次跑两步，一个每次跑一步。
                
                如果有圈，跑两步的总会套圈，然后落后跑一步的。
                
                那么落后能落后多少呢？
                
                这里可以分情况讨论。
                
                假如落后一步：那么会追上
                假如落后二步：下一步就会变成落后一步
                假如落后三步：下一步就会变成落后两步
                假如落后n步：下一步就会变成落后N-1步
                
                由一上可以归纳得知，无论相差几步，最终都是要追上的。
                
                如果我们扩展一下，一个跑一步，一个跑三步呢？
                
                落后一步：下一步反超。。。
                假如落后两步：下一步就追上
                落后三步：变成落后一步。。。
                
                可见，跑三步不如跑两步那么容易归纳。
               
                """,
        relatedQuestions = {}
)
public class Linked_List_Cycle {

    static class UsingSet {
        public boolean hasCycle(ListNode head) {
            Set<ListNode> visitedNodes = new HashSet<>();

            ListNode p = head;
            while(true) {           //让p一直走

                if(p == null) return false;

                if(visitedNodes.contains(p)) {
                    return true;
                } else {
                    visitedNodes.add(p);
                    p = p.next;
                }

            }
        }
    }

    static class Trick {
        public boolean hasCycle(ListNode head) {
            ListNode slow = head;
            ListNode fast = head;

            while(slow != null & fast != null && fast.next != null) {
                slow = slow.next;
                fast = fast.next.next;

                if(fast == slow) return true;
            }

            // 跳出循环说明一直没有找到
            return false;
        }
    }
}

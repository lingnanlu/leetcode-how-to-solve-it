package Stack;

import run.elder.Category;
import run.elder.Leetcode;

import java.util.LinkedList;
import java.util.Queue;

@Leetcode(
        title = "Implement Stack using Queues",
        link = "https://leetcode.com/problems/implement-stack-using-queues/",
        category = Category.STACK,
        how2SolveIt = """
                类似的模拟题, 同样要求
                
                这里不使用java提供的queue, 而是使用数组模拟, 更加有难度, 因为语言提供的queue帮你做好了容量的问题
                而使用数组, 则要考虑空间浪费.
                
                这题和两个栈实现队列有点不一样, 因为负负可以得正, 但正正不能得负.
                
                还真的是, 把全部除最后一个元素倒过去, 然后出最后一个元素
                
                这很耗时间, 没什么意义, 纯粹考察操作.
                
                这题还是觉得有点比别扭
                """,
        relatedQuestions = {}
)
public class MyStack {

    Queue<Integer> q1 = new LinkedList<>();
    Queue<Integer> q2 = new LinkedList<>();

    Queue<Integer> q = q1; // 这里是为了不来回倒元素用的. q指向的就是入栈时使用的队列
    public MyStack() {

    }

    public void push(int x) {
        q.add(x);
    }

    // 每次出栈都要倒一次元素, 再切换一次q
    public int pop() {

        /**
         * 取一个元素, 如果是最后一个元素就返回
         * 如果不是最后一个, 就放到另一个对列中.
         */
        int n;
        while(true) {
            n = q.remove();
            if (q.isEmpty()) {
                break;
            } else {
                if (q == q1) {
                    q2.add(n);
                } else {
                    q1.add(n);
                }
            }
        }

        // 此时, 倒完了元素, n是要返回的.

        // 切换q
        if (q == q1) {
            q = q2;
        } else {
            q = q1;
        }

        return n;

    }

    public int top() {
        int n = pop();
        q.add(n);
        return n;
    }

    public boolean empty() {
        return q.isEmpty();
    }

}

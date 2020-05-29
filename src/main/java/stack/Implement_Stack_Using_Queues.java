package stack;

import java.util.LinkedList;
import java.util.Queue;

public class Implement_Stack_Using_Queues {

    // q中的元素顺序和栈是一样的， 队头就是栈顶, 注意无论什么操作， 都要保持这个性质不变
    Queue<Integer> q = new LinkedList<>();

    // 用来中转
    Queue<Integer> temp = new LinkedList<>();

    /**
     * push前， q中的元素顺序就和栈是一样的， 队头就是栈顶
     * push后， 也要保持这个性质不变
     */
    public void push(int x) {
        temp.offer(x);    // 插入的元素为队头， 即栈顶

        // 将q中的元素依次插入temp
        while(!q.isEmpty()) {
            temp.offer(q.poll());
        }

        Queue t = q;
        q = temp;
        temp = t;
    }

    /**
     * Removes the element on top of the stack and returns that element.
     */
    public int pop() {
        return q.poll();
    }

    /**
     * Get the top element.
     */
    public int top() {
        return q.peek();
    }

    /**
     * Returns whether the stack is empty.
     */
    public boolean empty() {
        return q.isEmpty();
    }
}

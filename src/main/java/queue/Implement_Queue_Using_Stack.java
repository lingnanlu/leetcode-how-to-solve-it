package queue;

import java.util.Stack;

/**
 * 栈顶做队头， 栈底做队尾， 栈的顺序就是队的顺序
 * 保持这个性质不变
 */
public class Implement_Queue_Using_Stack {

    Stack<Integer> s = new Stack<>();       // 模拟对列
    Stack<Integer> tmp = new Stack<>();     // 用来做中转

    // 想办法将x放入s的栈底
    public void push(int x) {

        // 1. 先腾空s
        while (!s.empty()) {
            tmp.push(s.pop());
        }

        // 2. 将x放到s的栈底
        s.push(x);

        // 3. 再将tmp中的放回去
        while(!tmp.empty()) {
            s.push(tmp.pop());
        }
    }

    /** Removes the element from in front of queue and returns that element. */
    public int pop() {
        return s.pop();
    }

    /** Get the front element. */
    public int peek() {
        return s.peek();
    }

    /** Returns whether the queue is empty. */
    public boolean empty() {
        return s.empty();
    }
}

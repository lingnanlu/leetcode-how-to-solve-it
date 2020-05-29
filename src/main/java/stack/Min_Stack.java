package stack;

import java.util.Stack;

// 其实就是找到地方保存当前栈中的最小值
public class Min_Stack {

    Stack<Integer> stack = new Stack<>();
    // 栈顶保存的是当前栈的最小值, 这个栈的元素个数和stack是一一对应的, 理解这一条是关键
    Stack<Integer> mins = new Stack<>();

    public void push(int x) {
        stack.push(x);

        if (mins.empty()) {
            mins.push(x);
        } else if(mins.peek() < x) {
            mins.push(mins.peek());
        } else {
            mins.push(x);
        }
    }

    public void pop() {
        mins.pop();
        stack.pop();

    }

    public int top() {
        return stack.peek();
    }

    public int getMin() {
        return mins.peek();
    }

}

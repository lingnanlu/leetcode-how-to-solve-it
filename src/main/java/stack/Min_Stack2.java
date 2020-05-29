package stack;

import java.util.Stack;

/**
 * 另一种思想
 * 如果有一个元素保存当前栈中最小的值
 * 那么当有一个更小的值要入栈时， 要把当前的最小的值要记录下来。
 * 之前的方法是记录在另一个栈中
 * 其实也可以记录在一个栈中。
 *
 */
public class Min_Stack2 {

    int min = Integer.MAX_VALUE; // min记录的是当前栈中最小的元素

    Stack<Integer> stack = new Stack<>();


    /**
     * 以下操作保证了， 当只有入栈操作时
     *
     * min 肯定是栈中最小的。
     * min 的下一个， 肯定是次小的。
     * min 的下一个， 肯定是多余元素
     */
    public void push(int x) {
       if (x <= min) {          // 注意这里的=号
           // 要把之前的记录下来
           stack.push(min);
           min = x;

       }
       stack.push(x);
    }

    /**
     * 因为
     * min 肯定是栈中最小的。
     * min 的下一个， 肯定是次小的。
     * min 的下一个， 肯定是多余元素
     */
    public void pop() {
        int x = stack.pop();
        if (x == min) {
            // 把它的下一个次小赋值给它， 并弹出
            min = stack.pop();
        }
    }

    public int top() {
        return stack.peek();
    }

    public int getMin() {
        return min;
    }
}

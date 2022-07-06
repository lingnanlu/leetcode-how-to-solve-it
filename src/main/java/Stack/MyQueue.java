package Stack;

import run.elder.Category;
import run.elder.Leetcode;

@Leetcode(
        title = "Implement Queue using Stacks",
        link = "https://leetcode.com/problems/implement-queue-using-stacks/",
        category = Category.STACK,
        how2SolveIt = """
                本题是使用栈来模拟队列, 因为题目说了, 进队列的元素有限, 所以可以不用考虑动态扩容的问题
                
                这题不是求解题, 也是不证明题, 应该算是模拟题, 最好就是画一画图, 鼓捣鼓捣. 实验实验, 然后使用代码写出来
                
                这题不难, 画画图.
                
                注意全局不变式的保持
                """,
        relatedQuestions = {}
)
public class MyQueue {

    int[] stack1;
    int[] stack2;
    int i = 0;      // stack1 栈顶, 指下一个元素要压入的位置, 所以该位置中没有元素
    int j = 0;      // stack2 j - 1就是栈顶元素.

    public MyQueue() {
        stack1 = new int[100];
        stack2 = new int[100];
    }

    public void push(int x) {
        stack1[i] = x;
        i++;
    }

    /**
     * 这里其实可以简化一下if, 但这样写我觉得更清晰一些
     * @return
     */
    public int pop() {
        // 从stack2出来, 如果stack2有元素, 就直接出来, 如果没有元素, 就把stack1的全部倒过来, 然后出来
        if (j - 1 >= 0) {
            // 有元素, 就出栈
            j--;
            return stack2[j];
        } else {
            // 没有元素, 将stack1的全部倒过来
            dump();
            // 全部倒过来之后, 再从stack2出栈
            j--;
            return stack2[j];
        }
    }

    /**
     * 将stack1的全部导入stack2
     */
    private void dump() {

        // 不变式, i指向要出栈元素的下一个元素
        while ((i - 1) >= 0) {
            i--;
            // 此时i指向出栈元素
            stack2[j++] = stack1[i];
            //此时i指向了要出栈元素的下一个元素
        }
    }

    // 和pop类似, 只是不出栈
    public int peek() {
        if (j - 1 >= 0) {
            return stack2[j - 1];
        } else {
            dump();
            return stack2[j - 1];
        }
    }

    public boolean empty() {
        return i == 0 && j == 0;
    }
}

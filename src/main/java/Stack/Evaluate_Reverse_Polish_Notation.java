package Stack;

import run.elder.Category;
import run.elder.Leetcode;

import java.util.Stack;

@Leetcode(
        title = "Evaluate Reverse Polish Notation",
        link = "https://leetcode.com/problems/evaluate-reverse-polish-notation/",
        category = Category.STACK,
        how2SolveIt = """
                依然是一个模拟题
                
                依次读入, 遇到操作符就弹出两个操作数, 进行计算, 然后做为新的操作数
                """,
        relatedQuestions = {}
)
public class Evaluate_Reverse_Polish_Notation {

    public int evalRPN(String[] tokens) {

        Stack<Integer> stack = new Stack<>();

        // 操作数, 这里不用初始化
        int operand1;
        int operand2;

        for (String token : tokens) {

            if (token.equals("+") || token.equals("-") || token.equals("*") || token.equals("/")) {
                int result = 0; // 保存结果, 这里的初始化没有意义, 仅仅是为了让编译通过
                operand2 = stack.pop();
                operand1 = stack.pop();
                switch (token) {
                    case "+" -> result = operand1 + operand2;
                    case "-" -> result = operand1 - operand2;
                    case "*" -> result = operand1 * operand2;
                    case "/" -> result = operand1 / operand2;
                }
                stack.push(result);
            } else {
                stack.push(Integer.parseInt(token));
            }

        }
        // 此时栈中应该有一个数, 作为结果
        return stack.pop();
    }

    public static void main(String[] args) {
        Evaluate_Reverse_Polish_Notation test = new Evaluate_Reverse_Polish_Notation();
        String[] tokens = {"4","13","5","/","+"};
        test.evalRPN(tokens);
    }
}

package easy;

import java.util.Stack;

public class Valid_Parentheses {


    public static void main(String[] args) {
        Valid_Parentheses v = new Valid_Parentheses();

        System.out.println(v.isValid("()"));
    }

    public boolean isValid(String s) {


        Stack<Character> stack = new Stack<Character>();

        int i = 0;
        for (; i < s.length(); i++) {
            char c = s.charAt(i);
            if (isLeftBracket(c)) {
                stack.push(c);
            } else {
                if(!stack.isEmpty()) {
                    char topChar = stack.pop();
                    if (match(topChar, c)) {
                        continue;
                    } else {
                        break;
                    }
                } else {
                    break;
                }
            }
        }

        //含义是, 处理完之后, 栈为空
        if (i == s.length() && stack.isEmpty()) {
            return true;
        } else {
            return false;
        }
    }

    private boolean isLeftBracket(char c) {
        return c == '(' || c == '[' || c == '{';
    }

    private boolean match(char left, char right) {
        if ((left == '(' && right == ')') ||
                (left == '[' && right == ']') ||
                (left == '{' && right == '}')) {
            return true;
        }

        return false;
    }
}

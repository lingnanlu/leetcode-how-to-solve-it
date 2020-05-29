package stack;

import java.util.Stack;

public class Longest_Valid_Parentheses {


    public int longestValidParentheses(String s) {

        int maxLen = 0;
        Stack<Character> stack = new Stack<>();

        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c == '(') {
                stack.push(c);
            } else {
                if( !stack.empty() && stack.peek() == '(') {
                    maxLen += 2;
                    stack.pop();
                }
            }
        }

        return maxLen;

    }
}

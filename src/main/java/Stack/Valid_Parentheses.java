package Stack;

import run.elder.Category;
import run.elder.Leetcode;

import java.util.Stack;

@Leetcode(
        title = "Valid Parentheses",
        link = "https://leetcode.com/problems/valid-parentheses/",
        category = Category.STACK,
        how2SolveIt = """
               
               方法一:
               
               这题的思路, 我们可以从几个正确例子入手, 找找灵感, 比如
               
               () [] {} 
               
               {([])}
               
               ([]){}
               
               试着模拟一下规律, 发现有如下两条
               
               1. 你会从左往右看
               2. 遇到左括号, 就记录下来
               3. 遇到右括号, 就开始匹配, 而且匹配的元素就是最后一个遇到的左括号
               
               注意, 记录左括号的顺序是从左往右的, 而匹配时, 匹配的是最后一个, 这给你的灵感就是, 这个记录的结构可能需要使用一个栈.
               
               那么, 基本思路就是有了
               
               1. 左括号入栈
               2. 右括号就出栈并匹配, 匹配的上, 就继续往前走, 匹配不上, 就是说明该字符串是无效的.
               3. 最后如果走到了末尾, 都匹配上了, 那么, 就是有效的.
               
               注意匹配不上有两种情况
               1. 没有与之匹配的.
               2. 匹配错误的
               
               
               方法二:
               其实就是一的改进
               一中, 有很多左右括号代码, 如果在push时, push的是右括号, 之后的匹配就可以使用相等了, 节约了很多代码.
               
               https://programmercarl.com/0020.%E6%9C%89%E6%95%88%E7%9A%84%E6%8B%AC%E5%8F%B7.html#%E8%BF%9B%E5%85%A5%E6%AD%A3%E9%A2%98
                """,
        relatedQuestions = {}
)
public class Valid_Parentheses {
    public boolean isValid(String s) {
        Stack<Character> stack = new Stack<>();

        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c == '(' || c == '[' || c == '{') {
                stack.push(c);
            } else {

                if (stack.isEmpty()) {
                    // 说明没有与之匹配的.
                    return false;
                } else {
                    char left = stack.pop();
                    switch (c) {
                        case ')':
                            if (left != '(') {
                                return false;
                            }
                            break;
                        case ']':
                            if (left != '[') {
                                return false;
                            }
                            break;
                        case '}':
                            if (left != '{') {
                                return false;
                            }
                            break;
                    }
                }

            }
        }

        return stack.isEmpty();
    }
}

package Dp;

import run.elder.Category;
import run.elder.Leetcode;

@Leetcode(
        title = "Fibonacci Number",
        link = "https://leetcode.com/problems/fibonacci-number/",
        category = Category.ARRAY,
        how2SolveIt = """
  
         
                """,
        relatedQuestions = {}
)
public class Fibonacci_Number {

    public int fib(int n) {

        if (n <= 1) return n;

        // 每一轮计算f(i), 计算完成后,
        int first = 0;
        int second = 1;
        for (int i = 2; i <= n ; i++) {
            int temp = first + second; // temp = f(i)
            first = second;
            second = temp;
        }

        return second;
    }
}

package Other;

import run.elder.Category;
import run.elder.Leetcode;

import java.util.ArrayList;
import java.util.List;

@Leetcode(
        title = "Fizz Buzz",
        link = "https://leetcode.com/problems/fizz-buzz/",
        category = Category.UNKNOWN,
        how2SolveIt = """
               这题看起来很简单啊, 感觉没什么可说的, 难道有什么更好的方法么?
               
               我觉得, 这里最大计算量是在除法吧, 如果把除尘修改成其它cpu有好的方法, 应该更不错.
               
               方法一:
               
               最直观的做法
               
               方法二:
               
               方法一中的有些计算重复了(其实可能编译器已经优化了), 消除重复
               
               方法三:
               
               消除除法, 改用乘法
                """,
        relatedQuestions = {}
)
public class Fizz_Buzz {

    static class First {
        public List<String> fizzBuzz(int n) {

            List<String> result = new ArrayList<>();
            for (int i = 1; i <= n ; i++) {
                if (i % 3 == 0 && i % 5 == 0) {
                    result.add("FizzBuzz");
                } else if (i % 3 == 0) {
                    result.add("Fizz");
                } else if (i % 5 == 0) {
                    result.add("Buzz");
                } else {
                    result.add(i + "");
                }
            }

            return result;
        }
    }

    static class Second {

        public List<String> fizzBuzz(int n) {

            List<String> result = new ArrayList<>();
            for (int i = 1; i <= n ; i++) {
                boolean dividedBy3 = i % 3 == 0;
                boolean dividedBy5 = i % 5 == 0;
                if (dividedBy3 && dividedBy5) {
                    result.add("FizzBuzz");
                } else if (dividedBy3) {
                    result.add("Fizz");
                } else if (dividedBy5) {
                    result.add("Buzz");
                } else {
                    result.add(i + "");
                }
            }

            return result;
        }

    }

    static class Third {

        public List<String> fizzBuzz(int n) {
            List<String> result = new ArrayList<>();

            for (int i = 1; i <= n ; i++) {
                result.add(i + "");
            }

            for (int i = 3; i <= n ; i += 3) {
                result.set(i - 1, "Fizz");
            }

            for (int i = 5; i <= n ; i += 5) {
                result.set(i - 1, "Buzz");
            }

            for (int i = 15; i <= n ; i += 15) {
                result.set(i - 1, "FizzBuzz");
            }

            return result;


        }

    }

}

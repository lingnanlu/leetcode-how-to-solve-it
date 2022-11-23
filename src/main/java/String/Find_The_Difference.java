package String;

import run.elder.Category;
import run.elder.Leetcode;

import java.util.Arrays;

@Leetcode(
        title = "Find the Difference",
        link = "https://leetcode.com/problems/find-the-difference/",
        category = Category.STRING,
        how2SolveIt = """
                               
                方法一: 
                               
                 其实找t比s多的那一个字符, 这里和顺序无关, 和个数有关
                               
                 那么一种直观的想法就是统计s,t中字符个数, 然后相减, 再看看剩余的是什么就好.
                 
                方法二:
                               
                对于数组, 往往排序后可能就有思路, 所以, 可以排序试一试
                               
                方法三:
                        
                这涉及到自己不熟练的一种运算, XOR, 它有如下性质
                               
                 一个数和0做XOR运算等于本身：a⊕0 = a
                 一个数和其本身做XOR运算等于 0：a⊕a = 0
                 XOR 运算满足交换律和结合律：a⊕b⊕a = (a⊕a)⊕b = 0⊕b = b
                                
                这里a也可以是字符, 所以我们就可以把t,s中的所有的字符进行XOR.
                
                
                启示: 如果一个题有两两相消的意味, 可以考虑使用XOR. 关键是记住以上三条性质.
                               
                 """,
        relatedQuestions = {}
)
public class Find_The_Difference {

    static class First {
        public char findTheDifference(String s, String t) {

            // 记录26个字符出现的次数.
            int[] count = new int[26];

            // 统计t中出现的.
            for (char c : t.toCharArray()) {
                count[c - 'a']++;
            }

            // 减去s中的出现的次数
            for (char c : s.toCharArray()) {
                count[c - 'a']--;
            }

            //遇到的第一个不为0的就是了.
            for (int i = 0; i < count.length; i++) {
                if (count[i] != 0) {
                    return (char) (i + 'a');
                }
            }

            // 这里不会出现
            return '-';

        }
    }

    static class Second {

        public char findTheDifference(String s, String t) {
            return '0';
        }

    }

    static class Third {

        public char findTheDifference(String s, String t) {

            char ans = 0;

            for(char c : s.toCharArray()) {
                ans ^= c;
            }

            for (char c : t.toCharArray()) {
                ans ^= c;
            }

            return ans;
        }

    }




}

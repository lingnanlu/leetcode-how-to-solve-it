package String;

import run.elder.Category;
import run.elder.Leetcode;

@Leetcode(
        title = "Repeated Substring Pattern",
        link = "https://leetcode.com/problems/repeated-substring-pattern/",
        category = Category.STRING,
        how2SolveIt = """
                方法一: 直观做法
                
                该题要求判断一个s是否是子串的多个拼接, 如果是的话, 那么, 
                
                1. 开头的前几个字符也一定是
                2. 因为多个拼接的原因, 所以要求至少两个, 那么, 开头的第一个字串最多到s的中间
                
                以上两者就是隐含的条件.
                
                现在, 就可以用试验的方法, 先取前一个做为子串, 看是不是, 然后, 取前两个, 直到取到s的中间
                
                在做的过程中, 发现, 有些可以简化的地方
                
                1. i < s.length() /2
                2. 如果s.length() 不是子数组的倍数关系
                
                时间复杂度最差是一个O(n * n)
                因为i要遍历n, 而每一次, 都潜在可能遍历整个s, 注意最里面虽然有一个while, 但其实不能简单的直接看代码推断复杂度, 而是要明白
                算法的过程
                启示: 
                
                在做的过程中, 仔细观察一下, 虽然时间复杂度不变, 但可能会简化很多不必要的操作
                
                方法二:
                
                其它的比较trick, 像下面这种
                
                https://leetcode.com/problems/repeated-substring-pattern/discuss/94344/Simple-Java-solution-2-lines
                还有什么KMP之类的
                
                方法三:
                
                再仔细分析这个题, 题目中只是要求判断是不是某个子串的复制, 而不是让你找出来. 所以这是一个证明题, 而不是求解题, 所以一个思路就是
                
                如果是某个子串的复制, 一定会表现出某某现象
               
                即, 如果P, 肯定Q
                非Q, 而非P
             
                对于这个题来说, 如果S是某个子串的复制, 如XX, 那么, 把S本身复制一下, 则SS = XXXX
                那么, S肯定是SS的一个子串, 且该子串的开头不是0, 结尾不是的末尾.
                
         
                
                启示:
                
                这个题其实是一个证明题, 而不是一个求解题
                
                证明题的一般是要求证明P
                
                那么, 如果P不好证明的话, 则可以利用
                
                如果P, 肯定Q
                非Q, 则非P的形式.
                
                
                """,
        relatedQuestions = {}
)
public class Repeated_Substring_Pattern {

    static class First {
        public boolean repeatedSubstringPattern(String s) {

            // 判断[0, i]个字串是不是满足
            // 注意, s字符是奇偶时, i的位置
            for (int i = 0; i < s.length() / 2; i++) {
                // 现在子串是 s[0, i]
                // 子串长度
                int subLength = i + 1;
                if (s.length() % subLength != 0) {
                    // 如果不是倍数关系, 可直接跳过
                    continue;
                } else {
                    // 遍历s到最后
                    // 注意这里从i+1开始, 少比较一次
                    int j = i + 1;
                    while (j < s.length()) {
                        int k = 0; // k用来指向子串, 不用修改i, i表示子串结尾

                        // 匹配一个子串
                        while (k <= i) {
                            if (s.charAt(j) == s.charAt(k)) {
                                k++;
                                j++;
                            } else {
                                break;
                            }
                        }

                        // 没匹配完就中断了, 说明不匹配
                        if (k <= i) {
                            break;
                        }
                    }

                    // 某个子字符串能匹配完
                    if (j == s.length()) {
                        return true;
                    }

                }
            }

            return false;
        }

    }

    static class Third {
        public boolean repeatedSubstringPattern(String s) {
            String ss = s + s;
            ss = ss.substring(1, ss.length() - 1);
            // 只要s是ss的子串就可以了, 即转换成了strstr的问题
            return ss.contains(s);

        }
    }

}

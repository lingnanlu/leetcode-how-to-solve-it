package String;

import run.elder.Category;
import run.elder.Leetcode;

@Leetcode(
        title = "Implement strStr()",
        link = "https://leetcode.com/problems/implement-strstr/",
        category = Category.STRING,
        how2SolveIt = """
                方法一: 暴力
                
                最直观的方法, 遍历haystack, 以每个字符开始, 判断其是否和needle相等, 如果相等, 就返回
                
                方法二: 从后往前遍历, 避免出现很多浪费时间的比较
                
                发现这个问题anagrams类似, 而且更简单, 所以借鉴一下anagram的方法, 看看是否可以简化一下操作.
                这里最耗时的, 其实就是字符串对比的操作, 那么, 还是那个问题, 之前做的对比, 可不可以复用?
                
                那么, 我们就要看下, 一次对比能得到哪些信息.
                
                而利用这些信息, 我们可以节省多少个耗时操作.
                
                仔细观察, haystack, needle, 如果使用方法一, 理论上是 O(m * n)的操作, 但实际上, 如果两者包含的字符差距很大
                如 "abqweroasdfqe", "asw", 很可能, 每次判断一两次就可以结束了, 所以 O(m * n)并不一定是普遍的.
                
                但, 如果两者差距不大, 如
                
                "aaaaaaaaaaaaaab", "aaaab", 每次都要比较到最后一位才能知道, 那么, 我们怎么办呢?
                
                一种方法是从后往前对比, 因为差距主要在最后, 所以, 从前往后比会浪费很多
                
                但这种方法也有个坏处, 如果满足结果的在开头, 那么, 比正向找要慢
                
                方法三:
                
                方法二中满足结果的在开头, 那么, 正向找要慢, 可不可以正向和反向同时找呢?
                
                方法四:
                
                KMP: 这个就不是一般人靠直观想的出来的了.
                
                
                启示:
                
                不一定要用KMP, 也不一定非要迷信O(m*n)要分析具体实际的输入是什么样子的, 因为数据很可能并不是随机的.
                在非随机的数据下, 就可以设计专门的算法, 为之优化.
                
                数据是随机的, 那么, 暴力算法也不错.
                数据不是随机的, 那么, 从后往前匹配也可.
                
                如果是实际项目中, 如果不知道输入是什么样子的, 可以设计一种量化的方法来统计其是否随机的, 然后选择不同的方式.
                """,
        relatedQuestions = {}
)
public class Implement_StrStr {

    static class First {
        public int strStr(String haystack, String needle) {

            return 0;
        }
    }

    static class Second {
        /**
         * needle可能有前导空格或后导空格, 这种怎么办? 题目中没有说明白
         * 先不考虑这种情况吧
         */
        public int strStr(String haystack, String needle) {

            // todo 特殊情况出理

            int i = haystack.length() - 1;

            int result = -1;
            while (i >= (needle.length() - 1)) {
                int p = i;
                int q = needle.length() - 1;
                while (q >= 0 && haystack.charAt(p) == needle.charAt(q)) {
                    p--;
                    q--;
                }

                // 说明比较完了, 那么,
                if (q == -1) {
                    result = i - needle.length() + 1;
                }

                i--;
            }

            return result;
        }
    }

}

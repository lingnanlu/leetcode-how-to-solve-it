package tanxin;

import run.elder.Category;
import run.elder.Leetcode;

import java.util.Arrays;

@Leetcode(
        title = "Assign Cookies",
        link = "https://leetcode.com/problems/assign-cookies/",
        category = Category.ARRAY,
        how2SolveIt = """
               让尽可能多的孩子满意, 并且, 返回满意的孩子的数量.
               
               直观一点, 怎么让尽可能多的孩子满意呢? 
               
               就是优先满足那些需求少的, 比如极端一点, 一个要求1, 一个要求999, 那么, 就算有100个cookie, 可能也无法满足999的, 
               
               所以, 就优先满足要求小的. 而且是刚刚满足就好.
               
               先排一个序.
               
               有两种看法
               
               1. 遍历孩子: 以孩子为主, 以饼干为辅, 
               2. 遍历饼干: 以饼干为主, 以孩子为辅
             
               方法一: 
               
               根据孩子, 找饼干
               
               方法二:
               
               根据饼干, 找孩子
                
               启示:
               
               1. 依然是不变式
               2. 举例子时, 为了便于理解, 可使用极端的数据, 这样可以放大效果
                """,
        relatedQuestions = {}
)
public class Assign_Cookies {

    static class First {
        public int findContentChildren(int[] g, int[] s) {
            Arrays.sort(g);
            Arrays.sort(s);

            int contentChildrenCount = 0;
            // p指向下一个要满足的孩子, q指向还未分配的cookie
            // 每一次循环, 满足一个孩子
            int p = 0, q = 0;

            while (p != g.length && q != s.length) {
                while (q != s.length && s[q] < g[p]) {
                    q++;
                }

                // 找到满足要求的cookie
                if (q != s.length) {
                    contentChildrenCount++;
                    q++;
                    p++;
                } else {
                    // 没有能满足当前孩子要求的cookie, 不用再找了
                    break;
                }
            }

            return contentChildrenCount;
        }
    }

    static class Second {

        public int findContentChildren(int[] g, int[] s) {
            Arrays.sort(g);
            Arrays.sort(s);

            int contentChildrenCount = 0;
            // q指向下一个要分配的cookie, p指向还没满足的孩子,
            // 每一次循环, 分配一个饼干
            int p = 0, q = 0;

            while (p != g.length && q != s.length) {
               if (s[q] >= g[p]) {
                   contentChildrenCount++;
                   q++;
                   p++;
               } else {
                   q++;
               }
            }

            return contentChildrenCount;
        }

    }

}

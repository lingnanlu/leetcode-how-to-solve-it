package String;


import run.elder.Category;
import run.elder.Leetcode;

import java.util.ArrayList;
import java.util.List;

@Leetcode(
        title = "Find All Anagrams in a String",
        link = "https://leetcode.com/problems/find-all-anagrams-in-a-string/",
        category = Category.STRING,
        how2SolveIt = """
                方法一:
                
                最直观的, 依次遍历s, 看p长度的子字符串是否是一个anagrams
                
                方法二:
                
                仔细观察方法一中的方法, isAnagram是一个耗时操作, 尽量减少这个耗时操作的次数
                比较直观的想法就是, 让i不是每次一步的向前移动, 而可能是跳步.
                仔细观察, 当i每次往前移动时, 前后两次中有部分字符串是重叠的, 而前一次中已经利用它做某种计算了, 那么
                那么, 是否可以将前一次的计算结果缓存下来, 用于下一次的计算呢?
                
                前一次的计算结果有什么呢? 经过一次计算, 可以得到什么呢? 
                
                可以得到两个字符串比较时的字符差异. 
                
                注意, 这个差异中的正负, 一定要搞清楚, 是p相对于s, 还是s想对于p
                
                启示:
                这题看别人, 总是提到slide window, 而自己解题的时候并没有意识到slide window, 只是先从最直观的做起, 然后想办法减少
                消耗时间次数的调用, 仔细观察发现, 前一次的计算结果可以复用部分, 所以就得到了一种更好的解.
                """,
        relatedQuestions = {}
)
public class Find_All_Anagrams_In_A_String {

    static class First {

        public List<Integer> findAnagrams(String s, String p) {

            List<Integer> result = new ArrayList<>();

            int i = 0;
            // 尽量减少循环次数
            while (i <= (s.length() - p.length())) {

                String sub = s.substring(i, i + p.length());

                if (isAnagram(s, p)) {
                    result.add(i);
                }

                i++;
            }

            return result;
        }


        private boolean isAnagram(String s, String t) {


            if (s.length() != t.length()) {
                return false;
            }

            int[] charCounts = new int[26];

            // 统计s中的字符出现的次数
            for (int i = 0; i < s.length(); i++) {
                charCounts[s.charAt(i) - 'a']++;
                charCounts[t.charAt(i) - 'a']--;
            }

            for (int i = 0; i < 26; i++) {
                if (charCounts[i] != 0) {
                    return false;
                }
            }

            return true;

        }


    }

    static class Second {


        public List<Integer> findAnagrams(String s, String p) {

            List<Integer> result = new ArrayList<>();

            /**
             * i = 将要对比的子字符串的start
             * diff, 将要对比的子字符串与p中字符的diff
             *
             * 当diff[i]为正时, 说明p比s要多.
             * 当diff[i]为负时, 说明s比p要多.
             */
            int i = 0;
            int[] diff = new int[26];

            // 尽量减少循环次数
            while (i <= (s.length() - p.length())) {

                if (i == 0) {
                    // 创建diff
                    for (int j = 0; j < p.length(); j++) {
                        diff[p.charAt(j) - 'a']++;
                        diff[s.charAt(j) - 'a']--;
                    }
                } else {
                    // 修正diff
                    // !注意这里的++和--, 一定要搞清楚diff中, 正负的含义, 反了就错了!
                    diff[s.charAt(i - 1) - 'a']++;
                    diff[s.charAt(i + p.length() - 1) - 'a']--;
                }

                // 判读diff是否满足一定条件
                // 如果是, 则以i为开头的就是满足要求的
                int j = 0;
                while (j != 26) {
                    if (diff[j] != 0) {
                        break;
                    } else {
                        j++;
                    }
                }
                // 说明字符相同
                if (j == 26) {
                    result.add(i);
                }

                i++;
            }

            return result;
        }


    }

}
